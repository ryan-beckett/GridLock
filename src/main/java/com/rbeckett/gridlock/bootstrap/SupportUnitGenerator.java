package com.rbeckett.gridlock.bootstrap;

import com.rbeckett.gridlock.model.business.BusinessUnit;
import com.rbeckett.gridlock.model.business.Contact;
import com.rbeckett.gridlock.model.business.SupportUnit;
import com.rbeckett.gridlock.services.business.SupportUnitService;
import lombok.extern.slf4j.Slf4j;
import org.fluttercode.datafactory.impl.DataFactory;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Slf4j
@Component
public class SupportUnitGenerator implements Generator<SupportUnit> {

    private static final DataFactory dataFactory = new DataFactory();
    private static final String[] SUPPORT_UNITS = {"Windows Support", "Hardware Support", "Network Support",
            "Application Support", "Desktop Support", "Printer Support", "Server Support", "Unix/Linux Support",
            "Cloud Support", "VOIP Support"};
    private final List<SupportUnit> supportUnits = new ArrayList<>();
    private final SupportUnitService supportUnitService;

    public SupportUnitGenerator(final SupportUnitService supportUnitService) {
        this.supportUnitService = supportUnitService;
    }

    @Override
    public void generate(final int numResults, final Generator... generators) {
        Random random = new Random();
        for (int i = 0; i < numResults; i++) {
            final SupportUnit supportUnit = new SupportUnit();
            supportUnit.setName(SUPPORT_UNITS[i % SUPPORT_UNITS.length]);
            supportUnit.setBusinessUnit((BusinessUnit) dataFactory.getItem(generators[1].getResults()));
            List<Contact> contacts = generators[0].getResults();
            int numContacts = random.nextInt(contacts.size());
            for (int j = 0; j < numContacts; j++)
                supportUnit.getMembers().add(dataFactory.getItem(contacts));
            supportUnits.add(supportUnitService.save(supportUnit));
        }
        log.info("Generated data for SupportUnit entity");
    }

    @Override
    public List<SupportUnit> getResults() {
        return supportUnits;
    }
}
