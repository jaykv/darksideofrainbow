package com.darksideofrainbow.jpa;

import com.darksideofrainbow.models.Album;
import org.springframework.data.repository.CrudRepository;

public interface AlbumCrudRepository extends CrudRepository<Album, Long> {
}
