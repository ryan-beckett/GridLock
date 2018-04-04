package com.rbeckett.gridlock.bootstrap;

import com.rbeckett.gridlock.model.user.User;
import com.rbeckett.gridlock.services.user.UserService;
import org.fluttercode.datafactory.impl.DataFactory;
import org.springframework.stereotype.Component;

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
        for (int i = 0; i < numResults; i++) {
            User user = new User();
            //user.set
        }
    }

    @Override
    public List<User> getResults() {
        return users;
    }
}
