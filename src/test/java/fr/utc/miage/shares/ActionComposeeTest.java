package fr.utc.miage.shares;
import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ActionComposeeTest {


    @BeforeEach
    void setUp() {
        // Reste maître de ton environnement : on nettoie le Singleton !
        Application.getApplication().reinitialiser();
    }

    @Test
    void testEquals() {
        ActionSimple actionSimple1 = new ActionSimple("Action Simple 1");
        ActionSimple actionSimple2 = new ActionSimple("Action Simple 2");

        // ✅ ActionSimple = clé, Float = pourcentage
        Map<ActionSimple, Float> map1 = new HashMap<>();
        map1.put(actionSimple1, 0.7f);
        map1.put(actionSimple2, 0.3f);

        Map<ActionSimple, Float> map2 = new HashMap<>();
        map2.put(actionSimple1, 0.4f);
        map2.put(actionSimple2, 0.6f);

        ActionComposee actionComposee1 = new ActionComposee("Action Composee", map1);
        ActionComposee actionComposee2 = new ActionComposee("Action Composee", map2);

        assert actionComposee1.equals(actionComposee2) 
            : "Two ActionComposee with the same libelle should be equal";
    }

    @Test
    void testGetListeActionSimple() {
        ActionSimple actionSimple1 = new ActionSimple("Action Simple 1");
        ActionSimple actionSimple2 = new ActionSimple("Action Simple 2");

        Map<ActionSimple, Float> map = new HashMap<>();
        map.put(actionSimple1, 0.5f);
        map.put(actionSimple2, 0.5f); // ✅ même pourcentage OK car clés différentes

        ActionComposee actionComposee = new ActionComposee("Action Composee", map);

        assert actionComposee.getListeActionSimple().equals(map) 
            : "getListeActionSimple should return the map used in the constructor";
    }

    @Test
    void testHashCode() {
        ActionSimple actionSimple1 = new ActionSimple("Action Simple 1");
        ActionSimple actionSimple2 = new ActionSimple("Action Simple 2");

        Map<ActionSimple, Float> map = new HashMap<>();
        map.put(actionSimple1, 0.5f);
        map.put(actionSimple2, 0.5f);

        ActionComposee actionComposee1 = new ActionComposee("Action Composee", map);
        ActionComposee actionComposee2 = new ActionComposee("Action Composee", map);

        // ✅ Deux objets égaux doivent avoir le même hashCode
        assert actionComposee1.hashCode() == actionComposee2.hashCode() 
            : "Equal ActionComposee objects should have the same hashCode";
    }

    // @Test
    // void testCreerActionComposee() {
    //     Jour today = new Jour(LocalDate.now().getYear(), LocalDate.now().getDayOfMonth());
    //     Gestionnaire gestionnaire = new Gestionnaire("Doe", "John");

    //     Map<Jour, Float> cours1 = new HashMap<>();
    //     cours1.put(today, 100.0f);
    //     // ✅ Noms uniques pour éviter les conflits avec d'autres tests
    //     ActionSimple actionSimple1 = gestionnaire.creerActionSimple("Action Composee Simple 1", cours1);

    //     Map<Jour, Float> cours2 = new HashMap<>();
    //     cours2.put(today, 200.0f);
    //     ActionSimple actionSimple2 = gestionnaire.creerActionSimple("Action Composee Simple 2", cours2);

    //     Map<ActionSimple, Float> map = new HashMap<>();
    //     map.put(actionSimple1, 0.3f);
    //     map.put(actionSimple2, 0.7f);

    //     ActionComposee actionComposee = new ActionComposee("Action Composee", map);

    //     assert actionComposee.getListeActionSimple().equals(map)
    //         : "The created ActionComposee should contain the correct map of simple actions";
    // }
}