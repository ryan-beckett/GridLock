/*
 * Copyright 2018 Ryan M. Beckett
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */

package com.rbeckett.gridlock.controllers.asset;

import com.rbeckett.gridlock.model.asset.NetworkDevice;
import com.rbeckett.gridlock.services.asset.NetworkDeviceService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Lazy;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@Lazy
@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
@Slf4j
@RestController
@RequestMapping({"/api/network-devices/"})
class NetworkDeviceController {

    private final NetworkDeviceService networkDeviceService;

    private NetworkDeviceController(NetworkDeviceService networkDeviceService) {
        this.networkDeviceService = networkDeviceService;
    }

    @GetMapping("")
    public Set<NetworkDevice> getNetworkDevices() {
        try {
            return networkDeviceService.findAll();
        } catch (DataAccessException ex) {
            log.debug(ex.getMessage());
            return null;
        }
    }

    @GetMapping("id/{id}")
    public ResponseEntity getNetworkDeviceById(@PathVariable Long id) {
        try {
            return new ResponseEntity<>(networkDeviceService.findById(id), HttpStatus.OK);
        } catch (DataAccessException ex) {
            log.debug(ex.getMessage());
            return new ResponseEntity<>("No networkDevice with id = '" + id + "' could be found.", HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping(value = "")
    public ResponseEntity createNetworkDevice(@RequestBody NetworkDevice networkDevice) {
        try {
            return new ResponseEntity<>(networkDeviceService.save(networkDevice)
                    , HttpStatus.OK);
        } catch (DataAccessException ex) {
            log.debug(ex.getMessage());
            return new ResponseEntity<>("Could not create the networkDevice.", HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("id/{id}")
    public ResponseEntity deleteNetworkDevice(@PathVariable Long id) {
        try {
            networkDeviceService.deleteById(id);
            return new ResponseEntity<>(id, HttpStatus.OK);
        } catch (DataAccessException ex) {
            log.debug(ex.getMessage());
            return new ResponseEntity<>("No networkDevice with id = '" + id + "' could be found.", HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("id/{id}")
    public ResponseEntity updateNetworkDevice(@PathVariable Long id, @RequestBody NetworkDevice networkDevice) {
        try {
            networkDeviceService.save(networkDevice);
            return new ResponseEntity<>(networkDevice, HttpStatus.OK);
        } catch (DataAccessException ex) {
            log.debug(ex.getMessage());
            return new ResponseEntity<>("Could not update networkDevice with id = '" + id + "'.", HttpStatus.NOT_FOUND);
        }
    }
}
