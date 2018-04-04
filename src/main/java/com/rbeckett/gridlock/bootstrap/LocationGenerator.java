package com.rbeckett.gridlock.bootstrap;

import com.rbeckett.gridlock.model.business.Location;
import com.rbeckett.gridlock.services.business.LocationService;
import org.fluttercode.datafactory.impl.DataFactory;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

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
        final Random random = new Random();
        for (int i = 0, j = 0; i < numResults; i++) {
            Location location = new Location();
            location.setAddress(dataFactory.getAddress());
            location.setCity(dataFactory.getCity());
            location.setCountry(COUNTRIES[i % numResults]);
            location.setName("Site " + (random.nextInt(numResults) + 1));
            if (location.getCountry().equals("US")) {
                location.setZip(dataFactory.getNumberText(5));
                location.setState(STATES[(j++) % numResults]);
            }
            locations.add(locationService.save(location));
        }
    }

    @Override
    public List<Location> getResults() {
        return locations;
    }
}
