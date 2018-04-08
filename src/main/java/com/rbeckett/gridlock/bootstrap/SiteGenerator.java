package com.rbeckett.gridlock.bootstrap;

import com.rbeckett.gridlock.model.business.Location;
import com.rbeckett.gridlock.model.business.Site;
import com.rbeckett.gridlock.services.business.SiteService;
import lombok.extern.slf4j.Slf4j;
import org.fluttercode.datafactory.impl.DataFactory;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Slf4j
@Component
public class SiteGenerator implements Generator<Site> {
    private static final DataFactory dataFactory = new DataFactory();
    private final List<Site> sites = new ArrayList<>();
    private final SiteService siteService;

    public SiteGenerator(final SiteService siteService) {
        this.siteService = siteService;
    }

    @Override
    public void generate(final int numResults, final Generator... generators) {
        final Random random = new Random();
        for (int i = 0; i < numResults; i++) {
            Site site = new Site();
            site.setName("Site " + (i + 1));
            site.setLocation((Location) dataFactory.getItem(generators[0].getResults()));
            sites.add(siteService.save(site));
        }
        log.info("Generated data for Site entity");
    }

    @Override
    public List<Site> getResults() {
        return sites;
    }
}
