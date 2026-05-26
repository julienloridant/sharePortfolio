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

}
 