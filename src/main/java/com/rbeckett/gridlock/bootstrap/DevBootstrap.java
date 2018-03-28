/*
 * Copyright 2018 Ryan M. Beckett
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */

package com.rbeckett.gridlock.bootstrap;

import com.rbeckett.gridlock.enums.AssetStatus;
import com.rbeckett.gridlock.enums.AssetType;
import com.rbeckett.gridlock.enums.ConfigurationType;
import com.rbeckett.gridlock.enums.ServerDeviceType;
import com.rbeckett.gridlock.model.asset.Rack;
import com.rbeckett.gridlock.model.asset.ServerDevice;
import com.rbeckett.gridlock.model.business.*;
import com.rbeckett.gridlock.model.configuration.HardwareConfiguration;
import com.rbeckett.gridlock.model.network.GridLocation;
import com.rbeckett.gridlock.repositories.asset.RackRepository;
import com.rbeckett.gridlock.repositories.asset.ServerDeviceRepository;
import com.rbeckett.gridlock.repositories.business.*;
import com.rbeckett.gridlock.repositories.configuration.HardwareConfigurationRepository;
import com.rbeckett.gridlock.repositories.configuration.NetworkConfigurationRepository;
import com.rbeckett.gridlock.repositories.configuration.OSConfigurationRepository;
import com.rbeckett.gridlock.repositories.network.GridLocationRepository;
import com.rbeckett.gridlock.utils.GridUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

@Slf4j
@Component
class DevBootstrap implements ApplicationListener<ContextRefreshedEvent> {

    private final LocationRepository locationRepository;
    private final ContactRepository contactRepository;
    private final ServerDeviceRepository serverDeviceRepository;
    private final GridLocationRepository gridLocationRepository;
    private final ManufacturerRepository manufacturerRepository;
    private final HardwareConfigurationRepository hardwareConfigurationRepository;
    //private final NetworkConfigurationRepository networkConfigurationRepository;
    //private final OSConfigurationRepository osConfigurationRepository;
    private final BusinessUnitRepository businessUnitRepository;
    private final BusinessRepository businessRepository;
    private final RackRepository rackRepository;
    private final RoomRepository roomRepository;

    private DevBootstrap(LocationRepository locationRepository,
                         ContactRepository contactRepository,
                         ServerDeviceRepository serverDeviceRepository,
                         GridLocationRepository gridLocationRepository,
                         ManufacturerRepository manufacturerRepository,
                         HardwareConfigurationRepository hardwareConfigurationRepository,
                         NetworkConfigurationRepository networkConfigurationRepository,
                         OSConfigurationRepository osConfigurationRepository,
                         BusinessUnitRepository businessUnitRepository,
                         BusinessRepository businessRepository,
                         RackRepository rackRepository,
                         RoomRepository roomRepository) {
        this.locationRepository = locationRepository;
        this.contactRepository = contactRepository;
        this.serverDeviceRepository = serverDeviceRepository;
        this.gridLocationRepository = gridLocationRepository;
        this.manufacturerRepository = manufacturerRepository;
        this.hardwareConfigurationRepository = hardwareConfigurationRepository;
        //this.networkConfigurationRepository = networkConfigurationRepository;
        //this.osConfigurationRepository = osConfigurationRepository;
        this.businessUnitRepository = businessUnitRepository;
        this.businessRepository = businessRepository;
        this.rackRepository = rackRepository;
        this.roomRepository = roomRepository;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        initData();
    }

