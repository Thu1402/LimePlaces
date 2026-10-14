package com.limeplaces.app.model;

public class Place {
    public final String name;
    public final String category;
    public final String address;
    public final String distance;
    public final String imageUrl;

    public Place(String name, String category, String address, String distance, String imageUrl) {
        this.name = name;
        this.category = category;
        this.address = address;
        this.distance = distance;
        this.imageUrl = imageUrl;
    }
}
