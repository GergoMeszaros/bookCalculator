package service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import service.validator.ApiDataValidator;

import static org.junit.jupiter.api.Assertions.assertNotNull;

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