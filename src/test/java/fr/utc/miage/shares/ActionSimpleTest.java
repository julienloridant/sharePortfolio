package fr.utc.miage.shares;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ActionSimpleTest {

    private static final String DEFAULT_LABEL = "Test Action Simple";

    @Test
    void testConstructors() {
        // Test du constructeur avec Map
        final Map<Jour, Float> map = new HashMap<>();
        final ActionSimple action1 = new ActionSimple(DEFAULT_LABEL, map);
        Assertions.assertSame(map, action1.getCours(), "Le constructeur doit affecter la Map fournie");

        // Test du constructeur sans Map
        final ActionSimple action2 = new ActionSimple(DEFAULT_LABEL);
        Assertions.assertNotNull(action2.getCours(), "Le constructeur doit initialiser une nouvelle Map");
        Assertions.assertTrue(action2.getCours().isEmpty(), "La Map initialisée par défaut doit être vide");
    }

    @Test
    void testEnrgCours() {
        final ActionSimple action = new ActionSimple(DEFAULT_LABEL);
        final Jour jour = null; // Utilisation de null pour contourner l'absence d'instanciation de Jour

        // Test : enregistrement si le cours n'existe pas
        action.enrgCours(jour, 10.5f);
        Assertions.assertEquals(10.5f, action.getCours().get(jour), "La valeur doit être enregistrée car le jour n'existait pas");

        // Test : pas d'enregistrement si le cours existe déjà
        action.enrgCours(jour, 20.0f);
        Assertions.assertEquals(10.5f, action.getCours().get(jour), "La valeur NE doit PAS être écrasée si le jour existe déjà");
    }

    @Test
    void testEquals() {
        final ActionSimple action1 = new ActionSimple(DEFAULT_LABEL);
        final ActionSimple action2 = new ActionSimple("Autre Action");

        // Mêmes instances
        Assertions.assertEquals(action1, action1, "Doit être égal à lui-même");
        
        // Objet Null
        Assertions.assertNotEquals(action1, null, "Ne doit pas être égal à null");
        
        // Classe différente
        Assertions.assertNotEquals(action1, new Object(), "Ne doit pas être égal à un objet d'une autre classe");
        
        // Selon l'implémentation actuelle, la méthode equals retourne inconditionnellement true 
        // pour deux instances d'ActionSimple (même si les libellés diffèrent).
        Assertions.assertEquals(action1, action2, "Doit être égal selon le code actuel (return true en fin de méthode)");
    }

    @Test
    void testGetCours() {
        final Map<Jour, Float> map = new HashMap<>();
        final ActionSimple action = new ActionSimple(DEFAULT_LABEL, map);
        
        Assertions.assertSame(map, action.getCours(), "Doit retourner l'instance exacte de la Map");
    }

    @Test
    void testHashCode() {
        final ActionSimple action = new ActionSimple(DEFAULT_LABEL);
        
        Assertions.assertEquals(7, action.hashCode(), "Le hashcode doit toujours retourner 7 selon l'implémentation");
    }

    @Test
    void testModifierCours() {
        final ActionSimple action = new ActionSimple(DEFAULT_LABEL);
        final Jour jour = null;

        // Test : modification sur un jour inexistant
        action.modifierCours(jour, 15.0f);
        Assertions.assertTrue(action.getCours().isEmpty(), "Ne doit rien modifier si le jour n'existe pas dans la Map");

        // Test : modification sur un jour existant
        action.enrgCours(jour, 10.0f);
        action.modifierCours(jour, 25.5f);
        Assertions.assertEquals(25.5f, action.getCours().get(jour), "La valeur doit être mise à jour");
    }

    @Test
    void testModifierValeurAction() {
        final ActionSimple action = new ActionSimple(DEFAULT_LABEL);
        
        // La méthode étant vide dans la classe, on vérifie juste qu'elle s'exécute sans erreur
        Assertions.assertDoesNotThrow(() -> action.modifierValeurAction(action, 10.0f), 
                "L'exécution de la méthode vide ne doit lever aucune exception");
    }

    @Test
    void testValeur() {
        final ActionSimple action = new ActionSimple(DEFAULT_LABEL);
        final Jour jour = null;

        // Test : valeur pour un jour inexistant
        Assertions.assertEquals(0.0f, action.valeur(jour), "Doit retourner la DEFAULT_ACTION_VALUE (0.0f) si le jour est absent");

        // Test : valeur pour un jour existant
        action.enrgCours(jour, 42.0f);
        Assertions.assertEquals(42.0f, action.valeur(jour), "Doit retourner la valeur enregistrée");
    }
}