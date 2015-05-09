package com.ecomhack.riddle.sphere.models;

import org.json.JSONObject;

public class Attribute {
    private final String name;
    private final JSONObject value;

    public Attribute(String name, JSONObject value) {
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public JSONObject getValue() {
        return value;
    }

    @Override
    public String toString() {
        return "Attribute{" +
                "name='" + name + '\'' +
                ", value=" + value +
                '}';
    }
}
