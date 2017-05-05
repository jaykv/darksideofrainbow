package com.darksideofrainbow.repository;

import com.darksideofrainbow.models.ApplicationUser;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource(exported = false)
public interface ApplicationUserRepository {
    Boolean create(ApplicationUser user);
    ApplicationUser findOne(Long id);
    ApplicationUser edit(Long id, String username, Boolean isAdmin);
    List<ApplicationUser> findAll();
    ApplicationUser findByUserName(String username);
    Boolean delete(Long id);
}
