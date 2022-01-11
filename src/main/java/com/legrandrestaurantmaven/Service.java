package com.legrandrestaurantmaven;

import java.util.ArrayList;

public class Service {
    
    private int serviceId;
    private ArrayList<Client> listeClients;
    
    public Service(int serviceId, Restaurant r) {
        this.serviceId = serviceId;
        this.listeClients = new ArrayList<Client>();
        for(int i = 0; i < 4; i++) {
            this.listeClients.add(new Client());
        }
    }
    
    public void finirService() {
        while(this.listeClients.size() > 0) {
            this.listeClients.remove(0);
        }
    }
    
    public boolean definirSiServiceTermine() {
        boolean statutService = false;
        if(this.listeClients.size() == 0) {
            statutService= true;
        }
        return statutService;
    }
    
    public int getServiceId() {
        return serviceId;
    }

    public void setServiceId(int serviceId) {
        this.serviceId = serviceId;
    }

    public ArrayList<Client> getListeClients() {
        return listeClients;
    }

    public void setListeClients(ArrayList<Client> listeClients) {
        this.listeClients = listeClients;
    }
    
} // end of class
