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

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

@Slf4j
@Component
class AppDataGenerator implements ApplicationListener<ContextRefreshedEvent> {

    private final LocationGenerator locationGenerator;
    private final UserGenerator userGenerator;
    private final ContactGenerator contactGenerator;
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

    public AppDataGenerator(final LocationGenerator locationGenerator,
                            final UserGenerator userGenerator,
                            final ContactGenerator contactGenerator,
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
                            final StorageFrameGenerator storageFrameGenerator) {
        this.locationGenerator = locationGenerator;
        this.userGenerator = userGenerator;
        this.contactGenerator = contactGenerator;
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
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        locationGenerator.generate(10);
        contactGenerator.generate(10, locationGenerator);
        roomGenerator.generate(10, locationGenerator);
        manufacturerGenerator.generate(10, locationGenerator, contactGenerator);
        businessGenerator.generate(1, locationGenerator, contactGenerator);
        businessUnitGenerator.generate(10, contactGenerator, businessGenerator);
        serviceContractGenerator.generate(10, manufacturerGenerator);
        supportUnitGenerator.generate(10, contactGenerator, businessUnitGenerator);
        userGenerator.generate(-1, contactGenerator, businessUnitGenerator);
        userProfileGenerator.generate(-1, userGenerator);
        permissionGenerator.generate(-1);
        roleGenerator.generate(-1, permissionGenerator);
        gridLocationGenerator.generate(50);
        rackGenerator.generate(50, locationGenerator, manufacturerGenerator, roomGenerator,
                businessUnitGenerator, serviceContractGenerator, gridLocationGenerator);
        desktopDeviceGenerator.generate(50, locationGenerator, manufacturerGenerator, roomGenerator,
                businessUnitGenerator, serviceContractGenerator, contactGenerator);
        mainFrameGenerator.generate(50, locationGenerator, manufacturerGenerator, roomGenerator,
                businessUnitGenerator, serviceContractGenerator, gridLocationGenerator);
        networkDeviceGenerator.generate(50, locationGenerator, manufacturerGenerator, roomGenerator, businessUnitGenerator,
                serviceContractGenerator, rackGenerator);
        patchPanelGenerator.generate(50, locationGenerator, manufacturerGenerator, roomGenerator, businessUnitGenerator,
                serviceContractGenerator, rackGenerator);
        serverDeviceGenerator.generate(50, locationGenerator, manufacturerGenerator, roomGenerator, businessUnitGenerator,
                serviceContractGenerator, rackGenerator);
        storageDeviceGenerator.generate(50, locationGenerator, manufacturerGenerator, roomGenerator, businessUnitGenerator,
                serviceContractGenerator, rackGenerator);
        storageFrameGenerator.generate(50, locationGenerator, manufacturerGenerator, roomGenerator, businessUnitGenerator,
                serviceContractGenerator, gridLocationGenerator);
        //networkConfigurationGenerator.generate(350);
        //networkConnectionGenerator.generate(700, networkConfigurationGenerator);
        //osConfigurationGenerator.generate(300);
        //hardwareConfigurationGenerator.generate(300);
    }
}
