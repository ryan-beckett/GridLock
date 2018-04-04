package com.rbeckett.gridlock.bootstrap;

import com.rbeckett.gridlock.model.user.Permission;
import com.rbeckett.gridlock.services.user.PermissionService;
import org.fluttercode.datafactory.impl.DataFactory;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class PermissionGenerator implements Generator<Permission> {

    private static final DataFactory dataFactory = new DataFactory();
    private final List<Permission> permissions = new ArrayList<>();
    private final PermissionService permissionService;

    public PermissionGenerator(final PermissionService permissionService) {
        this.permissionService = permissionService;
    }

    @Override
    public void generate(final int numResults, final Generator... generators) {
        //TODO
    }

    @Override
    public List<Permission> getResults() {
        return permissions;
    }
}
