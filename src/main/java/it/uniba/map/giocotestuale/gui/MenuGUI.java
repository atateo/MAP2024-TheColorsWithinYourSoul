package it.uniba.map.giocotestuale.gui;

import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import javax.swing.GroupLayout;
import javax.swing.BorderFactory;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.CardLayout;



public class MenuGUI extends JPanel {

    private JPanel background;

    private JButton start;

    private JButton load;

    private JButton commands;

    private JButton credits;

    private static JButton audio;

    private JButton site;

    public MenuGUI() {
        initComponents();

    }
    private void initComponents() {

        background = new JPanel()
        {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                ImageIcon img = new ImageIcon("src/main/resources/backgroundBN.jpg");
                Image image = img.getImage();
                g.drawImage(image, 0, 0, getWidth(), getHeight(), this);
            }
        };

        this.setSize(1000,700);


        background.setSize(1000,700);
        background.setRequestFocusEnabled(false);


        start = new JButton("Nuova Partita");
        start.setFocusPainted(false);
        start.setForeground(Color.black);
        start.setBackground(Color.lightGray);
        start.setBorder(BorderFactory.createLineBorder(Color.gray, 5));
        start.addActionListener(this::StartActionPerformed);

        load = new JButton("Carica Partita");
        load.setFocusPainted(false);
        load.setForeground(Color.black);
        load.setBackground(Color.lightGray);
        load.setBorder(BorderFactory.createLineBorder(Color.gray, 5));
        load.addActionListener(this::LoadActionPerformed);

        commands = new JButton("Comandi di Gioco");
        commands.setFocusPainted(false);
        commands.setForeground(Color.black);
        commands.setBackground(Color.lightGray);
        commands.setBorder(BorderFactory.createLineBorder(Color.gray, 5));
        commands.addActionListener(this::CommandsActionPerformed);

        credits = new JButton("Crediti");
        credits.setFocusPainted(false);
        credits.setForeground(Color.black);
        credits.setBackground(Color.lightGray);
        credits.setBorder(BorderFactory.createLineBorder(Color.gray, 5));
        credits.addActionListener(this::CreditsActionPerformed);


        audio = new JButton( new ImageIcon("src/main/resources/audio_icon.png"));
        audio.setFocusPainted(false);
        audio.setForeground(Color.black);
        audio.setBackground(Color.lightGray);
        audio.setBorder(BorderFactory.createLineBorder(Color.gray, 5));
        audio.addActionListener(this::AudioActionPerformed);


        site = new JButton(new ImageIcon("src/main/resources/tavolozza_bn.png"));
        site.setFocusPainted(false);
        site.setForeground(Color.black);
        site.setBackground(Color.lightGray);
        site.setBorder(BorderFactory.createLineBorder(Color.gray, 5));
        site.addActionListener(this::SiteActionPerformed);

        GroupLayout backgroundLayout = new GroupLayout(background);
        backgroundLayout.setHorizontalGroup(
                backgroundLayout.createParallelGroup(Alignment.LEADING)
                        .addGroup(backgroundLayout.createSequentialGroup()
                                .addGap(25)
                                .addGroup(backgroundLayout.createParallelGroup(Alignment.TRAILING)
                                        .addComponent(audio, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(site, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
                        .addGroup(backgroundLayout.createSequentialGroup()
                                .addGap(400)
                                .addGroup(backgroundLayout.createParallelGroup(Alignment.LEADING)
                                        .addComponent(start, 200,200,200)
                                        .addComponent(load, 200,200,200)
                                        .addComponent(commands, 200,200,200)
                                        .addComponent(credits, 200,200,200))
                                .addContainerGap(600, Short.MAX_VALUE))
        );
        backgroundLayout.setVerticalGroup(
                backgroundLayout.createParallelGroup(Alignment.TRAILING)
                        .addGroup(backgroundLayout.createSequentialGroup()
                                .addGap(25)
                                .addGroup(backgroundLayout.createParallelGroup(Alignment.LEADING)
                                        .addGroup(backgroundLayout.createSequentialGroup()
                                                .addComponent(audio, 64,64,64)
                                                .addGap(12)
                                                .addComponent(site, 60,60,60)))
                                .addPreferredGap(ComponentPlacement.RELATED, 450, Short.MAX_VALUE)
                                .addComponent(start, 64,64,64)
                                .addGap(32)
                                .addComponent(load, 64,64,64)
                                .addGap(32)
                                .addComponent(commands, 64,64,64)
                                .addGap(32)
                                .addComponent(credits, 64,64,64)
                                .addGap(90))
        );

        background.setLayout(backgroundLayout);

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



    private void StartActionPerformed(ActionEvent evt) {
        CardLayout cl = (CardLayout) getParent().getLayout();
        cl.show(getParent(), "GameGUI");
    }

    private void LoadActionPerformed(ActionEvent evt) {
        //placeholder
    }

    private void CommandsActionPerformed(ActionEvent evt) {
        CommandsGUI help = CommandsGUI.getIstance();
        help.setVisible(true);
    }

    private void CreditsActionPerformed(ActionEvent evt) {
        CardLayout cl = (CardLayout) getParent().getLayout();
        cl.show(getParent(), "CreditsGUI");
    }

    private void AudioActionPerformed(ActionEvent evt) {
        //placeholder
    }

    private void SiteActionPerformed(ActionEvent evt) {
        //placeholder
    }


