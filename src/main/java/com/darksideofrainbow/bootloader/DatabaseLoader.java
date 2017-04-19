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

    @Autowired
    private AlbumRepository albumRepository;

    @Autowired
    private ApplicationUserRepository applicationUserRepository;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {

        Album moreLife = new Album("More Life", "Drake", LocalDateTime.now(), Genre.HIP_HOP, 12, 12.99);
        albumRepository.create(moreLife);
        Album damn = new Album("DAMN.", "Kendrick Lamar", LocalDateTime.now(), Genre.HIP_HOP, 13, 10.99);
        albumRepository.create(damn);
        Album pablo = new Album("Life of Pablo", "Kanye West", LocalDateTime.now(), Genre.HIP_HOP, 12, 15.00);
        albumRepository.create(pablo);

        ApplicationUser jay = new ApplicationUser("jay", "123456", Boolean.TRUE);
        applicationUserRepository.createApplicationUser(jay);
        ApplicationUser harambe = new ApplicationUser("harambe", "cincinnati", Boolean.TRUE);
        applicationUserRepository.createApplicationUser(harambe);
        ApplicationUser shia = new ApplicationUser("shia", "flagfail1", Boolean.FALSE);
        applicationUserRepository.createApplicationUser(shia);
    }
}
