package com.rbeckett.gridlock.bootstrap;

import com.rbeckett.gridlock.model.network.GridLocation;
import com.rbeckett.gridlock.services.network.GridLocationService;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Component
public class GridLocationGenerator implements Generator<GridLocation> {

    private final List<GridLocation> gridLocations = new ArrayList<>();
    private final GridLocationService gridLocationService;
    private final int MAX_GRID_X = 676, MAX_GRID_Y = 676;

    public GridLocationGenerator(final GridLocationService gridLocationService) {
        this.gridLocationService = gridLocationService;
    }

    @Override
    public void generate(final int numResults, final Generator... generators) {
        final Random random = new Random();
        for (int i = 0, j = 0; i < numResults; i++) {
            final GridLocation gridLocation = new GridLocation();
            gridLocation.setX(random.nextInt(MAX_GRID_X));
            gridLocation.setY(random.nextInt(MAX_GRID_Y));
            if (gridLocations.contains(gridLocation)) {
                i--;
                continue;
            }
            gridLocations.add(gridLocationService.save(gridLocation));
        }
    }

    @Override
    public List<GridLocation> getResults() {
        return gridLocations;
    }
}
