package com.legrandrestaurantmaven;

public class Item {
    
    private String nom;
    private String type;
    private double prix;
    
    public Item(String nom, String type, double prix) {
        this.nom = nom;
        this.type = type;
        this.prix = prix;
    }    

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public double getPrix() {
        return prix;
    }

    public void setPrix(double prix) {
        this.prix = prix;
    }
    
} // end of class
