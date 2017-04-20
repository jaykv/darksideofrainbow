package com.darksideofrainbow.repository.impl;

import com.darksideofrainbow.jpa.ApplicationUserCrudRepository;
import com.darksideofrainbow.models.ApplicationUser;
import com.darksideofrainbow.repository.ApplicationUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class ApplicationUserRepositoryImpl implements ApplicationUserRepository {

    @Autowired
    private ApplicationUserCrudRepository applicationUserCrudRepository;

    @Override
    public Boolean create(ApplicationUser user) {
        applicationUserCrudRepository.save(user);
        return true;
    }

    @Override
    public ApplicationUser findOne(Long id) {
        return applicationUserCrudRepository.findOne(id);
    }

    @Override
    public ApplicationUser edit(Long id, String username, Boolean isAdmin) {
        ApplicationUser user = applicationUserCrudRepository.findOne(id);
        if (user != null) {
            user.setUsername(username);
            user.setIsAdmin(isAdmin);
            return applicationUserCrudRepository.save(user);
        }
        return user;
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

    @Override
    public Boolean delete(Long id) {
        applicationUserCrudRepository.delete(id);
        return true;
    }

    @Override
    public List<ApplicationUser> findAll() {
        List<ApplicationUser> userList = new ArrayList<>();
        applicationUserCrudRepository.findAll().forEach(userList::add);
        return userList;
    }
}
