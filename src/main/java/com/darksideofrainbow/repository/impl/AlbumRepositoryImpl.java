package com.darksideofrainbow.repository.impl;

import com.darksideofrainbow.jpa.AlbumCrudRepository;
import com.darksideofrainbow.models.Album;
import com.darksideofrainbow.models.Genre;
import com.darksideofrainbow.repository.AlbumRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class AlbumRepositoryImpl implements AlbumRepository {


    @Autowired
    private AlbumCrudRepository albumCrudRepository;

    @Override
    public List<Album> findAll() {
        List<Album> albumList = new ArrayList<>();
        albumCrudRepository.findAll().forEach(albumList::add);
        return albumList;
    }

    @Override
    public Album find(Long id) {
        return albumCrudRepository.findOne(id);
    }

    @Override
    public List<Album> findByName(String albumName) {
        List<Album> albumList = new ArrayList<>();
        albumCrudRepository.findAll().forEach(albumList::add);
        return albumList.stream()
                .filter(album -> album.getTitle().toLowerCase().contains(albumName))
                .collect(Collectors.toList());
    }

    @Override
    public Album create(Album album) {
        return albumCrudRepository.save(album);
    }

    @Override
    public Album edit(Long id, String name, String artist, LocalDateTime dateReleased, int tracks, Genre genre, Double price) {
        Album album = albumCrudRepository.findOne(id);
        if (album != null) {
            album.setTitle(name);
            album.setArtist(artist);
            album.setDateReleased(dateReleased);
            album.setTracks(tracks);
            album.setGenre(genre);
            album.setPrice(price);
            return albumCrudRepository.save(album);
        }
        return null;
    }

    @Override
    public Boolean delete(Long id) {
        albumCrudRepository.delete(id);
        return Boolean.TRUE;
    }

}
