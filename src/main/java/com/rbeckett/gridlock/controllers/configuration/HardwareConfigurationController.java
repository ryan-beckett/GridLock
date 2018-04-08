/*
 * Copyright 2018 Ryan M. Beckett
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */

package com.rbeckett.gridlock.controllers.configuration;

import com.rbeckett.gridlock.model.configuration.HardwareConfiguration;
import com.rbeckett.gridlock.services.configuration.HardwareConfigurationService;
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
@RequestMapping({"/api/hardware-configurations/"})
class HardwareConfigurationController {

    private final HardwareConfigurationService hardwareConfigurationService;

    private HardwareConfigurationController(HardwareConfigurationService hardwareConfigurationService) {
        this.hardwareConfigurationService = hardwareConfigurationService;
    }

    @GetMapping("")
    public Set<HardwareConfiguration> getHardwareConfigurations() {
        try {
            return hardwareConfigurationService.findAll();
        } catch (DataAccessException ex) {
            log.debug(ex.getMessage());
            return null;
        }
    }

    @GetMapping("id/{id}")
    public ResponseEntity getHardwareConfigurationById(@PathVariable Long id) {
        try {
            return new ResponseEntity<>(hardwareConfigurationService.findById(id), HttpStatus.OK);
        } catch (DataAccessException ex) {
            log.debug(ex.getMessage());
            return new ResponseEntity<>("No hardwareConfiguration with id = '" + id + "' could be found.", HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping(value = "")
    public ResponseEntity createHardwareConfiguration(@RequestBody HardwareConfiguration hardwareConfiguration) {
        try {
            return new ResponseEntity<>(hardwareConfigurationService.save(hardwareConfiguration)
                    , HttpStatus.OK);
        } catch (DataAccessException ex) {
            log.debug(ex.getMessage());
            return new ResponseEntity<>("Could not create the hardwareConfiguration.", HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("id/{id}")
    public ResponseEntity deleteHardwareConfiguration(@PathVariable Long id) {
        try {
            hardwareConfigurationService.deleteById(id);
            return new ResponseEntity<>(id, HttpStatus.OK);
        } catch (DataAccessException ex) {
            log.debug(ex.getMessage());
            return new ResponseEntity<>("No hardwareConfiguration with id = '" + id + "' could be found.", HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("id/{id}")
    public ResponseEntity updateHardwareConfiguration(@PathVariable Long id,
                                                      @RequestBody HardwareConfiguration hardwareConfiguration) {
        try {
            hardwareConfigurationService.save(hardwareConfiguration);
            return new ResponseEntity<>(hardwareConfiguration, HttpStatus.OK);
        } catch (DataAccessException ex) {
            log.debug(ex.getMessage());
            return new ResponseEntity<>("Could not update hardwareConfiguration with id = '" + id + "'.", HttpStatus.NOT_FOUND);
        }
    }
}
