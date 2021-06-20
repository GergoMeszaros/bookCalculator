package service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import model.Listing;
import model.ListingStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import service.validator.ApiDataValidator;

import java.io.IOException;
import java.sql.SQLException;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

class GsonCreatorTest {



    @BeforeEach
    void setup() {
    }


    @Test
    void checkIfCreatorCreatesGsonBuilder(){
        GsonBuilder gsonBuilder = new GsonBuilder();
        Gson gson = gsonBuilder.create();

        assertNotNull(gson);
    }
}