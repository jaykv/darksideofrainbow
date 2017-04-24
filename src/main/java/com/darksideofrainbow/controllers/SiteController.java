package com.darksideofrainbow.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class SiteController {

    @RequestMapping(value = {"/about", "/aboutus", "/about-us"})
    public String aboutPage() {
        return "about";
    }

    @RequestMapping(value = "/search")
    public String searchPage() {
        return "search";
    }

    @RequestMapping(value = {"/contact", "/contactus", "/contact-us"})
    public String contactPage() {
        return "contact";
    }

    @RequestMapping(value = "/login")
    public String loginPage() {
        return "login";
    }

    @RequestMapping(value = "/register")
    public String registerPage(){return "register";}

    @RequestMapping(value = "/callback")
    public String callbackPage() {
        return "callback";
    }
}
