package com.darksideofrainbow.bootloader;

import com.darksideofrainbow.models.Album;
import com.darksideofrainbow.models.ApplicationUser;
import com.darksideofrainbow.models.Genre;
import com.darksideofrainbow.repository.AlbumRepository;
import com.darksideofrainbow.repository.ApplicationUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class DatabaseLoader implements ApplicationListener<ContextRefreshedEvent> {
/*
    @Autowired
    private AlbumRepository albumRepository;

    @Autowired
    private ApplicationUserRepository applicationUserRepository;
*/
    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {

       /*
        Album moreLife = new Album(1L, "More Life", "Drake", LocalDateTime.now(), Genre.HIP_HOP, 12, 12.99);
        albumRepository.create(moreLife);
        Album damn = new Album(2L, "DAMN.", "Kendrick Lamar", LocalDateTime.now(), Genre.HIP_HOP, 17, 10.99);
        albumRepository.create(damn);


        ApplicationUser jay = new ApplicationUser(1, "jay", "123456", Boolean.TRUE);
        applicationUserRepository.createApplicationUser(jay);
        ApplicationUser harambe = new ApplicationUser(2, "harambe", "cincinnati", Boolean.TRUE);
        applicationUserRepository.createApplicationUser(harambe);
        ApplicationUser shia = new ApplicationUser(3, "shia", "flagfail1", Boolean.FALSE);
        applicationUserRepository.createApplicationUser(shia);
        */
    }
}
