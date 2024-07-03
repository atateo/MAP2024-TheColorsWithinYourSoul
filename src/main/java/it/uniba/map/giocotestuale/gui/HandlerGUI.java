package it.uniba.map.giocotestuale.gui;

import it.uniba.map.giocotestuale.utility.Mixer;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.ImageIcon;
import java.awt.CardLayout;

// Classe che si occupa di coordinare tutte le GUI del gioco

public class HandlerGUI extends JFrame {

    
    private static GameGUI game;
    private static MenuGUI menu;
    private static CreditsGUI credits;
    private static CardLayout layout;

    /**
    * Costruttore pubblico che si occupa di definire le impostazioni del frame principale
    * e della creazione del CardLayout per gestire i vari pannelli.
    */
    public HandlerGUI() {

        // configurazione del frame principale
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("The colors whitin your soul");
        this.setSize(1000, 700);
        this.setResizable(false);
        this.setLocationRelativeTo(null);

        ImageIcon icon = new ImageIcon("src/main/resources/img/icona_pennello_bn.jpg");
        this.setIconImage(icon.getImage());
        
        // inizializzazione delle GUI
        menu = new MenuGUI();
        game = new GameGUI();
        credits = new CreditsGUI();

        // creazione del CardLayout e del Panel per il passaggio fra le varie GUI
        layout = new CardLayout();
        JPanel cards = new JPanel(layout);

        // aggiunta delle carte al pannello
        cards.add(menu, "MenuGUI");
        cards.add(game, "GameGUI");
        cards.add(credits, "CreditsGUI");

        // aggiunta delle carte al frame
        this.add(cards);
        this.setVisible(true); //  imposta il frame come visibile

        Mixer.getInstance().start();
    }

    /**
     * Ritorna l'istanza della GUI di gioco.
     * @return Istanza della GUI di gioco.
     */
    public static GameGUI getGameGUI() {
        return game;
    }
}
