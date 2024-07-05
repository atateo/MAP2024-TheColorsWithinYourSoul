package it.uniba.map.giocotestuale.gui;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.BorderFactory;
import javax.swing.SwingConstants;
import javax.swing.GroupLayout;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.beans.PropertyChangeSupport;
import java.beans.PropertyChangeListener;
import java.util.Timer;
import java.util.TimerTask;

/**
 *  Classe che si occupa della GUI della barra di caricamento
 */
public class ProgressBarGUI extends JPanel{
    /**
     * La ProgressBar del gioco
     */
    private JProgressBar progressBar;
    /**
     * Label per il testo della ProgressBar
     */
    private JLabel progressBarLabel;
    /**
     *  Pannello per lo sfondo
     */
    private JPanel background;
    /**
     *  Supporto alle PropertyChange
     */
    private final PropertyChangeSupport support;
    /**
     * Contatore per l'incremento della ProgressBar
     */
    private int counter;
    /**
     * Pannello per l'immagine in movimento
     */
    private JPanel imgPanel;
    /**
     * Posizione sull'asse x dell'immagine
     */
    private int xPosition;

    /**
     * Costruttore pubblico che si occupa di inizializzare le componenti a schermo.
     */
    public ProgressBarGUI() {

            this.setSize(1000,700);
            support = new PropertyChangeSupport(this);

            // aggiunta dell'immagine di sfondo
            background = new JPanel() {
                @Override
                protected void paintComponent(Graphics g) {
                    super.paintComponent(g);
                    ImageIcon img = new ImageIcon("src/main/resources/img/backgroundProgressBar.png");
                    g.drawImage(img.getImage(), 0, 0, getWidth(), getHeight(), this);
                }
            };

            // configurazione della progress bar
            progressBar = new JProgressBar();
            progressBar.setForeground(new Color(50, 168, 156));
            progressBar.setBackground(new Color(107, 90, 13));
            progressBar.setOpaque(true);
            progressBar.setBorder(BorderFactory.createLineBorder(new Color(168,129,50), 5));
            progressBar.setSize(500,50);
            progressBar.setStringPainted(false);

            //configurazione della label della progress bar
            progressBarLabel = new JLabel();
            progressBarLabel.setForeground(Color.BLACK);
            progressBarLabel.setHorizontalAlignment(SwingConstants.CENTER);

            // aggiunta della label alla progress bar
            progressBar.setLayout(new BorderLayout());
            progressBar.add(progressBarLabel, BorderLayout.CENTER);

            ImageIcon img = new ImageIcon("src/main/resources/img/progressBarIcon.png");
            xPosition = -img.getIconWidth();

            imgPanel =new JPanel() {
                @Override
                protected void paintComponent(Graphics g) {
                    super.paintComponent(g);
                    Image i = img.getImage();
                    g.drawImage(i, xPosition, 0, 100, getHeight(), this);
                }
            };
            imgPanel.setOpaque(false);
            imgPanel.setSize(500,100);

            // configurzione del GroupLayout per l'imgPanel
            GroupLayout imgPanelLayout = new GroupLayout(imgPanel);
            imgPanel.setLayout(imgPanelLayout);
            imgPanelLayout.setHorizontalGroup(
                    imgPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                            .addGap(0, 100, Short.MAX_VALUE)
            );
            imgPanelLayout.setVerticalGroup(
                    imgPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                            .addGap(0, 100, Short.MAX_VALUE)
            );

            // configurazione del GroupLayout per il background
            GroupLayout bglayout = new GroupLayout(background);
            background.setLayout(bglayout);
            bglayout.setHorizontalGroup(
                    bglayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                            .addGroup(bglayout.createSequentialGroup()
                                    .addGap(250)
                                    .addComponent(progressBar, 500, 500, 500)
                                    .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(GroupLayout.Alignment.TRAILING, bglayout.createSequentialGroup()
                                    .addGap(250)
                                    .addComponent(imgPanel,500,500,500)
                                    .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            );
            bglayout.setVerticalGroup(
                    bglayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                            .addGroup(GroupLayout.Alignment.TRAILING, bglayout.createSequentialGroup()
                                    .addContainerGap(300, Short.MAX_VALUE)
                                    .addComponent(imgPanel,100,100,100)
                                    .addGap(21, 21, 21)
                                    .addComponent(progressBar, 50,50,50)
                                    .addGap(200,200,200))
            );
            background.setLayout(bglayout);

            // configurazione del GroupLayout per il pannello principale
            GroupLayout layout = new GroupLayout(this);
            layout.setHorizontalGroup(
                    layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(background, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            );
            layout.setVerticalGroup(
                    layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(background, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE
                    )
            );
            this.setLayout(layout);

        }

       /**
       * Metodo che si occupa di configurare la proprietà isFinished della progress bar.
       * @param isFinished true se la progress bar è arrivata al 100%, false altrimenti.
       */
        public void setFinished(boolean isFinished) {

            support.firePropertyChange("isFinished", null, isFinished);
        }

       /**
       * Metodo che serve per aggiungere un PropertyChangeListener.
       * @param listener il listener da aggiungere.
       */
        public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
        }

       /**
        * Metodo che che si occupa del caricamento della progress bar.
        * e del movimento sull'asse x dell'immagine.
        */
        public void startProgressBar() {
            counter = 0;
            Timer timer = new Timer();
            TimerTask taskProgressBar = new TimerTask() {
                @Override
                public void run() {
                    if (counter < 100) {
                        counter++;
                        progressBar.setValue(counter);
                        progressBarLabel.setText("Colorando... " + counter + "%");
                        xPosition = (int) ((double) counter / 100 * (500+100)) - 100;
                        imgPanel.repaint();

                    } else {
                        progressBarLabel.setText("Si parte!");
                        Timer timerlw = new Timer();
                        TimerTask taskProgressBarLastWord = new TimerTask() {
                            @Override
                            public void run() {
                                setFinished(true);
                                timerlw.cancel();
                            }
                        };
                        timerlw.schedule(taskProgressBarLastWord, 500);
                        timer.cancel();
                    }
                }
            };
            timer.scheduleAtFixedRate(taskProgressBar, 0, 20);
        }
}
