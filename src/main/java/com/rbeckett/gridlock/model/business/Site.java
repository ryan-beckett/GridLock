package com.rbeckett.gridlock.model.business;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.context.annotation.Lazy;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@EqualsAndHashCode(of = {"id"})
@Data
@Lazy
@Entity
public class Site {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String name;

    @NotNull
    @OneToOne
    private Location location;
}