    private void initData() {
        log.info("Bootstraping sample data...");

        Location location = new Location();
        location.setName("Home");
        location.setCountry("USA");
        location.setCity("West Chester");
        location.setAddress("87 Corner Lane");
        location.setState("PA");
        location.setZip("19380");
        locationRepository.save(location);

        Contact contact = new Contact();
        contact.setLocation(location);
        contact.setLastName("Jones");
        contact.setFirstName("Ryan");
        contact.setPhone("610-123-4567");
        contact.setJobTitle("Site Engineer");
        contact.setEmail("jones.ryan@gmail.com");
        contactRepository.save(contact);

        location = new Location();
        location.setName("Work");
        location.setCountry("USA");
        location.setCity("West Chester");
        location.setAddress("229 Turner Terrace");
        location.setState("PA");
        location.setZip("19380");
        locationRepository.save(location);

        contact = new Contact();
        contact.setLocation(location);
        contact.setLastName("Arnold");
        contact.setFirstName("Charles");
        contact.setPhone("432-543-4432");
        contact.setJobTitle("Developer");
        contact.setEmail("carnold54@gmail.com");
        contactRepository.save(contact);

        Manufacturer manufacturer = new Manufacturer();
        manufacturer.setLocation(location);
        manufacturer.setName("Cisco");
        manufacturer.setContact(contact);
        manufacturerRepository.save(manufacturer);

        HardwareConfiguration hardwareConfiguration = new HardwareConfiguration();
        hardwareConfiguration.setRamSizeInGB(6);
        hardwareConfiguration.setRamModel("ECC DIMM");
        hardwareConfiguration.setRamManufacturer("Cisco");
        hardwareConfiguration.setNumberOfCpus(4);
        hardwareConfiguration.setCpuSpeedInGhz(3.5);
        hardwareConfiguration.setCpuModel("Xeon E7 V2");
        hardwareConfiguration.setCpuManufacturer("Intel");
        hardwareConfiguration.setConfigurationType(ConfigurationType.HARDWARE);
        hardwareConfiguration.setDescription("Any additional information for this configuration...");
        hardwareConfigurationRepository.save(hardwareConfiguration);

        Business business = new Business();
        business.setName("ManageDCIM");
        business.setLocation(location);
        business.setContact(contact);
        businessRepository.save(business);

        BusinessUnit businessUnit = new BusinessUnit();
        businessUnit.setName("Human Resources");
        businessUnit.setContact(contact);
        businessUnit.setBusiness(business);
        businessUnitRepository.save(businessUnit);

        GridLocation gridLocation = new GridLocation();
        gridLocation.setX(GridUtils.gridXUserStringToInt("AA"));
        gridLocation.setY(GridUtils.gridYUserStringToInt("01"));
        gridLocationRepository.save(gridLocation);

        Room room = new Room();
        room.setLocation(location);
        room.setName("Compute Cluster Room 1");
        roomRepository.save(room);

        Rack rack = new Rack();
        rack.setUHeight(42);
        rack.setGridLocation(gridLocation);
        rack.setCost(1000);
        rack.setDescription("Server Rack");
        rack.setLocation(location);
        rack.setManufacturer(manufacturer);
        rack.setModel("Gen3 Ability");
        rack.setName("cmpt-clstr-rck-1");
        rack.setOwner(businessUnit);
        rack.setPartNumber("NL093|45");
        rack.setPurchaseOrderNumber("23049033243");
        rack.setRoom(room);
        rack.setSerial("324324");
        rack.setServiceContract(null);
        rack.setStatus(AssetStatus.INSTALLED);
        rack.setType(AssetType.RACK);
        rackRepository.save(rack);

        ServerDevice serverDevice = new ServerDevice();
        serverDevice.setSubType(ServerDeviceType.BLADE);
        serverDevice.setCost(15000);
        serverDevice.setDescription("Cisco UCS B460 M4 Blade Server");
        serverDevice.setGridLocation(gridLocation);
        serverDevice.setHardwareConfiguration(null);
        serverDevice.setLocation(location);
        serverDevice.setManufacturer(manufacturer);
        serverDevice.setModel("UCS B460 M4");
        serverDevice.setName("cmpt-clstr-nd-1");
        serverDevice.setNetworkConfiguration(null);
        serverDevice.setOsConfiguration(null);
        //serverDevice.setHardwareConfiguration(hardwareConfiguration);
        serverDevice.setOwner(businessUnit);
        serverDevice.setPartNumber("UCSB-EX-M4-3A");
        serverDevice.setPurchaseOrderNumber("11-02432343-023");
        //serverDevice.setRack(rack);
        serverDevice.setRoom(room);
        serverDevice.setSerial("90843");
        serverDevice.setServiceContract(null);
        serverDevice.setStatus(AssetStatus.NEW);
        serverDevice.setType(AssetType.SERVER_DEVICE);
        serverDevice.setUHeight(2);
        serverDevice.setULocation(1);
        serverDevice.setZone("cmpt-clstr-zone-1");
        serverDeviceRepository.save(serverDevice);

        //rack.getDevices().add(serverDevice);
        //rackRepository.save(rack);

        //hardwareConfiguration.setConfigurableDevice(serverDevice);
        //hardwareConfigurationRepository.save(hardwareConfiguration);
    }
}
