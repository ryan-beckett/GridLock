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
            "Supermicro AS-2022G", "Asus RS100-E9-P12", "PowerEdge T330", "Dell Server r730",
            "HP GridEdge 4550", "Cisco Blade I930"};
    private static final String[] SERVER_DEVICES_IMG_URLS = {
            "https://www.supermicro.com/products/system/1U/5015/SYS-5015A-EHF-D525.cfm",
            "https://www.supermicro.com/aplus/system/2u/2022/as-2022g-urf4_.cfm",
            "http://www.taknet.com.my/products/asus/rs100-e9-p12/",
            "http://www.router-switch.com/dell-poweredge-t330-xeon-e3-1240-v5-32gb-2tb-server.html",
            "http://www.itpro.co.uk/server/23980/dell-poweredge-r730-review",
            "https://www.indiamart.com/proddetail/hp-proliant-dl20-gen-9-server-866233-375-13489239973.html",
            "https://www.cisco.com/c/en/us/products/servers-unified-computing/ucs-b-series-blade-servers/index.html"};
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
            serverDevice.setImageURL(SERVER_DEVICES_IMG_URLS[i % SERVER_DEVICES.length]);
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
