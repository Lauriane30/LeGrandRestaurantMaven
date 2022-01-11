package com.legrandrestaurantmaven;

import java.util.ArrayList;
import java.util.Random;

public class MaitreHotel extends Employe {
    
    private ArrayList<Table> tablesAttribuees;
    
    public MaitreHotel(String nom, ArrayList<Table> tablesRestaurant, Restaurant r) {
        super(nom, "Maitre d'hotel", r);
        this.tablesAttribuees = tablesRestaurant;
    }
    
    public Serveur trouverServeurMoinsOccupe(ArrayList<Employe> listeEmployes) {
        int compteur = 1000000;
        Serveur serveurMoinsOccupe = null;
        for(Employe e : listeEmployes) {
            if(e instanceof Serveur) {
                if(((Serveur) e).getTablesAttribuees().size() < compteur) {
                    compteur = ((Serveur) e).getTablesAttribuees().size();
                    serveurMoinsOccupe = ((Serveur) e);
                }
            }
        }
        return serveurMoinsOccupe;
    }
    
    public void attribuerTableServeur() {
        Serveur s = trouverServeurMoinsOccupe(super.getRestaurant().getListeEmployes());
        if(this.tablesAttribuees.size() > 0) {
            s.getTablesAttribuees().add(this.tablesAttribuees.get(0));
            retirerTableListe(this.tablesAttribuees, 0);
        }
    } 
    
    public void retirerTableListe(ArrayList<Table> listeTables, int index) {
        listeTables.remove(index);
    }
    
    public boolean changerAttributionTable(int numTable, ArrayList<Table> listeTables) {
        boolean isInList = false;
        for(Table t : listeTables) {
            if(t.getNumeroTable() == numTable) {
                isInList = true;
            }
        }
        return isInList;
    }

    public ArrayList<Table> getTablesAttribuees() {
        return tablesAttribuees;
    }

    public void setTablesAttribuees(ArrayList<Table> tablesAttribuees) {
        this.tablesAttribuees = tablesAttribuees;
    }
    
} // end of class
