package com.rbeckett.gridlock.bootstrap;

import com.rbeckett.gridlock.enums.AssetType;
import com.rbeckett.gridlock.enums.StorageDeviceType;
import com.rbeckett.gridlock.model.asset.StorageDevice;
import com.rbeckett.gridlock.model.network.GridLocation;
import com.rbeckett.gridlock.services.asset.RackService;
import com.rbeckett.gridlock.services.asset.StorageDeviceService;
import org.fluttercode.datafactory.impl.DataFactory;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class StorageDeviceGenerator extends RackableDeviceGenerator implements Generator<StorageDevice> {

    private static final DataFactory dataFactory = new DataFactory();
    private static final String[] STORAGE_DEVICES = {"Pegasus 2 R6", "CineRAID CR-H458",
            "Mediasonic HF7-SU3S3", "SANS DIGITAL TR4UTBPN 4-Bay", "Dell PowerVault MD1000",
            "Hitachi HDS 2100", "HPE K2R80A"};
    private final List<StorageDevice> storageDevices = new ArrayList<>();
    private final StorageDeviceService storageDeviceService;
    private final RackService rackService;

    public StorageDeviceGenerator(final StorageDeviceService storageDeviceService,
                                  final RackService rackService) {
        this.storageDeviceService = storageDeviceService;
        this.rackService = rackService;
    }

    @Override
    public void generate(final int numResults, final Generator... generators) {
        for (int i = 0; i < numResults; i++) {
            final StorageDevice storageDevice = new StorageDevice();
            storageDevice.setType(AssetType.STORAGE_DEVICE);
            generate(storageDevice, rackService, generators);
            storageDevice.setModel(dataFactory.getItem(STORAGE_DEVICES));
            storageDevice.setSubType(dataFactory.getItem(StorageDeviceType.values()));
            storageDevice.setGridLocation((GridLocation) dataFactory.getItem(generators[5].getResults()));
            storageDevices.add(storageDeviceService.save(storageDevice));
        }
    }

    @Override
    public List<StorageDevice> getResults() {
        return storageDevices;
    }
}
