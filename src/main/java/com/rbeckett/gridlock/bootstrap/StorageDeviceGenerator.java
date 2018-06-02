package com.rbeckett.gridlock.bootstrap;

import com.rbeckett.gridlock.enums.AssetType;
import com.rbeckett.gridlock.enums.StorageDeviceType;
import com.rbeckett.gridlock.model.asset.StorageDevice;
import com.rbeckett.gridlock.services.asset.RackService;
import com.rbeckett.gridlock.services.asset.StorageDeviceService;
import lombok.extern.slf4j.Slf4j;
import org.fluttercode.datafactory.impl.DataFactory;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Component
public class StorageDeviceGenerator extends RackableDeviceGenerator implements Generator<StorageDevice> {

    private static final DataFactory dataFactory = new DataFactory();
    private static final String[] STORAGE_DEVICES = {"Pegasus 2 R6", "CineRAID CR-H458",
            "Mediasonic HF7-SU3S3", "SANS DIGITAL TR4UTBPN 4-Bay", "Dell PowerVault MD1000",
            "Hitachi HDS 2100", "HPE K2R80A"};
    private static final String[] STORAGE_DEVICES_IMG_URLS = {
            "https://s15.postimg.cc/66i90bffb/16-322-019-07.jpg",
            "https://s15.postimg.cc/9q46q42pj/41_FK0_Rb2h_XL._SX300_QL70.jpg",
            "https://s15.postimg.cc/wrkrvuunb/h458_front.jpg",
            "https://s15.postimg.cc/8nu07krlz/item2.jpg",
            "https://s15.postimg.cc/xtuyefnrb/md1000-bezel_59987.1490112544.png"
    };
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
            storageDevice.setModel(STORAGE_DEVICES[i % STORAGE_DEVICES.length]);
            storageDevice.setImageURL(STORAGE_DEVICES_IMG_URLS[i % STORAGE_DEVICES_IMG_URLS.length]);
            storageDevice.setSubType(dataFactory.getItem(StorageDeviceType.values()));
            generate(storageDevice, rackService, generators);
            storageDevices.add(storageDeviceService.findByName(storageDevice.getName()));
        }
        log.info("Generated data for StorageDevice entity");
    }

    @Override
    public List<StorageDevice> getResults() {
        return storageDevices;
    }
}
