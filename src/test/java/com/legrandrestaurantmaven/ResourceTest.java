package com.legrandrestaurantmaven;

import com.thoughtworks.qdox.parser.ParseException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ResourceTest {

    private static final double DELTA = 1e-15;
    
    @Test
    public void testChiffresAffaires() {
        // ÉTANT DONNÉ un nouveau serveur 
        Serveur s = new Serveur("Bobby", new Restaurant("Il Pizzaiolo", new Carte()));
        // QUAND on récupére son chiffre d'affaires
        double chiffreAffaires = s.getChiffreAffaires();
        // ALORS celui-ci est à 0
        assertEquals(0, chiffreAffaires, DELTA);
    }
    
    @Test
    public void testChiffresAffairesPremiereCommande() {
        double resultatAttendu = 120.5;
        // ÉTANT DONNÉ un nouveau serveur
        Serveur s = new Serveur("Bobby", new Restaurant("Il Pizzaiolo", new Carte()));
        // QUAND il prend une commande
        double resultatEffectif = s.nouvelleCommande(000001);
        // ALORS son chiffre d'affaires est le montant de celle-ci
        assertEquals(resultatAttendu, resultatEffectif, DELTA);
    }
    
    @Test
    public void testAdditionCommandes() {
        int resultatAttendu = 241;
        // ÉTANT DONNÉ un serveur ayant déjà pris une commande
        Serveur s = new Serveur("Bobby", new Restaurant("Il Pizzaiolo", new Carte()));
        // QUAND il prend une nouvelle commande
        int commandeSuivante = 35;
        double resultatEffectif = s.nouvelleCommande(000001) + s.nouvelleCommande(000002);
        // ALORS son chiffre d'affaires est la somme des deux commandes
        assertEquals(resultatAttendu, resultatEffectif, DELTA);
    }
    
    @Test
    public void testchiffreAffairesFranchise() {
        double resultatAttendu = 241;
        double resultatEffectif = 0;
        // ÉTANT DONNÉ un restaurant ayant X serveurs
        Franchise f = new Franchise("Il Pizzaiolo", 1);
        for(Restaurant r : f.getListeRestaurants()) {
            resultatEffectif = r.renvoieMontantCommandesServeurs();
        };
        f.setChiffreAffairesFranchise(resultatEffectif);
        // QUAND tous les serveurs prennent une commande d'un montant Y
        // ALORS le chiffre d'affaires de la franchise est X * Y
        assertEquals(resultatAttendu, f.getChiffreAffairesFranchise(), DELTA);
    }
    
    @Test
    public void testchiffreAffairesFranchise2() {
        double resultatAttendu = 723;
        double resultatEffectif = 0;
        int nbRestaurants = 3;
        // ÉTANT DONNÉ une franchise ayant X restaurants de Y serveurs chacuns
        Franchise f = new Franchise("Il Pizzaiolo", nbRestaurants);
        for(Restaurant r : f.getListeRestaurants()) {
            resultatEffectif = r.renvoieMontantCommandesServeurs();
        };
        f.setChiffreAffairesFranchise(resultatEffectif);
        // QUAND tous les serveurs prennent une commande d'un montant Z
        // ALORS le chiffre d'affaires de la franchise est X * Y * Z
        assertEquals(resultatAttendu, f.getChiffreAffairesFranchise() * nbRestaurants, DELTA);
    }
    
    @Test
    public void testAffectationTables1() {
        int resultatAttendu = 3;
        ArrayList<Table> listeTables = null;
        // ÉTANT DONNE un restaurant ayant 3 tables
        // QUAND le service commence        
        // ALORS elles sont toutes affectées au Maître d'Hôtel
        Restaurant r = new Restaurant("Il Pizzaiolo", new Carte());
        listeTables = r.remplirListeTables(resultatAttendu);
        int resultatEffectif = listeTables.size();
        
        assertEquals(resultatAttendu, resultatEffectif);
    }
    
    @Test
    public void testAffectationTables2() {
        // ÉTANT DONNÉ un restaurant ayant 3 tables dont une affectée à un serveur
        // QUAND le service débute
	// ALORS la table éditée est affectée au serveur et les deux autres au maître d'hôtel
        MaitreHotel m = null;
        Restaurant r = new Restaurant("Il Pizzaiolo", new Carte());
        for(Employe e : r.getListeEmployes()) {
            if(e instanceof MaitreHotel) {
                m = (MaitreHotel) e;
                m.attribuerTableServeur();
            }
        }
        
        int resultatAttendu = 0;
        int resultatEffectif = m.getTablesAttribuees().size();
        
        assertEquals(resultatAttendu, resultatEffectif);
    }
    
    @Test
    public void testAffectationTables3() {
        // ÉTANT DONNÉ un restaurant ayant 3 tables dont une affectée à un serveur
	// QUAND le service débute
	// ALORS il n'est pas possible de modifier le serveur affecté à la table
        MaitreHotel m = null;
        Restaurant r = new Restaurant("Il Pizzaiolo", new Carte());
        
        for(Employe e : r.getListeEmployes()) {
            if(e instanceof MaitreHotel) {
                m = (MaitreHotel) e;
                m.attribuerTableServeur();
            }
        }

        boolean resultatAttendu = false;
        boolean resultatEffectif = m.changerAttributionTable(1, m.getTablesAttribuees());
        
        assertEquals(resultatAttendu, resultatEffectif);
    }
    
    @Test
    public void testAffectationTables4() {
        // ÉTANT DONNÉ un restaurant ayant 3 tables dont une affectée à un serveur
	// ET ayant débuté son service
	// QUAND le service se termine
	// ET qu'une table est affectée à un serveur
	// ALORS la table éditée est affectée au serveur et les deux autres au maître d'hôtel
        MaitreHotel m = null;
        Restaurant r = new Restaurant("Il Pizzaiolo", new Carte());
        r.remplirListeTables(4);
        for(Service s : r.getListeServices()) {
            if(s.getServiceId() == 1) {
                s.finirService();
            }
        }
        for(Employe e : r.getListeEmployes()) {
            if(e instanceof MaitreHotel) {
                m = (MaitreHotel) e;
                m.attribuerTableServeur();
            }
        }
        
        int resultatAttendu = 3;
        int resultatEffectif = m.getTablesAttribuees().size();
        
        assertEquals(resultatAttendu, resultatEffectif);
    }
    
    @Test
    public void testInstallation1() {
        //ÉTANT DONNE une table dans un restaurant ayant débuté son service
	//QUAND un client est affecté à une table
	//ALORS cette table n'est plus sur la liste des tables libres du restaurant
        boolean resultatAttendu = false;
        Restaurant r = new Restaurant("Il Pizzaiolo", new Carte());
        r.remplirListeTables(1);
        Table t = r.renvoiePremiereTable();
        ArrayList<Table> listeTables = null;
        for(Employe e : r.getListeEmployes()) {
            if(e instanceof Hotesse) {
                listeTables = ((Hotesse) e).installerClientATable(t, r.renvoiePremierService());
            }
        }
        boolean resultatEffectif = false;
        for(Table t1 : listeTables) {
           if(t == t1) {
               resultatEffectif = true;
           } 
        }
        
        assertEquals(resultatAttendu, resultatEffectif);
    }
    
    @Test
    public void testInstallation2() {
        //ÉTANT DONNE une table occupée par un client
	//QUAND la table est libérée
	//ALORS cette table appraît sur la liste des tables libres du restaurant
        boolean resultatAttendu = true;
        Restaurant r = new Restaurant("Il Pizzaiolo", new Carte());
        r.remplirListeTables(1);
        Hotesse h = null;
        Table t = r.renvoiePremiereTable();
        for(Employe e : r.getListeEmployes()) {
            if(e instanceof Hotesse) {
                h = ((Hotesse) e);
                ((Hotesse) e).installerClientATable(t, r.renvoiePremierService());
            }
        }
        h.libererTable(t, r.renvoiePremierService());
        boolean resultatEffectif = false;
        for(Table t1 : h.getTablesLibres()) {
           if(t == t1) {
               resultatEffectif = true;
           } 
        }
        
        assertEquals(resultatAttendu, resultatEffectif);
    }
    
    @Test
    public void testMenus1() {
        //ÉTANT DONNE un restaurant ayant le statut de filiale d'une franchise
	//ET une franchise définissant un menu ayant un plat
	//QUAND la franchise modifie le prix du plat
	//ALORS le prix du plat dans le menu du restaurant est celui défini par la franchise
        double resultatAttendu = 30;
        double resultatEffectif = 0;
        Franchise f = new Franchise("Il Pizzaiolo", 1);
        Carte c = f.getCarteFranchise();
        Item i = null;
        for(Menu m : c.getListeMenus()) {
            for(Item i1 : m.getListeItem()) {
                i = i1;
                if(i1.getType() == "plat") {
                    i1.setPrix(30);
                    resultatEffectif = i1.getPrix();
                }
            }
        }
        assertEquals(resultatAttendu, resultatEffectif, DELTA);
    }
    
    @Test
    public void testMenus2() {
        //ÉTANT DONNE un restaurant appartenant à une franchise et définissant un menu ayant un plat
	//ET une franchise définissant un menu ayant le même plat
	//QUAND la franchise modifie le prix du plat
	//ALORS le prix du plat dans le menu du restaurant reste inchangé
        double resultatAttendu = 25;
        double resultatEffectif = 0;
        Franchise f = new Franchise("Il Pizzaiolo", 1);
        Restaurant r = f.renvoiePremierRestaurant();
        Carte c = f.getCarteFranchise();
        Carte c1 = r.ajouterMenuRestaurant(1);
        Item i = null;
        for(Menu m : c.getListeMenus()) {
            for(Item i1 : m.getListeItem()) {
                i = i1;
                if(i1.getType() == "plat") {
                    i1.setPrix(30);
                }
            }
        }
        resultatEffectif = c1.getListeMenus().get(1).getListeItem().get(1).getPrix();
        assertEquals(resultatAttendu, resultatEffectif, DELTA);
    }
    
    @Test
    public void testMenus3() {
        //ÉTANT DONNE un restaurant appartenant à une franchise et définissant un menu ayant un plat
	//QUAND la franchise ajoute un nouveau plat
	//ALORS la carte du restaurant propose le premier plat au prix du restaurant et le second au prix de la franchise
        double resultatAttendu = 30;
        double resultatEffectif = 0;
        Franchise f = new Franchise("Il Pizzaiolo", 1);
        Restaurant r = f.renvoiePremierRestaurant();
        Carte c = f.getCarteFranchise();
        f.ajouterMenus();
        r.setCarteRestaurant(c);
        Carte c1 = r.actualiserCarteRestaurant();
        c1 = r.ajouterMenuRestaurant(1);
        resultatEffectif = c1.getListeMenus().get(2).getListeItem().get(1).getPrix();
        assertEquals(resultatAttendu, resultatEffectif, DELTA);
    }
    
    @Test
    public void testCommande1() {
        //ÉTANT DONNE un serveur dans un restaurant
	//QUAND il prend une commande de nourriture
	//ALORS cette commande apparaît dans la liste de tâches de la cuisine de ce restaurant
        int resultatAttendu = 1;
        Franchise f = new Franchise("Il Pizzaiolo", 1);
        Restaurant r = f.renvoiePremierRestaurant();
        f.ajouterMenus();
        Cuisine c = r.getCuisineRestaurant();
        Serveur s = r.renvoieUnServeur();
        s.nouvelleCommande(00001);
        s.envoyerCommandeCuisine(c);
        int resultatEffectif = c.getListeCommandes().size();
        assertEquals(resultatAttendu, resultatEffectif, DELTA);
    }
    
    @Test
    public void testCommande2() {
        //ÉTANT DONNE un serveur dans un restaurant
	//QUAND il prend une commande de boissons
	//ALORS cette commande n'apparaît pas dans la liste de tâches de la cuisine de ce restaurant
        int resultatAttendu = 1;
        Franchise f = new Franchise("Il Pizzaiolo", 1);
        Restaurant r = f.renvoiePremierRestaurant();
        f.ajouterMenus();
        Cuisine c = r.getCuisineRestaurant();
        Serveur s = r.renvoieUnServeur();
        s.nouvelleCommande(00001);
        s.nouvelleCommandeBoisson(00002);
        s.envoyerCommandeCuisine(c);
        int resultatEffectif = c.getListeCommandes().size();
        assertEquals(resultatAttendu, resultatEffectif, DELTA);
    }
    
    @Test
    public void testEpinglage1() {
        // ÉTANT DONNE un serveur ayant pris une commande
	// QUAND il la déclare comme non-payée
	// ALORS cette commande est marquée comme épinglée
        int resultatAttendu = 1;
        Franchise f = new Franchise("Il Pizzaiolo", 1);
        Restaurant r = f.renvoiePremierRestaurant();
        f.ajouterMenus();
        Serveur s = r.renvoieUnServeur();
        s.nouvelleCommande(00001);
        Commande c = s.getListeCommandes().get(0);
        s.epinglerCommande(c);        
        int resultatEffectif = s.getListeCommandesEpinglees().size();
        assertEquals(resultatAttendu, resultatEffectif, DELTA);
    }
    
    @Test
    public void testEpinglage2() throws java.text.ParseException {
        //ÉTANT DONNE un serveur ayant épinglé une commande
	//QUAND elle date d'il y a au moins 15 jours
	//ALORS cette commande est marquée comme à transmettre gendarmerie
        String resultatAttendu = "A transmettre à la gendarmerie";
        Franchise f = new Franchise("Il Pizzaiolo", 1);
        Restaurant r = f.renvoiePremierRestaurant();
        f.ajouterMenus();
        Serveur s = r.renvoieUnServeur();
        s.nouvelleCommande(00001);
        Commande c = s.getListeCommandes().get(0);
        c.setDateCommande("12/01/2021");
        try {
            s.verifSiPlusDe15Jours(c);
        } catch (ParseException ex) {
            Logger.getLogger(ResourceTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        String resultatEffectif = c.getStatutCommande();
        assertEquals(resultatAttendu, resultatEffectif);
    }
    
    @Test
    public void testEpinglage3() throws java.text.ParseException {
        //ÉTANT DONNE une commande à transmettre gendarmerie
	//QUAND on consulte la liste des commandes à transmettre du restaurant
	//ALORS elle y figure
        int resultatAttendu = 1;
        Franchise f = new Franchise("Il Pizzaiolo", 1);
        Restaurant r = f.renvoiePremierRestaurant();
        f.ajouterMenus();
        Serveur s = r.renvoieUnServeur();
        s.nouvelleCommande(00001);
        Commande c = s.getListeCommandes().get(0);
        c.setDateCommande("12/01/2021");
        try {
            s.verifSiPlusDe15Jours(c);
        } catch (ParseException ex) {
            Logger.getLogger(ResourceTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        int resultatEffectif = s.getListeCommandesEpinglees().size();
        assertEquals(resultatAttendu, resultatEffectif, DELTA);
    }
    
    @Test
    public void testEpinglage4() throws java.text.ParseException {
        //ÉTANT DONNE une commande à transmettre gendarmerie
	//QUAND elle est marquée comme transmise à la gendarmerie
	//ALORS elle ne figure plus dans la liste des commandes à transmettre du restaurant
        int resultatAttendu = 0;
        Franchise f = new Franchise("Il Pizzaiolo", 1);
        Restaurant r = f.renvoiePremierRestaurant();
        f.ajouterMenus();
        Serveur s = r.renvoieUnServeur();
        s.nouvelleCommande(00001);
        Commande c = s.getListeCommandes().get(0);
        c.setDateCommande("12/01/2021");
        try {
            s.verifSiPlusDe15Jours(c);
        } catch (ParseException ex) {
            Logger.getLogger(ResourceTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        r.marquerCommandeTransmise(c);
        int resultatEffectif = r.getCommandesATransmettre().size();
        assertEquals(resultatAttendu, resultatEffectif, DELTA);
    }
        
}
