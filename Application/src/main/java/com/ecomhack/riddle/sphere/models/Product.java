package com.ecomhack.riddle.sphere.models;

public class Product {
    private final Variant masterVariant;
    private final LocalizedStrings name;

    public Product(Variant masterVariant, LocalizedStrings name) {
        this.masterVariant = masterVariant;
        this.name = name;
    }

    @Override
    public String toString() {
        return "Product{" +
                "masterVariant=" + masterVariant +
                ", name=" + name +
                '}';
    }
}
