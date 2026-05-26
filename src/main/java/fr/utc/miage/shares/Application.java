package fr.utc.miage.shares;

import java.util.ArrayList;

public class Application {


    private final ArrayList<Action> actions;

   
    private Application() {
        this.actions = new ArrayList<>(); 
    }


    private static class Holder {
        private static final Application INSTANCE = new Application();
    }

    public static Application getApplication() {
        return Holder.INSTANCE;
    }


    public ArrayList<Action> getActions() {
        return actions;
    }

    public void addAction(Action action) {
        this.actions.add(action);
    }

    public void removeAction(Action action) {
        this.actions.remove(action);
    }


    public void reinitialiser() {
    this.actions.clear(); 
}
}