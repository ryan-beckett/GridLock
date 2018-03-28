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

import com.rbeckett.gridlock.model.asset.DesktopDevice;
import com.rbeckett.gridlock.repositories.asset.DesktopDeviceRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Slf4j
@Service
public class DesktopDeviceServiceImpl implements DesktopDeviceService {

    private final DesktopDeviceRepository desktopDeviceRepository;

    private DesktopDeviceServiceImpl(DesktopDeviceRepository desktopDeviceRepository) {
        this.desktopDeviceRepository = desktopDeviceRepository;
    }

    @Override
    public Set<DesktopDevice> findAll() {
        Set<DesktopDevice> desktopDevices = new HashSet<>();
        desktopDeviceRepository.findAll().forEach(desktopDevices::add);
        return desktopDevices;
    }

    @Override
    public DesktopDevice findById(Long id) {
        Optional<DesktopDevice> desktopDeviceOptional = desktopDeviceRepository.findById(id);
        return desktopDeviceOptional.orElse(null);
    }

    @Override
    public DesktopDevice save(DesktopDevice desktopDevice) {
        return desktopDeviceRepository.save(desktopDevice);
    }

    @Override
    public long count() {
        return desktopDeviceRepository.count();
    }

    @Override
    public void delete(DesktopDevice desktopDevice) {
        desktopDeviceRepository.delete(desktopDevice);
    }

    @Override
    public void deleteById(Long id) {
        desktopDeviceRepository.deleteById(id);
    }
}
