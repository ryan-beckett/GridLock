package com.rbeckett.gridlock.bootstrap;

import com.rbeckett.gridlock.model.user.User;
import com.rbeckett.gridlock.model.user.UserProfile;
import com.rbeckett.gridlock.services.user.UserProfileService;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class UserProfileGenerator implements Generator<UserProfile> {

    private final List<UserProfile> userProfiles = new ArrayList<>();
    private final UserProfileService userProfileService;

    public UserProfileGenerator(final UserProfileService userProfileService) {
        this.userProfileService = userProfileService;
    }

    @Override
    public void generate(final int numResults, final Generator... generators) {
        UserProfile userProfile = new UserProfile();
        userProfile.setUser((User) generators[0].getResults().get(0));
        userProfiles.add(userProfileService.save(userProfile));

        userProfile = new UserProfile();
        userProfile.setUser((User) generators[0].getResults().get(1));
        userProfiles.add(userProfileService.save(userProfile));
    }

    @Override
    public List<UserProfile> getResults() {
        return userProfiles;
    }
}
