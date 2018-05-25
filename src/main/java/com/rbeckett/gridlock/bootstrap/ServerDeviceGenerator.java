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
            "https://www.supermicro.com/products/system/1U/5015/SYS-5015A-EHF-D525.jpg",
            "https://www.supermicro.com/a_images/products/Aplus/System/2U/AS-2022G-URF4__spec.jpg",
            "http://www.taknet.com.my/wp-content/uploads/2017/09/try7.jpg",
            "http://img.router-switch.com/media/wysiwyg/Dell-Products/Dell-Server/Dell-PowerEdge-T330-Server/dell-poweredge-t330-server_Front_and_Back_Panel.jpg",
            "http://cdn1.itpro.co.uk/sites/itpro/files/styles/article_main_wide_image/public/1/17//r730_6_x_4.jpg",
            "https://4.imimg.com/data4/DL/UF/MY-256711/hp-server-dl20-500x500.jpg",
            "https://www.cisco.com/c/en/us/products/servers-unified-computing/ucs-b-series-blade-servers/index/_jcr_content/Grid/subcategory_atl/layout-subcategory-atl/blade/bladeContents/halves_5f6e/H-Half-1/tile_9d06/image.img.jpg/1499460535159.jpg"};
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
