package it.uniba.map.giocotestuale.gui;

import it.uniba.map.giocotestuale.database.domain.Score;
import it.uniba.map.giocotestuale.socket.GameClient;

import javax.swing.*;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ScoreGui extends JFrame {
	/**
     * Logger per la registrazione degli eventi.
     */
    protected static final Logger logger = LogManager.getLogger();
    
	private static final long serialVersionUID = 1L;
	private JTextField nicknameField;
    private DefaultListModel<String> scoreListModel;
    private JList<String> scoreList;
    private int myIdScore;

    public ScoreGui() {
        // Inizializzazione del frame
        setTitle("The Colors within yuor Soul - Classifica");
        setSize(700, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        ImageIcon img = new ImageIcon("src/main/resources/img/icona_pennello.jpg");
        setIconImage(img.getImage());

        // Pannello principale con BorderLayout
        JPanel mainPanel = new JPanel(new BorderLayout());
        getContentPane().add(mainPanel);

        // Pannello per l'inserimento del nickname
        JPanel inputPanel = new JPanel();
        JLabel nameLabel = new JLabel("Nickname:");
        nicknameField = new JTextField(20);
        inputPanel.add(nameLabel);
        inputPanel.add(nicknameField);
        
     // Lista dei punteggi
        scoreListModel = new DefaultListModel<>();
        scoreList = new JList<>(scoreListModel);
        
        updateScoreList(getScores());
        // Pulsante per aggiungere il punteggio
        JButton addButton = new JButton("Inserisci il tuo nome");
        
     // Pannello per il pulsante
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(addButton);
        
        //score.setTime(new Long(2500));
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	
            	String nickname = nicknameField.getText().trim();
            	Score score = new Score();
                score.setPlayer(nickname);
                //prendere il tempo
                score.setTime(System.currentTimeMillis());
                addScore(score);
                inputPanel.setVisible(false);
                buttonPanel.setVisible(false);
                updateScoreList(getScores());
                String riga= String.format("%-40s%-40s%-40s", "--", score.getPlayer(), score.getTime());
             
                scoreListModel.addElement(riga);
            }
        });

        

        
        
        JScrollPane scrollPane = new JScrollPane(scoreList);

        // Aggiungi i pannelli al layout principale
        mainPanel.add(inputPanel, BorderLayout.NORTH);
        mainPanel.add(scrollPane, BorderLayout.CENTER);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);
    }

    private void addScore(Score score) {
        
        if (score!=null) {
            
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
            //updateScoreList();
        } else {
            JOptionPane.showMessageDialog(this, "Inserisci un nickname.", "Errore", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private List<Score> getScores() {
    	GameClient client = new GameClient();
    	List<Score> scores = new ArrayList<Score>();
    	try {
			client.startConnection("localhost", 3999);
			scores = client.getScores();
			scores = scores.subList(0, Math.min(scores.size(), 10));
		} catch (IOException e) {
			logger.error("Eccezione di I/O in fase di recupero dei punteggi dal server");
		}
    	return scores;
    }

    private boolean updateScoreList(List<Score> scores) {
        scoreListModel.clear();
        boolean firstTen=false;
        int positionOnBoard=1;
        for (Score score : scores) {
        	if(score.getId()==myIdScore)
        		firstTen=true;
        	
        	String riga = String.format("%-40s%-40s%-40s", positionOnBoard, score.getPlayer(), score.getTime());
            scoreListModel.addElement(riga);
            positionOnBoard++;
        }
        return firstTen;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new ScoreGui().setVisible(true);
            }
        });
    }
}

