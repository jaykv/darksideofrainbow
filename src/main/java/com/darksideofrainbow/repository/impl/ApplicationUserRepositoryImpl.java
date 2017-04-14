package com.darksideofrainbow.repository.impl;

import com.darksideofrainbow.jpa.ApplicationUserCrudRepository;
import com.darksideofrainbow.models.ApplicationUser;
import com.darksideofrainbow.repository.ApplicationUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ApplicationUserRepositoryImpl implements ApplicationUserRepository {

    @Autowired
    private ApplicationUserCrudRepository applicationUserCrudRepository;

    @Override
    public void createApplicationUser(ApplicationUser user) {
        applicationUserCrudRepository.save(user);
    }

    @Override
    public ApplicationUser findByUserName(String username) {
        List<ApplicationUser> userList = applicationUserCrudRepository.findByUsernameAllIgnoreCase(username);
        if (userList != null && userList.size() > 0) {
            return userList.get(0);
        } else {
            return null;
        }
    }
}
