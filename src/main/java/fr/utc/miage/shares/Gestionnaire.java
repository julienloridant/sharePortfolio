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

    public ActionSimple createActionSimple(final String libelle, final float valeur) throws IllegalArgumentException {
        ActionSimple action = new ActionSimple(libelle, new java.util.HashMap<Jour, Float>(){{
            put(new Jour(LocalDate.now().getYear(), LocalDate.now().getDayOfYear()), valeur);
        }});
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

} 