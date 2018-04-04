package com.rbeckett.gridlock.bootstrap;

import com.rbeckett.gridlock.model.business.Location;
import com.rbeckett.gridlock.model.business.Room;
import com.rbeckett.gridlock.services.business.RoomService;
import org.fluttercode.datafactory.impl.DataFactory;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

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
        final Random random = new Random();
        for (int i = 0; i < numResults; i++) {
            Room room = new Room();
            room.setName("Room " + (random.nextInt(numResults) + 1));
            //noinspection unchecked,unchecked
            room.setLocation((Location) dataFactory.getItem(generators[0].getResults()));
            rooms.add(roomService.save(room));
        }
    }

    @Override
    public List<Room> getResults() {
        return rooms;
    }
}
