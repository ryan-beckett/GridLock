package com.rbeckett.gridlock.bootstrap;

import com.rbeckett.gridlock.enums.AssetType;
import com.rbeckett.gridlock.enums.NetworkDeviceType;
import com.rbeckett.gridlock.model.asset.NetworkDevice;
import com.rbeckett.gridlock.services.asset.NetworkDeviceService;
import com.rbeckett.gridlock.services.asset.RackService;
import lombok.extern.slf4j.Slf4j;
import org.fluttercode.datafactory.impl.DataFactory;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Component
public class NetworkDeviceGenerator extends RackableDeviceGenerator implements Generator<NetworkDevice> {

    private static final DataFactory dataFactory = new DataFactory();
    private static final String[] NETWORK_DEVICES = {"Catalyst 2590", "Nexus 7K", "Nexus 9K", "OfficeConnect 1420",
            "ProSafe 8-Port", "TrendNet TEG-S50G", "SAN 48-TP", "Fibre-Rtr 24G", "D-Link TL56E"};
    private static final String[] NETWORK_DEVICES_IMG_URL = {
            "https://s15.postimg.cc/62ofk64tz/download.jpg",
            "https://s15.postimg.cc/n37bsupl3/1478509011390.jpg",
            "https://s15.postimg.cc/5d5n7swkn/mx240-frontwtop.jpg",
            "https://s15.postimg.cc/uvxzku39z/Screen-_Shot-2015-05-19-at-08.08.39.png"
    };
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
            networkDevice.setModel(NETWORK_DEVICES[i % NETWORK_DEVICES.length]);
            networkDevice.setImageURL(NETWORK_DEVICES_IMG_URL[i % NETWORK_DEVICES_IMG_URL.length]);
            networkDevice.setSubType(dataFactory.getItem(NetworkDeviceType.values()));
            generate(networkDevice, rackService, generators);
            networkDevices.add(networkDeviceService.findByName(networkDevice.getName()));
        }
        log.info("Generated data for NetworkDevice entity");
    }

    @Override
    public List<NetworkDevice> getResults() {
        return networkDevices;
    }
}
