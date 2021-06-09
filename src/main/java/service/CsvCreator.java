package service;

import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CsvCreator {


    public String convertToCsv(String [] data){
        return String.join(",", data);
    }
}
