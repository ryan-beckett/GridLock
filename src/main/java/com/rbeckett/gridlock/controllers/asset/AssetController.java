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

import com.rbeckett.gridlock.model.asset.Asset;
import com.rbeckett.gridlock.services.asset.AssetService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@Slf4j
@RestController
@RequestMapping({"/api/assets/"})
class AssetController {

    private final AssetService assetService;

    private AssetController(AssetService assetService) {
        this.assetService = assetService;
    }

    @GetMapping("")
    public Set<Asset> getAssets() {
        try {
            return assetService.findAllByOrderByIdAsc();
        } catch (DataAccessException ex) {
            log.debug(ex.getMessage());
            return null;
        }
    }

    @GetMapping("id/{id}")
    public ResponseEntity getAssetById(@PathVariable Long id) {
        try {
            return new ResponseEntity<>(assetService.findById(id), HttpStatus.OK);
        } catch (DataAccessException ex) {
            log.debug(ex.getMessage());
            return new ResponseEntity<>("No asset with id = '" + id + "' could be found.", HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("name/{name}")
    public Set<Asset> getAssetsByName(@PathVariable String name) {
        try {
            return assetService.findByNameIgnoreCaseContainingByOrderByIdAsc(name);
        } catch (DataAccessException ex) {
            log.debug(ex.getMessage());
            return null;
        }
    }

    @PostMapping(value = "")
    public ResponseEntity createAsset(@RequestBody Asset asset) {
        try {
            return new ResponseEntity<>(assetService.save(asset)
                    , HttpStatus.OK);
        } catch (DataAccessException ex) {
            log.debug(ex.getMessage());
            return new ResponseEntity<>("Could not create the asset.", HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("id/{id}")
    public ResponseEntity deleteAsset(@PathVariable Long id) {
        try {
            assetService.deleteById(id);
            return new ResponseEntity<>(id, HttpStatus.OK);
        } catch (DataAccessException ex) {
            log.debug(ex.getMessage());
            return new ResponseEntity<>("No asset with id = '" + id + "' could be found.", HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("id/{id}")
    public ResponseEntity updateAsset(@PathVariable Long id, @RequestBody Asset asset) {
        try {
            assetService.save(asset);
            return new ResponseEntity<>(asset, HttpStatus.OK);
        } catch (DataAccessException ex) {
            log.debug(ex.getMessage());
            return new ResponseEntity<>("Could not update asset with id = '" + id + "'.", HttpStatus.NOT_FOUND);
        }
    }
}
