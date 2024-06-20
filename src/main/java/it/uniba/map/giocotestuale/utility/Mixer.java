package it.uniba.map.giocotestuale.utility;

public class Mixer {
    public static Mixer instance;

    private Mixer() {
        instance = this;
    }

    public static Mixer getInstance() {
        if (instance == null) {
            instance = new Mixer();
        }
        return instance;
    }
}
