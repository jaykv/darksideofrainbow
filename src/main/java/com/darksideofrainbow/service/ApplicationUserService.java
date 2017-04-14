package com.darksideofrainbow.service;

import com.darksideofrainbow.models.ApplicationUser;

/**
 * Created by Jay on 4/13/2017.
 */
public interface ApplicationUserService {
    void createApplicationUser(ApplicationUser user);
    ApplicationUser findByUsername(String username);
}
