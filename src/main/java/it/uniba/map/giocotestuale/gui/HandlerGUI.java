package it.uniba.map.giocotestuale.gui;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.ImageIcon;
import java.awt.CardLayout;


public class HandlerGUI extends JFrame {

    private static GameGUI game;
    private static MenuGUI menu;
    private static CreditsGUI credits;
    private static CardLayout layout;

    public HandlerGUI() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("The colors whitin your soul");
        this.setSize(1000, 700);
        this.setResizable(false);
        this.setLocationRelativeTo(null);


        ImageIcon icon = new ImageIcon("src/main/resources/img/icona_pennello_bn.jpg");
        this.setIconImage(icon.getImage());
        menu = new MenuGUI();
        game = new GameGUI();
        credits = new CreditsGUI();

        layout = new CardLayout();
        JPanel cards = new JPanel(layout);


        cards.add(menu, "MenuGUI");
        cards.add(game, "GameGUI");
        cards.add(credits, "CreditsGUI");


        this.add(cards);
        this.setVisible(true);
    }
    }

