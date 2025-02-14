package com.hoenscanner;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hoenscanner.model.SearchResult;
import io.dropwizard.Application;
import io.dropwizard.setup.Environment;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class HoenScannerApplication extends Application<HoenScannerConfiguration> {
    private static final List<SearchResult> searchResults = new ArrayList<>();

    public static void main(String[] args) throws Exception {
        new HoenScannerApplication().run(args);
    }

    @Override
    public void run(HoenScannerConfiguration configuration, Environment environment) throws IOException {
        ObjectMapper mapper = new ObjectMapper();

        List<SearchResult> rentalCars = mapper.readValue(Paths.get("resources/rental_cars.json").toFile(),
                mapper.getTypeFactory().constructCollectionType(List.class, SearchResult.class));

        List<SearchResult> hotels = mapper.readValue(Paths.get("resources/hotels.json").toFile(),
                mapper.getTypeFactory().constructCollectionType(List.class, SearchResult.class));

        searchResults.addAll(rentalCars);
        searchResults.addAll(hotels);

        environment.jersey().register(new SearchResource());
    }

    public static List<SearchResult> getSearchResults() {
        return searchResults;
    }
}
