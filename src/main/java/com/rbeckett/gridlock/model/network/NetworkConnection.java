/*
 * Copyright 2018 Ryan M. Beckett
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */

package com.rbeckett.gridlock.model.network;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.rbeckett.gridlock.enums.ConnectionType;
import com.rbeckett.gridlock.model.configuration.NetworkConfiguration;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.context.annotation.Lazy;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;

@EqualsAndHashCode(of = {"id"})
@Data
@Lazy
@Entity
public class NetworkConnection {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(value = EnumType.STRING)
    private ConnectionType type;

    @Lob
    private String notes;

    private String ipAddress;

    private String macAddress;

    private boolean backup;

    private double speed;

    @NotNull
    @OneToOne
    private NetworkConfiguration sourceNetworkConfiguration;

    @NotNull
    @OneToOne
    private NetworkConfiguration targetNetworkConfiguration;

    @NotNull
    @OneToOne
    private Port sourcePort;

    @NotNull
    @OneToOne
    private Port targetPort;

    @JsonManagedReference
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "networkConnection", fetch = FetchType.LAZY)
    private Set<Hop> hops = new HashSet<>();
}
