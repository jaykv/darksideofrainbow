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
        try {
            return !(auth == null || auth.getName().equals("anonymousUser"));
        } catch(Exception e) {
            return false;
        }
    }

    @ModelAttribute("isAdmin")
    public Boolean isAdmin() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        try {
            if (auth == null || auth.getName().equals("anonymousUser"))
                return false;
        } catch(Exception e) {
            return false;
        }

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
