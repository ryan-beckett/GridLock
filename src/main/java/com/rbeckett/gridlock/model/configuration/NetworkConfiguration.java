/*
 * Copyright 2018 Ryan M. Beckett
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */

package com.rbeckett.gridlock.model.configuration;

import com.rbeckett.gridlock.model.network.NetworkConnection;
import lombok.Data;
import org.springframework.context.annotation.Lazy;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;

@Data
@Lazy
@Entity
public class NetworkConfiguration extends Configuration {

    private String hostname;

    private String workGroup;

    @SuppressWarnings("JpaDataSourceORMInspection")
    @NotNull
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "networkConfiguration_networkConnection", joinColumns = @JoinColumn(name = "networkConfiguration_id"),
            inverseJoinColumns = @JoinColumn(name = "networkConnection_id"))
    private Set<NetworkConnection> networkConnections = new HashSet<>();
}
