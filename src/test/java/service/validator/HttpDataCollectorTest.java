package service.validator;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import service.config.Endpoint;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

import static org.junit.jupiter.api.Assertions.assertTrue;

class HttpDataCollectorTest {

    private Endpoint endpoint;


    @BeforeEach
    void setUp(){
        endpoint = Mockito.mock(Endpoint.class);
    }


    @Test
    public void tryConnection() throws IOException {


        for (String elem : endpoint.getEndpoints().keySet()) {

            URL url = new URL(elem);

            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            assertTrue(connection.getResponseCode() != 404);

        }




    }

}