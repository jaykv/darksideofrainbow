package com.darksideofrainbow.controllers;

import com.darksideofrainbow.models.ApplicationUser;
import com.darksideofrainbow.service.ApplicationUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;


@RestController
public class RestSiteController {

    @Autowired
    private ApplicationUserDetailsService applicationUserDetailsService;

    @RequestMapping("/user")
    public ApplicationUser user(ApplicationUser user) {
        return user;
    }

    @RequestMapping("/resource")
    public Map<String, Object> home() {
        Map<String,Object> model = new HashMap<>();
        model.put("id", UUID.randomUUID().toString());
        model.put("content", "Hello World");
        return model;
    }

    @RequestMapping(value="/rest/login", method= RequestMethod.POST, consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE )
    public @ResponseBody String login(ApplicationUser user) {
        System.out.println(user);
        try {
            applicationUserDetailsService.loadUserByUsername(user.getUsername());
        } catch(UsernameNotFoundException e) {
            System.out.println(e.getMessage());
            return e.getMessage();
        }
        System.out.println("Success");
        return "success";
    }

}
