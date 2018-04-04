package com.rbeckett.gridlock.bootstrap;

import com.rbeckett.gridlock.model.asset.Rack;
import com.rbeckett.gridlock.model.asset.RackableDevice;
import com.rbeckett.gridlock.services.asset.RackService;
import org.fluttercode.datafactory.impl.DataFactory;

import java.util.List;

public class RackableDeviceGenerator extends AssetGenerator {

    private static final DataFactory dataFactory = new DataFactory();
    private static final Integer[] U_HEIGHTS = {1, 2, 4, 8, 16};

    protected void generate(RackableDevice rackableDevice, RackService rackService, Generator... generators) {
        super.generate(rackableDevice, generators);
        rackableDevice.setUHeight(dataFactory.getItem(U_HEIGHTS));
        List<Rack> racks = (List<Rack>) generators[5].getResults();
        for (int i = 0; i < racks.size(); i++) {
            Rack rack = racks.get(i);
            if (!rack.getDevices().contains(rackableDevice) && rack.totalUSpaceLeft() >= rackableDevice.getUHeight()) {
                int uLocation = 1, openSpace;
                while (uLocation <= rack.getUHeight()) {
                    uLocation = rack.nextOpenULocation(uLocation);
                    openSpace = rack.openSpaceAtULocation(uLocation);
                    if (openSpace >= rackableDevice.getUHeight()) {
                        rackableDevice.setULocation(uLocation);
                        rack.getDevices().add(rackableDevice);
                        rackService.save(rack);
                        rackableDevice.setRack(rack);
                        rackableDevice.setGridLocation(rack.getGridLocation());
                        break;
                    }
                    uLocation += openSpace;
                }
                break;
            }
        }
    }
}
