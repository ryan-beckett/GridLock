/*
 * Copyright 2018 Ryan M. Beckett
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */

package com.rbeckett.gridlock.model.asset;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.rbeckett.gridlock.model.network.GridLocation;
import lombok.Data;
import org.springframework.context.annotation.Lazy;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;

@Data
@Lazy
@Entity
public class Rack extends Asset implements GridAsset {

    @NotNull
    private int uHeight;

    @JsonManagedReference
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "rack", fetch = FetchType.LAZY)
    private Set<RackableDevice> devices = new HashSet<>();

    @OneToOne
    private GridLocation gridLocation;

    @Override
    public GridLocation getGridLocation() {
        return gridLocation;
    }

    public int totalUSpaceLeft() {
        int uSpaceLeft = uHeight;
        for (RackableDevice rackableDevice : devices)
            uSpaceLeft -= rackableDevice.getUHeight();
        return uSpaceLeft;
    }

    public int nextOpenULocation() {
        return nextOpenULocation(1);
    }

    public int nextOpenULocation(int startULocation) {
        if (startULocation < 1)
            throw new IllegalArgumentException("startULocation < 1");
        for (int i = startULocation; i < uHeight; i++) {
            boolean found = false;
            for (RackableDevice rackableDevice : devices) {
                if (rackableDevice.getULocation() == i) {
                    found = true;
                    break;
                }
            }
            if (!found)
                return i;
        }
        return -1;
    }

    public int openSpaceAtULocation(final int uLocation) {
        if (uLocation < 1)
            throw new IllegalArgumentException("uLocation < 1");
        int openSpace = 0;
        for (int i = uLocation; i < uHeight; i++) {
            for (RackableDevice rackableDevice : devices)
                if (rackableDevice.getULocation() == i)
                    return openSpace;
            openSpace++;
        }
        return openSpace;
    }
}
