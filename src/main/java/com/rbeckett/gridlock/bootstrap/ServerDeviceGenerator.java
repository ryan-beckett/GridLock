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
            "HP GridEdge 4550", "Cisco Blade I930", "Asus RS100-E9-P12", "PowerEdge T330", "Dell Server r730",
            "Supermicro AS-2022G"};
    private static final String[] SERVER_DEVICES_IMG_URLS = {
            "https://s15.postimg.cc/qhgbhg8s7/DSX-2_32_Port_FRONT_REAR-_M_PIC.jpg",
            "https://s15.postimg.cc/uql1jlh6f/hp_bladesystem_c7000_platinum.jpg",
            "https://s15.postimg.cc/by96g2i87/KS15099-_High-900-_Cropped.jpg",
            "https://s15.postimg.cc/3sr4hv493/lenovo-servers-rack-system-x3650-m5-gallery-1.jpg",
            "https://s15.postimg.cc/5xbhiz8gn/original.jpg",
            "https://s15.postimg.cc/4v1b0eus7/r730_6_x_4.jpg",
            "https://s15.postimg.cc/xko6x2lxj/Storage_Review-_Supermicro-_Super_Server-2028_U-_TNR4_T-_Rear.jpg"};
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
            serverDevice.setImageURL(SERVER_DEVICES_IMG_URLS[i % SERVER_DEVICES_IMG_URLS.length]);
            serverDevice.setSubType(dataFactory.getItem(ServerDeviceType.values()));
            generate(serverDevice, rackService, generators);
            serverDevices.add(serverDeviceService.findByName(serverDevice.getName()));
        }
        log.info("Generated data for ServerDevice entity");
    }

    @Override
    public List<ServerDevice> getResults() {
        return serverDevices;
    }
}
