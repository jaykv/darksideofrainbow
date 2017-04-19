package com.darksideofrainbow.repository;

import com.darksideofrainbow.models.Album;
//import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

//@RepositoryRestResource(collectionResourceRel = "Album", path="album")
public interface AlbumRepository { // extends PagingAndSortingRepository<Album, Long> {
    List<Album> findAll();
    Album find(Long id);
    List<Album> findByName(String name);
    Album create(Album album);
    Album edit(Long id, String name, Double price);
    Boolean delete(Long id);
}
