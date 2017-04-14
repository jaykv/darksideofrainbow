package com.darksideofrainbow.repository;

import com.darksideofrainbow.models.ApplicationUser;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;


@RepositoryRestResource(collectionResourceRel = "user", path="user")
public interface ApplicationUserRepository {
    void createApplicationUser(ApplicationUser applicationUser);
    ApplicationUser findByUserName(String username);
    //List<ApplicationUser> findByLastName(@Param("username") String username);
}
