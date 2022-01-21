package com.shashank.platform.furnitureecommerceappui.Models;

public class Categorie {

    private  int categorie_id;
    private String name;
    private String description;

    public Categorie() {
    }

    public Categorie(int categorie_id, String name, String description) {
        this.categorie_id = categorie_id;
        this.name = name;
        this.description = description;
    }

    public int getCategorie_id() {
        return categorie_id;
    }

    public void setCategorie_id(int categorie_id) {
        this.categorie_id = categorie_id;
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
}
