package com.shashank.platform.furnitureecommerceappui.Models;

public class Produit {
    private int produit_id;
    private String produit_name;
    private int produit_image;
    private float produit_price;
    private float shipping_price;
    private int categorie_id;



    public Produit() {
    }

    public Produit(int produit_id, String produit_name, int produit_image, float produit_price, float shipping_price, int categorie_id) {
        this.produit_id = produit_id;
        this.produit_name = produit_name;
        this.produit_image = produit_image;
        this.produit_price = produit_price;
        this.shipping_price = shipping_price;
        this.categorie_id = categorie_id;
    }

    public int getProduit_id() {
        return produit_id;
    }

    public void setProduit_id(int produit_id) {
        this.produit_id = produit_id;
    }

    public String getProduit_name() {
        return produit_name;
    }

    public void setProduit_name(String produit_name) {
        this.produit_name = produit_name;
    }

    public int getProduit_image() {
        return produit_image;
    }

    public void setProduit_image(int produit_image) {
        this.produit_image = produit_image;
    }

    public float getProduit_price() {
        return produit_price;
    }

    public void setProduit_price(float produit_price) {
        this.produit_price = produit_price;
    }

    public float getShipping_price() {
        return shipping_price;
    }

    public void setShipping_price(float shipping_price) {
        this.shipping_price = shipping_price;
    }

    public int getCategorie_id() {
        return categorie_id;
    }

    public void setCategorie_id(int categorie_id) {
        this.categorie_id = categorie_id;
    }

    @Override
    public String toString() {
        return "Produit{" +
                "produit_id=" + produit_id +
                ", produit_name='" + produit_name + '\'' +
                ", produit_image=" + produit_image +
                ", produit_price=" + produit_price +
                ", shipping_price=" + shipping_price +
                ", categorie_id=" + categorie_id +
                '}';
    }
}
