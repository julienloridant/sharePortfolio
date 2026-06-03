package fr.utc.miage.shares;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ApplicationTest {

    // Implémentation factice minimale de la classe abstraite Action pour les tests
    private static class ActionImpl extends Action {
        public ActionImpl(final String aLabel) {
            super(aLabel);
        }

        @Override
        public float valeur(final Jour aJour) {
            return 0.0F;
        }
    }

    private Application app;

    @BeforeEach
    void setUp() {
        // Étant donné que Application est un Singleton, son état est partagé.
        // On s'assure de repartir sur une liste vide avant chaque test.
        app = Application.getApplication();
        app.reinitialiser();
    }

    @Test
    void testGetApplication() {
        final Application instance1 = Application.getApplication();
        final Application instance2 = Application.getApplication();

        Assertions.assertNotNull(instance1, "L'instance de l'application ne doit pas être null");
        Assertions.assertSame(instance1, instance2, "L'application est un Singleton, les deux instances doivent être exactement les mêmes");
    }

    @Test
    void testGetActions() {
        Assertions.assertNotNull(app.getActions(), "La liste d'actions ne doit pas être null à l'initialisation");
        Assertions.assertTrue(app.getActions().isEmpty(), "La liste d'actions doit être vide par défaut");
    }

    @Test
    void testAddAction() {
        final Action action = new ActionImpl("Action Test Add");
        
        app.addAction(action);

        Assertions.assertEquals(1, app.getActions().size(), "La liste doit contenir exactement un élément après l'ajout");
        Assertions.assertTrue(app.getActions().contains(action), "La liste doit contenir l'action qui vient d'être ajoutée");
    }

    @Test
    void testRemoveAction() {
        final Action action = new ActionImpl("Action Test Remove");
        app.addAction(action);
        
        // On s'assure qu'elle est bien là avant de supprimer
        Assertions.assertEquals(1, app.getActions().size());

        app.removeAction(action);

        Assertions.assertTrue(app.getActions().isEmpty(), "La liste doit être de nouveau vide après la suppression");
        Assertions.assertFalse(app.getActions().contains(action), "L'action supprimée ne doit plus se trouver dans la liste");
    }

    @Test
    void testReinitialiser() {
        app.addAction(new ActionImpl("Action 1"));
        app.addAction(new ActionImpl("Action 2"));
        app.addAction(new ActionImpl("Action 3"));
        
        Assertions.assertEquals(3, app.getActions().size(), "La liste devrait contenir 3 éléments avant réinitialisation");

        app.reinitialiser();

        Assertions.assertTrue(app.getActions().isEmpty(), "La liste doit être complètement vidée après l'appel à reinitialiser()");
    }
}