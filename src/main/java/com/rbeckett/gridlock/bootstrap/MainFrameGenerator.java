package com.rbeckett.gridlock.bootstrap;

import com.rbeckett.gridlock.enums.AssetType;
import com.rbeckett.gridlock.model.asset.MainFrame;
import com.rbeckett.gridlock.model.network.GridLocation;
import com.rbeckett.gridlock.services.asset.MainFrameService;
import org.fluttercode.datafactory.impl.DataFactory;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class MainFrameGenerator extends AssetGenerator implements Generator<MainFrame> {

    private static final DataFactory dataFactory = new DataFactory();
    private static final String[] MAIN_FRAMES = {"IBM Z9 MainFrame", "IBM Z10 MainFrame",
            "DEC VAX", "Siemens 24M-Series", "Hitachi SAN V10 Tape Series"};
    private final List<MainFrame> mainFrames = new ArrayList<>();
    private final MainFrameService mainFrameService;

    public MainFrameGenerator(final MainFrameService mainFrameService) {
        this.mainFrameService = mainFrameService;
    }

    @Override
    public void generate(final int numResults, final Generator... generators) {
        for (int i = 0; i < numResults; i++) {
            final MainFrame mainFrame = new MainFrame();
            mainFrame.setType(AssetType.MAIN_FRAME);
            generate(mainFrame, generators);
            mainFrame.setModel(dataFactory.getItem(MAIN_FRAMES));
            mainFrame.setGridLocation((GridLocation) dataFactory.getItem(generators[5].getResults()));
            mainFrames.add(mainFrameService.save(mainFrame));
        }
    }

    @Override
    public List<MainFrame> getResults() {
        return mainFrames;
    }
}
