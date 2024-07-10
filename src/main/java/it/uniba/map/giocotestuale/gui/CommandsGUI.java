package it.uniba.map.giocotestuale.gui;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.swing.*;
import java.io.File;
import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;

/**
 * Classe singleton per la visualizzazione dei comandi di gioco.
 */
public class CommandsGUI extends JFrame {

    private static final Logger log = LogManager.getLogger(CommandsGUI.class);

    /**
     * Istanza della
     */
    private static CommandsGUI instance;
    /**
     * Immagine di background della finestra dei comandi.
     */
    private Image backgroundImage;

    /**
     * Metodo getter per ottenere la singola istanza della classe.
     * Se la classe non è stata ancora istanziata, la istanzia.
     *
     * @return istanza di CommandsGUI.
     */
    public static CommandsGUI getInstance() {
        if (instance == null) {
            instance = new CommandsGUI();
        }
        return instance;
    }

    /**
     * Costruttore privato per l'impostazione del frame
     */
    private CommandsGUI() {
        // Imposta il titolo della finestra
        super("Comandi di gioco");

        // Carica l'immagine di sfondo
        try {
            backgroundImage = ImageIO.read(new File("src/main/resources/img/Comandi.png"));
        } catch (IOException e) {
            log.error("Errore nel caricamento dell'immagine di background: {}", e.getMessage());
        }

        // Configura la finestra
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        setSize(400, 600);
        setResizable(false);
        setLayout(new BorderLayout());
        ImageIcon img = new ImageIcon("src/main/resources/img/icona_pennello.jpg");
        setIconImage(img.getImage());

        // Pannello personalizzato per disegnare lo sfondo
        JPanel backgroundPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);

                // Disegna l'immagine di sfondo
                if (backgroundImage != null) {
                    g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
                }
            }
        };
        backgroundPanel.setLayout(new BorderLayout());
        add(backgroundPanel, BorderLayout.CENTER);

        //JTextArea per il testo sopra l'immagine
        JTextArea textArea = new JTextArea("""
                                               \s
                Nord: Permette di muoversi in avanti
                                                        \s
                Sud: Permette di muoversi indietro
                                               \s
                Ovest: Permette di muoversi a sinistra
                                               \s
                Est: Permette di muoversi a destra
                                               \s
                Back: Se sei in una stanza con una sola porta, esce da quella porta (se è aperta)
                                               \s
                Osserva: Mostra la descrizione della stanza e degli oggetti che risaltano all'occhio (usalo se si resta bloccati, dà consigli utili)
                                               \s
                Osserva [oggetto]: Mostra la descrizione dell'oggetto se presente nella stanza o nell'inventario
                                               \s
                Inventario: Mostra l'inventario
                                               \s
                Prendi [oggetto]: Prendi l'oggetto specificato, andrà nell'inventario
                                               \s
                Lascia [oggetto]: Lascia l'oggetto specificato, deve essere presente nell'inventario
                                               \s
                Usa [oggetto]: Utilizza l'oggetto specificato
                                               \s
                Usa [oggetto1] [oggetto2]: Utilizza l'oggetto 1 sull'oggetto 2
                                               \s
                Spingi [oggetto]: Spingi l'oggetto selezionato
                                               \s
                Colora [oggetto] [colore]: Colora l'oggetto selezionato del colore scelto, l'oggetto deve essere nell'inventario o nella stanza
               \s""");

        textArea.setOpaque(false);
        textArea.setEditable(false);
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        textArea.setFont(new Font("Serif", Font.BOLD | Font.ITALIC, 13));
        textArea.setForeground(Color.BLACK);

        // Pannello con layout trasparente per il testo
        JPanel textPanel = new JPanel(new BorderLayout());
        textPanel.setOpaque(false);
        textPanel.add(textArea, BorderLayout.NORTH);

        backgroundPanel.add(textPanel, BorderLayout.CENTER);

        // Rendi la finestra visibile
        setLocationRelativeTo(null);
        setVisible(true);
    }
}
