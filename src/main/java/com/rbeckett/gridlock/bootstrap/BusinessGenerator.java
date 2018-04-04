package com.rbeckett.gridlock.bootstrap;

import com.rbeckett.gridlock.model.business.Business;
import com.rbeckett.gridlock.model.business.Contact;
import com.rbeckett.gridlock.model.business.Location;
import com.rbeckett.gridlock.services.business.BusinessService;
import org.fluttercode.datafactory.impl.DataFactory;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class BusinessGenerator implements Generator<Business> {

    private static final DataFactory dataFactory = new DataFactory();
    private static final String BUSINESS_NAME = "GridLock Inc";
    private final List<Business> businesses = new ArrayList<>();
    private final BusinessService businessService;

    public BusinessGenerator(final BusinessService businessService) {
        this.businessService = businessService;
    }

    @Override
    public void generate(final int numResults, final Generator... generators) {
        for (int i = 0; i < numResults; i++) {
            final Business business = new Business();
            business.setName(BUSINESS_NAME);
            business.setLocation((Location) dataFactory.getItem(generators[0].getResults()));
            business.setContact((Contact) dataFactory.getItem(generators[1].getResults()));
            businesses.add(businessService.save(business));
        }
    }

    @Override
    public List<Business> getResults() {
        return businesses;
    }
}
