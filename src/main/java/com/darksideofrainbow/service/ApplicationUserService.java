package com.darksideofrainbow.service;

import com.darksideofrainbow.models.ApplicationUser;

public interface ApplicationUserService {
    void createApplicationUser(ApplicationUser user);
    ApplicationUser findByUserName(String username);
}
