package fr.utc.miage.shares;

import java.time.LocalDate;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class GestionnaireTest {

    @BeforeEach
    void setUp() {
        // Reste maître de ton environnement : on nettoie le Singleton !
        Application.getApplication().reinitialiser();
    }

    @Test
    void testCreateActionSimpleDoitRetournerUneActionAvecLesValeursSpecifiees() {
        Gestionnaire gestionnaire = new Gestionnaire("Doe", "John");
        ActionSimple action = gestionnaire.createActionSimple("TestAction", 100.0f);
        Jour today = new Jour(LocalDate.now().getYear(), LocalDate.now().getDayOfMonth());
        
        // Ajout du delta de tolérance pour le float (0.001f)
        assertEquals(100.0f, action.valeur(today), 0.001f);
    }

    @Test
    void testCreateActionSimpleMemeDateMemeLibelleNeDoitPasFonctionner() {
        Gestionnaire gestionnaire = new Gestionnaire("Doe", "John");
        gestionnaire.createActionSimple("action", 50.0f);
        
        assertThrows(IllegalArgumentException.class, () -> {
            gestionnaire.createActionSimple("action", 50.0f);
        });
    }
}