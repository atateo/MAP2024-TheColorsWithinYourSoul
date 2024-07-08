package it.uniba.map.giocotestuale.gui;

import javax.swing.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import it.uniba.map.giocotestuale.database.domain.Score;
import it.uniba.map.giocotestuale.socket.GameClient;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.imageio.ImageIO;

/**
 * GUI per la gestione della classifica dei punteggi ottenuti.
 * Ritorna a video la lista dei primi 10 migliori tempi e se il tempo di gioco
 * non è tra i primi 10, lo accoda in fondo alla classifica come non classificato "--"
 */
public class ScoreGUI extends JFrame {
    /**
     * Logger per la registrazione degli eventi.
     */
    protected static final Logger logger = LogManager.getLogger();
    private JTextField nicknameField;
    private JTextArea textArea;
    private JButton sendButton;
    private BufferedImage backgroundImage;
    private int myIdScore;

    /**
     * costruttore pubblico della ScoreGUI
     */
    public ScoreGUI() {
        // Carica l'immagine di sfondo
        try {
            backgroundImage = ImageIO.read(new File("src/main/resources/img/Score.png"));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        ImageIcon img = new ImageIcon("src/main/resources/img/icona_pennello.jpg");
        setIconImage(img.getImage());
        createView();

        setTitle("The Colors within your Soul - Classifica");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(650, 400);
        setLocationRelativeTo(null);
        setResizable(false);
    }

    /**
     * metodo che formatta la view della ScoreGUI
     */
    private void createView() {
        JPanel panel = new BackgroundPanel();
        panel.setLayout(new GridBagLayout());
        getContentPane().add(panel);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = 0;

        JPanel inputPanel = new JPanel(new FlowLayout());
        inputPanel.setOpaque(false); // Rendi il pannello trasparente
        panel.add(inputPanel, gbc);

        JLabel nicknameLabel = new JLabel("Player:");
        nicknameLabel.setForeground(Color.BLACK); // Cambia il colore del testo a nero
        nicknameLabel.setFont(nicknameLabel.getFont().deriveFont(16f)); // Imposta il testo leggermente più grande
        inputPanel.add(nicknameLabel);

        nicknameField = new JTextField(20);
        nicknameField.setFont(nicknameField.getFont().deriveFont(16f)); // Imposta il testo leggermente più grande
        inputPanel.add(nicknameField);

        sendButton = new JButton("Invia");
        sendButton.setFont(sendButton.getFont().deriveFont(16f)); // Imposta il testo leggermente più grande
        inputPanel.add(sendButton);

        gbc.fill = GridBagConstraints.BOTH;
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;

        textArea = new JTextArea();
        textArea.setEditable(false);
        textArea.setOpaque(false); // Rendi trasparente la JTextArea
        textArea.setForeground(Color.BLACK); // Cambia il colore del testo a nero
        textArea.setFont(new Font("Monospaced", Font.PLAIN, 16)); // Imposta un font monospaziato
        JScrollPane scrollPane = new JScrollPane(textArea);
        scrollPane.setOpaque(false); // Rendi trasparente lo JScrollPane
        scrollPane.getViewport().setOpaque(false); // Rendi trasparente la viewport dello JScrollPane
        scrollPane.setBorder(BorderFactory.createEmptyBorder()); // Rimuovi il bordo dello JScrollPane
        panel.add(scrollPane, gbc);

        updateScoreList(getScores());

        sendButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nickname = nicknameField.getText();

                Score score = new Score();
                score.setPlayer(nickname);
                // prendere il tempo
                score.setTime(System.currentTimeMillis());
                addScore(score);
                textArea.append(" \n");
                // spengo i campi che non mi occorrono più
                inputPanel.setVisible(false);
                boolean inFirstTen = updateScoreList(getScores());
                if (!inFirstTen) {
                    String riga = String.format("%-20s%-20s%-20s", "Posizione:  --", "Player:  " + score.getPlayer(), "Tempo:  " + score.getTime());
                    textArea.append(riga + "\n");
                }
            }
        });
    }

    /**
     * classe BackgroundPanel che estende Jpanel, utilizzata per impostare l'immagine di background
     */
    private class BackgroundPanel extends JPanel {
    	// Pannello personalizzato per disegnare l'immagine di sfondo
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            if (backgroundImage != null) {
                g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
            }
        }
    }

    /**
     * Metodo che restituisce la lista dei punteggi (tempi) contenente i primi 10
     * players
     *
     * @return la lista dei primi 10 player per tempo impiegato per risolvere il
     * gioco
     */
    private List<Score> getScores() {
        GameClient client = new GameClient();
        List<Score> scores = new ArrayList<>();
        try {
            client.startConnection("localhost", 3999);
            scores = client.getScores();
        } catch (IOException e) {
            logger.error("Eccezione di I/O in fase di recupero dei punteggi dal server");
        }
        return scores;
    }

    /**
     * Metodo che predispone la lista dei migliori dieci giocatori
     *
     * @param scores la lista dei migliori dieci player per tempo impiegato
     * @return il boolean che indica se il player attuale è presente nei primi dieci
     */
    private boolean updateScoreList(List<Score> scores) {
        textArea.setText("");
        boolean firstTen = false;
        int positionOnBoard = 1;
        for (Score score : scores) {
            if (score.getId() == myIdScore || scores.size() < 10)
                firstTen = true;

            String riga = String.format("%-20s%-20s%-20s", "Posizione:  " + positionOnBoard, "Player:  " + score.getPlayer(), "Tempo:  " + score.getTime());
            textArea.append(riga + "\n");
            positionOnBoard++;
        }
        return firstTen;
    }

    /**
     * Metodo void che aggiunge il tempo conseguito dal player alla classifica
     *
     * @param score l'oggetto che rappresenta il punteggio del player
     */
    private void addScore(Score score) {
        if (score != null) {
            GameClient client = new GameClient();
            try {
                client.startConnection("localhost", 3999);
                String resp = client.sendScore(score);
                Pattern pattern = Pattern.compile("KEY=(.*)");
                Matcher matcher = pattern.matcher(resp);
                if (matcher.find()) {
                    String keyGenerated = matcher.group(1);
                    myIdScore = Integer.parseInt(keyGenerated);
                } else {
                    logger.info("KEY non trovata");
                }
            } catch (IOException e) {
                logger.error("Eccezione di I/O in fase di invio del punteggio al server");
                e.printStackTrace();
            }
        } else {
            JOptionPane.showMessageDialog(this, "Inserisci un nickname.", "Errore", JOptionPane.ERROR_MESSAGE);
        }
    }

    /*utile solo ai fini di test
     * public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new ScoreGUI().setVisible(true);
            }
        });
    }*/
}

