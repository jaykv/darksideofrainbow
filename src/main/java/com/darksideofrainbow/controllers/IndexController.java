package com.darksideofrainbow.controllers;

import com.darksideofrainbow.models.ApplicationUser;
import com.darksideofrainbow.service.AlbumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class IndexController {
    @Autowired
    private AlbumService albumService;

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public String searchForm(@RequestParam(value = "albumName") String albumName, Model model) {
        model.addAttribute("album", albumService.findByName(albumName));
        return "index";
    }

    @RequestMapping(value = {"", "/", "/index"}, method = RequestMethod.GET)
    public String homePage(Model model, ApplicationUser user) {
        model.addAttribute("albums", albumService.findAll());
        return "index";
    }
}
