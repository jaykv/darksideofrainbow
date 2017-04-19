package com.darksideofrainbow.repository;

import com.darksideofrainbow.models.ApplicationUser;

public interface ApplicationUserRepository {
    void createApplicationUser(ApplicationUser applicationUser);
    ApplicationUser findByUserName(String username);
}
