package com.rbeckett.gridlock.bootstrap;

import com.rbeckett.gridlock.enums.AssetType;
import com.rbeckett.gridlock.enums.PortType;
import com.rbeckett.gridlock.model.asset.PatchPanel;
import com.rbeckett.gridlock.services.asset.PatchPanelService;
import com.rbeckett.gridlock.services.asset.RackService;
import lombok.extern.slf4j.Slf4j;
import org.fluttercode.datafactory.impl.DataFactory;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Component
public class PatchPanelGenerator extends RackableDeviceGenerator implements Generator<PatchPanel> {

    private static final DataFactory dataFactory = new DataFactory();
    private static final String[] PATCH_PANELS = {"C2G 03859 24-Port",
            "TrendNet TC-P16C5E 16-Port", "Monoprice 48-port Cat 6 Patch",
            "StarTech 240-Port MPO", "C2G 48-Port Cat 5", "StarTech LC-196"};
    private static final Integer[] PORTS = {16, 24, 48, 96, 298};
    private final List<PatchPanel> patchPanels = new ArrayList<>();
    private final PatchPanelService patchPanelService;
    private final RackService rackService;

    public PatchPanelGenerator(final PatchPanelService patchPanelService,
                               final RackService rackService) {
        this.patchPanelService = patchPanelService;
        this.rackService = rackService;
    }

    @Override
    public void generate(final int numResults, final Generator... generators) {
        for (int i = 0; i < numResults; i++) {
            final PatchPanel patchPanel = new PatchPanel();
            patchPanel.setType(AssetType.PATCH_PANEL);
            patchPanel.setModel(PATCH_PANELS[i % PATCH_PANELS.length]);
            patchPanel.setPortType(dataFactory.getItem(PortType.values()));
            patchPanel.setTotalNumberOfPorts(dataFactory.getItem(PORTS));
            generate(patchPanel, rackService, generators);
            patchPanels.add(patchPanelService.findByName(patchPanel.getName()));
        }
        log.info("Generated data for PatchPanel entity");
    }

    @Override
    public List<PatchPanel> getResults() {
        return patchPanels;
    }
}
