package com.rbeckett.gridlock.bootstrap;

import com.rbeckett.gridlock.model.business.Location;
import com.rbeckett.gridlock.services.business.LocationService;
import lombok.extern.slf4j.Slf4j;
import org.fluttercode.datafactory.impl.DataFactory;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Component
public class LocationGenerator implements Generator<Location> {

    private static final DataFactory dataFactory = new DataFactory();
    private static final String[] COUNTRIES = {"US", "Russia", "Canada", "Japan", "China", "England", "India",
            "Spain", "Mexico", "Egypt"};
    private static final String[] STATES = {"PA", "FL", "WS", "CA", "NY", "NJ", "NV", "AK", "MI", "DE"};
    private final List<Location> locations = new ArrayList<>();
    private final LocationService locationService;

    public LocationGenerator(final LocationService locationService) {
        this.locationService = locationService;
    }

    @Override
    public void generate(final int numResults, final Generator... generators) {
        for (int i = 0, j = 0; i < numResults; i++) {
            Location location = new Location();
            location.setAddress(dataFactory.getAddress());
            location.setCity(dataFactory.getCity());
            location.setCountry(COUNTRIES[i % COUNTRIES.length]);
            location.setName("Location " + (i + 1));
            if (location.getCountry().equals("US")) {
                location.setZip(dataFactory.getNumberText(5));
                location.setState(STATES[(j++) % STATES.length]);
            } else {
                location.setZip(dataFactory.getRandomChars(5, 8));
                location.setState(dataFactory.getStreetName());
            }
            locations.add(locationService.save(location));
        }
        log.info("Generated data for Location entity");
    }

    @Override
    public List<Location> getResults() {
        return locations;
    }
}
