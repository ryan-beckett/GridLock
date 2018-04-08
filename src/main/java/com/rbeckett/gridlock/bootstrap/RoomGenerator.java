package com.rbeckett.gridlock.bootstrap;

import com.rbeckett.gridlock.model.business.Room;
import com.rbeckett.gridlock.model.business.Site;
import com.rbeckett.gridlock.services.business.RoomService;
import lombok.extern.slf4j.Slf4j;
import org.fluttercode.datafactory.impl.DataFactory;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Component
public class RoomGenerator implements Generator<Room> {
    private static final DataFactory dataFactory = new DataFactory();
    private final List<Room> rooms = new ArrayList<>();
    private final RoomService roomService;

    public RoomGenerator(final RoomService roomService) {
        this.roomService = roomService;
    }

    @Override
    public void generate(final int numResults, final Generator... generators) {
        for (int i = 0; i < numResults; i++) {
            Room room = new Room();
            room.setName("Room " + (i + 1));
            room.setSite((Site) dataFactory.getItem(generators[0].getResults()));
            rooms.add(roomService.save(room));
        }
        log.info("Generated data for Room entity");
    }

    @Override
    public List<Room> getResults() {
        return rooms;
    }
}
