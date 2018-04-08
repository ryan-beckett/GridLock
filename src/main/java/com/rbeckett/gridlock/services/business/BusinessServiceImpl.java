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

import com.rbeckett.gridlock.model.business.Business;
import com.rbeckett.gridlock.repositories.business.BusinessRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Slf4j
@Lazy
@Service
public class BusinessServiceImpl implements BusinessService {

    private final BusinessRepository businessRepository;

    private BusinessServiceImpl(BusinessRepository businessRepository) {
        this.businessRepository = businessRepository;
    }

    @Override
    public Set<Business> findAll() {
        Set<Business> businesss = new HashSet<>();
        businessRepository.findAll().forEach(businesss::add);
        return businesss;
    }

    @Override
    public Business findById(Long id) {
        Optional<Business> businessOptional = businessRepository.findById(id);
        return businessOptional.orElse(null);
    }

    @Override
    public Business save(Business business) {
        return businessRepository.save(business);
    }

    @Override
    public long count() {
        return businessRepository.count();
    }

    @Override
    public void delete(Business business) {
        businessRepository.delete(business);
    }

    @Override
    public void deleteById(Long id) {
        businessRepository.deleteById(id);
    }
}
