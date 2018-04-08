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

import com.rbeckett.gridlock.model.business.Room;
import com.rbeckett.gridlock.model.network.GridLocation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.util.*;

import static com.rbeckett.gridlock.bootstrap.AppDataGeneratorConfig.*;

@Slf4j
@Component
class AppDataGenerator implements ApplicationListener<ContextRefreshedEvent> {

    private final LocationGenerator locationGenerator;
    private final UserGenerator userGenerator;
    private final ContactGenerator contactGenerator;
    private static final Map<Room, List<GridLocation>> roomGridLocationMap = new HashMap<>();
    private final RoomGenerator roomGenerator;
    private final ManufacturerGenerator manufacturerGenerator;
    private final BusinessGenerator businessGenerator;
    private final BusinessUnitGenerator businessUnitGenerator;
    private final ServiceContractGenerator serviceContractGenerator;
    private final SupportUnitGenerator supportUnitGenerator;
    private final UserProfileGenerator userProfileGenerator;
    private final PermissionGenerator permissionGenerator;
    private final RoleGenerator roleGenerator;
    private final NetworkConnectionGenerator networkConnectionGenerator;
    private final RackGenerator rackGenerator;
    private final GridLocationGenerator gridLocationGenerator;
    private final DesktopDeviceGenerator desktopDeviceGenerator;
    private final MainFrameGenerator mainFrameGenerator;
    private final NetworkDeviceGenerator networkDeviceGenerator;
    private final PatchPanelGenerator patchPanelGenerator;
    private final ServerDeviceGenerator serverDeviceGenerator;
    private final StorageDeviceGenerator storageDeviceGenerator;
    private final StorageFrameGenerator storageFrameGenerator;
    private final SiteGenerator siteGenerator;
    private final NetworkConfigurationGenerator networkConfigurationGenerator;
    private final OSConfigurationGenerator osConfigurationGenerator;
    private final HardwareConfigurationGenerator hardwareConfigurationGenerator;

    public AppDataGenerator(final LocationGenerator locationGenerator,
                            final UserGenerator userGenerator,
                            final ContactGenerator contactGenerator,
                            final SiteGenerator siteGenerator,
                            final RoomGenerator roomGenerator,
                            final ManufacturerGenerator manufacturerGenerator,
                            final BusinessGenerator businessGenerator,
                            final BusinessUnitGenerator businessUnitGenerator,
                            final ServiceContractGenerator serviceContractGenerator,
                            final SupportUnitGenerator supportUnitGenerator,
                            final UserProfileGenerator userProfileGenerator,
                            final PermissionGenerator permissionGenerator,
                            final RoleGenerator roleGenerator,
                            final NetworkConnectionGenerator networkConnectionGenerator,
                            final RackGenerator rackGenerator,
                            final GridLocationGenerator gridLocationGenerator,
                            final DesktopDeviceGenerator desktopDeviceGenerator,
                            final MainFrameGenerator mainFrameGenerator,
                            final NetworkDeviceGenerator networkDevice,
                            final PatchPanelGenerator patchPanelGenerator,
                            final ServerDeviceGenerator serverDevice,
                            final StorageDeviceGenerator storageDevice,
                            final StorageFrameGenerator storageFrameGenerator,
                            final NetworkConfigurationGenerator networkConfigurationGenerator,
                            final OSConfigurationGenerator osConfigurationGenerator,
                            final HardwareConfigurationGenerator hardwareConfigurationGenerator) {
        this.locationGenerator = locationGenerator;
        this.userGenerator = userGenerator;
        this.contactGenerator = contactGenerator;
        this.siteGenerator = siteGenerator;
        this.roomGenerator = roomGenerator;
        this.manufacturerGenerator = manufacturerGenerator;
        this.businessGenerator = businessGenerator;
        this.businessUnitGenerator = businessUnitGenerator;
        this.serviceContractGenerator = serviceContractGenerator;
        this.supportUnitGenerator = supportUnitGenerator;
        this.userProfileGenerator = userProfileGenerator;
        this.permissionGenerator = permissionGenerator;
        this.roleGenerator = roleGenerator;
        this.networkConnectionGenerator = networkConnectionGenerator;
        this.rackGenerator = rackGenerator;
        this.gridLocationGenerator = gridLocationGenerator;
        this.desktopDeviceGenerator = desktopDeviceGenerator;
        this.mainFrameGenerator = mainFrameGenerator;
        this.networkDeviceGenerator = networkDevice;
        this.patchPanelGenerator = patchPanelGenerator;
        this.serverDeviceGenerator = serverDevice;
        this.storageDeviceGenerator = storageDevice;
        this.storageFrameGenerator = storageFrameGenerator;
        this.networkConfigurationGenerator = networkConfigurationGenerator;
        this.osConfigurationGenerator = osConfigurationGenerator;
        this.hardwareConfigurationGenerator = hardwareConfigurationGenerator;
    }

