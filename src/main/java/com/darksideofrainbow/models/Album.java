package com.darksideofrainbow.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Album {
    private int albumId;
    private String title;
    private String artist;
    private LocalDateTime dateReleasted;
    private Genre genre;
    private int tracks;
    private double price;
}
