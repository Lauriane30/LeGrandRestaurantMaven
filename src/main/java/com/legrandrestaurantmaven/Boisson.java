package com.legrandrestaurantmaven;

import java.util.ArrayList;

public class Boisson {
    
    private String nom;
    private double prix;
    
    public Boisson(String nom, double prix) {
        this.nom = nom;
        this.prix = prix;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public double getPrix() {
        return prix;
    }

    public void setPrix(double prix) {
        this.prix = prix;
    }
    
} // end of class