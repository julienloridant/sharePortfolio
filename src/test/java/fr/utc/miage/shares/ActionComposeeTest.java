package fr.utc.miage.shares;
import java.util.Map;

import org.junit.jupiter.api.Test;

public class ActionComposeeTest {
    @Test
    void testEquals() { 
        ActionSimple actionSimple1 = new ActionSimple("Action Simple 1"); 
        ActionSimple actionSimple2 = new ActionSimple("Action Simple 2");

        Map<Float, ActionSimple> map1 = Map.of(0.5f, actionSimple1, 0.5f, actionSimple2);
        Map<Float, ActionSimple> map2 = Map.of(0.5f, actionSimple1, 0.5f, actionSimple2);

        ActionComposee actionComposee1 = new ActionComposee("Action Composee", map1);
        ActionComposee actionComposee2 = new ActionComposee("Action Composee", map2);

        assert actionComposee1.equals(actionComposee2) : "Two ActionComposee objects with the same properties should be equal";

    }

    @Test
    void testGetListeActionSimple() {
        ActionSimple actionSimple1 = new ActionSimple("Action Simple 1"); 
        ActionSimple actionSimple2 = new ActionSimple("Action Simple 2");

        Map<Float, ActionSimple> map = Map.of(0.5f, actionSimple1, 0.5f, actionSimple2);

        ActionComposee actionComposee = new ActionComposee("Action Composee", map);

        assert actionComposee.getListeActionSimple().equals(map) : "getListeActionSimple should return the map of simple actions used in the constructor";

    }

    @Test
    void testHashCode() {
        ActionSimple actionSimple1 = new ActionSimple("Action Simple 1"); 
        ActionSimple actionSimple2 = new ActionSimple("Action Simple 2");

        Map<Float, ActionSimple> map = Map.of(0.5f, actionSimple1, 0.5f, actionSimple2);

        ActionComposee actionComposee = new ActionComposee("Action Composee", map);

        assert actionComposee.hashCode() == 7 : "hashCode should return the constant value 7";

    }
    //Test Création Action Composee
    @Test
    void testCreerActionComposee() {
        Gestionnaire gestionnaire = new Gestionnaire("Doe", "John");
        ActionSimple actionSimple1 = gestionnaire.creerActionSimple("Action Simple 1", 100);
        ActionSimple actionSimple2 = gestionnaire.creerActionSimple("Action Simple 2", 200);

        Map<Float, ActionSimple> map = Map.of(0.5f, actionSimple1, 0.5f, actionSimple2);

        ActionComposee actionComposee = gestionnaire.creerActionComposee("Action Composee", map);

        assert actionComposee.getListeActionSimple().equals(map) : "The created ActionComposee should contain the correct map of simple actions";
    }
}
