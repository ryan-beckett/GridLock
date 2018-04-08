package com.rbeckett.gridlock.bootstrap;

import com.rbeckett.gridlock.model.network.NetworkConnection;
import com.rbeckett.gridlock.services.network.NetworkConnectionService;
import lombok.extern.slf4j.Slf4j;
import org.fluttercode.datafactory.impl.DataFactory;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Component
public class NetworkConnectionGenerator implements Generator<NetworkConnection> {

    private final List<NetworkConnection> networkConnections = new ArrayList<>();
    private static final DataFactory dataFactory = new DataFactory();
    private final NetworkConnectionService networkConnectionService;

    public NetworkConnectionGenerator(
            final NetworkConnectionService networkConnectionService) {
        this.networkConnectionService = networkConnectionService;
    }

    @Override
    public void generate(final int numResults, final Generator... generators) {
        //TODO
        log.info("Generated data for NetworkConnection entity");
    }

    @Override
    public List<NetworkConnection> getResults() {
        return networkConnections;
    }
}
