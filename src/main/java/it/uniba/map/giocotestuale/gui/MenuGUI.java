package it.uniba.map.giocotestuale.gui;

import it.uniba.map.giocotestuale.impl.GameToGUICommunication;
import it.uniba.map.giocotestuale.type.ColorEnum;
import it.uniba.map.giocotestuale.utility.Mixer;

import javax.swing.*;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.CardLayout;


/**
 * Classe che si occupa di mostrare gli elementi della GUI del menu di inizio partita.
 */
public class MenuGUI extends JPanel {

    /**
     * Pannello per il background
     */
    private JPanel background;

    /**
     * Pulsante di avvio di una nuova partita
     */
    private JButton start;
    /**
     * Pulsante per caricare una partita già iniziata
     */
    private JButton load;
    /**
     * Pulsante per la visualizzazione dei comandi di gioco
     */
    private JButton commands;
    /**
     * Pulsante per la visualizzazione dei crediti
     */
    private JButton credits;
    /**
     * Pulsante per attivare o disattivare l'audio del gioco
     */
    private static JButton audio;
    /**
     * Pulsante per mostrare la classifica
     */
    private static JButton score;
    /**
     * Immagine audio spento
     */
    final ImageIcon audio_off = new ImageIcon("src/main/resources/img/audio_off.png");
    /**
     * Immagine audio acceso
     */
    final ImageIcon audio_on = new ImageIcon("src/main/resources/img/audio_icon.png");

    /**
     * Costruttore pubblico che chiama il metodo per istanziare i componenti a schermo.
     */
    public MenuGUI() {
        initComponents();
    }

