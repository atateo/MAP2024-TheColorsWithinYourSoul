package it.uniba.map.giocotestuale.impl;

import it.uniba.map.giocotestuale.logic.GameEngine;

public class ColorsWithinYourSoulGame extends GameEngine {

    public ColorsWithinYourSoulGame() {
        super();
    }

    @Override
    public void welcomePlayer() {
        //Scrivere qui il codice che gestirà l'intro di gioco
    }

    @Override
    public void defineGameInteractions() {
        //Scrivere qui il codice che definirà effettivamente il flow del gioco, nello specifico le interazioni
    }

    @Override
    public void update() {
        //Scrivere qui il codice che scorrerà le varie interactions per eseguire quelle da effettuare
    }

    @Override
    public boolean checkIfGameIsOver() {
        //Scrivere qui il codice che verificherà se il gioco è terminato

        return false;
    }

    @Override
    public void goodbyePlayer() {
        //Scrivere qui il codice che gestirà la fine del gioco
    }
}
