package fr.utc.miage.shares;

import java.time.LocalDate;
import java.util.ArrayList;

public class Investisseur {

    private String nom; 
    private String prenom;
    private float solde;
    private ArrayList<Action> portefeuille;
   

    public Investisseur(String nom, String prenom) {
        this.nom = nom;
        this.prenom = prenom;
        this.solde = 0.0f;
        this.portefeuille = new ArrayList<>();
      
    }

    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }
    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public float getSolde() {
        return solde;
    }

    public void setSolde(float solde) {
        this.solde = solde;
    }

    public ArrayList<Action> getPortefeuille() {
        return portefeuille;
    }

    public void setPortefeuille(ArrayList<Action> portefeuille) {
        this.portefeuille = portefeuille;
    }

    public static float retournerCoursActionSimple(ActionSimple uneActionSimple){

        LocalDate date = LocalDate.now();
        int annee = date.getYear(); // Année
        int jourDeLAnnee = date.getDayOfYear(); // Jour
        Jour jourActuel = new Jour(annee, jourDeLAnnee);

        return uneActionSimple.valeur(jourActuel);
        
    }

   
    public ArrayList<Action> afficherListeActions() {
        
        return Application.getApplication().getActions(); 
    }

    public float vendreAction(Action action, int quantite) {
        float montantVente = action.getValeur() * quantite;
        solde += montantVente; 
        portefeuille.remove(action);
        return montantVente;
    }


}
