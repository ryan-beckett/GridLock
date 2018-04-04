package com.rbeckett.gridlock.bootstrap;

import com.rbeckett.gridlock.enums.AssetType;
import com.rbeckett.gridlock.enums.DesktopDeviceType;
import com.rbeckett.gridlock.model.asset.DesktopDevice;
import com.rbeckett.gridlock.model.business.Contact;
import com.rbeckett.gridlock.services.asset.DesktopDeviceService;
import org.fluttercode.datafactory.impl.DataFactory;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DesktopDeviceGenerator extends AssetGenerator implements Generator<DesktopDevice> {

    private static final DataFactory dataFactory = new DataFactory();
    private static final String[] DESKTOP_DEVICES = {"OptiPlex 3050", "Aspire TC-780-AMZKi5",
            "ProDesk 600 G3 ThinClient", "EliteDesk 800 G3"};
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
            generate(desktopDevice, generators);
            desktopDevice.setModel(dataFactory.getItem(DESKTOP_DEVICES));
            desktopDevice.setSubType(dataFactory.getItem(DesktopDeviceType.values()));
            desktopDevice.setUser((Contact) dataFactory.getItem(generators[5].getResults()));
            desktopDevices.add(desktopDeviceService.save(desktopDevice));
        }
    }

    @Override
    public List<DesktopDevice> getResults() {
        return desktopDevices;
    }
}
