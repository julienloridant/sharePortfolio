package fr.utc.miage.shares;

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

    public ActionSimple creerActionSimple(final String libelle, java.util.Map<Jour, Float> cours) throws IllegalArgumentException {
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
    


    
    public void modifierCoursActionSimple(final String libelle, Map<Jour, Float> cours) {
    for (Action a : Application.getApplication().getActions()) {
        if (a.getLibelle().equals(libelle) && a instanceof ActionSimple) {
            ActionSimple action = (ActionSimple) a;
            for (Map.Entry<Jour, Float> entry : cours.entrySet()) {
                action.enrgCours(entry.getKey(), entry.getValue());
            }
            return;
        }
    }
    throw new IllegalArgumentException("Action not found: " + libelle);
}

} 