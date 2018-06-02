package com.rbeckett.gridlock.bootstrap;

import com.rbeckett.gridlock.enums.AssetType;
import com.rbeckett.gridlock.enums.DesktopDeviceType;
import com.rbeckett.gridlock.model.asset.DesktopDevice;
import com.rbeckett.gridlock.model.business.Contact;
import com.rbeckett.gridlock.services.asset.DesktopDeviceService;
import lombok.extern.slf4j.Slf4j;
import org.fluttercode.datafactory.impl.DataFactory;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Component
public class DesktopDeviceGenerator extends AssetGenerator implements Generator<DesktopDevice> {

    private static final DataFactory dataFactory = new DataFactory();
    private static final String[] DESKTOP_DEVICES = {"OptiPlex 3050", "Aspire TC-780-AMZKi5",
            "ProDesk 600 G3 ThinClient", "EliteDesk 800 G3"};
    private static final String[] DESKTOP_DEVICES_IMG_URL = {
            "https://s15.postimg.cc/i9iza769j/15-298-007-04.jpg",
            "https://s15.postimg.cc/t946lsoyv/A24_G_1_201610101978737490.jpg",
            "https://s15.postimg.cc/8ozcnbonb/ABGC_1314194156868598615_HGr61_H835.jpg",
    };
    private final List<DesktopDevice> desktopDevices = new ArrayList<>();
    private final DesktopDeviceService desktopDeviceService;

    public DesktopDeviceGenerator(final DesktopDeviceService desktopDeviceService) {
        this.desktopDeviceService = desktopDeviceService;
    }

    @Override
    public void generate(final int numResults, final Generator... generators) {
        for (int i = 0; i < numResults; i++) {
            final DesktopDevice desktopDevice = new DesktopDevice();
            desktopDevice.setType(AssetType.DESKTOP_DEVICE);
            desktopDevice.setModel(DESKTOP_DEVICES[i % DESKTOP_DEVICES.length]);
            desktopDevice.setImageURL(DESKTOP_DEVICES_IMG_URL[i % DESKTOP_DEVICES_IMG_URL.length]);
            desktopDevice.setSubType(dataFactory.getItem(DesktopDeviceType.values()));
            desktopDevice.setUser((Contact) dataFactory.getItem(generators[5].getResults()));
            generate(desktopDevice, generators);
            desktopDevices.add(desktopDeviceService.save(desktopDevice));
        }
        log.info("Generated data for DesktopDevice entity");
    }

    @Override
    public List<DesktopDevice> getResults() {
        return desktopDevices;
    }
}
