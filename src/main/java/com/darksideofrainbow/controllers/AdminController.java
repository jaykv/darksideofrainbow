package com.darksideofrainbow.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AdminController {

    @RequestMapping(value = "/admin/albums/view")
    public String viewAlbum() {
        return "viewalbum";
    }

    @RequestMapping(value = "/admin/albums/add")
    public String addAlbum() {
        return "addalbum";
    }

    @RequestMapping(value = "/admin/albums/edit")
    public String editAlbum() {
        return "editalbum";
    }

    @RequestMapping(value = "/admin/albums/delete")
    public String deleteAlbum() {
        return "deletealbum";
    }

}
