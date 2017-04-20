package com.darksideofrainbow.controllers;

import com.darksideofrainbow.models.ApplicationUser;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

@ControllerAdvice
public class GlobalController {

    @ModelAttribute("isUser")
    public Boolean isUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return !(auth == null || auth.getName().equals("anonymousUser"));
    }

    @ModelAttribute("isAdmin")
    public Boolean isAdmin() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth == null || auth.getName().equals("anonymousUser"))
            return false;

        /* TODO- Update to Use Granted Authorities */
        ApplicationUser principal = (ApplicationUser)auth.getPrincipal();
        return (principal.getIsAdmin());
    }

    @ModelAttribute("user")
    public Object getUser() {
        if(!isUser())
            return null;
        try {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            return auth.getPrincipal();
        } catch(Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }
}
