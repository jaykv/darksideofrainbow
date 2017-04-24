package com.darksideofrainbow.models;

import lombok.*;
import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

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

    public Album(String title, String artist, LocalDateTime dateReleased, Genre genre, int tracks, double price) {
        this.title = title;
        this.artist = artist;
        this.dateReleased = dateReleased;
        this.genre = genre;
        this.tracks = tracks;
        this.price = price;
    }


    public String formatDateReleased() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy MMM d");
        return this.dateReleased.format(formatter);
    }


}
