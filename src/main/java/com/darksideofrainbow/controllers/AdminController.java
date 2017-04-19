package com.darksideofrainbow.controllers;

import com.darksideofrainbow.models.Album;
import com.darksideofrainbow.models.Genre;
import com.darksideofrainbow.service.AlbumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDateTime;

@Controller
public class AdminController {

    @Autowired
    private AlbumService albumService;

    @RequestMapping(value = "/admin/albums/add", method = RequestMethod.GET)
    public String addGamePage() {
        return "addalbum";
    }

    @RequestMapping(value = "/admin/albums/add", method = RequestMethod.POST)
    public String submitGameForm(@RequestParam("albumTitle") String title
            , @RequestParam("albumArtist") String artist, @RequestParam("albumDateReleased") String dateReleased, @RequestParam("albumTracks") String tracks, @RequestParam("albumGenre") String genre, @RequestParam("albumPrice") String price) {
        Album album = new Album();
        album.setTitle(title);
        album.setArtist(artist);
        album.setDateReleased(LocalDateTime.parse(dateReleased));
        album.setTracks(Integer.parseInt(tracks));
        album.setGenre(Genre.valueOf(genre));
        album.setPrice(Double.parseDouble(price));
        albumService.create(album);
        return "redirect:/";
    }

    @RequestMapping(value = "/admin/albums/edit/{id}", method = RequestMethod.GET)
    public String editGameForm(@PathVariable Long id, Model model) {

        Album album = albumService.find(id);
        if (album != null) {
            model.addAttribute("title", album.getTitle());
            model.addAttribute("artist", album.getArtist());
            model.addAttribute("price", album.getPrice());
            model.addAttribute("dateReleased", album.getDateReleased());
            model.addAttribute("tracks", album.getTracks());
            model.addAttribute("albumId", album.getAlbumId());
            return "editalbum";
        } else {
            return "index";
        }
    }

    @RequestMapping(value = "/admin/albums/edit", method = RequestMethod.POST)
    public String saveAlbumEdit(@RequestParam(value="albumId") String id, @RequestParam(value = "albumTitle") String title, @RequestParam(value = "albumPrice") Double price) {

        albumService.edit(Long.parseLong(id), title, price);
        return "redirect:/";
    }

    @RequestMapping("/admin/albums/delete/{id}")
    public String deleteGame(@PathVariable Long id) {
        albumService.delete(id);
        return "redirect:/";
    }

}
