package fr.utc.miage.shares;

import java.time.LocalDate;
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



}
