package fr.utc.miage.shares;
import java.util.Map;
import java.util.Objects;

public class ActionComposee extends Action {

    // Attributs
    private Map<ActionSimple, Float> listeActionSimple;

    // Constructeur
    public ActionComposee(String libelle, Map<ActionSimple, Float> map) {
        super(libelle);
        this.listeActionSimple = map;
    }

    // Getter
    public Map<ActionSimple, Float> getListeActionSimple() {
        return listeActionSimple;
    }

    // Méthodes
    @Override
    public float valeur(Jour j) {
        float valeur = 0;
        for (Map.Entry<ActionSimple, Float> entry : this.listeActionSimple.entrySet()) {
            valeur += entry.getValue() * entry.getKey().valeur(j);
        }
        return valeur;
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.getLibelle());
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        final ActionComposee other = (ActionComposee) obj;
        return Objects.equals(this.getLibelle(), other.getLibelle());
    }
}