/*
 * Copyright 2018 Ryan M. Beckett
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */

package com.rbeckett.gridlock.services.network;

import com.rbeckett.gridlock.model.network.NetworkConnection;
import com.rbeckett.gridlock.repositories.network.NetworkConnectionRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Slf4j
@Service
public class NetworkConnectionServiceImpl implements NetworkConnectionService {

    private final NetworkConnectionRepository networkConnectionRepository;

    private NetworkConnectionServiceImpl(NetworkConnectionRepository networkConnectionRepository) {
        this.networkConnectionRepository = networkConnectionRepository;
    }

    @Override
    public Set<NetworkConnection> findAll() {
        Set<NetworkConnection> networkConnections = new HashSet<>();
        networkConnectionRepository.findAll().forEach(networkConnections::add);
        return networkConnections;
    }

    @Override
    public NetworkConnection findById(Long id) {
        Optional<NetworkConnection> networkConnectionOptional = networkConnectionRepository.findById(id);
        return networkConnectionOptional.orElse(null);
    }

    @Override
    public NetworkConnection save(NetworkConnection networkConnection) {
        return networkConnectionRepository.save(networkConnection);
    }

    @Override
    public long count() {
        return networkConnectionRepository.count();
    }

    @Override
    public void delete(NetworkConnection networkConnection) {
        networkConnectionRepository.delete(networkConnection);
    }

    @Override
    public void deleteById(Long id) {
        networkConnectionRepository.deleteById(id);
    }
}
