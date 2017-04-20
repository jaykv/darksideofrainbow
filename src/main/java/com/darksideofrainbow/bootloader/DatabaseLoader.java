package com.darksideofrainbow.bootloader;

import com.darksideofrainbow.models.Album;
import com.darksideofrainbow.models.ApplicationUser;
import com.darksideofrainbow.models.Genre;
import com.darksideofrainbow.repository.ApplicationUserRepository;
import com.darksideofrainbow.repository.AlbumRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import java.time.LocalDateTime;

@Component
public class DatabaseLoader implements ApplicationListener<ContextRefreshedEvent> {

    @Autowired
    private AlbumRepository albumRepository;

    @Autowired
    private ApplicationUserRepository applicationUserRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    private void addAlbum(String title, String artist, LocalDateTime dateReleased, Genre genre, int tracks, double price) {
        Album album = new Album(title, artist, dateReleased, genre, tracks, price);
        albumRepository.create(album);
    }

    private LocalDateTime getDate(int year, int month, int dayOfMonth) {
        return LocalDateTime.of(year, month, dayOfMonth, 0, 0);
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        addAlbum("More Life", "Drake", getDate(2017, 3, 18), Genre.HIP_HOP, 12, 12.99);
        addAlbum("DAMN.", "Kendrick Lamar", getDate(2017, 4, 14), Genre.HIP_HOP, 13, 10.99);
        addAlbum("Life of Pablo", "Kanye West", getDate(2016, 2, 14), Genre.HIP_HOP, 12, 15.00);
        addAlbum("Dark Side of the Moon", "Pink Floyd", getDate(1973, 3, 1), Genre.ROCK, 9, 10.00);
        addAlbum("Starboy", "The Weeknd", getDate(2016, 9, 25), Genre.OTHER, 18, 14.00);
        addAlbum("Guardians of the Galaxy: Awesome Mix Vol. 1", "Various Artists", getDate(2014, 7, 29), Genre.OTHER, 12, 10.00);
        addAlbum("Twenty One Pilots", "Twenty One Pilots", getDate(2009, 12, 29), Genre.POP, 14, 10.00);
        addAlbum("In Return", "Odesza", getDate(2014, 9, 8), Genre.ELECTRONIC, 13, 10.00);
        addAlbum("American Idiot", "Green Day", getDate(2004, 9, 8), Genre.ROCK, 9, 10.00);
        addAlbum("Fallen", "Evanescence", getDate(2003, 1, 1), Genre.ROCK, 12, 10.00);
        addAlbum("Hybrid Theory", "Linking Park", getDate(2000, 10, 24), Genre.ROCK, 12, 11.00);
        addAlbum("The Best of Nickelback Vol. 1", "Nickelback", getDate(2013, 11, 4), Genre.ROCK, 19, 15.00);
        addAlbum("Queen: Platinum Collection", "Queen", getDate(2000, 11, 13), Genre.ROCK, 51, 20.00);
        addAlbum("Search For Everything", "John Mayer", getDate(2017, 4, 14), Genre.ROCK, 12, 12.00);
        addAlbum("24K Magic", "Bruno Mars", getDate(2016, 11, 18), Genre.POP, 9, 10.00);

        ApplicationUser admin = new ApplicationUser("admin", passwordEncoder.encode("123456"), Boolean.TRUE);
        applicationUserRepository.create(admin);
        ApplicationUser admin2 = new ApplicationUser("manager", passwordEncoder.encode("password"), Boolean.TRUE);
        applicationUserRepository.create(admin2);
        ApplicationUser user1 = new ApplicationUser("john", passwordEncoder.encode("user"), Boolean.FALSE);
        applicationUserRepository.create(user1);
        ApplicationUser user2 = new ApplicationUser("bobby", passwordEncoder.encode("user"), Boolean.FALSE);
        applicationUserRepository.create(user2);
    }
}
