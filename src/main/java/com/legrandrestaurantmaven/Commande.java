package com.legrandrestaurantmaven;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class Commande {
    
    private int numCommande;
    private ArrayList<Menu> menusCommandes;
    private double montantCommande;
    private String dateCommande;
    private String statutCommande;
    private ArrayList<Boisson> listeBoissons;
    
    public Commande() {
        this.menusCommandes = new ArrayList<Menu>();
        this.listeBoissons = new ArrayList<Boisson>();
    }
    
    public void ajouterCommandeMenu() {
        this.menusCommandes.add(new Menu());
    }
    
    public void ajouterCommandeBoissons(String boisson, double prix) {
        this.listeBoissons.add(new Boisson(boisson, prix));
    }

    public ArrayList<Boisson> getListeBoissons() {
        return listeBoissons;
    }

    public void setListeBoissons(ArrayList<Boisson> listeBoissons) {
        this.listeBoissons = listeBoissons;
    }

    public int getNumCommande() {
        return numCommande;
    }

    public void setNumCommande(int numCommande) {
        this.numCommande = numCommande;
    }

    public ArrayList<Menu> getMenusCommandes() {
        return menusCommandes;
    }

    public void setMenusCommandes(ArrayList<Menu> menusCommandes) {
        this.menusCommandes = menusCommandes;
    }

    public double getMontantCommande() {
        return montantCommande;
    }

    public void setMontantCommande(double montantCommande) {
        this.montantCommande = montantCommande;
    }

    public String getDateCommande() {
        return dateCommande;
    }

    public void setDateCommande(String dateCommande) {
        this.dateCommande = dateCommande;
    }

    public String getStatutCommande() {
        return statutCommande;
    }

    public void setStatutCommande(String statutCommande) {
        this.statutCommande = statutCommande;
    }
    
} // end of class
