package com.rbeckett.gridlock.bootstrap;

import java.util.List;

public interface Generator<T> {
    void generate(int numResults, Generator... generators);

    List<T> getResults();
}
