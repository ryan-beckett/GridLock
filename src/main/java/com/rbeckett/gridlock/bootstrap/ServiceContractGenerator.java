package com.rbeckett.gridlock.bootstrap;

import com.rbeckett.gridlock.enums.ServiceContractType;
import com.rbeckett.gridlock.model.business.Manufacturer;
import com.rbeckett.gridlock.model.business.ServiceContract;
import com.rbeckett.gridlock.services.business.ServiceContractService;
import org.fluttercode.datafactory.impl.DataFactory;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Component
public class ServiceContractGenerator implements Generator<ServiceContract> {

    private static final DataFactory dataFactory = new DataFactory();
    private final List<ServiceContract> serviceContracts = new ArrayList<>();
    private final ServiceContractService serviceContractService;

    public ServiceContractGenerator(
            final ServiceContractService serviceContractService) {
        this.serviceContractService = serviceContractService;
    }

    @Override
    public void generate(final int numResults, final Generator... generators) {
        for (int i = 0; i < numResults; i++) {
            final ServiceContract serviceContract = new ServiceContract();
            serviceContract.setType(dataFactory.getItem(ServiceContractType.values()));
            if (!serviceContract.getType().equals(ServiceContractType.NO_WARRANTY)) {
                serviceContract.setStartDate(getRandomLocalDate());
                if (!serviceContract.getType().equals(ServiceContractType.LIFETIME_WARRANTY))
                    serviceContract.setEndDate(serviceContract.getStartDate().plusYears(3));
            }
            serviceContract.setContractor((Manufacturer) dataFactory.getItem(generators[0].getResults()));
            serviceContracts.add(serviceContractService.save(serviceContract));
        }
    }

    private LocalDate getRandomLocalDate() {
        LocalDate d1 = LocalDate.ofEpochDay(0), d2 = LocalDate.now();
        int days = (int) ChronoUnit.DAYS.between(d1, d2);
        return d1.plusDays(new Random().nextInt(days + 1));
    }

    @Override
    public List<ServiceContract> getResults() {
        return serviceContracts;
    }
}
