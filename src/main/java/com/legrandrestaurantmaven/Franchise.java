package com.legrandrestaurantmaven;

import java.util.ArrayList;

public class Franchise {
    
    private String nom;
    private ArrayList<Restaurant> listeRestaurants;
    private Carte carteFranchise;
    private double chiffreAffairesFranchise;
    
    public Franchise(String nom, int nbRestaurants) {
        this.nom = nom;
        this.carteFranchise = new Carte(nom);
        this.listeRestaurants = new ArrayList<Restaurant>();
        for(int i = 0; i < nbRestaurants; i++) {
            this.listeRestaurants.add(new Restaurant(nom + "-00" + (i+1), this.carteFranchise));
        }
    }
    
    public Restaurant renvoiePremierRestaurant() {
        return this.listeRestaurants.get(0);
    }
    
    public void ajouterMenus() {
        this.carteFranchise.getListeMenus().add(new Menu());
        this.carteFranchise.getListeMenus().get(1).getListeItem().get(1).setPrix(30);
    }
    
    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public ArrayList<Restaurant> getListeRestaurants() {
        return listeRestaurants;
    }

    public void setListeRestaurants(ArrayList<Restaurant> listeRestaurants) {
        this.listeRestaurants = listeRestaurants;
    }

    public Carte getCarteFranchise() {
        return carteFranchise;
    }

    public void setCarteFranchise(Carte carteFranchise) {
        this.carteFranchise = carteFranchise;
    }

    public double getChiffreAffairesFranchise() {
        return chiffreAffairesFranchise;
    }

    public void setChiffreAffairesFranchise(double chiffreAffairesFranchise) {
        this.chiffreAffairesFranchise = chiffreAffairesFranchise;
    }
    
    
    
} // end of class
