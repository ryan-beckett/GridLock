package com.rbeckett.gridlock.bootstrap;

import com.rbeckett.gridlock.model.configuration.HardwareConfiguration;
import com.rbeckett.gridlock.services.configuration.HardwareConfigurationService;
import lombok.extern.slf4j.Slf4j;
import org.fluttercode.datafactory.impl.DataFactory;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Component
public class HardwareConfigurationGenerator implements Generator<HardwareConfiguration> {

    private static final DataFactory dataFactory = new DataFactory();
    private final List<HardwareConfiguration> hardwareConfigurations = new ArrayList<>();
    private final HardwareConfigurationService hardwareConfigurationService;

    public HardwareConfigurationGenerator(
            final HardwareConfigurationService hardwareConfigurationService) {
        this.hardwareConfigurationService = hardwareConfigurationService;
    }

    @Override
    public void generate(final int numResults, final Generator... generators) {
        //TODO
        log.info("Generated data for HardwareConfiguration entity");
    }

    @Override
    public List<HardwareConfiguration> getResults() {
        return hardwareConfigurations;
    }
}
