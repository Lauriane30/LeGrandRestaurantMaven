package com.legrandrestaurantmaven;

import java.sql.SQLException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Consumes;
import javax.ws.rs.Path;
import javax.enterprise.context.RequestScoped;
import javax.ws.rs.POST;
import org.json.JSONException;
import org.json.JSONObject;

@Path("restauranttdd")
@RequestScoped
public class Resource {

    @Context
    private UriInfo context;

    public Resource() {
    }

    @Path("/nouveauServeur")
    @POST
    @Consumes(javax.ws.rs.core.MediaType.APPLICATION_JSON)
    public String nouveauServeur() throws SQLException, JSONException {
        JSONObject result = new JSONObject();
        Serveur s = new Serveur("Bobby", new Restaurant("Il Pizzaiolo", new Carte()));
        double retour = s.getChiffreAffaires();
        result.put("ca", retour);
        return result.toString();
    }

    @POST
    @Path("/nouveauServeur2")
    @Consumes(javax.ws.rs.core.MediaType.APPLICATION_JSON)
    public String nouveauServeur2() throws SQLException, JSONException {
        JSONObject retour = new JSONObject();
        Serveur s = new Serveur("Bobby", new Restaurant("Il Pizzaiolo", new Carte()));
        double resultatEffectif = s.nouvelleCommande(000001);
        retour.put("nom", s.getNom());
        retour.put("ca", resultatEffectif);
        return retour.toString();
    }
    
    @POST
    @Path("/nouveauServeur3")
    @Consumes(javax.ws.rs.core.MediaType.APPLICATION_JSON)
    public String nouveauServeur3() throws SQLException, JSONException {
        JSONObject retour = new JSONObject();
        Serveur s = new Serveur("Bobby", new Restaurant("Il Pizzaiolo", new Carte()));
        double resultatEffectif = s.nouvelleCommande(000001) + s.nouvelleCommande(000002);
        retour.put("nom", s.getNom());
        retour.put("ca", resultatEffectif);
        return retour.toString();
    }
    
    @POST
    @Path("/Restaurant")
    @Consumes(javax.ws.rs.core.MediaType.APPLICATION_JSON)
    public String restaurant() throws SQLException, JSONException {
        JSONObject retour = new JSONObject();
        double resultatEffectif = 0;
        Franchise f = new Franchise("Il Pizzaiolo", 1);
        for(Restaurant r : f.getListeRestaurants()) {
            resultatEffectif = r.renvoieMontantCommandesServeurs();
        };
        f.setChiffreAffairesFranchise(resultatEffectif);
        retour.put("nom", f.getNom());
        retour.put("ca", resultatEffectif);
        return retour.toString();
    }
    
    @POST
    @Path("/Franchise")
    @Consumes(javax.ws.rs.core.MediaType.APPLICATION_JSON)
    public String franchise() throws SQLException, JSONException {
        JSONObject retour = new JSONObject();
        double resultatEffectif = 0;
        int nbRestaurants = 3;
        // ÉTANT DONNÉ une franchise ayant X restaurants de Y serveurs chacuns
        Franchise f = new Franchise("Il Pizzaiolo", nbRestaurants);
        for(Restaurant r : f.getListeRestaurants()) {
            resultatEffectif = r.renvoieMontantCommandesServeurs();
        };
        f.setChiffreAffairesFranchise(resultatEffectif);
        retour.put("nom", f.getNom());
        retour.put("ca", resultatEffectif * nbRestaurants);
        return retour.toString();
    }
}
