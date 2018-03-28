/*
 * Copyright 2018 Ryan M. Beckett
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */

package com.rbeckett.gridlock.services.business;

import com.rbeckett.gridlock.model.business.ServiceContract;
import com.rbeckett.gridlock.repositories.business.ServiceContractRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Slf4j
@Service
public class ServiceContractServiceImpl implements ServiceContractService {

    private final ServiceContractRepository serviceContractRepository;

    private ServiceContractServiceImpl(ServiceContractRepository serviceContractRepository) {
        this.serviceContractRepository = serviceContractRepository;
    }

    @Override
    public Set<ServiceContract> findAll() {
        Set<ServiceContract> serviceContracts = new HashSet<>();
        serviceContractRepository.findAll().forEach(serviceContracts::add);
        return serviceContracts;
    }

    @Override
    public ServiceContract findById(Long id) {
        Optional<ServiceContract> serviceContractOptional = serviceContractRepository.findById(id);
        return serviceContractOptional.orElse(null);
    }

    @Override
    public ServiceContract save(ServiceContract serviceContract) {
        return serviceContractRepository.save(serviceContract);
    }

    @Override
    public long count() {
        return serviceContractRepository.count();
    }

    @Override
    public void delete(ServiceContract serviceContract) {
        serviceContractRepository.delete(serviceContract);
    }

    @Override
    public void deleteById(Long id) {
        serviceContractRepository.deleteById(id);
    }
}
