package com.darksideofrainbow.models;

public enum Genre {
    ROCK("Rock"), CLASSICAL("Classical"), POP("Pop"), JAZZ("Jazz"),
    HIP_HOP("Hip Hop"), ELECTRONIC("Electronic"), OTHER("Other");

    private String genre;

    private Genre(String genre) {
        this.genre = genre;
    }

    public String getGenre() {
        return genre;
    }
    @Override
    public String toString(){
        return this.getGenre();
    }
}
