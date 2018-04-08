package com.rbeckett.gridlock.bootstrap;

import com.rbeckett.gridlock.model.network.GridLocation;
import com.rbeckett.gridlock.services.network.GridLocationService;
import com.rbeckett.gridlock.utils.GridUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Slf4j
@Component
public class GridLocationGenerator implements Generator<GridLocation> {

    private final List<GridLocation> gridLocations = new ArrayList<>();
    private final GridLocationService gridLocationService;

    public GridLocationGenerator(final GridLocationService gridLocationService) {
        this.gridLocationService = gridLocationService;
    }

    @Override
    public void generate(final int numResults, final Generator... generators) {
        final Random random = new Random();
        for (int i = 0, j = 0; i < numResults; i++) {
            final GridLocation gridLocation = new GridLocation();
            gridLocation.setX(random.nextInt(GridUtils.MAX_GRID_INDEX));
            gridLocation.setY(random.nextInt(GridUtils.MAX_GRID_INDEX));
            if (gridLocations.contains(gridLocation)) {
                i--;
                continue;
            }
            gridLocations.add(gridLocationService.save(gridLocation));
        }
        log.info("Generated data for GridLocation entity");
    }

    @Override
    public List<GridLocation> getResults() {
        return gridLocations;
    }
}
