package com.rbeckett.gridlock.bootstrap;

import com.rbeckett.gridlock.model.user.Role;
import com.rbeckett.gridlock.services.user.RoleService;
import org.fluttercode.datafactory.impl.DataFactory;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class RoleGenerator implements Generator<Role> {

    private static final DataFactory dataFactory = new DataFactory();
    private final List<Role> roles = new ArrayList<>();
    private final RoleService roleService;

    public RoleGenerator(final RoleService roleService) {
        this.roleService = roleService;
    }

    @Override
    public void generate(final int numResults, final Generator... generators) {

    }

    @Override
    public List<Role> getResults() {
        return roles;
    }
}
