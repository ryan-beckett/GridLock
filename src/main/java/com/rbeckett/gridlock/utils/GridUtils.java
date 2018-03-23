/*
 * Copyright 2018 Ryan M. Beckett
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */

package com.rbeckett.gridlock.utils;

import com.rbeckett.gridlock.model.network.GridLocation;

public class GridUtils {

    private static final int MAX_GRID_INDEX = 100;

    public static String gridXToUserString(GridLocation gridLocation) {
        int x = gridLocation.getX();
        if (x > MAX_GRID_INDEX)
            throw new IllegalArgumentException("Grid Index is too large.");
        char c1 = 'A', c2 = 'A';
        for (int i = 0; i < x; i++, c2++) {
            if (i % 26 == 0) {
                c1++;
                c2 = 'A' - 1;
            }
        }
        return c1 + c2 + "";
    }

    public static String gridYToUserString(GridLocation gridLocation) {
        int y = gridLocation.getY();
        if (y > MAX_GRID_INDEX)
            throw new IllegalArgumentException("Grid Index is too large.");
        return String.format("%02d", y);
    }

    public static int gridXUserStringToInt(String userString) {
        if (userString.length() > 2)
            throw new IllegalArgumentException("User string length should be 2.");
        userString = userString.toUpperCase();
        int index = 0;
        for (char c1 = userString.charAt(0), c2 = userString.charAt(1); c1 >= 'A'; c2--, index++) {
            if (c2 == 'A') {
                c1--;
                c2 = 'Z' + 1;
            }
        }
        return index;
    }

    public static int gridYUserStringToInt(String userString) {
        if (userString.length() > 2)
            throw new IllegalArgumentException("User string length should be 2.");
        return Integer.parseInt(userString);
    }
}
