/*
 * Copyright 2018 Ryan M. Beckett
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */

package com.rbeckett.gridlock.services;

import com.rbeckett.gridlock.model.asset.ConfigurableDevice;
import com.rbeckett.gridlock.repositories.ConfigurableDeviceRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Slf4j
@Service
public class ConfigurableDeviceServiceImpl implements ConfigurableDeviceService {

    private final ConfigurableDeviceRepository configurableDeviceRepository;

    private ConfigurableDeviceServiceImpl(
            ConfigurableDeviceRepository configurableDeviceRepository) {
        this.configurableDeviceRepository = configurableDeviceRepository;
    }


    @Override
    public Set<ConfigurableDevice> findAll() {
        Set<ConfigurableDevice> configurableDevices = new HashSet<>();
        configurableDeviceRepository.findAll().forEach(configurableDevices::add);
        return configurableDevices;
    }

    @Override
    public ConfigurableDevice findById(Long id) {
        Optional<ConfigurableDevice> configurableDeviceOptional = configurableDeviceRepository.findById(id);
        return configurableDeviceOptional.orElse(null);
    }

    @Override
    public ConfigurableDevice save(ConfigurableDevice configurableDevice) {
        return configurableDeviceRepository.save(configurableDevice);
    }

    @Override
    public long count() {
        return configurableDeviceRepository.count();
    }

    @Override
    public void delete(ConfigurableDevice configurableDevice) {
        configurableDeviceRepository.delete(configurableDevice);
    }

    @Override
    public void deleteById(Long id) {
        configurableDeviceRepository.deleteById(id);
    }
}
