package fr.utc.miage.shares;
import java.time.LocalDate;



public class Investisseur {


    public Investisseur() {

    }

    public static float retournerCoursActionSimple(ActionSimple uneActionSimple){

        LocalDate date = LocalDate.now();
        int annee = date.getYear(); // Année
        int jourDeLAnnee = date.getDayOfYear(); // Jour
        Jour jourActuel = new Jour(annee, jourDeLAnnee);

        return uneActionSimple.valeur(jourActuel);
        
    }


    

}
