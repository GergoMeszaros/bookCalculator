package service.report;

import model.MarketPlaceType;
import model.Report;
import service.database.DatabaseConnector;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ReportCreator {

    private final DatabaseConnector databaseConnector;
    private final ReportToJsonWriter reportToJsonWriter;
    private Connection connection;

    public ReportCreator(DatabaseConnector databaseConnector, ReportToJsonWriter reportToJsonWriter) {
        this.databaseConnector = databaseConnector;
        this.reportToJsonWriter = reportToJsonWriter;
        createConnection();
    }

    public void startReporting() throws SQLException {

        MarketPlaceType[] marketPlaceTypes = MarketPlaceType.values();

        for (MarketPlaceType element : marketPlaceTypes) {
            readDatabase(element.getId());
        }

        System.out.println("Creating Json report file");
    }

    private void readDatabase(int marketplace) throws SQLException {

        createConnection();
        String monthlyQuery =
                """
                        SELECT YEAR(upload_time)       AS year,
                               MONTH(upload_time)      AS month,
                               COUNT(id)               AS total_listing,
                               SUM(listing_price)      AS total_listing_price,
                               AVG(listing_price)      AS average_listing_price,

                               (SELECT best_lister.owner_email_address
                                FROM (SELECT owner_email_address, count(owner_email_address) AS count
                                      FROM listing
                                      WHERE MONTH(upload_time) = month
                                        AND YEAR(upload_time) = year
                                        AND marketplace = ?
                                      GROUP BY owner_email_address
                                      ORDER BY count DESC
                                      LIMIT 1
                                     ) AS best_lister) AS best_listers_email, marketplace

                        FROM listing
                        WHERE year(upload_time) IS NOT NULL AND marketplace = ?
                        GROUP BY year, month, marketplace
                        ORDER BY year""";

        PreparedStatement monthlyPreparedStatement = connection.prepareStatement(monthlyQuery);
        monthlyPreparedStatement.setInt(1, marketplace);
        monthlyPreparedStatement.setInt(2, marketplace);

        ResultSet monthlyResultSet = monthlyPreparedStatement.executeQuery();

        turnResultSetIntoMonthlyObjects(monthlyResultSet);
        monthlyResultSet.close();
        connection.close();


        createConnection();
        String annualQuery = """
                SELECT YEAR(upload_time)       AS year,
                       COUNT(id)               AS total_listing,
                       SUM(listing_price)      AS total_listing_price,
                       AVG(listing_price)      AS average_listing_price,

                       (SELECT best_lister.owner_email_address
                        FROM (SELECT owner_email_address, count(owner_email_address) AS count
                              FROM listing
                                WHERE YEAR(upload_time) = year
                                AND marketplace = ?
                              GROUP BY owner_email_address
                              ORDER BY count DESC
                              LIMIT 1
                             ) AS best_lister) AS best_listers_email, marketplace

                FROM listing
                WHERE year(upload_time) IS NOT NULL AND marketplace = ?
                GROUP BY year, marketplace
                ORDER BY year""";

        PreparedStatement annualPreparedStatement = connection.prepareStatement(annualQuery);

        annualPreparedStatement.setInt(1, marketplace);
        annualPreparedStatement.setInt(2, marketplace);

        ResultSet annualResultSet = annualPreparedStatement.executeQuery();
        turnResultSetIntoAnnualObjects(annualResultSet);

        annualPreparedStatement.close();
        connection.close();
    }

    private void createConnection() {
        try {
            connection = databaseConnector.createConnection();
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
    }

    private void turnResultSetIntoMonthlyObjects(ResultSet resultSet) throws SQLException {
        List<Report> reports = new ArrayList<>();

        while (resultSet.next()) {
            reports.add(new Report(
                    resultSet.getInt("year"),
                    resultSet.getInt("month"),
                    resultSet.getInt("marketplace"),
                    resultSet.getString("best_listers_email"),
                    resultSet.getFloat("total_listing_price"),
                    resultSet.getLong("total_listing"),
                    resultSet.getFloat("average_listing_price")));
        }
        reportToJsonWriter.createJsonFromArrayList(reports);
    }

    private void turnResultSetIntoAnnualObjects(ResultSet resultSet) throws SQLException {
        List<Report> annualReports = new ArrayList<>();

        while (resultSet.next()) {
            annualReports.add(new Report(
                    resultSet.getInt("year"),
                    resultSet.getInt("marketplace"),
                    resultSet.getString("best_listers_email"),
                    resultSet.getFloat("total_listing_price"),
                    resultSet.getLong("total_listing"),
                    resultSet.getFloat("average_listing_price")
            ));
        }
        reportToJsonWriter.createJsonFromArrayList(annualReports);
    }
}


