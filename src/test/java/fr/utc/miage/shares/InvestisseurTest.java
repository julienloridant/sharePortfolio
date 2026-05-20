package fr.utc.miage.shares;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

public class InvestisseurTest {
    
    
    @Test
    void devraitRetournerLeBonCoursDeLAction() {

        // GIVEN
        ActionSimple action = new ActionSimple("Apple");
        LocalDate date = LocalDate.now();
        Jour jourActuel = new Jour(date.getYear(), date.getDayOfYear());

        // WHEN
        action.enrgCours(jourActuel, 105.2f);
        float cours = action.valeur(jourActuel);

        // THEN
        assertEquals(105.2f, cours, 0.001f);
        

    }
}
