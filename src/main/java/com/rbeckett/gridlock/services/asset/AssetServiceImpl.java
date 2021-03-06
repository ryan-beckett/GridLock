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

import com.querydsl.jpa.impl.JPAQuery;
import com.rbeckett.gridlock.enums.AssetStatus;
import com.rbeckett.gridlock.enums.AssetType;
import com.rbeckett.gridlock.model.asset.Asset;
import com.rbeckett.gridlock.model.asset.QAsset;
import com.rbeckett.gridlock.repositories.asset.AssetRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.HashSet;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

@SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
@Slf4j
@Lazy
@Service
public class AssetServiceImpl implements AssetService {

    @PersistenceContext
    private EntityManager entityManager;
    private final AssetRepository assetRepository;

    private AssetServiceImpl(AssetRepository assetRepository, EntityManager entityManager) {
        this.assetRepository = assetRepository;
        this.entityManager = entityManager;
    }

    @Override
    public Set<Asset> findAll() {
        Set<Asset> assets = new HashSet<>();
        assetRepository.findAll().forEach(assets::add);
        return assets;
    }

    @Override
    public Asset findById(Long id) {
        Optional<Asset> assetOptional = assetRepository.findById(id);
        return assetOptional.orElse(null);
    }

    @Override
    public Asset save(Asset asset) {
        return assetRepository.save(asset);
    }

    @Override
    public long count() {
        return assetRepository.count();
    }

    @Override
    public void delete(Asset asset) {
        assetRepository.delete(asset);
    }

    @Override
    public void deleteById(Long id) {
        assetRepository.deleteById(id);
    }

    @Override
    public Set<Asset> findByNameIgnoreCaseContainingByOrderByIdAsc(String name) {
        return new HashSet<>(assetRepository.findByNameIgnoreCaseContainingOrderByIdAsc(name));
    }

    @Override
    public Set<Asset> findAllByOrderByIdAsc() {
        return new HashSet<>(assetRepository.findAllByOrderByIdAsc());
    }

    @Override
    public Set<Asset> findAllByQueryParams(Map<String, String[]> parameterMap) {
        if (isQueryParamsEmpty(parameterMap))
            return new HashSet<>();
        JPAQuery<Asset> query = new JPAQuery<>(entityManager);
        QAsset asset = QAsset.asset;
        query = query.from(asset);
        String id = parameterMap.get("id")[0];
        if (!id.isEmpty()) {
            try {
                query = query.where(asset.id.eq(Long.parseLong(id)));
            } catch (NumberFormatException e) {
                log.info("Asset API request has unparsable asset ID: " + id);
                return new HashSet<>();
            }
        }
        String name = parameterMap.get("name")[0];
        if (!name.isEmpty())
            query = query.where(asset.name.containsIgnoreCase(name));
        String serial = parameterMap.get("serial")[0];
        if (!serial.isEmpty())
            query = query.where(asset.serial.containsIgnoreCase(serial));
        String type = parameterMap.get("type")[0];
        if (!type.isEmpty())
            query = query.where(asset.type.eq(AssetType.valueOf(type)));
        String status = parameterMap.get("status")[0];
        if (!status.isEmpty())
            query = query.where(asset.status.eq(AssetStatus.valueOf(status)));
        String manufacturer = parameterMap.get("manufacturer")[0];
        if (!manufacturer.isEmpty())
            query = query.where(asset.manufacturer.name.containsIgnoreCase(manufacturer));
        String model = parameterMap.get("model")[0];
        if (!model.isEmpty())
            query = query.where(asset.model.containsIgnoreCase(model));
        String partNumber = parameterMap.get("partNumber")[0];
        if (!partNumber.isEmpty())
            query = query.where(asset.partNumber.containsIgnoreCase(partNumber));
        String description = parameterMap.get("description")[0];
        if (!description.isEmpty())
            query = query.where(asset.description.containsIgnoreCase(description));
        String location = parameterMap.get("location")[0];
        if (!location.isEmpty())
            query = query.where(asset.room.site.location.name.containsIgnoreCase(location));
        String room = parameterMap.get("room")[0];
        if (!room.isEmpty())
            query = query.where(asset.room.name.containsIgnoreCase(room));
        String owner = parameterMap.get("owner")[0];
        if (!owner.isEmpty())
            query = query.where(asset.owner.name.containsIgnoreCase(owner));
        Set<Asset> assets = new HashSet<>(query.fetch());
        final StringBuilder sb = new StringBuilder();
        parameterMap.forEach((k, v) -> sb.append(k + " => '" + v[0] + "', "));
        log.info("Asset API query request w/ params: " + sb.toString() + "' returned " + assets.size() + " assets");
        return assets;
    }

    private boolean isQueryParamsEmpty(final Map<String, String[]> parameterMap) {
        for (Map.Entry<String, String[]> e : parameterMap.entrySet())
            if (e.getValue()[0].length() > 0)
                return false;
        return true;
    }

}
