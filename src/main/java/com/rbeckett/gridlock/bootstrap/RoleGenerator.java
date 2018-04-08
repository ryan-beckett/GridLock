package com.rbeckett.gridlock.bootstrap;

import com.rbeckett.gridlock.model.user.Role;
import com.rbeckett.gridlock.services.user.RoleService;
import lombok.extern.slf4j.Slf4j;
import org.fluttercode.datafactory.impl.DataFactory;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Slf4j
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
        //TODO
        log.info("Generated data for Role entity");
    }

    @Override
    public List<Role> getResults() {
        return roles;
    }
}
