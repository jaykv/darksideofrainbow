package com.darksideofrainbow.controllers;

import com.darksideofrainbow.models.Album;
import com.darksideofrainbow.models.ApplicationUser;
import com.darksideofrainbow.models.Genre;
import com.darksideofrainbow.service.AlbumService;
import com.darksideofrainbow.service.ApplicationUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import java.time.LocalDateTime;

@Controller
public class AdminController {

    private String alert = null;
    private String status = null;

    private final AlbumService albumService;
    private final ApplicationUserService applicationUserService;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public AdminController(AlbumService albumService, ApplicationUserService applicationUserService, PasswordEncoder passwordEncoder) {
        this.albumService = albumService;
        this.applicationUserService = applicationUserService;
        this.passwordEncoder = passwordEncoder;
    }

    private Model alertAttributes(Model model) {
        if(this.alert != null) {
            model.addAttribute("alert", this.alert);
            model.addAttribute("status", this.status);
            this.status = null;
            this.alert = null;
        }
        return model;
    }

    @RequestMapping(value = "/admin", method = RequestMethod.GET)
    public String mainAdminPage() {
        return "admin/admin";
    }

    @RequestMapping(value = "/admin/users", method = RequestMethod.GET)
    public String usersAdminPage(Model model) {
        model = alertAttributes(model);
        model.addAttribute("users", applicationUserService.findAll());
        return "admin/users";
    }

    @RequestMapping(value = {"/admin/albums", "/admin/albums/view"}, method = RequestMethod.GET)
    public String albumsAdminPage(Model model) {
        model = alertAttributes(model);
        model.addAttribute("albums", albumService.findAll());
        return "admin/albums";
    }

    @RequestMapping(value = "/admin/albums/add", method = RequestMethod.GET)
    public String addAlbumPage() {
        return "admin/addalbum";
    }

    @RequestMapping(value = "/admin/albums/add", method = RequestMethod.POST)
    public String submitAlbumForm(@RequestParam("albumTitle") String title, @RequestParam("albumArtist") String artist, @RequestParam("albumDateReleased") LocalDateTime dateReleased, @RequestParam("albumTracks") int tracks, @RequestParam("albumGenre") Genre genre, @RequestParam("albumPrice") Double price) {
        try {
            Album album = new Album(title, artist, dateReleased, genre, tracks, price);
            albumService.create(album);
            this.alert = "Album: " + title + " added to inventory!";
            this.status = "alert-success";
        } catch(Exception e) {
            this.alert = "Woops, something failed, try again later";
            this.status = "alert-danger";
        }
        return "redirect:/admin/albums";
    }

    @RequestMapping(value = "/admin/albums/edit/{id}", method = RequestMethod.GET)
    public String editAlbumForm(Model model, @PathVariable Long id) {

        Album album = albumService.find(id);
        if (album != null) {
            model.addAttribute("title", album.getTitle());
            model.addAttribute("artist", album.getArtist());
            model.addAttribute("price", album.getPrice());
            model.addAttribute("dateReleased", album.getDateReleased());
            model.addAttribute("tracks", album.getTracks());
            model.addAttribute("albumId", album.getAlbumId());
            return "admin/editalbum";
        } else {
            this.alert = "Sorry, that album does not exist!";
            this.status = "alert-danger";
            return "redirect:/admin/albums";
        }
    }

    @RequestMapping(value = "/admin/albums/edit", method = RequestMethod.POST)
    public String saveAlbumEdit(@RequestParam("albumId") Long id, @RequestParam("albumTitle") String title, @RequestParam("albumArtist") String artist, @RequestParam("albumDateReleased") String dateReleased, @RequestParam("albumTracks") int tracks, @RequestParam("albumGenre") Genre genre, @RequestParam("albumPrice") Double price) {
        try {
            albumService.edit(id, title, artist, LocalDateTime.parse(dateReleased), tracks, genre, price);
            this.alert = title + " edited successfully!";
            this.status = "alert-success";
        } catch (Exception e) {
            this.alert = "Woops, ran into an error, try again later";
            this.status = "alert-danger";
        }
        return "redirect:/admin/albums";
    }

    @RequestMapping("/admin/albums/delete/{id}")
    public String deleteAlbum(@PathVariable Long id) {
        this.alert = "Sorry, album could not be deleted";
        try {
            albumService.delete(id);
            this.alert = "Album " + id + " deleted!";
            this.status = "alert-success";
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return "redirect:/admin/albums";
    }


    /* Users */
    @RequestMapping(value = "/admin/users/add", method = RequestMethod.GET)
    public String addUserPage() {
        return "admin/adduser";
    }

    @RequestMapping(value = "/admin/users/add", method = RequestMethod.POST)
    public String submitUserForm(@RequestParam("username") String name, @RequestParam("userpass") String password, @RequestParam("isadmin") Boolean isAdmin) {
        try {
            ApplicationUser user = new ApplicationUser(name, passwordEncoder.encode(password), isAdmin);
            applicationUserService.create(user);
            this.alert = "Added " + name + " successfully!";
            this.status = "alert-success";
        } catch(Exception e) {
            System.out.println(e.getMessage());
            this.alert = "Sorry, user could not be added, try again";
            this.status = "alert-danger";
        }
        return "redirect:/admin/users";
    }

    @RequestMapping(value = "/admin/users/edit/{id}", method = RequestMethod.GET)
    public String editUserForm( Model model, @PathVariable Long id) {
        try {
            ApplicationUser user = applicationUserService.findOne(id);
            if (user != null) {
                model.addAttribute("username", user.getUsername());
                model.addAttribute("isadmin", user.getIsAdmin());
                return "admin/edituser";
            }
        }catch(Exception e) {
            System.out.println(e.getMessage());
        }
        this.alert = "Sorry, there is an error with getting the user information";
        this.status = "alert-danger";
        return "redirect:/admin/users";
    }

    @RequestMapping(value = "/admin/users/edit", method = RequestMethod.POST)
    public String saveUsereEdit(@RequestParam("userid") Long id, @RequestParam("username") String username, @RequestParam("isadmin") Boolean isAdmin) {
        try {
            applicationUserService.edit(id, username, isAdmin);
            this.alert = username + " has been updated!";
            this.status = "alert-success";
        } catch (Exception e) {
            System.out.println(e.getMessage());
            this.alert = "Sorry, user could not be edited";
            this.status = "alert-danger";
        }
        return "redirect:/admin/users";
    }

    @RequestMapping("/admin/users/delete/{id}")
    public String deleteUser(@PathVariable Long id) {
        try {
            applicationUserService.delete(id);
            this.alert = "User successfully deleted!";
            this.status = "alert-success";
        } catch(Exception e) {
            System.out.println(e.getMessage());
            this.alert = "User could not be deleted";
            this.status = "alert-danger";
        }
        return "redirect:/admin/users";
    }

}
