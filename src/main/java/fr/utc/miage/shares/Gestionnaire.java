package fr.utc.miage.shares;

import java.time.LocalDate;

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

    public ActionSimple createActionSimple(final String libelle, java.util.Map<Jour, Float> cours) throws IllegalArgumentException {
        ActionSimple action = new ActionSimple(libelle, cours);
        if (Application.getApplication().getActions().contains(action)) {
            throw new IllegalArgumentException("Action already exists");
        } else {
            Application.getApplication().getActions().add(action);
            return action;
        }
    } 

    public void supprimerActionSimple(final String libelle) throws IllegalArgumentException {
        ActionSimple actionToRemove = new ActionSimple(libelle, new java.util.HashMap<>());

        if (!Application.getApplication().getActions().contains(actionToRemove)) {
            throw new IllegalArgumentException("Action does not exist");
        }
        Application.getApplication().getActions().remove(actionToRemove);


    }

    public void modifierCoursActionSimple(final String Libelle, final float valeur) throws IllegalArgumentException {
        ActionSimple actionAModifier = new ActionSimple(Libelle);
        int index = Application.getApplication().getActions().indexOf(actionAModifier);

        if (index == -1) {
            throw new IllegalArgumentException("Action does not exist");
        }

        ActionSimple action = (ActionSimple) Application.getApplication().getActions().get(index);

        int todayYear = LocalDate.now().getYear();
        int todayDay = LocalDate.now().getDayOfMonth();
        Jour todayDate = new Jour(todayYear, todayDay);

        action.modifierCours(todayDate, valeur);
    }

} 