package com.rbeckett.gridlock.bootstrap;

import com.rbeckett.gridlock.model.configuration.NetworkConfiguration;
import com.rbeckett.gridlock.services.configuration.NetworkConfigurationService;
import lombok.extern.slf4j.Slf4j;
import org.fluttercode.datafactory.impl.DataFactory;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Component
public class NetworkConfigurationGenerator implements Generator<NetworkConfiguration> {

    private static final DataFactory dataFactory = new DataFactory();
    private final List<NetworkConfiguration> networkConfigurations = new ArrayList<>();
    private final NetworkConfigurationService networkConfigurationService;

    public NetworkConfigurationGenerator(
            final NetworkConfigurationService networkConfigurationService) {
        this.networkConfigurationService = networkConfigurationService;
    }

    @Override
    public void generate(final int numResults, final Generator... generators) {
        //TODO
        log.info("Generated data for NetworkConfiguration entity");
    }

    @Override
    public List<NetworkConfiguration> getResults() {
        return networkConfigurations;
    }
}
