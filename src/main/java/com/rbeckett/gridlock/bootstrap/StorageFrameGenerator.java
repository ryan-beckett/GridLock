package com.rbeckett.gridlock.bootstrap;

import com.rbeckett.gridlock.enums.AssetType;
import com.rbeckett.gridlock.enums.StorageDeviceType;
import com.rbeckett.gridlock.model.asset.StorageFrame;
import com.rbeckett.gridlock.model.network.GridLocation;
import com.rbeckett.gridlock.services.asset.StorageFrameService;
import org.fluttercode.datafactory.impl.DataFactory;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class StorageFrameGenerator extends AssetGenerator implements Generator<StorageFrame> {

    private static final DataFactory dataFactory = new DataFactory();
    private static final String[] STORAGE_FRAMES = {"EMC CloudStore V2", "IBM SAN Cluster K9",
            "Cisco ECS SAN 1000TB", "HP MSA 1050", "Hitachi Enterprise SAN 6700"};
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
            generate(storageFrame, generators);
            storageFrame.setModel(dataFactory.getItem(STORAGE_FRAMES));
            storageFrame.setSubType(dataFactory.getItem(StorageDeviceType.values()));
            storageFrame.setGridLocation((GridLocation) dataFactory.getItem(generators[5].getResults()));
            storageFrames.add(storageFrameService.save(storageFrame));
        }
    }

    @Override
    public List<StorageFrame> getResults() {
        return storageFrames;
    }
}
