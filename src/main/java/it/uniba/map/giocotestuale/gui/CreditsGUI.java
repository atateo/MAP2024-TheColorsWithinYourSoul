package it.uniba.map.giocotestuale.gui;

import it.uniba.map.giocotestuale.utility.Mixer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Classe che gestisce la schermata dei crediti.
 */
public class CreditsGUI extends JPanel {

    /**
     * Pulsante per tornare indietro al menu
     */
    private JButton back;

    /**
     * Immagine di sfondo
     */
    private JPanel background;

    /**
     * Titolo
     */
    private JLabel title;
    /**
     * Contenuto dei crediti
     */
    private JLabel content;

    /**
     * Immagine del profilo github di Angelo Vincenti
     */
    private JLabel angeloImg;
    /**
     * Immagine del profilo github di Antimo Tateo
     */
    private JLabel antimoImg;
    /**
     * Immagine del profilo github di Yuri Tateo
     */
    private JLabel yuriImg;

    /**
    * Costruttore pubbico che si occupa di istanziare e settare i vari componenti sullo schermo
    */
    public CreditsGUI() {

        this.setSize(1000, 700);

        background = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                ImageIcon img = new ImageIcon("src/main/resources/img/creditsBackground.png");
                g.drawImage(img.getImage(), 0, 0, getWidth(), getHeight(), this);
            }
        };

        background.setSize(1000, 700);

        back = new JButton(new ImageIcon("src/main/resources/img/backButton.png"));
        back.setFocusPainted(false);
        back.setBackground(new Color(50, 168, 156));
        back.setForeground(Color.BLACK);
        back.setBorder(BorderFactory.createLineBorder(new Color(168,129,50),3));
        back.addActionListener(this::backActionPerformed);

        yuriImg = new JLabel(new ImageIcon(new ImageIcon("src/main/resources/img/pfpYuri.png").getImage().getScaledInstance(150, 150, Image.SCALE_DEFAULT)));
        yuriImg.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
               String command =  "rundll32 url.dll,FileProtocolHandler https://github.com/yuritateo03";
                try {
                    Process p = Runtime.getRuntime().exec(command);
                } catch (Exception e1) {
                    JOptionPane.showMessageDialog(null, "\n Error! \n");
                }

            }
        });
        GroupLayout yuriImgLayout = new GroupLayout(yuriImg);
        yuriImg.setLayout(yuriImgLayout);
        yuriImgLayout.setHorizontalGroup(
                yuriImgLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGap(0, 150, Short.MAX_VALUE)
        );
        yuriImgLayout.setVerticalGroup(
                yuriImgLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGap(0, 150, Short.MAX_VALUE)
        );

        antimoImg = new JLabel(new ImageIcon(new ImageIcon("src/main/resources/img/pfpAntimo.png").getImage().getScaledInstance(150, 150, Image.SCALE_DEFAULT)));
        antimoImg.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                String command =  "rundll32 url.dll,FileProtocolHandler https://github.com/atateo";
                try {
                    Process p = Runtime.getRuntime().exec(command);
                } catch (Exception e1) {
                    JOptionPane.showMessageDialog(null, "\n Error! \n");
                }

            }
        });

        GroupLayout antimoImgLayout = new GroupLayout(antimoImg);
        antimoImg.setLayout(antimoImgLayout);
        antimoImgLayout.setHorizontalGroup(
                antimoImgLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGap(0, 150, Short.MAX_VALUE)
        );
        antimoImgLayout.setVerticalGroup(
                antimoImgLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGap(0, 150, Short.MAX_VALUE)
        );

        angeloImg = new JLabel(new ImageIcon(new ImageIcon("src/main/resources/img/pfpAngelo.png").getImage().getScaledInstance(150, 150, Image.SCALE_DEFAULT)));
        angeloImg.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                String command =  "rundll32 url.dll,FileProtocolHandler https://github.com/AngeloVincenti";
                try {
                    Process p = Runtime.getRuntime().exec(command);
                } catch (Exception e1) {
                    JOptionPane.showMessageDialog(null, "\n Error! \n");
                }

            }
        });
        GroupLayout angeloImgLayout = new GroupLayout(angeloImg);
        angeloImg.setLayout(angeloImgLayout);
        angeloImgLayout.setHorizontalGroup(
                angeloImgLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGap(0, 150, Short.MAX_VALUE)
        );
        angeloImgLayout.setVerticalGroup(
                angeloImgLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGap(0, 150, Short.MAX_VALUE)
        );

        title = new JLabel("        Credits");
        title.setForeground(Color.WHITE);
        title.setOpaque(true);
        title.setVerticalAlignment(SwingConstants.TOP);
        title.setBackground(new Color(50, 168, 156));
        title.setBorder(BorderFactory.createLineBorder(new Color(168,129,50), 5));


        // Set the properties of the content label
        content = new JLabel();
        content.setBackground(new Color(204, 173, 27));
        content.setForeground(new Color(255, 255, 255));
        content.setOpaque(true);
        content.setVerticalAlignment(SwingConstants.TOP);
        content.setText(
                "<html>"+"<center>"
                        + "<p>The Colors Within Your Soul è un'avventura testuale sviluppata da Yuri Tateo, Antimo Tateo e Angelo Vincenti come piccolo progetto di passione. Ci auguriamo che il gioco sia di vostro gradimento!</p>"
                        + "<p>Cliccando sulle immagini verrete portati sulla pagina web GitHub di ciascuno dei membri.</p>"
                        + "</html>"+"</center>"
        );
        content.setBackground(new Color(50, 168, 156));
        content.setBorder(BorderFactory.createLineBorder(new Color(168,129,50), 5));


        // Set the layout of the background panel
        GroupLayout backgroundPanelLayout = new GroupLayout(background);
        background.setLayout(backgroundPanelLayout);
        backgroundPanelLayout.setHorizontalGroup(
                backgroundPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(backgroundPanelLayout.createSequentialGroup()
                                .addGroup(backgroundPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addGroup(backgroundPanelLayout.createSequentialGroup()
                                                .addGap(25, 25, 25)
                                                .addComponent(back,40,40,40))
                                        .addGroup(backgroundPanelLayout.createSequentialGroup()
                                                .addGap(180)
                                                .addComponent(yuriImg, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                                .addGap(100)
                                                .addComponent(antimoImg, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                                .addGap(100)
                                                .addComponent(angeloImg, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
                                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGroup(GroupLayout.Alignment.TRAILING, backgroundPanelLayout.createSequentialGroup()
                                .addGroup(backgroundPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addGroup(GroupLayout.Alignment.TRAILING, backgroundPanelLayout.createSequentialGroup()
                                                .addComponent(title, 100,100,100)
                                                .addGap(450))
                                        .addGroup(GroupLayout.Alignment.TRAILING, backgroundPanelLayout.createSequentialGroup()
                                                .addComponent(content,500,500,500)
                                                .addGap(250)

        ))));
        backgroundPanelLayout.setVerticalGroup(
                backgroundPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(backgroundPanelLayout.createSequentialGroup()
                                .addGap(25)
                                .addComponent(back,40,40,40)
                                .addGroup(backgroundPanelLayout.createSequentialGroup())
                                .addGap(100)
                                .addComponent(title,25,25,25)
                                .addGap(50)
                                .addComponent(content, 100,100,100)
                                .addGap(100)
                                .addGroup(backgroundPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addComponent(yuriImg, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(antimoImg, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(angeloImg, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                               )
        );

        GroupLayout layout = new GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addComponent(background,1000,1000,1000)
                                )
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addComponent(background, 700,700,700)
                        )
        );
    }
    /**
    * Metodo che definisce il comportamento del pulsante back quando viene cliccato.
    * Passa al menu principale selezionando MenuGUI attraverso il CardLayout.
    * @param evt rappresenta l'evento del click sul pulsante.
     */
    private void backActionPerformed(ActionEvent evt) {
        CardLayout cl = (CardLayout) getParent().getLayout();
        cl.show(getParent(), "MenuGUI");
        Mixer.getInstance().changRoomMusic("Menu");
        Mixer.getInstance().startTrack();
    }
}