package com.ecomhack.riddle.models;

public class Challenge {
    private String id;
    private LocalizedStrings name;

    public String getId() {
        return id;
    }

    public LocalizedStrings getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Challenge{" +
                "id='" + id + '\'' +
                ", name=" + name +
                '}';
    }
}
