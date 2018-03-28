/*
 * Copyright 2018 Ryan M. Beckett
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */

package com.rbeckett.gridlock.services.asset;

import com.rbeckett.gridlock.model.asset.MainFrame;
import com.rbeckett.gridlock.repositories.asset.MainFrameRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Slf4j
@Service
public class MainFrameServiceImpl implements MainFrameService {

    private final MainFrameRepository mainFrameRepository;

    private MainFrameServiceImpl(MainFrameRepository mainFrameRepository) {
        this.mainFrameRepository = mainFrameRepository;
    }

    @Override
    public Set<MainFrame> findAll() {
        Set<MainFrame> mainFrames = new HashSet<>();
        mainFrameRepository.findAll().forEach(mainFrames::add);
        return mainFrames;
    }

    @Override
    public MainFrame findById(Long id) {
        Optional<MainFrame> mainFrameOptional = mainFrameRepository.findById(id);
        return mainFrameOptional.orElse(null);
    }

    @Override
    public MainFrame save(MainFrame mainFrame) {
        return mainFrameRepository.save(mainFrame);
    }

    @Override
    public long count() {
        return mainFrameRepository.count();
    }

    @Override
    public void delete(MainFrame mainFrame) {
        mainFrameRepository.delete(mainFrame);
    }

    @Override
    public void deleteById(Long id) {
        mainFrameRepository.deleteById(id);
    }
}

