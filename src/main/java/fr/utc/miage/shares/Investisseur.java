package fr.utc.miage.shares;

import java.util.ArrayList;

public class Investisseur {

    private String nom; 
    private String prenom;
   


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

   

    public Investisseur(String nom, String prenom) {
        this.nom = nom;
        this.prenom = prenom;
      
    }

   
    /*public ArrayList<Action> afficherListeActions() {
        
        return getApplication; //
    }*/
   


}
