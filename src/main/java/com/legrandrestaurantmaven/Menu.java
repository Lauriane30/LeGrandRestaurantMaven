package com.legrandrestaurantmaven;

import java.util.ArrayList;

public class Menu {
    
    private String nom;
    private ArrayList<Item> listeItem;
    private double prixMenu;
    
    public Menu() {
        this.listeItem = new ArrayList<Item>();
        this.ajouterMenus();
    }

    public void ajouterMenus() {
        this.listeItem.add(new Item("Tomates mozzarella", "entrée", 10));
        this.listeItem.add(new Item("Boeuf bourguignon", "plat", 25));
        this.listeItem.add(new Item("Tiramisu", "dessert", 7));
        this.listeItem.add(new Item("Salade Caesar", "entrée", 8));
        this.listeItem.add(new Item("Cassoulet", "plat", 22.5));
        this.listeItem.add(new Item("Crème brûlée", "dessert", 6));
        this.listeItem.add(new Item("Carpaccio de boeuf", "entrée", 9));
        this.listeItem.add(new Item("Poulet basquaise", "plat", 28));
        this.listeItem.add(new Item("Salade de fruits", "dessert", 5));
        for (Item i : listeItem) {
            this.prixMenu += i.getPrix();
        } 
    }
    
    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public ArrayList<Item> getListeItem() {
        return listeItem;
    }

    public void setListeItem(ArrayList<Item> listeItem) {
        this.listeItem = listeItem;
    }

    public double getPrixMenu() {
        return prixMenu;
    }

    public void setPrixMenu(double prixMenu) {
        this.prixMenu = prixMenu;
    }
    
} // end of class
