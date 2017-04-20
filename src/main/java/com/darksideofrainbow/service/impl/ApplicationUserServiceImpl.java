package com.darksideofrainbow.service.impl;

import com.darksideofrainbow.models.ApplicationUser;
import com.darksideofrainbow.repository.ApplicationUserRepository;
import com.darksideofrainbow.service.ApplicationUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ApplicationUserServiceImpl implements ApplicationUserService {

    @Autowired
    private ApplicationUserRepository applicationUserRepository;

    @Override
    public Boolean create(ApplicationUser user) {
        applicationUserRepository.create(user);
        return true;
    }

    @Override
    public ApplicationUser findOne(Long id) {
        return applicationUserRepository.findOne(id);
    }

    @Override
    public ApplicationUser edit(Long id, String username, Boolean isAdmin) {
        return applicationUserRepository.edit(id, username, isAdmin);
    }

    @Override
    public List<ApplicationUser> findAll() {
        return applicationUserRepository.findAll();
    }

    @Override
    public ApplicationUser findByUserName(String username) {
        return applicationUserRepository.findByUserName(username);
    }

    @Override
    public Boolean delete(Long id) {
        return applicationUserRepository.delete(id);
    }
}
