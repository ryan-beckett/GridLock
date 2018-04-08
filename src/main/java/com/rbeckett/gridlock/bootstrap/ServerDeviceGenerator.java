package com.rbeckett.gridlock.bootstrap;

import com.rbeckett.gridlock.enums.AssetType;
import com.rbeckett.gridlock.enums.ServerDeviceType;
import com.rbeckett.gridlock.model.asset.ServerDevice;
import com.rbeckett.gridlock.services.asset.RackService;
import com.rbeckett.gridlock.services.asset.ServerDeviceService;
import lombok.extern.slf4j.Slf4j;
import org.fluttercode.datafactory.impl.DataFactory;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Component
public class ServerDeviceGenerator extends RackableDeviceGenerator implements Generator<ServerDevice> {

    private static final DataFactory dataFactory = new DataFactory();
    private static final String[] SERVER_DEVICES = {"SuperMicro SYS-5015A-EHF-D525",
            "Supermicro AS-2022G", "Asus RS100-E9-P12", "PowerEdge T330", "Dell NetShelter",
            "HP GridEdge 4550", "HP ComputerPower 3-Series"};
    private final List<ServerDevice> serverDevices = new ArrayList<>();
    private final ServerDeviceService serverDeviceService;
    private final RackService rackService;

    public ServerDeviceGenerator(final ServerDeviceService serverDeviceService,
                                 final RackService rackService) {
        this.serverDeviceService = serverDeviceService;
        this.rackService = rackService;
    }

    @Override
    public void generate(final int numResults, final Generator... generators) {
        for (int i = 0; i < numResults; i++) {
            final ServerDevice serverDevice = new ServerDevice();
            serverDevice.setType(AssetType.SERVER_DEVICE);
            serverDevice.setModel(SERVER_DEVICES[i % SERVER_DEVICES.length]);
            serverDevice.setSubType(dataFactory.getItem(ServerDeviceType.values()));
            generate(serverDevice, rackService, generators);
            serverDevices.add(serverDeviceService.save(serverDevice));
        }
        log.info("Generated data for ServerDevice entity");
    }

    @Override
    public List<ServerDevice> getResults() {
        return serverDevices;
    }
}
