package com.legrandrestaurantmaven;

import java.util.ArrayList;

public class Cuisine {
    
    private ArrayList<Commande> listeCommandes;
    
    public Cuisine() {
        this.listeCommandes = new ArrayList<Commande>();
    }

    public ArrayList<Commande> getListeCommandes() {
        return listeCommandes;
    }

    public void setListeCommandes(ArrayList<Commande> listeCommandes) {
        this.listeCommandes = listeCommandes;
    }
    
} // end of class
