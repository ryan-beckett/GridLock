/*
 * Copyright 2018 Ryan M. Beckett
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */

package com.rbeckett.gridlock.services.asset;

import com.rbeckett.gridlock.model.asset.NetworkDevice;
import com.rbeckett.gridlock.repositories.asset.NetworkDeviceRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Slf4j
@Service
public class NetworkDeviceServiceImpl implements NetworkDeviceService {

    private final NetworkDeviceRepository networkDeviceRepository;

    private NetworkDeviceServiceImpl(NetworkDeviceRepository networkDeviceRepository) {
        this.networkDeviceRepository = networkDeviceRepository;
    }

    @Override
    public Set<NetworkDevice> findAll() {
        Set<NetworkDevice> networkDevices = new HashSet<>();
        networkDeviceRepository.findAll().forEach(networkDevices::add);
        return networkDevices;
    }

    @Override
    public NetworkDevice findById(Long id) {
        Optional<NetworkDevice> networkDeviceOptional = networkDeviceRepository.findById(id);
        return networkDeviceOptional.orElse(null);
    }

    @Override
    public NetworkDevice save(NetworkDevice networkDevice) {
        return networkDeviceRepository.save(networkDevice);
    }

    @Override
    public long count() {
        return networkDeviceRepository.count();
    }

    @Override
    public void delete(NetworkDevice networkDevice) {
        networkDeviceRepository.delete(networkDevice);
    }

    @Override
    public void deleteById(Long id) {
        networkDeviceRepository.deleteById(id);
    }
}
