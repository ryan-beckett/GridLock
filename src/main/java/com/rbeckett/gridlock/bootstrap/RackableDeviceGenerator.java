package com.rbeckett.gridlock.bootstrap;

import com.rbeckett.gridlock.model.asset.Rack;
import com.rbeckett.gridlock.model.asset.RackableDevice;
import com.rbeckett.gridlock.services.asset.RackService;
import lombok.extern.slf4j.Slf4j;
import org.fluttercode.datafactory.impl.DataFactory;

import java.util.ArrayList;
import java.util.List;

@Slf4j
public class RackableDeviceGenerator extends AssetGenerator {

    private static final DataFactory dataFactory = new DataFactory();
    private static final Integer[] U_HEIGHTS = {1, 2, 4};

    protected void generate(RackableDevice rackableDevice, RackService rackService, Generator... generators) {
        super.generate(rackableDevice, generators);
        rackableDevice.setUHeight(dataFactory.getItem(U_HEIGHTS));
        List<Rack> racks = new ArrayList<>((List<Rack>) generators[5].getResults());
        boolean placed = false;
        while (!placed && !racks.isEmpty()) {
            Rack rack = dataFactory.getItem(racks);
            racks.remove(rack);
            if (rack.totalUSpaceLeft() >= rackableDevice.getUHeight()) {
                int uLocation = 1, openSpace;
                while (uLocation <= rack.getUHeight()) {
                    uLocation = rack.nextOpenULocation(uLocation);
                    if (uLocation < 0)
                        break;
                    openSpace = rack.openSpaceAtULocation(uLocation);
                    if (openSpace >= rackableDevice.getUHeight()) {
                        rackableDevice.setRack(rack);
                        rackableDevice.setULocation(uLocation);
                        rackableDevice.setGridLocation(rack.getGridLocation());
                        rackableDevice.setRoom(rack.getRoom());
                        rack = rackService.save(rack);
                        updateGeneratedRackList(generators[5].getResults(), rack);
                        placed = true;
                        break;
                    }
                    uLocation += openSpace;
                }
            }
        }
        if (!placed)
            log.info("Couldn't place rackable device in any rack: {" + rackableDevice.toString() + "}");
    }

    private void updateGeneratedRackList(List<Rack> racks, Rack updatedRack) {
        int index = -1;
        for (int i = 0; i < racks.size(); i++) {
            if (racks.get(i).getName().equals(updatedRack.getName())) {
                index = i;
                break;
            }
        }
        if (index < 0)
            log.info("Couldn't find rack in generated rack list");
        else {
            racks.remove(index);
            racks.add(index, updatedRack);
        }
    }
}
