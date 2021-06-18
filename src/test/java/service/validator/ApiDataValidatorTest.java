package service.validator;

import model.Listing;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.FileNotFoundException;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class ApiDataValidatorTest {


    ApiDataValidator apiDataValidator;

    Listing listing1 = new Listing(UUID.randomUUID(), " ", " ", " ", 3,
            1, UUID.randomUUID(), 2F, 4, " ",
            " ");

    Listing listing2 = new Listing(UUID.randomUUID(), "", " ", "",
            4, 2, UUID.randomUUID(), 3F, 1, "",
            "");

    Listing[] listings = new Listing[]{listing1, listing2};

    @BeforeEach
    void setup() {
        apiDataValidator = Mockito.mock(ApiDataValidator.class);

    }

    @Test
    void validateApiDataReturnsCorrectElements() throws FileNotFoundException {
        when(apiDataValidator.validateApiData(listings)).thenReturn(new Listing[]{listing2});
    }

}