    public static RoomGridLocationPair getNextRandomRoomAndGridLocation() {
        Random random = new Random();
        List<Room> rooms = new ArrayList<>(roomGridLocationMap.keySet());
        while (!isRoomGridLocationMapEmpty()) {
            Room room = rooms.get(random.nextInt(rooms.size()));
            List<GridLocation> gridLocations = roomGridLocationMap.get(room);
            if (!gridLocations.isEmpty()) {
                GridLocation gridLocation = gridLocations.remove(random.nextInt(gridLocations.size()));
                return new RoomGridLocationPair(room, gridLocation);
            }
        }
        return null;
    }

    private static boolean isRoomGridLocationMapEmpty() {
        for (Room room : roomGridLocationMap.keySet())
            if (!roomGridLocationMap.get(room).isEmpty())
                return false;
        return true;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        locationGenerator.generate(NUM_LOCATIONS);
        contactGenerator.generate(NUM_CONTACTS, locationGenerator);
        siteGenerator.generate(NUM_SITES, locationGenerator);
        roomGenerator.generate(NUM_ROOMS, siteGenerator);
        manufacturerGenerator.generate(NUM_MANUFACTURERS, locationGenerator, contactGenerator);
        businessGenerator.generate(NUM_BUSINESSES, locationGenerator, contactGenerator);
        businessUnitGenerator.generate(NUM_BUSINESS_UNITS, contactGenerator, businessGenerator);
        serviceContractGenerator.generate(NUM_SERVICE_CONTRACTS, manufacturerGenerator);
        supportUnitGenerator.generate(NUM_SUPPORT_UNITS, contactGenerator, businessUnitGenerator);
        userGenerator.generate(NUM_USERS, contactGenerator, businessUnitGenerator);
        userProfileGenerator.generate(NUM_USER_PROFILES, userGenerator);
        permissionGenerator.generate(NUM_PERMISSIONS);
        roleGenerator.generate(NUM_ROLES, permissionGenerator);
        gridLocationGenerator.generate(NUM_GRID_LOCATIONS);
        for (Room room : roomGenerator.getResults())
            roomGridLocationMap.put(room, new ArrayList<>(gridLocationGenerator.getResults()));
        rackGenerator.generate(NUM_RACKS, locationGenerator, manufacturerGenerator, roomGenerator,
                businessUnitGenerator, serviceContractGenerator, gridLocationGenerator);
        desktopDeviceGenerator.generate(NUM_DESKTOP_DEVICES, locationGenerator, manufacturerGenerator, roomGenerator,
                businessUnitGenerator, serviceContractGenerator, contactGenerator);
        mainFrameGenerator.generate(NUM_MAIN_FRAMES, locationGenerator, manufacturerGenerator, roomGenerator,
                businessUnitGenerator, serviceContractGenerator, gridLocationGenerator);
        networkDeviceGenerator.generate(NUM_NETWORK_DEVICES, locationGenerator, manufacturerGenerator, roomGenerator, businessUnitGenerator,
                serviceContractGenerator, rackGenerator);
        patchPanelGenerator.generate(NUM_PATCH_PANELS, locationGenerator, manufacturerGenerator, roomGenerator, businessUnitGenerator,
                serviceContractGenerator, rackGenerator);
        serverDeviceGenerator.generate(NUM_SERVER_DEVICES, locationGenerator, manufacturerGenerator, roomGenerator, businessUnitGenerator,
                serviceContractGenerator, rackGenerator);
        storageDeviceGenerator.generate(NUM_STORAGE_DEVICES, locationGenerator, manufacturerGenerator, roomGenerator, businessUnitGenerator,
                serviceContractGenerator, rackGenerator);
        storageFrameGenerator.generate(NUM_STORAGE_FRAMES, locationGenerator, manufacturerGenerator, roomGenerator, businessUnitGenerator,
                serviceContractGenerator, gridLocationGenerator);
        networkConfigurationGenerator.generate(NUM_NETWORK_CONFIGURATIONS);
        networkConnectionGenerator.generate(NUM_NETWORK_CONNECTIONS, networkConfigurationGenerator);
        osConfigurationGenerator.generate(NUM_OS_CONFIGURATIONS);
        hardwareConfigurationGenerator.generate(NUM_HARDWARE_CONFIGURATIONS);
    }

    public static final class RoomGridLocationPair {
        Room room;
        GridLocation gridLocation;

        public RoomGridLocationPair(final Room room, final GridLocation gridLocation) {
            this.room = room;
            this.gridLocation = gridLocation;
        }
    }
}
