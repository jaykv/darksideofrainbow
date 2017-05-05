package com.darksideofrainbow.repository;


import com.darksideofrainbow.models.Album;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "Album", path="albums")
public interface AlbumRestRepository extends CrudRepository<Album, Long>, PagingAndSortingRepository<Album, Long> {

}
