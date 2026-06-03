package fr.utc.miage.shares;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class GestionnaireTest {

    private Gestionnaire gestionnaire;

    @BeforeEach
    void setUp() {
        // Reste maître de ton environnement : on nettoie le Singleton !
        Application.getApplication().reinitialiser();
        this.gestionnaire = new Gestionnaire("Doe", "John");
    }

    @Test
    void testCreateActionSimpleDoitRetournerUneActionAvecLesValeursSpecifiees() {
        ActionSimple action = gestionnaire.creerActionSimple("TestAction", new java.util.HashMap<>(){{
            put(new Jour(LocalDate.now().getYear(), LocalDate.now().getDayOfMonth()), 100.0f);
        }});
        Jour today = new Jour(LocalDate.now().getYear(), LocalDate.now().getDayOfMonth());
        
        // Ajout du delta de tolérance pour le float (0.001f)
        assertEquals(100.0f, action.valeur(today), 0.001f);
    }

    @Test
    void testCreateActionSimpleMemeDateMemeLibelleNeDoitPasFonctionner() {
        gestionnaire.creerActionSimple("action", new java.util.HashMap<>(){{
            put(new Jour(LocalDate.now().getYear(), LocalDate.now().getDayOfMonth()), 50.0f);
        }});
        
        assertThrows(IllegalArgumentException.class, () -> {
            gestionnaire.creerActionSimple("action", new java.util.HashMap<>(){{
                put(new Jour(LocalDate.now().getYear(), LocalDate.now().getDayOfMonth()), 50.0f);
            }});
        });
    }

    @Test
    void testSupprimerActionSimpleDoitSupprimerLAction() {
        gestionnaire.creerActionSimple("actionToRemove", new java.util.HashMap<>(){{
            put(new Jour(LocalDate.now().getYear(), LocalDate.now().getDayOfMonth()), 50.0f);
        }});
        
        // Vérifie que l'action a été ajoutée
        assertEquals(1, Application.getApplication().getActions().size());
        
        gestionnaire.supprimerActionSimple("actionToRemove");
        
        // Vérifie que l'action a été supprimée
        assertEquals(0, Application.getApplication().getActions().size());
    }

    @Test
    void testSupprimerActionSimpleActionInexistanteDoitLancerException() {
        assertThrows(IllegalArgumentException.class, () -> {
            gestionnaire.supprimerActionSimple("nonExistentAction");
        });
    }

    @Test
    void testModifierActionSimpleDoitModifierLaValeurDeLAction() {
        Jour today = new Jour(LocalDate.now().getYear(), LocalDate.now().getDayOfMonth());
        
        Map<Jour, Float> cours = new HashMap<>();
        cours.put(today, 50.0f);
        gestionnaire.creerActionSimple("actionToModify", cours);
        
        Map<Jour, Float> coursMaj = new HashMap<>();
        coursMaj.put(today, 75.0f);
        gestionnaire.modifierCoursActionSimple("actionToModify", coursMaj);
        
        ActionSimple action = (ActionSimple) Application.getApplication().getActions().get(0);
        assertEquals(75.0f, action.valeur(today), 0.001f);
    }

    @Test
    void testModifierActionSimpleActionInexistanteDoitLancerException() {
        Jour today = new Jour(LocalDate.now().getYear(), LocalDate.now().getDayOfMonth());
        
        Map<Jour, Float> coursMaj = new HashMap<>();
        coursMaj.put(today, 75.0f);
        
        assertThrows(IllegalArgumentException.class, () -> {
            gestionnaire.modifierCoursActionSimple("nonExistentAction", coursMaj);
        });
}

    @Test
    void testModifierActionSimpleAvecMemeValeurNeDoitPasModifierLaValeur() {
        Jour today = new Jour(LocalDate.now().getYear(), LocalDate.now().getDayOfMonth());
        
        Map<Jour, Float> cours = new HashMap<>();
        cours.put(today, 50.0f);
        gestionnaire.creerActionSimple("actionToModify", cours);
        
        // Même valeur, même jour
        Map<Jour, Float> coursMaj = new HashMap<>();
        coursMaj.put(today, 50.0f);
        gestionnaire.modifierCoursActionSimple("actionToModify", coursMaj);
        
        ActionSimple action = (ActionSimple) Application.getApplication().getActions().get(0);
        assertEquals(50.0f, action.valeur(today), 0.001f);
    }
}