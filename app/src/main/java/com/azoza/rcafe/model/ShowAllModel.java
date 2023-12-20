package com.azoza.rcafe.model;

import java.io.Serializable;

public class ShowAllModel implements Serializable {

    private String image_url;
    private String description;
    private String name;
    private int price;
    private String type;
    private String rating;

    public ShowAllModel() {
    }

    public ShowAllModel(String image_url, String description, String name, int price, String type, String rating) {
        this.image_url = image_url;
        this.description = description;
        this.name = name;
        this.price = price;
        this.type = type;
        this.rating = rating;
    }

    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }
}
