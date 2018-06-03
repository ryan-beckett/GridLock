package com.rbeckett.gridlock.bootstrap;

import com.rbeckett.gridlock.enums.AssetStatus;
import com.rbeckett.gridlock.model.asset.Asset;
import com.rbeckett.gridlock.model.business.BusinessUnit;
import com.rbeckett.gridlock.model.business.Manufacturer;
import com.rbeckett.gridlock.model.business.Room;
import com.rbeckett.gridlock.model.business.ServiceContract;
import org.fluttercode.datafactory.impl.DataFactory;

import java.util.Random;

public class AssetGenerator {

    private static final DataFactory dataFactory = new DataFactory();
    private static final float MIN_COST = 1000f, MAX_COST = 100000f;
    private static final int MIN_DESC_LEN = 20, MAX_DESC_LEN = 100;
    private static final int PO_LEN = 10;
    private static final int SERIAL_LEN = 10;
    private static final int PRT_NUM_LEN = 6;
    private static int numRacks, numDesktopDevices, numMainFrames, numServerDevices, numStorageFrames,
            numNetworkDevices, numStorageDevices, numPatchPanels;

    protected void generate(Asset asset, Generator... generators) {
        final Random random = new Random();
        asset.setManufacturer((Manufacturer) dataFactory.getItem(generators[1].getResults()));
        asset.setRoom((Room) dataFactory.getItem(generators[2].getResults()));
        asset.setOwner((BusinessUnit) dataFactory.getItem(generators[3].getResults()));
        asset.setServiceContract((ServiceContract) dataFactory.getItem(generators[4].getResults()));
        asset.setPurchaseOrderNumber(dataFactory.getRandomChars(PO_LEN));
        asset.setSerial(dataFactory.getRandomChars(SERIAL_LEN));
        asset.setPartNumber(dataFactory.getRandomChars(PRT_NUM_LEN));
        asset.setDescription(dataFactory.getRandomText(MIN_DESC_LEN, MAX_DESC_LEN));
        asset.setCost(MIN_COST + (random.nextDouble() * (MAX_COST - MIN_COST)));
        asset.setStatus(dataFactory.getItem(AssetStatus.values()));
        switch (asset.getType()) {
            case RACK:
                asset.setName("rack-" + (++numRacks));
                break;
            case DESKTOP_DEVICE:
                asset.setName("desktop-device-" + (++numDesktopDevices));
                break;
            case MAIN_FRAME:
                asset.setName("main-frame-" + (++numMainFrames));
                break;
            case SERVER_DEVICE:
                asset.setName("server-device-" + (++numServerDevices));
                break;
            case STORAGE_FRAME:
                asset.setName("storage-frame-" + (++numStorageFrames));
                break;
            case NETWORK_DEVICE:
                asset.setName("network-device-" + (++numNetworkDevices));
                break;
            case STORAGE_DEVICE:
                asset.setName("storage-device-" + (++numStorageDevices));
                break;
            case PATCH_PANEL:
                asset.setName("patch-panel-" + (++numPatchPanels));
                break;
        }
    }

}
