package com.rbeckett.gridlock.bootstrap;

import com.rbeckett.gridlock.enums.AssetType;
import com.rbeckett.gridlock.model.asset.Rack;
import com.rbeckett.gridlock.services.asset.RackService;
import lombok.extern.slf4j.Slf4j;
import org.fluttercode.datafactory.impl.DataFactory;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Component
public class RackGenerator extends AssetGenerator implements Generator<Rack> {

    private static final DataFactory dataFactory = new DataFactory();
    private static final String[] RACK_NAMES = {"iStarUSA WN228", "Tripp Lite 42U", "APC AR23", "Rack Solutions 42UKit",
            "Rosewill RSWM-12U"};
    private static final String[] RACK_IMG_URLS = {"https://s15.postimg.cc/cnrysddlz/151-3969-base-202-front.jpg",
            "https://s15.postimg.cc/gwwoulebb/Z0000027246.jpg",
            "https://s15.postimg.cc/lvk793fjb/NPNB-3108-3-024-22_01.jpg",
            "https://s15.postimg.cc/5kk3csxwn/XR-_NRE2-25_U-_US-2.jpg"};
    private final List<Rack> racks = new ArrayList<>();
    private final RackService rackService;
    private final int RACK_HEIGHT = 48;

    public RackGenerator(final RackService rackService) {
        this.rackService = rackService;
    }

    @Override
    public void generate(final int numResults, final Generator... generators) {
        for (int i = 0; i < numResults; i++) {
            final Rack rack = new Rack();
            rack.setType(AssetType.RACK);
            rack.setModel(RACK_NAMES[i % RACK_NAMES.length]);
            rack.setImageURL(RACK_IMG_URLS[i % RACK_IMG_URLS.length]);
            rack.setUHeight(RACK_HEIGHT);
            generate(rack, generators);
            AppDataGenerator.RoomGridLocationPair pr = AppDataGenerator.getNextRandomRoomAndGridLocation();
            rack.setRoom(pr.room);
            rack.setGridLocation(pr.gridLocation);
            racks.add(rackService.save(rack));

        }
        log.info("Generated data for Rack entity");
    }

    @Override
    public List<Rack> getResults() {
        return racks;
    }
}
