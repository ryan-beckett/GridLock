/*
 * Copyright 2018 Ryan M. Beckett
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */

package com.rbeckett.gridlock.services.network;

import com.rbeckett.gridlock.model.network.Hop;
import com.rbeckett.gridlock.repositories.network.HopRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Slf4j
@Lazy
@Service
public class HopServiceImpl implements HopService {

    private final HopRepository hopRepository;

    private HopServiceImpl(HopRepository hopRepository) {
        this.hopRepository = hopRepository;
    }

    @Override
    public Set<Hop> findAll() {
        Set<Hop> hops = new HashSet<>();
        hopRepository.findAll().forEach(hops::add);
        return hops;
    }

    @Override
    public Hop findById(Long id) {
        Optional<Hop> hopOptional = hopRepository.findById(id);
        return hopOptional.orElse(null);
    }

    @Override
    public Hop save(Hop hop) {
        return hopRepository.save(hop);
    }

    @Override
    public long count() {
        return hopRepository.count();
    }

    @Override
    public void delete(Hop hop) {
        hopRepository.delete(hop);
    }

    @Override
    public void deleteById(Long id) {
        hopRepository.deleteById(id);
    }
}
