package com.darksideofrainbow.service.impl;

import com.darksideofrainbow.models.Album;
import com.darksideofrainbow.models.Genre;
import com.darksideofrainbow.repository.AlbumRepository;
import com.darksideofrainbow.service.AlbumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class AlbumServiceImpl implements AlbumService {

    @Autowired
    private AlbumRepository albumRepository;

    @Override
    public List<Album> findAll() {
        return albumRepository.findAll();
    }

    @Override
    public Album find(Long id) {
        return albumRepository.find(id);
    }

    @Override
    public List<Album> findByName(String name) {
        return albumRepository.findByName(name);
    }

    @Override
    public Album create(Album album) {
        return albumRepository.create(album);
    }

    @Override
    public Album edit(Long id, String name, String artist, LocalDateTime dateReleased, int tracks, Genre genre, Double price) {
        return albumRepository.edit(id, name, artist, dateReleased, tracks, genre, price);
    }

    @Override
    public Boolean delete(Long id) {
        albumRepository.delete(id);
        return true;
    }
}