    /**
     * Metodo che istanzia e setta i componenti sullo schermo.
     */
    private void initComponents() {
        // disegna l'immagine di sfondo
        background = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                ImageIcon img = new ImageIcon("src/main/resources/img/backgroundBN.jpg");
                Image image = img.getImage();
                g.drawImage(image, 0, 0, getWidth(), getHeight(), this);
            }
        };

        // setta le dimensioni del pannello menu e dello sfondo
        this.setSize(1000, 700);

        background.setSize(1000, 700);
        background.setRequestFocusEnabled(false);

        // configurazione del pulsante start

        start = new JButton("Nuova Partita");
        start.setFocusPainted(false);
        start.setForeground(Color.black);
        start.setBackground(Color.lightGray);
        start.setBorder(BorderFactory.createLineBorder(Color.gray, 5));
        start.addActionListener(this::startActionPerformed);

        // configurazione del pulsante load

        load = new JButton("Carica Partita");
        load.setFocusPainted(false);
        load.setForeground(Color.black);
        load.setBackground(Color.lightGray);
        load.setBorder(BorderFactory.createLineBorder(Color.gray, 5));
        load.addActionListener(this::loadActionPerformed);

        // configurazione del pulsante commands
        commands = new JButton("Comandi di Gioco");
        commands.setFocusPainted(false);
        commands.setForeground(Color.black);
        commands.setBackground(Color.lightGray);
        commands.setBorder(BorderFactory.createLineBorder(Color.gray, 5));
        commands.addActionListener(this::commandsActionPerformed);

        // confiurazione del pulsante credits
        credits = new JButton("Crediti");
        credits.setFocusPainted(false);
        credits.setForeground(Color.black);
        credits.setBackground(Color.lightGray);
        credits.setBorder(BorderFactory.createLineBorder(Color.gray, 5));
        credits.addActionListener(this::creditsActionPerformed);

        // configurazione del pulsante audio
        audio = new JButton(audio_on);
        audio.setFocusPainted(false);
        audio.setForeground(Color.black);
        audio.setBackground(Color.lightGray);
        audio.setBorder(BorderFactory.createLineBorder(Color.gray, 5));
        audio.addActionListener(this::audioActionPerformed);

        // configurazione del pulsante score
        score = new JButton(new ImageIcon("src/main/resources/img/tavolozza_bn.png"));
        score.setForeground(Color.black);
        score.setBackground(Color.lightGray);
        score.setBorder(BorderFactory.createLineBorder(Color.gray, 5));
        score.addActionListener(this::scoreActionPerformed);


        setGroupLayout();


    }

    /**
     * Metodo che configura il GroupLayout
     */
    private void setGroupLayout() {
        // impostazione del Group Layout per il posizionamento dei vari componenti sullo sfondo
        GroupLayout backgroundLayout = new GroupLayout(background);
        backgroundLayout.setHorizontalGroup(
                backgroundLayout.createParallelGroup(Alignment.LEADING)
                        .addGroup(backgroundLayout.createSequentialGroup()
                                .addGap(25)
                                .addGroup(backgroundLayout.createParallelGroup(Alignment.TRAILING)
                                        .addComponent(audio, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(score, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
                        .addGroup(backgroundLayout.createSequentialGroup()
                                .addGap(400)
                                .addGroup(backgroundLayout.createParallelGroup(Alignment.LEADING)
                                        .addComponent(start, 200, 200, 200)
                                        .addComponent(load, 200, 200, 200)
                                        .addComponent(commands, 200, 200, 200)
                                        .addComponent(credits, 200, 200, 200))
                                .addContainerGap(600, Short.MAX_VALUE))
        );
        backgroundLayout.setVerticalGroup(
                backgroundLayout.createParallelGroup(Alignment.TRAILING)
                        .addGroup(backgroundLayout.createSequentialGroup()
                                .addGap(25)
                                .addGroup(backgroundLayout.createParallelGroup(Alignment.LEADING)
                                        .addGroup(backgroundLayout.createSequentialGroup()
                                                .addComponent(audio, 64, 64, 64)
                                                .addGap(12)
                                                .addComponent(score, 64,64,64)))
                                .addPreferredGap(ComponentPlacement.RELATED, 450, Short.MAX_VALUE)
                                .addComponent(start, 64, 64, 64)
                                .addGap(32)
                                .addComponent(load, 64, 64, 64)
                                .addGap(32)
                                .addComponent(commands, 64, 64, 64)
                                .addGap(32)
                                .addComponent(credits, 64, 64, 64)
                                .addGap(90))
        );

        background.setLayout(backgroundLayout);

        // impostazione del Group Layout per il posizionamento dello sfondo
        GroupLayout layout = new GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(Alignment.TRAILING)
                        .addComponent(background, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(Alignment.TRAILING)
                        .addComponent(background, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }

    /**
     * Metodo che aggiorna il menu rendendolo colorato
     */
    public void updateMenu() {
        this.remove(background);
        background = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                ImageIcon img = new ImageIcon("src/main/resources/img/backgroundColorato.jpg");
                Image image = img.getImage();
                g.drawImage(image, 0, 0, getWidth(), getHeight(), this);
            }
        };

        Color colorePulsante = new Color(71, 111, 206);
        Color coloreBordo = new Color(76, 172, 62);

        // modifica del colore del pulsante start
        start.setBackground(colorePulsante);
        start.setBorder(BorderFactory.createLineBorder(coloreBordo, 5));

        // modifica del colore del pulsante load
        load.setBackground(colorePulsante);
        load.setBorder(BorderFactory.createLineBorder(coloreBordo, 5));

        // modifica del colore del pulsante commands
        commands.setBackground(colorePulsante);
        commands.setBorder(BorderFactory.createLineBorder(coloreBordo, 5));

        // modifica del colore del pulsante credits
        credits.setBackground(colorePulsante);
        credits.setBorder(BorderFactory.createLineBorder(coloreBordo, 5));

        // modifica del colore del pulsante audio
        audio.setBackground(colorePulsante);
        audio.setBorder(BorderFactory.createLineBorder(coloreBordo, 5));

        // modifica del colore e dell'immagine del pulsante score
        score.setBackground(colorePulsante);
        score.setBorder(BorderFactory.createLineBorder(coloreBordo, 5));
        score.setIcon(new ImageIcon("src/main/resources/img/tavolozza_colorata.png"));


        setGroupLayout();
    }

    /**
     * Metodo che definisce il comportamento del pulsante start quando viene cliccato.
     * Inizia una nuova partita selezionando la GameGUI attraverso il CardLayout.
     *
     * @param evt rappresenta l'evento del click sul pulsante.
     */
    private void startActionPerformed(ActionEvent evt) {
        CardLayout cl = (CardLayout) getParent().getLayout();
        ProgressBarGUI progressBar = (ProgressBarGUI) this.getParent().getComponent(2);
        cl.show(getParent(), "ProgressBarGUI");
        progressBar.addPropertyChangeListener(evt1 -> {
            if (evt1.getPropertyName().equals("isFinished")) {
                cl.show(getParent(), "GameGUI");

                //Fa partire la musica di gioco
                resetAudio();
                Mixer.getInstance().changRoomMusic("AtticoCentrale");
                Mixer.getInstance().startTrack();
            }
        });
        progressBar.startProgressBar();

        //Carica l'istanza di gioco
        GameToGUICommunication.getInstance().setGameEngineFromFile("src/main/resources/static/start.json");
        GameToGUICommunication.getInstance().start();
        GameGUI.setRoomImage(GameToGUICommunication.getInstance().getCurrentGameRoom());

        //Imposta l'aggiornamento del timer
        GameGUI.updateTimerLabel();

        //Imposta il gioco a non finito
        HandlerGUI.getGameGUI().setFinished(false);
        HandlerGUI.getGameGUI().setScore(false);
        HandlerGUI.getGameGUI().enableUserInputField();
    }

    /**
     * Metodo che definisce il comportamento del pulsante load quando viene cliccato.
     * Carica il salvataggio di una partita.
     *
     * @param evt rappresenta l'evento del click sul pulsante.
     */
    private void loadActionPerformed(ActionEvent evt) {
        JFileChooser j = new JFileChooser("saves");

        // Open the save dialog
        int r = j.showOpenDialog(null);
        if (r == JFileChooser.APPROVE_OPTION) {
            CardLayout cl = (CardLayout) getParent().getLayout();
            ProgressBarGUI progressBar = (ProgressBarGUI) this.getParent().getComponent(2);
            cl.show(getParent(), "ProgressBarGUI");
            progressBar.addPropertyChangeListener(evt1 -> {
                if (evt1.getPropertyName().equals("isFinished")) {
                    cl.show(getParent(), "GameGUI");

                    //Imposta la musica di gioco
                    resetAudio();
                    Mixer.getInstance().changRoomMusic(GameToGUICommunication.getInstance().getCurrentGameRoom());
                    Mixer.getInstance().startTrack();
                }
            });
            progressBar.startProgressBar();

            //Carica il file di salvataggio scelto
            GameToGUICommunication.getInstance().setGameEngineFromFile(j.getSelectedFile().getAbsolutePath());
            GameToGUICommunication.getInstance().restartTimer(GameToGUICommunication.getInstance().getElapsedTime());
            GameGUI.setRoomImage(GameToGUICommunication.getInstance().getCurrentGameRoom());

            //Imposta il timer
            GameGUI.updateTimerLabel();

            //Accende le immagini dei colori sbloccati
            for (ColorEnum color : GameToGUICommunication.getInstance().getUnlockedColors()) {
                HandlerGUI.getGameGUI().unlockColor(color);
            }

            //Imposta l'inventario
            GameToGUICommunication.getInstance().notifyInventoryUpdateToGUI();

            //Imposta il gioco a non finito
            HandlerGUI.getGameGUI().setFinished(false);
            HandlerGUI.getGameGUI().setScore(false);
            HandlerGUI.getGameGUI().enableUserInputField();
        }
    }

    /**
     * Metodo che definisce il comportamento del pulsante commands quando viene cliccato.
     * Apre la finestra comandi chiamando il metodo getIstance di CommandsGUI.
     *
     * @param evt rappresenta l'evento del click sul pulsante.
     */
    private void commandsActionPerformed(ActionEvent evt) {
        CommandsGUI help = CommandsGUI.getInstance();
        help.setVisible(true);
    }

    /**
     * Metodo che definisce il comportamento del pulsante credits quando viene cliccato.
     * Passa alla schermata crediti selezionando la CreditsGUI attraverso il CardLayout.
     *
     * @param evt rappresenta l'evento del click sul pulsante.
     */
    private void creditsActionPerformed(ActionEvent evt) {
        CardLayout cl = (CardLayout) getParent().getLayout();
        cl.show(getParent(), "CreditsGUI");
        Mixer.getInstance().changRoomMusic("Credits");
        Mixer.getInstance().startTrack();
    }

    /**
     * Metodo che definisce il comportamento del pulsante audio quando viene cliccato.
     * Attiva o disattiva l'audio di gioco.
     *
     * @param evt rappresenta l'evento del click sul pulsante.
     */
    private void audioActionPerformed(ActionEvent evt) {
        if (audio.getIcon().toString().equals(audio_on.toString())) {
            audio.setIcon(audio_off);
            Mixer.getInstance().stopTrack();
        } else {
            audio.setIcon(audio_on);
            Mixer.getInstance().startTrack();
        }
    }
    /**
     * Metodo che definisce il comportamento del pulsante score quando viene cliccato.
     * Attiva o disattiva l'audio di gioco.
     *
     * @param evt rappresenta l'evento del click sul pulsante.
     */
    private void scoreActionPerformed(ActionEvent evt) {
        ScoreGUI scoreGUI = new ScoreGUI();
        scoreGUI.setVisible(true);
    }

    /**
     * Metodo che accende l'audio se era stato spento in precedenza,
     * usato per ripristinare l'audio quando si cambia schermata
     */
    public void resetAudio() {
        if (audio.getIcon().toString().equals(audio_off.toString())) {
            audio.setIcon(audio_on);
        }
    }
}
