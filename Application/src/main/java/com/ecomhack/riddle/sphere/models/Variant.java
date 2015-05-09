package com.ecomhack.riddle.sphere.models;

import java.util.List;

public class Variant {
    private final List<Image> images;
    private final String beacon;
    private final LocalizedStrings riddle;

    public Variant(List<Image> images, LocalizedStrings riddle, String beacon) {
        this.images = images;
        this.riddle = riddle;
        this.beacon = beacon;
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

    @Override
    public String toString() {
        return "Variant{" +
                "images=" + images +
                ", beacon='" + beacon + '\'' +
                ", riddle=" + riddle +
                '}';
    }
}
