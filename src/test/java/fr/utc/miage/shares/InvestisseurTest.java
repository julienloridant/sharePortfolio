package fr.utc.miage.shares;
import java.time.LocalDate;
import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;


class InvestisseurTest {

    private static final String NOM_CORRECT = "Lapoule";
    private static final String PRENOM_CORRECT = "Cecile";
    private Investisseur investisseur;

    @BeforeEach
    void setUp() {
        Application.getApplication().reinitialiser();
        this.investisseur = new Investisseur(NOM_CORRECT, PRENOM_CORRECT);
    }

    private static final ActionSimple ACTION_APPLE = new ActionSimple("Apple", new java.util.HashMap<>(){{
        put(new Jour(LocalDate.now().getYear(), LocalDate.now().getDayOfMonth()), 100.0f);
    }});

    private static final ActionSimple ACTION_CAPGEMINI = new ActionSimple("Apple", new java.util.HashMap<>(){{
        put(new Jour(LocalDate.now().getYear(), LocalDate.now().getDayOfMonth()), 50.0f);
    }});

    private static final Action ACTION_COMPOSEE = new ActionComposee("Composée Apple et Capgemini", new java.util.HashMap<>(){{
        put(ACTION_APPLE, 0.5f);
        put(ACTION_CAPGEMINI, 0.5f);
    }});

    @Test
    public void testGetPortefeuillewithCorrectsParams() {
        ArrayList<Action> portefeuille = Application.getApplication().getActions();
        assertEquals(portefeuille, new ArrayList<Action>());
    }

    @Test
    public void testConstructorwithCorrectsParams() {
        assertDoesNotThrow(() -> new Investisseur(NOM_CORRECT, PRENOM_CORRECT));
    }

    @Test
    public void testAllGetterswithCorrectsParams() {
        Investisseur currentInvestisseur = new Investisseur(NOM_CORRECT, PRENOM_CORRECT);

        assertAll("GettersCorrectsParams",
                () -> assertEquals(currentInvestisseur.getNom(), NOM_CORRECT),
                () -> assertEquals(currentInvestisseur.getPrenom(), PRENOM_CORRECT)
        );
    }

    @Test
    public void testAllSetterswithCorrectsParams() {
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
        assertAll("VendreAction",
                () -> assertEquals(100.0f, investisseur.getSolde(), 0.001f, "Le solde de l'investisseur doit être de 100"),
                () -> assertFalse(investisseur.getPortefeuille().contains(ACTION_APPLE), "L'action doit être retirée du portefeuille")
        );
    }

    @Test
    public void testSetSolde() {
        Investisseur investisseur = new Investisseur(NOM_CORRECT, PRENOM_CORRECT);
        investisseur.setSolde(250.75f);

        assertEquals(250.75f, investisseur.getSolde(), 0.001f, "Le solde doit être correctement mis à jour");
    }

    @Test
    public void testRetournerCoursActionSimple() {
        // GIVEN
        ActionSimple action = new ActionSimple("Apple Static Test");
        LocalDate date = LocalDate.now();
        Jour jourActuel = new Jour(date.getYear(), date.getDayOfYear());
        action.enrgCours(jourActuel, 150.0f);

        // WHEN
        float cours = Investisseur.retournerCoursActionSimple(action);

        // THEN
        assertEquals(150.0f, cours, 0.001f, "Doit retourner le cours pour le jour actuel");
    }

    @Test
    public void testAfficherListeActions() {
        // GIVEN
        Application.getApplication().reinitialiser();
        ActionSimple actionTest = new ActionSimple("Action Globale");
        Application.getApplication().addAction(actionTest);

        Investisseur investisseur = new Investisseur(NOM_CORRECT, PRENOM_CORRECT);

        // WHEN
        ArrayList<Action> listeAffichee = investisseur.afficherListeActions();

        // THEN
        assertEquals(1, listeAffichee.size(), "Doit récupérer la liste depuis l'Application");
        assertEquals(actionTest, listeAffichee.get(0), "L'action récupérée doit correspondre à celle de l'Application");
    }

    @Test
    public void testVendreActionLaisseExceptionSiPasActionSimple() {
        // GIVEN
        Investisseur investisseur = new Investisseur(NOM_CORRECT, PRENOM_CORRECT);

        Action actionInvalide = new Action("Action Invalide") {
            @Override
            public float valeur(Jour j) {
                return 10.0f;
            }
        };

        // WHEN & THEN
        assertThrows(IllegalArgumentException.class, () -> {
            investisseur.vendreAction(actionInvalide, 5);
        }, "Doit lancer une exception car l'action n'est pas une instance de ActionSimple");
    }

    @Test
    void testRetirerAvecSoldeSuffisant() {
        // GIVEN
        investisseur.setSolde(200.0f);

        // WHEN
        investisseur.retirerSolde(150.0f);

        // THEN
        assertEquals(50.0f, investisseur.getSolde(), 0.001f, "Le solde de l'investisseur doit être de 50 après le retrait");
    }

    @Test
    void testRetirerAvecSoldeInsuffisant() {
        // GIVEN
        investisseur.setSolde(100.0f);

        // WHEN & THEN
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            investisseur.retirerSolde(150.0f);
        });

        assertEquals("Solde insuffisant", exception.getMessage(), "Le message d'erreur doit indiquer un solde insuffisant");
    }

    @Test
    void testAjouterSoldeAvecMontantPositif() {
        // GIVEN
        investisseur.setSolde(200.0f);

        // WHEN
        investisseur.ajouterSolde(150.0f);

        // THEN
        assertEquals(350.0f, investisseur.getSolde(), 0.001f, "Le solde de l'investisseur doit être de 350 après l'ajout");
    }

    @Test
    void testAcheterAction() {
        // GIVEN
        investisseur.setSolde(100.0f);

        // WHEN
        investisseur.acheterAction(ACTION_APPLE, 1);

        // THEN
        assertAll("AcheterAction",
                () -> assertEquals(0.0f, investisseur.getSolde(), 0.001f),
                () -> assertTrue(investisseur.getPortefeuille().contains(ACTION_APPLE))
        );
    }

    @Test
    void testAjouterSoldeAvecMontantNegatif() {
        // GIVEN
        investisseur.setSolde(200.0f);

        // WHEN & THEN
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            investisseur.ajouterSolde(-150.0f);
        });

        assertEquals("Le montant doit être positif", exception.getMessage(), "Le message d'erreur doit indiquer un montant négatif");
    }
}