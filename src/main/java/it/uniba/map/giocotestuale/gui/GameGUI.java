package it.uniba.map.giocotestuale.gui;

import it.uniba.map.giocotestuale.database.impl.DialogDaoImpl;
import it.uniba.map.giocotestuale.entities.artwork.ArtworkResponse;
import it.uniba.map.giocotestuale.impl.GameToGUICommunication;
import it.uniba.map.giocotestuale.rest.ClientRest;
import it.uniba.map.giocotestuale.type.ColorEnum;
import it.uniba.map.giocotestuale.utility.Mixer;

import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JToolBar;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.JViewport;
import javax.swing.JTextPane;
import javax.swing.JTextField;
import javax.swing.ImageIcon;
import javax.swing.Box;
import javax.swing.UIManager;
import javax.swing.SwingUtilities;
import javax.swing.ScrollPaneConstants;
import javax.swing.GroupLayout;
import javax.swing.SwingConstants;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Classe che mostra la GUI del gioco.
 */
public class GameGUI extends JPanel {

    /**
     * Pulsante per tornare indietro al menu principale
     */
    private JButton back;
    /**
     * Pulsante per salvare il gioco
     */
    private JButton save;
    /**
     * Pulsante di aiuto, mostra la finestra dei comandi di gioco
     */
    private JButton help;
    /**
     * Pulsante per attivare o disattivare l'audio del gioco
     */
    private static JButton audio;
    /**
     * Label per posizionare il timer
     */
    private static JLabel timerLabel;

    /**
     * Label per l'icona sinistra del timer
     */
    private JLabel timerImgLabel1;

    /**
     * Label per l'icona destra del timer
     */
    private JLabel timerImgLabel2;
    /**
     * Label che segna l'acquisizione del colore rosso
     */
    private JLabel redColorLabel;
    /**
     * Label che segna l'acquisizione del colore blu
     */
    private JLabel blueColorLabel;

    /**
     * label che segna l'acquisizione del colore giallo
     */
    private JLabel yellowColorLabel;
    /**
     * Label che segna l'acquisizione del colore verde
     */
    private JLabel greenColorLabel;

    /**
     * Label che segna l'acquisizione del colore marrone
     */
    private JLabel brownColorLabel;

    /**
     * Label che segna l'acquisizione del colore viola
     */
    private JLabel purpleColorLabel;
    /**
     * Label per l'inventario
     */
    private JLabel inventoryLabel;
    /**
     * Pannello che mostra l'immagine di gioco corrente
     */
    private static JPanel imagePanel;

    /**
     * TextPane che mostra il testo del gioco
     */
    private static JTextPane displayTextPane;
    /**
     * ScrollPane per il testo del gioco
     */
    private JScrollPane scrollPaneDisplayText;
    /**
     * TextArea che mostra il testo dell'inventario
     */
    private static JTextArea inventoryTextArea;
    /**
     * ScrollPAne per l'inventario
     */
    private JScrollPane scrollPaneInventoryText;
    /**
     * TextField che riceve in input i comandi dell'utente
     */
    private JTextField userInputField;
    /**
     * ToolBar che comprende il timer e i vari pulsanti
     */
    private JToolBar toolBar;
    /**
     * CardLayout che consente di cambiare GUI
     */
    private static CardLayout cardLayout;
    /**
     * Immagine audio spento
     */
    final ImageIcon audio_off = new ImageIcon("src/main/resources/img/audio_off_game.png");
    /**
     * Immagine audio acceso
     */
    final ImageIcon audio_on = new ImageIcon("src/main/resources/img/audio_icon_game.png");
    /**
     * Booleano che indica se il gioco è finito o meno. Definisce cosa deve fare con l'input dell'utente.
     */
    private boolean isFinished;
    /**
     * Booleano che indica se l'outro è finita e si può passare alla stampa della classifica.
     */
    private boolean startScore;

    /**
     * Costruttore pubblico che imposta la scrollbar a 0 e chiama i metodi initComponents e initCurrentImage.
     * Usato per settare i componenti a video e l'immagine di gioco corrente.
     */
    public GameGUI() {
        UIManager.put("ScrollBar.width", 0);
        SwingUtilities.updateComponentTreeUI(this);
        initComponents();
        initCurrentImage();
    }

