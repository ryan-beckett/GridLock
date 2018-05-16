package com.rbeckett.gridlock.repositories.business;

import com.rbeckett.gridlock.model.business.Site;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Set;

public interface SiteRepository extends CrudRepository<Site, Long> {
    @Query(value = "select s from Site s where lower(s.location.country) = lower(:country)")
    Set<Site> findAllByCountry(@Param("country") String country);
}
