package com.rbeckett.gridlock.bootstrap;

import com.rbeckett.gridlock.model.configuration.OSConfiguration;
import com.rbeckett.gridlock.services.configuration.OSConfigurationService;
import lombok.extern.slf4j.Slf4j;
import org.fluttercode.datafactory.impl.DataFactory;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Component
public class OSConfigurationGenerator implements Generator<OSConfiguration> {

    private static final DataFactory dataFactory = new DataFactory();
    private final List<OSConfiguration> osConfigurations = new ArrayList<>();
    private final OSConfigurationService osConfigurationService;

    public OSConfigurationGenerator(
            final OSConfigurationService osConfigurationService) {
        this.osConfigurationService = osConfigurationService;
    }

    @Override
    public void generate(final int numResults, final Generator... generators) {
        //TODO
        log.info("Generated data for OSConfiguration entity");
    }

    @Override
    public List<OSConfiguration> getResults() {
        return osConfigurations;
    }
}
