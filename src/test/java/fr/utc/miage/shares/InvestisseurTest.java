package fr.utc.miage.shares;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;



public class InvestisseurTest {

    private static final String NOM_CORRECT = "Lapoule";
    private static final String PRENOM_CORRECT = "Cecile";

   /* @Test
    public void testGetPortefeuillewithCorrectsParams () {

        Investisseur currentInvestisseur = new Investisseur (NOM_CORRECT, PRENOM_CORRECT);

        ArrayList<Action> portefeuille = currentInvestisseur.getApplication();

        assertEquals(portefeuille, new ArrayList<Action>());

    }*/

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


}
