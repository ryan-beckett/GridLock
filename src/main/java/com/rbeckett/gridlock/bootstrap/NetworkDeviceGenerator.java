package com.rbeckett.gridlock.bootstrap;

import com.rbeckett.gridlock.enums.AssetType;
import com.rbeckett.gridlock.enums.NetworkDeviceType;
import com.rbeckett.gridlock.model.asset.NetworkDevice;
import com.rbeckett.gridlock.services.asset.NetworkDeviceService;
import com.rbeckett.gridlock.services.asset.RackService;
import org.fluttercode.datafactory.impl.DataFactory;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class NetworkDeviceGenerator extends RackableDeviceGenerator implements Generator<NetworkDevice> {

    private static final DataFactory dataFactory = new DataFactory();
    private static final String[] NETWORK_DEVICES = {"Catalyst 2590", "Nexus 7K", "Nexus 9K", "OfficeConnect 1420",
            "ProSafe 8-Port", "TrendNet TEG-S50G", "SAN 48-TP", "Fibre-Rtr 24G", "D-Link TL56E"};
    private final List<NetworkDevice> networkDevices = new ArrayList<>();
    private final NetworkDeviceService networkDeviceService;
    private final RackService rackService;

    public NetworkDeviceGenerator(final NetworkDeviceService networkDeviceService,
                                  final RackService rackService) {
        this.networkDeviceService = networkDeviceService;
        this.rackService = rackService;
    }

    @Override
    public void generate(final int numResults, final Generator... generators) {
        for (int i = 0; i < numResults; i++) {
            final NetworkDevice networkDevice = new NetworkDevice();
            networkDevice.setType(AssetType.NETWORK_DEVICE);
            generate(networkDevice, rackService, generators);
            networkDevice.setModel(dataFactory.getItem(NETWORK_DEVICES));
            networkDevice.setSubType(dataFactory.getItem(NetworkDeviceType.values()));
            networkDevices.add(networkDeviceService.save(networkDevice));
        }
    }

    @Override
    public List<NetworkDevice> getResults() {
        return networkDevices;
    }
}
