package fr.utc.miage.shares;
import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.util.ArrayList;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


public class InvestisseurTest {

    private static final String NOM_CORRECT = "Lapoule";
    private static final String PRENOM_CORRECT = "Cecile";
    private static final Action ACTION_APPLE = new ActionSimple("Apple", new java.util.HashMap<>(){{
        put(new Jour(LocalDate.now().getYear(), LocalDate.now().getDayOfMonth()), 100.0f);
    }});   


    @Test 
    public void testConstructorwithCorrectsParams () {

       assertDoesNotThrow(() -> new Investisseur(NOM_CORRECT, PRENOM_CORRECT));

    }

    @Test
    public void testAllGetterswithCorrectsParams () {
        Investisseur currentInvestisseur = new Investisseur(NOM_CORRECT, PRENOM_CORRECT);

        assertAll("GettersCorrectsParams",
                () -> assertEquals(currentInvestisseur.getNom(), NOM_CORRECT),
                () -> assertEquals(currentInvestisseur.getPrenom(), PRENOM_CORRECT)
        );
        
    }

    @Test
    public void testAllSetterswithCorrectsParams () {
        Investisseur currentInvestisseur = new Investisseur(NOM_CORRECT, PRENOM_CORRECT);

        String newNom = "LePoulet";
        String newPrenom = "Cecilia";

        currentInvestisseur.setNom(newNom);
        currentInvestisseur.setPrenom(newPrenom);

        assertAll("SettersCorrectsParams",
                () -> assertEquals(currentInvestisseur.getNom(), newNom),
                () -> assertEquals(currentInvestisseur.getPrenom(), newPrenom)
        );
        
    }
  
    @Test
    void devraitRetournerLeBonCoursDeLAction() {

        // GIVEN
        ActionSimple action = new ActionSimple("Apple", new java.util.HashMap<>());
        LocalDate date = LocalDate.now();
        Jour jourActuel = new Jour(date.getYear(), date.getDayOfYear());

        // WHEN
        action.enrgCours(jourActuel, 105.2f);
        float cours = action.valeur(jourActuel);

        // THEN
        assertEquals(105.2f, cours, 0.001f);
        

    }

    @Test
    void testVendreAction() {
        // GIVEN
        Investisseur investisseur = new Investisseur(NOM_CORRECT, PRENOM_CORRECT);
        investisseur.setPortefeuille(new ArrayList<Action>() {{
            add(ACTION_APPLE);
        }});


        // WHEN
        investisseur.vendreAction(ACTION_APPLE, 1);

        // THEN
        Assertions.assertAll("VendreAction",
                () -> assertEquals(100.0f, investisseur.getSolde(), 0.001f, "Le solde de l'investisseur doit être de 100"),
                () -> assertFalse(investisseur.getPortefeuille().contains(ACTION_APPLE), "L'action doit être retirée du portefeuille")
        );
    }
}
