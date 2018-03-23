/*
 * Copyright 2018 Ryan M. Beckett
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */

package com.rbeckett.gridlock.controllers;

import com.rbeckett.gridlock.model.asset.StorageDevice;
import com.rbeckett.gridlock.services.StorageDeviceService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@Slf4j
@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
@RestController
@RequestMapping({"/api/"})
class StorageDeviceController {

    private final StorageDeviceService storageDeviceService;

    private StorageDeviceController(StorageDeviceService storageDeviceService) {
        this.storageDeviceService = storageDeviceService;
    }

    @GetMapping("/storageDevices")
    public Set<StorageDevice> getStorageDevices() {
        try {
            return storageDeviceService.findAll();
        } catch (DataAccessException ex) {
            log.debug(ex.getMessage());
            return null;
        }
    }

    @GetMapping("/storageDevices/{id}")
    public ResponseEntity getStorageDeviceById(@PathVariable Long id) {
        try {
            return new ResponseEntity<>(storageDeviceService.findById(id), HttpStatus.OK);
        } catch (DataAccessException ex) {
            log.debug(ex.getMessage());
            return new ResponseEntity<>("No storageDevice with id = '" + id + "' could be found.", HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping(value = "/storageDevices")
    public ResponseEntity createStorageDevice(@RequestBody StorageDevice storageDevice) {
        try {
            return new ResponseEntity<>(storageDeviceService.save(storageDevice)
                    , HttpStatus.OK);
        } catch (DataAccessException ex) {
            log.debug(ex.getMessage());
            return new ResponseEntity<>("Could not create the storageDevice.", HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/storageDevices/{id}")
    public ResponseEntity deleteStorageDevice(@PathVariable Long id) {
        try {
            storageDeviceService.deleteById(id);
            return new ResponseEntity<>(id, HttpStatus.OK);
        } catch (DataAccessException ex) {
            log.debug(ex.getMessage());
            return new ResponseEntity<>("No storageDevice with id = '" + id + "' could be found.", HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/storageDevices/{id}")
    public ResponseEntity updateStorageDevice(@PathVariable Long id, @RequestBody StorageDevice storageDevice) {
        try {
            storageDeviceService.save(storageDevice);
            return new ResponseEntity<>(storageDevice, HttpStatus.OK);
        } catch (DataAccessException ex) {
            log.debug(ex.getMessage());
            return new ResponseEntity<>("Could not update storageDevice with id = '" + id + "'.", HttpStatus.NOT_FOUND);
        }
    }
}
