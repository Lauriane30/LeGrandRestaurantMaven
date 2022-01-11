package com.legrandrestaurantmaven;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Hotesse extends Employe {
    
    private ArrayList<Table> tablesLibres;
    private Map<Table, Client> clientTable;
    
    public Hotesse(String nomEmploye, Restaurant r) {
        super(nomEmploye, "Hôtesse", r);
        this.tablesLibres = r.getListeTables();
        this.clientTable = new HashMap<Table, Client>();
    }
    
    public ArrayList<Table> installerClientATable(Table t, Service s) {
        int index = 0;
        for(Table tableLibre : this.tablesLibres) {
            if(t.getNumeroTable() == tableLibre.getNumeroTable()) {
                if(s.getListeClients().size() > 0) {
                    this.clientTable.put(t, s.getListeClients().get(0));
                }
                index = this.tablesLibres.indexOf(tableLibre);
            }
        }
        retirerTableListe(this.tablesLibres, index);
        return tablesLibres;
    }
    
    public void libererTable(Table t, Service s) {
        this.tablesLibres.add(t);
        if(s.getListeClients().size() > 0) {
            s.getListeClients().remove(0);
        }
        this.clientTable.remove(t);
    }
    
    public void retirerTableListe(ArrayList<Table> listeTables, int index) {
        listeTables.remove(index);
    }

    public ArrayList<Table> getTablesLibres() {
        return tablesLibres;
    }

    public void setTablesLibres(ArrayList<Table> tablesLibres) {
        this.tablesLibres = tablesLibres;
    }
        
} // end of class
