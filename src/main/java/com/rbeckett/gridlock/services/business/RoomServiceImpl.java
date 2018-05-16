/*
 * Copyright 2018 Ryan M. Beckett
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */

package com.rbeckett.gridlock.services.business;

import com.rbeckett.gridlock.model.business.Room;
import com.rbeckett.gridlock.repositories.business.RoomRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;


@Slf4j
@Lazy
@Service
public class RoomServiceImpl implements RoomService {

    private final RoomRepository roomRepository;

    private RoomServiceImpl(RoomRepository roomRepository) {
        this.roomRepository = roomRepository;
    }

    @Override
    public Set<Room> findAll() {
        Set<Room> rooms = new HashSet<>();
        roomRepository.findAll().forEach(rooms::add);
        return rooms;
    }

    @Override
    public Room findById(Long id) {
        Optional<Room> roomOptional = roomRepository.findById(id);
        return roomOptional.orElse(null);
    }

    @Override
    public Set<Room> findAllBySite(String site) {
        Set<Room> rooms = new HashSet<>();
        roomRepository.findAllBySite(site).forEach(rooms::add);
        return rooms;
    }

    @Override
    public Room save(Room room) {
        return roomRepository.save(room);
    }

    @Override
    public long count() {
        return roomRepository.count();
    }

    @Override
    public void delete(Room room) {
        roomRepository.delete(room);
    }

    @Override
    public void deleteById(Long id) {
        roomRepository.deleteById(id);
    }
}
