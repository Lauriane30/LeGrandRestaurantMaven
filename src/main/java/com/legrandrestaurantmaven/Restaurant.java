package com.legrandrestaurantmaven;

import java.util.ArrayList;

public class Restaurant {
    
    private String nom;
    private ArrayList<Employe> listeEmployes;
    private ArrayList<Table> listeTables;
    private Carte carteRestaurant;
    private ArrayList<Service> listeServices;
    private Carte menusExtra;
    private Cuisine cuisineRestaurant;
    private ArrayList<Commande> commandesATransmettre;
    
    public Restaurant(String nomRestaurant, Carte carte) {
        this.nom = nomRestaurant;
        this.carteRestaurant = carte;
        this.listeTables = new ArrayList<Table>();
        this.listeEmployes = new ArrayList<Employe>();
        this.listeServices = new ArrayList<Service>();
        for(int i = 0; i < 2; i++) {
            this.listeServices.add(new Service(i+1, this));
        }
        this.listeEmployes.add(new Serveur("Pedro", this));
        this.listeEmployes.add(new Serveur("Bobby", this));
        this.listeEmployes.add(new MaitreHotel("Alfred", listeTables, this));
        this.listeEmployes.add(new Hotesse("Mimi", this));
        this.menusExtra = new Carte(this.nom);
        this.cuisineRestaurant = new Cuisine();
        this.commandesATransmettre = new ArrayList<Commande>();
    }

    public ArrayList<Commande> getCommandesATransmettre() {
        return commandesATransmettre;
    }

    public void setCommandesATransmettre(ArrayList<Commande> commandesATransmettre) {
        this.commandesATransmettre = commandesATransmettre;
    }

    public ArrayList<Service> getListeServices() {
        return listeServices;
    }

    public void marquerCommandeTransmise(Commande c) {
        int index = 0;
        for(Commande c1 : this.commandesATransmettre) {
            if(c1 == c) {
                index = this.commandesATransmettre.indexOf(c1);
            }
        }
        this.commandesATransmettre.get(index).setStatutCommande("Transmise à la gendarmerie");
        this.commandesATransmettre.remove(index);
    }
    
    public void setListeServices(ArrayList<Service> listeServices) {
        this.listeServices = listeServices;
    }
    
    public ArrayList<Table> remplirListeTables(int nbTables) {
        for(int i = 0; i < nbTables; i++) {
            this.listeTables.add(new Table(i+1));
        }
        return this.listeTables;
    }
    
    public Table renvoiePremiereTable() {
        return this.listeTables.get(0);
    }
    
    public Serveur renvoieUnServeur() {
        Serveur s = null;
        for(Employe e : this.listeEmployes) {
            if(e instanceof Serveur) {
                s = (Serveur)e;
            }
        }
        return s;
    }

    public Cuisine getCuisineRestaurant() {
        return cuisineRestaurant;
    }

    public void setCuisineRestaurant(Cuisine cuisineRestaurant) {
        this.cuisineRestaurant = cuisineRestaurant;
    }
    
    public Service renvoiePremierService() {
        return this.listeServices.get(0);
    }
    
    public double renvoieMontantCommandesServeurs() {
        double montant = 0;
        for (Employe e : listeEmployes) {
            if(e instanceof Serveur) {
                montant += ((Serveur) e).nouvelleCommande(000001);
            }
        }
        return montant;
    }
    
    public Carte ajouterMenuRestaurant(int nbMenus) {
        for(int i = 0; i < nbMenus; i++) {
           this.menusExtra.getListeMenus().add(new Menu());
        }
        return this.menusExtra;
    }

    public Carte actualiserCarteRestaurant() {
        for(Menu m : carteRestaurant.getListeMenus()) {
            this.menusExtra.getListeMenus().add(m);
        }
        return this.menusExtra;
    }
    
    public Carte getMenusExtra() {
        return menusExtra;
    }

    public void setMenusExtra(Carte menusExtra) {
        this.menusExtra = menusExtra;
    }
    
    public Carte getCarteRestaurant() {
        return carteRestaurant;
    }

    public void setCarteRestaurant(Carte carteRestaurant) {
        this.carteRestaurant = carteRestaurant;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public ArrayList<Employe> getListeEmployes() {
        return listeEmployes;
    }

    public void setListeEmployes(ArrayList<Employe> listeEmployes) {
        this.listeEmployes = listeEmployes;
    }

    public ArrayList<Table> getListeTables() {
        return listeTables;
    }

    public void setListeTables(ArrayList<Table> listeTables) {
        this.listeTables = listeTables;
    }
    
   
    
    
} // end of class
