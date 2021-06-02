package service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class HttpDataCollector {


    public static StringBuffer getDataFromApiEndPoint(String apiEndPoint) throws IOException {

        URL url = new URL(apiEndPoint);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");

        BufferedReader input = new BufferedReader(
                new InputStreamReader(connection.getInputStream()));

        String inputLine;
        StringBuffer content = new StringBuffer();

        while ((inputLine = input.readLine()) != null) {
            content.append(inputLine );
        }

        input.close();
        connection.disconnect();

        return content;
    }
}
