package com.darksideofrainbow.service;

import com.darksideofrainbow.models.Album;

import java.util.List;

public interface AlbumService {
    List<Album> findAll();
    Album find(Long id);
    List<Album> findByName(String name);
    Album create(Album album);
    Album edit(Long id, String name, Double price);
    Boolean delete(Long id);
}
