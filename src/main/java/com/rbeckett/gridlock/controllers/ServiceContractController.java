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

import com.rbeckett.gridlock.model.business.ServiceContract;
import com.rbeckett.gridlock.services.ServiceContractService;
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
class ServiceContractController {

    private final ServiceContractService serviceContractService;

    private ServiceContractController(ServiceContractService serviceContractService) {
        this.serviceContractService = serviceContractService;
    }

    @GetMapping("/serviceContracts")
    public Set<ServiceContract> getServiceContracts() {
        try {
            return serviceContractService.findAll();
        } catch (DataAccessException ex) {
            log.debug(ex.getMessage());
            return null;
        }
    }

    @GetMapping("/serviceContracts/{id}")
    public ResponseEntity getServiceContractById(@PathVariable Long id) {
        try {
            return new ResponseEntity<>(serviceContractService.findById(id), HttpStatus.OK);
        } catch (DataAccessException ex) {
            log.debug(ex.getMessage());
            return new ResponseEntity<>("No serviceContract with id = '" + id + "' could be found.", HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping(value = "/serviceContracts")
    public ResponseEntity createServiceContract(@RequestBody ServiceContract serviceContract) {
        try {
            return new ResponseEntity<>(serviceContractService.save(serviceContract)
                    , HttpStatus.OK);
        } catch (DataAccessException ex) {
            log.debug(ex.getMessage());
            return new ResponseEntity<>("Could not create the serviceContract.", HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/serviceContracts/{id}")
    public ResponseEntity deleteServiceContract(@PathVariable Long id) {
        try {
            serviceContractService.deleteById(id);
            return new ResponseEntity<>(id, HttpStatus.OK);
        } catch (DataAccessException ex) {
            log.debug(ex.getMessage());
            return new ResponseEntity<>("No serviceContract with id = '" + id + "' could be found.", HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/serviceContracts/{id}")
    public ResponseEntity updateServiceContract(@PathVariable Long id, @RequestBody ServiceContract serviceContract) {
        try {
            serviceContractService.save(serviceContract);
            return new ResponseEntity<>(serviceContract, HttpStatus.OK);
        } catch (DataAccessException ex) {
            log.debug(ex.getMessage());
            return new ResponseEntity<>("Could not update serviceContract with id = '" + id + "'.", HttpStatus.NOT_FOUND);
        }
    }
}
