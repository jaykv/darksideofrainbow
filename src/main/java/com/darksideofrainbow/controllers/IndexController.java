package com.darksideofrainbow.controllers;

import com.darksideofrainbow.models.Album;
import com.darksideofrainbow.models.ApplicationUser;
import com.darksideofrainbow.service.AlbumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class IndexController {
    private final AlbumService albumService;

    @Autowired
    public IndexController(AlbumService albumService) {
        this.albumService = albumService;
    }

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

    @RequestMapping(value = {"/search"}, method = RequestMethod.GET)
    public String searchPage(Model model) {
        return "search";
    }

    @RequestMapping(value = {"/search"}, method = RequestMethod.POST)
    public String searchPostPage(Model model, @RequestParam(value = "searchquery") String query) {
        List<Album> foundList = albumService.findByName(query);
        model.addAttribute("query", query);

        if(!foundList.isEmpty()) {
            model.addAttribute("totFound", foundList.size());
            model.addAttribute("isFound", true);
            model.addAttribute("foundAlbums", foundList);
        } else {
            model.addAttribute("isFound", false);
        }
        return "search";
    }
}
