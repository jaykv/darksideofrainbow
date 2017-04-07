package com.darksideofrainbow.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class SiteController {

    @RequestMapping(value = "/about")
    public String aboutPage() {
        return "about";
    }

    @RequestMapping(value = "/search")
    public String searchPage() {
        return "search";
    }

    @RequestMapping(value = "/contact-us")
    public String contactPage() {
        return "contact";
    }

    @RequestMapping(value = "/login")
    public String loginPage() {
        return "login";
    }

}
