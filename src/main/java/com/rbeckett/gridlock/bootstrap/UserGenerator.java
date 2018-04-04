package com.rbeckett.gridlock.bootstrap;

import com.rbeckett.gridlock.model.business.BusinessUnit;
import com.rbeckett.gridlock.model.business.Contact;
import com.rbeckett.gridlock.model.user.User;
import com.rbeckett.gridlock.services.user.UserService;
import org.fluttercode.datafactory.impl.DataFactory;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Component
public class UserGenerator implements Generator<User> {

    private static final DataFactory dataFactory = new DataFactory();
    private final List<User> users = new ArrayList<>();
    private final UserService userService;

    public UserGenerator(final UserService userService) {
        this.userService = userService;
    }

    @Override
    public void generate(int numResults, Generator... generators) {
        User user = new User();
        user.setDeleted(false);
        user.setUsername("admin");
        user.setPassword("admin");
        user.setUserCreatedDateTime(LocalDateTime.now());
        user.setLastLoginDateTime(LocalDateTime.now());
        user.setContact((Contact) dataFactory.getItem(generators[0].getResults()));
        user.setBusinessUnit((BusinessUnit) dataFactory.getItem(generators[1].getResults()));
        users.add(userService.save(user));

        user = new User();
        user.setDeleted(false);
        user.setUsername("user");
        user.setPassword("user");
        user.setUserCreatedDateTime(LocalDateTime.now());
        user.setLastLoginDateTime(LocalDateTime.now());
        user.setContact((Contact) dataFactory.getItem(generators[0].getResults()));
        user.setBusinessUnit((BusinessUnit) dataFactory.getItem(generators[1].getResults()));
        users.add(userService.save(user));
    }

    @Override
    public List<User> getResults() {
        return users;
    }
}
