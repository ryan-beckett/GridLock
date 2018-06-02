package com.rbeckett.gridlock.bootstrap;

import com.rbeckett.gridlock.enums.AssetType;
import com.rbeckett.gridlock.enums.StorageDeviceType;
import com.rbeckett.gridlock.model.asset.StorageFrame;
import com.rbeckett.gridlock.services.asset.StorageFrameService;
import lombok.extern.slf4j.Slf4j;
import org.fluttercode.datafactory.impl.DataFactory;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Component
public class StorageFrameGenerator extends AssetGenerator implements Generator<StorageFrame> {

    private static final DataFactory dataFactory = new DataFactory();
    private static final String[] STORAGE_FRAMES = {"EMC CloudStore V2", "IBM SAN Cluster K9",
            "Cisco ECS SAN 1000TB", "HP MSA 1050", "Hitachi Enterprise SAN 6700"};
    private static final String[] STORAGE_FRAMES_IMG_URL = {
            "https://s15.postimg.cc/nz3t8eu5j/2980222.jpg",
            "https://s15.postimg.cc/whd9cr8dz/339_33_fk_e.jpg",
            "https://s15.postimg.cc/6ykwzrjp3/b8891462b44b1e852dafcec277b5a54d.png",
            "https://s15.postimg.cc/56ry4ukwn/EMC-_Unity-350_F-_All-_Flash-_Storage-1-_IMG-_XL.jpg",
            "https://s15.postimg.cc/inownpnif/HPP9500.jpg",
            "https://s15.postimg.cc/hleq55hjr/vmax_10k.png"
    };
    private final List<StorageFrame> storageFrames = new ArrayList<>();
    private final StorageFrameService storageFrameService;

    public StorageFrameGenerator(final StorageFrameService storageFrameService) {
        this.storageFrameService = storageFrameService;
    }

    @Override
    public void generate(final int numResults, final Generator... generators) {
        for (int i = 0; i < numResults; i++) {
            final StorageFrame storageFrame = new StorageFrame();
            storageFrame.setType(AssetType.STORAGE_DEVICE);
            storageFrame.setModel(STORAGE_FRAMES[i % STORAGE_FRAMES.length]);
            storageFrame.setImageURL(STORAGE_FRAMES_IMG_URL[i % STORAGE_FRAMES_IMG_URL.length]);
            storageFrame.setSubType(dataFactory.getItem(StorageDeviceType.values()));
            generate(storageFrame, generators);
            AppDataGenerator.RoomGridLocationPair pr = AppDataGenerator.getNextRandomRoomAndGridLocation();
            storageFrame.setRoom(pr.room);
            storageFrame.setGridLocation(pr.gridLocation);
            storageFrames.add(storageFrameService.save(storageFrame));
        }
        log.info("Generated data for StorageFrame entity");
    }

    @Override
    public List<StorageFrame> getResults() {
        return storageFrames;
    }
}