    /**
     * Metodo che si occupa di configurare e posizionare tutte le componenti sullo schermo.
     */
    private void initComponents() {

        //Istanzia il booleano di fine gioco a false
        isFinished = false;
        startScore = false;

        // configurazione del pannello
        this.setVisible(true);
        this.setSize(1000, 700);

        // configurazione del pannello per l'immagine
        imagePanel = new JPanel();
        imagePanel.setSize(400, 400);
        imagePanel.setBorder(BorderFactory.createLineBorder(new Color(168, 129, 50), 5));

        // configurazione della toolBar
        toolBar = new JToolBar();
        toolBar.setFloatable(false);
        toolBar.setBorderPainted(false);
        toolBar.setBackground(new Color(50, 168, 156));
        toolBar.add(Box.createHorizontalStrut(10));

        // configurazione del pulsante back 
        back = new JButton(new ImageIcon("src/main/resources/img/backButton.png"));
        back.setFocusPainted(false);
        back.setBackground(new Color(166, 15, 15));
        back.setForeground(Color.BLACK);
        back.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));
        back.addActionListener(this::backActionPerformed);
        toolBar.add(back);

        // aggiunta del gap per il posizionamento
        toolBar.add(Box.createHorizontalStrut(20));

        // configurazione del pulsante save
        save = new JButton(new ImageIcon("src/main/resources/img/saveButton.png"));
        save.setFocusPainted(false);
        save.setBackground(new Color(166, 15, 15));
        save.setForeground(Color.BLACK);
        save.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));
        save.addActionListener(this::saveActionPerformed);
        toolBar.add(save);

        // aggiunta del gap per il posizionamento
        toolBar.add(Box.createHorizontalStrut(20));

        // configurazione del pulsante help
        help = new JButton(new ImageIcon("src/main/resources/img/help_icon.png"));
        help.setFocusPainted(false);
        help.setBackground(new Color(166, 15, 15));
        help.setForeground(Color.BLACK);
        help.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));
        help.addActionListener(this::helpActionPerformed);
        toolBar.add(help);

        // aggiunta del gap per il posizionamento
        toolBar.add(Box.createHorizontalStrut(20));

        // configurazione del pulsante audio
        audio = new JButton(audio_on);
        audio.setFocusPainted(false);
        audio.setBackground(new Color(166, 15, 15));
        audio.setForeground(Color.BLACK);
        audio.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));
        audio.addActionListener(this::audioActionPerformed);
        toolBar.add(audio);

        // aggiunta del gap per il posizionamento
        toolBar.add(Box.createHorizontalStrut(100));

        // configurazione della timerLabel
        timerLabel = new JLabel();
        timerLabel.setHorizontalTextPosition(SwingConstants.CENTER);
        timerLabel.setVerticalTextPosition(SwingConstants.CENTER);
        timerLabel.setText("00:00:00");
        timerLabel.setForeground(Color.BLACK);
        timerLabel.setOpaque(true);
        timerLabel.setBackground(new Color(168, 129, 50));

        // configurazione delle icone per il timer
        timerImgLabel1 = new JLabel(new ImageIcon("src/main/resources/img/timerImg.png"));
        timerImgLabel2 = new JLabel(new ImageIcon("src/main/resources/img/timerImg.png"));
        toolBar.add(timerImgLabel1);
        toolBar.add(Box.createHorizontalStrut(20));
        toolBar.add(timerLabel);
        toolBar.add(Box.createHorizontalStrut(20));
        toolBar.add(timerImgLabel2);

        // configurazione delle label per l'acquisizione dei colori

        toolBar.add(Box.createHorizontalStrut(100));
        redColorLabel = new JLabel(new ImageIcon("src/main/resources/img/quadroGrigio.png"));
        toolBar.add(redColorLabel);

        toolBar.add(Box.createHorizontalStrut(10));
        blueColorLabel = new JLabel(new ImageIcon("src/main/resources/img/quadroGrigio.png"));
        toolBar.add(blueColorLabel);

        toolBar.add(Box.createHorizontalStrut(10));
        yellowColorLabel = new JLabel(new ImageIcon("src/main/resources/img/quadroGrigio.png"));
        toolBar.add(yellowColorLabel);

        toolBar.add(Box.createHorizontalStrut(10));
        greenColorLabel = new JLabel(new ImageIcon("src/main/resources/img/quadroGrigio.png"));
        toolBar.add(greenColorLabel);

        toolBar.add(Box.createHorizontalStrut(10));
        brownColorLabel = new JLabel(new ImageIcon("src/main/resources/img/quadroGrigio.png"));
        toolBar.add(brownColorLabel);

        toolBar.add(Box.createHorizontalStrut(10));
        purpleColorLabel = new JLabel(new ImageIcon("src/main/resources/img/quadroGrigio.png"));
        toolBar.add(purpleColorLabel);


        // aggiunta dello sfondo dell'inventario
        ImageIcon inventoryImg = new ImageIcon("src/main/resources/img/sfondoInventario.jpg");

        JViewport inventoryView = new JViewport() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(inventoryImg.getImage(), 0, 0, getWidth(), getHeight(), this);
            }
        };


        // aggiunta e posizionamento dell'icona borsaInventario 
        inventoryLabel = new JLabel(new ImageIcon("src/main/resources/img/borsaInventario.png"));
        inventoryLabel.setSize(40, 40);
        inventoryLabel.setBounds((GroupLayout.PREFERRED_SIZE) + 530, (GroupLayout.PREFERRED_SIZE) + 10, 40, 40);

        // configurazione dell'inventoryTextArea
        inventoryTextArea = new JTextArea();
        inventoryTextArea.setEditable(false);
        inventoryTextArea.setAutoscrolls(false);
        inventoryTextArea.setBorder(BorderFactory.createLineBorder(new Color(168, 129, 50), 7));
        inventoryTextArea.setEnabled(false);
        inventoryTextArea.setFocusable(false);
        inventoryTextArea.setOpaque(false);
        inventoryTextArea.setSize(440, 440);
        inventoryTextArea.setText("Inventario");
        inventoryTextArea.setForeground(Color.BLACK);
        inventoryTextArea.add(inventoryLabel);

        // configurazione dello scrollPaneInventoryText
        scrollPaneInventoryText = new JScrollPane();
        scrollPaneInventoryText.setSize(440, 400);
        scrollPaneInventoryText.setViewport(inventoryView);
        scrollPaneInventoryText.setViewportView(inventoryTextArea);

        // aggiunta dello sfondo per il testo del gioco
        ImageIcon img = new ImageIcon("src/main/resources/img/sfondoTela.jpg");
        JViewport view = new JViewport() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(img.getImage(), 0, 0, getWidth(), getHeight(), this);
            }
        };

        // configurazione di displayTextPane
        displayTextPane = new JTextPane();
        displayTextPane.setEditable(false);
        displayTextPane.setFocusable(false);
        displayTextPane.setAutoscrolls(false);
        displayTextPane.setBorder(null);
        displayTextPane.setOpaque(false);
        displayTextPane.setForeground(new Color(0, 0, 0));

        // configurazione dello scrollPaneDisplayText
        scrollPaneDisplayText = new JScrollPane();
        scrollPaneDisplayText.setViewport(view);
        scrollPaneDisplayText.setViewportView(displayTextPane);
        scrollPaneDisplayText.setSize(400, 400);
        scrollPaneDisplayText.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPaneDisplayText.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPaneDisplayText.setBorder(BorderFactory.createLineBorder(new Color(168, 129, 50), 5));

        // configurazione dell'userInputField
        userInputField = new JTextField();
        userInputField.setOpaque(false);
        userInputField.setSize(400, 107);
        userInputField.setBounds(0, 0, 400, 107);
        userInputField.setBorder(BorderFactory.createLineBorder(new Color(168, 129, 50), 5));
        userInputField.addActionListener(this::userInputActionPerformed);

        // aggiunta dello sfondo per l'user input
        JPanel userInputFieldPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(img.getImage(), 0, 0, getWidth(), getHeight(), this);
            }
        };
        // configurazione dell'userInputFieldPanel
        userInputFieldPanel.setLayout(null);
        userInputFieldPanel.setSize(400, 107);
        userInputFieldPanel.setBorder(BorderFactory.createLineBorder(new Color(168, 129, 50), 7));
        userInputFieldPanel.add(userInputField);


        // impostazione del Group Layout per il posizionamento dei vari componenti sul pannello
        GroupLayout layout = new GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(toolBar, 1000, 1000, 1000)
                        .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                        .addGroup(layout.createSequentialGroup()
                                                .addComponent(userInputFieldPanel, 400, 400, 400))
                                        .addComponent(scrollPaneDisplayText, 400, 400, 400))
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addComponent(scrollPaneInventoryText, 587, 587, 587)
                                        .addComponent(imagePanel, 587, 587, 587))
                                .addGap(5, 5, 5))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addComponent(toolBar, GroupLayout.PREFERRED_SIZE, 45, GroupLayout.PREFERRED_SIZE)
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                                .addComponent(imagePanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(scrollPaneInventoryText, GroupLayout.PREFERRED_SIZE, 103, GroupLayout.PREFERRED_SIZE))
                                        .addGroup(layout.createSequentialGroup()
                                                .addComponent(scrollPaneDisplayText, 510, 510, 510)
                                                .addComponent(userInputFieldPanel, 107, 107, 107)))
                        )
        );

    }

    /**
     * Metodo che si occupa di aggiungere le immagini al layout
     */
    public void initCurrentImage() {
        cardLayout = new CardLayout();
        cardLayout.setVgap(0);
        cardLayout.setVgap(0);
        cardLayout.setHgap(0);
        cardLayout.minimumLayoutSize(imagePanel);
        cardLayout.maximumLayoutSize(imagePanel);
        cardLayout.preferredLayoutSize(imagePanel);

        imagePanel.setLayout(cardLayout);
    }

    /**
     * Metodo che aggiunge un immagine all'imagePanel.
     *
     * @param RoomName nome della stanza, usato per trovare il path della stessa.
     */
    public static void addImage(String RoomName) {
        imagePanel.add(new JPanel() {
            @Override
            public void paintComponent(Graphics g) {
                super.paintComponent(g);
                ImageIcon image = new ImageIcon("src/main/resources/img/" + RoomName + ".png");
                g.drawImage(image.getImage(), 0, 0, getWidth(), getHeight(), this);
            }
        }, RoomName);
    }

    /**
     * Metodo che cambia l'immagine di gioco
     *
     * @param roomName nome della stanza che si vuole impostare
     */
    public static void setRoomImage(String roomName) {
        imagePanel.removeAll();
        addImage(roomName);
        cardLayout.show(imagePanel, roomName);
        imagePanel.revalidate();
        imagePanel.repaint();
    }

    /**
     * Metodo che aggiorna l'area di testo dell'inventario.
     *
     * @param inventoryText Stringa che rappresenta l'inventario dell'utente.
     */
    public void updateInventory(final String inventoryText) {
        inventoryTextArea.setText(inventoryText);
    }

    /**
     * Metodo che aggiorna il timer nella label
     */
    public static void updateTimerLabel() {
        Timer timer = new Timer();
        TimerTask updateTimerTask = new TimerTask() {
            @Override
            public void run() {
                timerLabel.setText(GameToGUICommunication.getInstance().getTime());
            }
        };

        timer.scheduleAtFixedRate(updateTimerTask, 1000, 1000);
    }

    /**
     * Metodo che definisce il comportamento del pulsante save quando viene cliccato.
     * Apre la finestra per la selezione del file di salvataggio o la creazione di un nuovo file.
     *
     * @param evt rappresenta l'evento del click sul pulsante.
     */
    private void saveActionPerformed(ActionEvent evt) {
        JFileChooser j = new JFileChooser("saves");

        // Open the save dialog
        int r = j.showSaveDialog(null);
        if (r == JFileChooser.APPROVE_OPTION) {
            String nomeFile = GameToGUICommunication.getInstance().saveGame(j.getSelectedFile().getAbsolutePath());
            GameToGUICommunication.getInstance().toGUI("Salvataggio effettuato: " + nomeFile);
        }
    }

    /**
     * Metodo che definisce il comportamento del pulsante back quando viene cliccato.
     * Passa al menu principale selezionando MenuGUI attraverso il CardLayout.
     *
     * @param evt rappresenta l'evento del click sul pulsante.
     */
    private void backActionPerformed(ActionEvent evt) {
        displayTextPane.setText("");
        CardLayout cl = (CardLayout) getParent().getLayout();
        cl.show(getParent(), "MenuGUI");
        resetAudio();
        Mixer.getInstance().changRoomMusic("Menu");
        Mixer.getInstance().startTrack();

        inventoryTextArea.setText("Inventario");
        //Resetta i colori sbloccati
        redColorLabel.setIcon(new ImageIcon("src/main/resources/img/quadroGrigio.png"));
        blueColorLabel.setIcon(new ImageIcon("src/main/resources/img/quadroGrigio.png"));
        yellowColorLabel.setIcon(new ImageIcon("src/main/resources/img/quadroGrigio.png"));
        greenColorLabel.setIcon(new ImageIcon("src/main/resources/img/quadroGrigio.png"));
        brownColorLabel.setIcon(new ImageIcon("src/main/resources/img/quadroGrigio.png"));
        purpleColorLabel.setIcon(new ImageIcon("src/main/resources/img/quadroGrigio.png"));
    }

    /**
     * Metodo che definisce il comportamento del pulsante help quando viene cliccato.
     * Apre la finestra comandi chiamando il metodo getIstance di CommandsGUI.
     *
     * @param evt rappresenta l'evento del click sul pulsante.
     */
    private void helpActionPerformed(ActionEvent evt) {
        CommandsGUI help = CommandsGUI.getInstance();
        help.setVisible(true);
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
     * Metodo che colora un quadro della toolbar quando l'utente sblocca un colore.
     *
     * @param color colore da sbloccare nella barra dei colori.
     */
    public void unlockColor(ColorEnum color) {
        switch (color) {
            case ColorEnum.RED:
                redColorLabel.setIcon(new ImageIcon("src/main/resources/img/quadroRosso.png"));
                break;
            case ColorEnum.BLUE:
                blueColorLabel.setIcon(new ImageIcon("src/main/resources/img/quadroBlu.png"));
                break;
            case ColorEnum.YELLOW:
                yellowColorLabel.setIcon(new ImageIcon("src/main/resources/img/quadroGiallo.png"));
                break;
            case ColorEnum.GREEN:
                greenColorLabel.setIcon(new ImageIcon("src/main/resources/img/quadroVerde.png"));
                break;
            case ColorEnum.BROWN:
                brownColorLabel.setIcon(new ImageIcon("src/main/resources/img/quadroMarrone.png"));
                break;
            case ColorEnum.PURPLE:
                purpleColorLabel.setIcon(new ImageIcon("src/main/resources/img/quadroViola.png"));
                break;
        }

    }

    /**
     * Metodo che gestisce l'input dell'utente. Gestisce anche il comportamento
     * della TextArea in base a se il gioco è finito o meno.
     *
     * @param evt rappresenta l'evento dell'input dell'utente.
     */
    private void userInputActionPerformed(ActionEvent evt) {
        //istanzo la classe DAO
        DialogDaoImpl dialog = new DialogDaoImpl();

        String text = userInputField.getText();
        userInputField.setText("");

        if (!isFinished) {
            GameToGUICommunication.getInstance().toGame(text);
        } else if (!startScore) {
            ArtworkResponse artworkResponse = ClientRest.getArtwork();
            ImageIcon artwork = new ImageIcon(artworkResponse.getArtwork());

            imagePanel.removeAll();

            imagePanel.add(new JPanel() {
                @Override
                public void paintComponent(Graphics g) {
                    super.paintComponent(g);
                    g.drawImage(artwork.getImage(), 0, 0, getWidth(), getHeight(), this);
                }
            });

            imagePanel.revalidate();
            imagePanel.repaint();

            GameToGUICommunication.getInstance().toGUI(dialog.getTestoById(49));//49

            GameToGUICommunication.getInstance().toGUI(dialog.getTestoById(50));//50

            GameToGUICommunication.getInstance().toGUI(dialog.getTestoById(51));//51

            startScore = true;
        } else {
            userInputField.setEnabled(false);

            //Apertura della finestra con la classifica qui
            ScoreGUI scoreGUI = new ScoreGUI(GameToGUICommunication.getInstance().getElapsedTime());
            scoreGUI.setVisible(true);
        }
    }

    /**
     * Metodo utilizzato per scrivere sul panel di output displayTextPane.
     *
     * @param text Stringa da scrivere sul panel.
     */
    public static void writeOnPanel(final String text) {
        if (displayTextPane.getText().isEmpty()) {
            displayTextPane.setText(text);
        } else {
            displayTextPane.setText(displayTextPane.getText() + "\n\n" + text);
        }
        displayTextPane.setCaretPosition(displayTextPane.getDocument().getLength());
    }

    /**
     * Metodo che ritorna la lunghezza in caratteri di displayTextPane.
     *
     * @return Lunghezza in caratteri di displayTextPane.
     */
    public static int getTextPaneWidth() {
        return displayTextPane.getWidth();
    }

    /**
     * Metodo che ritorna l'oggetto FontMetrics legato al font di displayTextPane.
     * Questo oggetto contiene alcune caratteristiche utili al rendering del testo sul panel.
     *
     * @return Istanza di FontMetrics di displayTextPane.
     */
    public static FontMetrics getTextPaneFontMetrics() {
        return displayTextPane.getFontMetrics(displayTextPane.getFont());
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

    /**
     * Metodo setter per l'attributo isFinished.
     *
     * @param isFinished Nuovo valore booleano dell'attributo isFinished.
     */
    public void setFinished(final boolean isFinished) {
        this.isFinished = isFinished;

        //Se il gioco è finito, blocca il tasto save, altrimenti sbloccalo
        save.setEnabled(!isFinished);
    }

    /**
     * Metodo setter per l'attributo startScore.
     *
     * @param startScore Nuovo valore booleano dell'attributo startScore.
     */
    public void setScore(final boolean startScore) {
        this.startScore = startScore;
    }

    /**
     * Metodo che riattiva la casella di testo di input dell'utente.
     */
    public void enableUserInputField() {
        userInputField.setEnabled(true);
    }
}
