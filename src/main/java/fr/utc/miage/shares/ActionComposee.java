package fr.utc.miage.shares;
import java.util.Map;

public class ActionComposee extends Action {

    //Attributs
    private Map<Float,ActionSimple> listeActionSimple;

    //Construc
    public ActionComposee(String Libelle, Map<Float,ActionSimple> map){
        super(Libelle);
        this.listeActionSimple = map;
    }

    //Get and set 
    public Map<Float, ActionSimple> getListeActionSimple() {
        return listeActionSimple;
    }

    //Méthodes
    @Override
    public float valeur(Jour j) {
        float valeur = 0;
        for (Map.Entry<Float, ActionSimple> entry : this.listeActionSimple.entrySet()) {
            valeur += entry.getKey() * entry.getValue().valeur(j);
        }
        return valeur;
    }
    @Override 
    public int hashCode() {
        int hash = 7;
        return hash;
    }
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final ActionComposee other = (ActionComposee) obj;
        return true;
    }

    //Méthode pour créer une action composée à partir d'au moins 2 actions simples
    public static ActionComposee creerActionComposee(String libelle, Map<Float, ActionSimple> map) throws IllegalArgumentException {
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
