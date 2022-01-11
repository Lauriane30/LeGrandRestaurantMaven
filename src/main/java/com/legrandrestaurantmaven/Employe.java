package com.legrandrestaurantmaven;

import java.util.ArrayList;

public class Employe {
    
    protected String nom;
    protected String typePoste;
    private double chiffreAffaires;
    private Restaurant restaurant;
    
    public Employe(String nomEmploye, String typePoste, Restaurant restaurant) {
        this.nom = nomEmploye;
        this.typePoste = typePoste;
        this.restaurant = restaurant;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    public String getTypePoste() {
        return typePoste;
    }

    public void setTypePoste(String typePoste) {
        this.typePoste = typePoste;
    }

    public double getChiffreAffaires() {
        return chiffreAffaires;
    }

    public void setChiffreAffaires(double chiffreAffaires) {
        this.chiffreAffaires = chiffreAffaires;
    }   
    
} // end of class
