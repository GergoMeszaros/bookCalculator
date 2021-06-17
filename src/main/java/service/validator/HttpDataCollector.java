package service.validator;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

public class HttpDataCollector {

    public HttpDataCollector() {

    }

    public String getDataFromApiEndPoint(String apiEndPoint) {

        try {

            URL url = new URL(apiEndPoint);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            BufferedReader input = new BufferedReader(
                    new InputStreamReader(connection.getInputStream()));

            String inputLine;
            StringBuilder content = new StringBuilder();

            while ((inputLine = input.readLine()) != null) {
                content.append(inputLine);
            }

            input.close();
            connection.disconnect();

            return String.valueOf(content);

        } catch (IOException exception) {
            System.out.println("Error while gathering data from endpoints " + exception);
            exception.printStackTrace();
        }
        return null;
    }
}
