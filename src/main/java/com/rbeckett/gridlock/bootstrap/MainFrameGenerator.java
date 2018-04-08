package com.rbeckett.gridlock.bootstrap;

import com.rbeckett.gridlock.enums.AssetType;
import com.rbeckett.gridlock.model.asset.MainFrame;
import com.rbeckett.gridlock.services.asset.MainFrameService;
import lombok.extern.slf4j.Slf4j;
import org.fluttercode.datafactory.impl.DataFactory;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Slf4j
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
            mainFrame.setModel(MAIN_FRAMES[i % MAIN_FRAMES.length]);
            generate(mainFrame, generators);
            AppDataGenerator.RoomGridLocationPair pr = AppDataGenerator.getNextRandomRoomAndGridLocation();
            mainFrame.setRoom(pr.room);
            mainFrame.setGridLocation(pr.gridLocation);
            mainFrames.add(mainFrameService.save(mainFrame));
        }
        log.info("Generated data for MainFrame entity");
    }

    @Override
    public List<MainFrame> getResults() {
        return mainFrames;
    }
}