package com.example.k.scf;

public class Search {

    public String name, description, image;

    public Search() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Search(String name, String description, String image) {
        this.name = name;
        this.description = description;
        this.image = image;
    }
}
