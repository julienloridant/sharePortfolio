package fr.utc.miage.shares;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.Test;

class GestionnaireTest {

    @Test
    void testCreateActionSimpleDoitRetournerUneActionAvecLesValeursSpecifiees() {
        Gestionnaire gestionnaire = new Gestionnaire("Doe", "John");
        ActionSimple action = gestionnaire.createActionSimple("TestAction", 100.0f);
        Jour today = new Jour(LocalDate.now().getYear(), LocalDate.now().getDayOfMonth());
        assertEquals(100.0f, action.valeur(today));
    }

    @Test
    void testCreateActionSimpleMemeDateMemeLibelleNeDoitPasFonctionner() {
        Gestionnaire gestionnaire = new Gestionnaire("Doe", "John");
        gestionnaire.createActionSimple("action", 50.0f);
        assertThrows(IllegalArgumentException.class, () -> gestionnaire.createActionSimple("action", 50.0f));
    }




    
}
