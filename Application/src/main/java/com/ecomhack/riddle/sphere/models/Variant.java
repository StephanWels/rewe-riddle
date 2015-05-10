package com.ecomhack.riddle.sphere.models;

import java.util.List;

public class Variant {
    private final List<Image> images;
    private final String beacon;
    private final LocalizedStrings riddle;
    private final Enumeration difficulty;

    public Variant(List<Image> images, String beacon, LocalizedStrings riddle, Enumeration difficulty) {
        this.images = images;
        this.beacon = beacon;
        this.riddle = riddle;
        this.difficulty = difficulty;
    }

    public List<Image> getImages() {
        return images;
    }

    public String getBeacon() {
        return beacon;
    }

    public LocalizedStrings getRiddle() {
        return riddle;
    }

    public Enumeration getDifficulty() {
        return difficulty;
    }

    @Override
    public String toString() {
        return "Variant{" +
                "images=" + images +
                ", beacon='" + beacon + '\'' +
                ", riddle=" + riddle +
                ", difficulty=" + difficulty +
                '}';
    }
}
