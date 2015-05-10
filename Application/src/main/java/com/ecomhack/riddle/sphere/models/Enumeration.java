package com.ecomhack.riddle.sphere.models;

public class Enumeration {
    private final String key;
    private final String label;

    public Enumeration(String key, String label) {
        this.key = key;
        this.label = label;
    }

    public String getKey() {
        return key;
    }

    public String getLabel() {
        return label;
    }

    @Override
    public String toString() {
        return "Enumeration{" +
                "key='" + key + '\'' +
                ", label='" + label + '\'' +
                '}';
    }
}
