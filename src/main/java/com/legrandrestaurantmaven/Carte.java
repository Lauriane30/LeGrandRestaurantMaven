package com.legrandrestaurantmaven;

import java.util.ArrayList;

public class Carte {
    
    private ArrayList<Menu> listeMenus;
    private ArrayList<Boisson> listeBoissons;
    private double prixBoissons;
    
    public Carte() {
        this.listeMenus = new ArrayList<Menu>();
    }
    
    public Carte(String nomRestaurant) {
        this.listeMenus = new ArrayList<Menu>();
        this.listeMenus.add(new Menu());
        this.listeBoissons = new ArrayList<Boisson>();
    }
    
    public void ajouterBoissons() {
        this.listeBoissons.add(new Boisson("Coca", 3.5));
        this.listeBoissons.add(new Boisson("Schweppes", 2));
        this.listeBoissons.add(new Boisson("Jus de fruits", 2.5));
        this.listeBoissons.add(new Boisson("Kir", 4));
        this.listeBoissons.add(new Boisson("Champagne", 5.5));
        for (Boisson b : listeBoissons) {
            this.prixBoissons += b.getPrix();
        } 
    }
    
    public ArrayList<Menu> getListeMenus() {
        return listeMenus;
    }

    public void setListeMenus(ArrayList<Menu> listeMenus) {
        this.listeMenus = listeMenus;
    }
    
} // end of class
