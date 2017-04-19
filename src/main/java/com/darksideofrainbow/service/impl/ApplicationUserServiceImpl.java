package com.darksideofrainbow.service.impl;

import com.darksideofrainbow.models.ApplicationUser;
import com.darksideofrainbow.repository.ApplicationUserRepository;
import com.darksideofrainbow.service.ApplicationUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ApplicationUserServiceImpl implements ApplicationUserService {
    @Autowired
    private ApplicationUserRepository applicationUserRepository;

    @Override
    public void createApplicationUser(ApplicationUser user) {
        applicationUserRepository.createApplicationUser(user);
    }

    @Override
    public ApplicationUser findByUserName(String username) {
        return applicationUserRepository.findByUserName(username);
    }
}
