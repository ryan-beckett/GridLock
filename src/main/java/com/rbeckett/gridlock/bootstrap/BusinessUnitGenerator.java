package com.rbeckett.gridlock.bootstrap;

import com.rbeckett.gridlock.model.business.Business;
import com.rbeckett.gridlock.model.business.BusinessUnit;
import com.rbeckett.gridlock.model.business.Contact;
import com.rbeckett.gridlock.services.business.BusinessUnitService;
import lombok.extern.slf4j.Slf4j;
import org.fluttercode.datafactory.impl.DataFactory;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Component
public class BusinessUnitGenerator implements Generator<BusinessUnit> {

    private static final DataFactory dataFactory = new DataFactory();
    private static final String[] BUSINESS_UNITS = {"Human Resources", "Information Technology Services", "Marketing",
            "Sales", "Research and Development", "Legal", "Logistics", "Accounting", "Administrative Services",
            "Operations"};
    private final List<BusinessUnit> businessUnits = new ArrayList<>();
    private final BusinessUnitService businessUnitService;

    public BusinessUnitGenerator(final BusinessUnitService businessUnitService) {
        this.businessUnitService = businessUnitService;
    }

    @Override
    public void generate(final int numResults, final Generator... generators) {
        for (int i = 0; i < numResults; i++) {
            final BusinessUnit businessUnit = new BusinessUnit();
            businessUnit.setName(BUSINESS_UNITS[i % BUSINESS_UNITS.length]);
            businessUnit.setContact((Contact) dataFactory.getItem(generators[0].getResults()));
            businessUnit.setBusiness((Business) dataFactory.getItem(generators[1].getResults()));
            businessUnits.add(businessUnitService.save(businessUnit));
        }
        log.info("Generated data for BusinessUnit entity");
    }

    @Override
    public List<BusinessUnit> getResults() {
        return businessUnits;
    }
}
