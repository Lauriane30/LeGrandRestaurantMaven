package com.legrandrestaurantmaven;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class Serveur extends Employe {
    
    private ArrayList<Table> tablesAttribuees;
    private ArrayList<Commande> listeCommandes;
    private ArrayList<Commande> listeCommandesEpinglees;
    private double chiffreAffaires;
    
    public Serveur(String nomEmploye, Restaurant r) {
        super(nomEmploye, "Serveur", r);
        this.chiffreAffaires = 0;
        this.tablesAttribuees = new ArrayList<Table>();
        this.listeCommandes = new ArrayList<Commande>();
        this.listeCommandesEpinglees = new ArrayList<Commande>();
    }

    public ArrayList<Commande> getListeCommandes() {
        return listeCommandes;
    }

    public void setListeCommandes(ArrayList<Commande> listeCommandes) {
        this.listeCommandes = listeCommandes;
    }
    
    public void epinglerCommande(Commande c) {
        this.listeCommandes.get(0).setStatutCommande("Non payée");
        this.listeCommandesEpinglees.add(c);
    }
    
    public void verifSiPlusDe15Jours(Commande c) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
        String startDate = c.getDateCommande();
        Calendar calendar = Calendar.getInstance();
        Date endDate = calendar.getTime();
        String formattedDate = sdf.format(endDate);
        TimeUnit time = TimeUnit.DAYS;
        long diff = sdf.parse(formattedDate).getTime() - sdf.parse(startDate).getTime();
        long difference = time.convert(diff, TimeUnit.MILLISECONDS);
        if(difference >= 15) {
            c.setStatutCommande("A transmettre à la gendarmerie");
            this.listeCommandesEpinglees.add(c);
            super.getRestaurant().getCommandesATransmettre().add(c);
        }
    }

    public ArrayList<Commande> getListeCommandesEpinglees() {
        return listeCommandesEpinglees;
    }

    public void setListeCommandesEpinglees(ArrayList<Commande> listeCommandesEpinglees) {
        this.listeCommandesEpinglees = listeCommandesEpinglees;
    }
    
    public double nouvelleCommande(int numCommande) {
        Commande c = new Commande();
        double prixCommande = 0;
        c.setNumCommande(numCommande);
        c.setMontantCommande(0.1);
        c.ajouterCommandeMenu();
        this.listeCommandes.add(c);
        for (Menu m : c.getMenusCommandes()) {
            prixCommande = m.getPrixMenu();
        }
        return prixCommande;
    }
    
    public void nouvelleCommandeBoisson(int numCommande) {
        Commande c = new Commande();
        double prixCommande = 0;
        c.setNumCommande(numCommande);
        c.setMontantCommande(0.1);
        c.ajouterCommandeBoissons("Coca", 2.5);
        this.listeCommandes.add(c);
        for(Boisson b : c.getListeBoissons()) {
            prixCommande += b.getPrix();
        }
    }
    
    public void envoyerCommandeCuisine(Cuisine cuisine) {
        ArrayList<Menu> listeMenus = null;
        for(Commande c : this.listeCommandes) {
            if(c.getListeBoissons().size() == 0) {
                cuisine.getListeCommandes().add(c);
            }
        }
    }
 
    public ArrayList<Table> getTablesAttribuees() {
        return tablesAttribuees;
    }

    public void setTablesAttribuees(ArrayList<Table> tablesAttribuees) {
        this.tablesAttribuees = tablesAttribuees;
    }

    public double getChiffreAffaires() {
        return chiffreAffaires;
    }

    public void setChiffreAffaires(double chiffreAffaires) {
        this.chiffreAffaires = chiffreAffaires;
    }
    
} // end of class
