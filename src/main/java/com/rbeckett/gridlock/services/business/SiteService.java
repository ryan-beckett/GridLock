package com.rbeckett.gridlock.services.business;

import com.rbeckett.gridlock.model.business.Site;
import com.rbeckett.gridlock.services.BaseService;

import java.util.Set;

public interface SiteService extends BaseService<Site> {
    Set<Site> findAllByCountry(String country);
}
