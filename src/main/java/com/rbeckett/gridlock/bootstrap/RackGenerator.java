package com.rbeckett.gridlock.bootstrap;

import com.rbeckett.gridlock.enums.AssetType;
import com.rbeckett.gridlock.model.asset.Rack;
import com.rbeckett.gridlock.model.network.GridLocation;
import com.rbeckett.gridlock.services.asset.RackService;
import org.fluttercode.datafactory.impl.DataFactory;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class RackGenerator extends AssetGenerator implements Generator<Rack> {

    private static final DataFactory dataFactory = new DataFactory();
    private static final String[] RACK_NAMES = {"iStarUSA WN228", "Tripp Lite 42U", "APC AR23", "Rack Solutions 42UKit",
            "Rosewill RSWM-12U"};
    private final List<Rack> racks = new ArrayList<>();
    private final RackService rackService;
    private final Integer[] RACK_HEIGHTS = {12, 24, 42};

    public RackGenerator(final RackService rackService) {
        this.rackService = rackService;
    }

    @Override
    public void generate(final int numResults, final Generator... generators) {
        for (int i = 0; i < numResults; i++) {
            final Rack rack = new Rack();
            rack.setType(AssetType.RACK);
            generate(rack, generators);
            rack.setModel(dataFactory.getItem(RACK_NAMES));
            rack.setUHeight(dataFactory.getItem(RACK_HEIGHTS));
            rack.setGridLocation((GridLocation) dataFactory.getItem(generators[5].getResults()));
            racks.add(rackService.save(rack));
        }
    }

    @Override
    public List<Rack> getResults() {
        return racks;
    }
}
