package service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class HttpDataCollector {

    private static HttpDataCollector httpDataCollector;

    private HttpDataCollector(){

    }

    public static HttpDataCollector getHttpDataCollectorInstance(){
        if(httpDataCollector == null){
            httpDataCollector = new HttpDataCollector();
        }
        return httpDataCollector;
    }


    public String getDataFromApiEndPoint(String apiEndPoint) throws IOException {

        URL url = new URL(apiEndPoint);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");

        BufferedReader input = new BufferedReader(
                new InputStreamReader(connection.getInputStream()));

        String inputLine;
        StringBuilder content = new StringBuilder();

        while ((inputLine = input.readLine()) != null) {
            content.append(inputLine );
        }

        input.close();
        connection.disconnect();

        return String.valueOf(content);
    }
}
