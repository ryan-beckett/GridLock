package com.rbeckett.gridlock.bootstrap;

import com.rbeckett.gridlock.model.business.Contact;
import com.rbeckett.gridlock.model.business.Location;
import com.rbeckett.gridlock.model.business.Manufacturer;
import com.rbeckett.gridlock.services.business.ManufacturerService;
import lombok.extern.slf4j.Slf4j;
import org.fluttercode.datafactory.impl.DataFactory;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Component
public class ManufacturerGenerator implements Generator<Manufacturer> {

    private static final DataFactory dataFactory = new DataFactory();
    private static final String[] MANUFACTURERS = {"Cisco", "EMC", "Dell", "HP", "MSI", "Juniper", "Microsoft",
            "Intel", "Amd", "Apple", "IBM", "Gateway", "TP-Link", "NetGear", "LinkSys", "Brocade", "Panduit",
            "Leviton", "Signamax", "AMCO", "Rainford", "Martin Enclosures", "DAMAC"};
    private final List<Manufacturer> manufacturers = new ArrayList<>();
    private final ManufacturerService manufacturerService;

    public ManufacturerGenerator(final ManufacturerService manufacturerService) {
        this.manufacturerService = manufacturerService;
    }

    @Override
    public void generate(final int numResults, final Generator... generators) {
        for (int i = 0; i < numResults; i++) {
            Manufacturer manufacturer = new Manufacturer();
            manufacturer.setName(MANUFACTURERS[i % MANUFACTURERS.length]);
            manufacturer.setLocation((Location) dataFactory.getItem(generators[0].getResults()));
            manufacturer.setContact((Contact) dataFactory.getItem(generators[1].getResults()));
            manufacturers.add(manufacturerService.save(manufacturer));
        }
        log.info("Generated data for Manufacturer entity");
    }

    @Override
    public List<Manufacturer> getResults() {
        return manufacturers;
    }
}
