package it.uniba.map.giocotestuale.gui;

import it.uniba.map.giocotestuale.utility.Mixer;

import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.GroupLayout;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.CardLayout;

 /**
 * Classe che gestisce la schermata dei crediti.
 */
public class CreditsGUI extends JPanel {

    private JButton back;

    private JPanel background;

    private JLabel title1;

    private JLabel title2;

    private JLabel content1;

    private JLabel content2;

    /**
    * Costruttore pubbico che si occupa di istanziare e settare i vari componenti sullo schermo
    */
    public CreditsGUI() {


        this.setSize(1000,700);

        background = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                ImageIcon img = new ImageIcon("src/main/resources/img/creditsBackground.png");
                g.drawImage(img.getImage(), 0, 0, getWidth(), getHeight(), this);
            }
        };

        background.setSize(1000,700);

        back = new JButton(new ImageIcon("src/main/resources/img/backButton.png"));
        back.setFocusPainted(false);
        back.setBackground(new Color(166, 15, 15));
        back.setForeground(Color.BLACK);
        back.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));
        back.addActionListener(this::backActionPerformed);


        title1 = new JLabel();
        title1.setText("Credits");
        title1.setBackground(Color.BLUE);
        title1.setForeground(Color.WHITE);


        title2 = new JLabel();
        title2.setText("La nostra esperienza");
        title2.setBackground(Color.BLUE);
        title2.setForeground(Color.WHITE);

        content1 = new JLabel("placeholder");
        content2 = new JLabel("placeholder");

        GroupLayout bglayout = new GroupLayout(background);
        background.setLayout(bglayout);




        bglayout.setHorizontalGroup(
                bglayout.createSequentialGroup()
                        .addComponent(back)
                        .addGap(200)
                        .addComponent(title1)
                        .addComponent(title2)
                        .addComponent(content1)
                        .addComponent(content2)

        );

        bglayout.setVerticalGroup(
                bglayout.createSequentialGroup()
                        .addComponent(back)
                        .addComponent(title1)
                        .addComponent(title2)
                        .addComponent(content1)
                        .addComponent(content2)
        );

        GroupLayout layout = new GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addComponent(background,1000,1000,1000)
                                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addComponent(background, 700,700,700)
                                .addGap(0, 0, Short.MAX_VALUE))
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
