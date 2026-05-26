package fr.utc.miage.shares;

import java.time.LocalDate;
import java.util.Map;

public class Gestionnaire {

    private final String nom;
    private final String prenom;

    public Gestionnaire(final String nom, final String prenom) {
        this.nom = nom;
        this.prenom = prenom;
    }

    public String getNom() {
        return this.nom;
    }

    public String getPrenom() {
        return this.prenom;
    }

    public ActionSimple creerActionSimple(final String libelle, final float valeur) throws IllegalArgumentException {
        ActionSimple action = new ActionSimple(libelle);
        int todayYear = LocalDate.now().getYear();
        int todayDay = LocalDate.now().getDayOfMonth();
        Jour todayDate = new Jour(todayYear, todayDay);

        if (Application.getApplication().getActions().contains(action)) {
            throw new IllegalArgumentException("Action already exists");
        } else {
            Application.getApplication().getActions().add(action);
            action.enrgCours(todayDate, valeur);
            return action;
        }
    } 

    public void supprimerActionSimple(final String libelle) throws IllegalArgumentException {
        ActionSimple actionToRemove = new ActionSimple(libelle);

        if (!Application.getApplication().getActions().contains(actionToRemove)) {
            throw new IllegalArgumentException("Action does not exist");
        }
        Application.getApplication().getActions().remove(actionToRemove);


    }

    //Méthode pour créer une action composée à partir d'au moins 2 actions simples
    public  ActionComposee creerActionComposee(String libelle, Map<Float, ActionSimple> map) throws IllegalArgumentException {
        if (map.size() < 2) {
            throw new IllegalArgumentException("Une action composée doit être constituée d'au moins 2 actions simples.");
        }
        ActionComposee actionComposee = new ActionComposee(libelle, map);
        if (Application.getApplication().getActions().contains(actionComposee)) {
            throw new IllegalArgumentException("Action already exists");
        } else {
            Application.getApplication().getActions().add(actionComposee);
            return actionComposee;
        }
    }
    


    

} 