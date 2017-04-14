package com.darksideofrainbow.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor

@Entity
public class Album {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "album_id_seq")
    @SequenceGenerator(name = "album_id_seq", sequenceName = "album_id_seq", allocationSize = 100)
    private Long albumId;

    private String title;
    private String artist;
    private LocalDateTime dateReleased;
    private Genre genre;
    private int tracks;
    private double price;
}
