package it.uniba.map.giocotestuale.gui;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import javax.swing.BorderFactory;

import java.awt.event.ActionEvent;
import java.awt.Color;

public class MenuGUI extends JFrame {

    private JLabel background;

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

        start = new JButton("Nuova Partita");
        start.setBounds(400, 250, 200, 64);
        start.setSize(200, 64);
        start.setFocusPainted(false);
        start.setForeground(Color.black);
        start.setBackground(Color.lightGray);
        start.setBorder(BorderFactory.createLineBorder(Color.gray, 5));
        start.addActionListener(this::StartActionPerformed);

        load = new JButton("Carica Partita");
        load.setBounds(400, 350, 200, 64);
        load.setSize(200, 64);
        load.setFocusPainted(false);
        load.setForeground(Color.black);
        load.setBackground(Color.lightGray);
        load.setBorder(BorderFactory.createLineBorder(Color.gray, 5));
        load.addActionListener(this::LoadActionPerformed);

        commands = new JButton("Comandi di Gioco");
        commands.setBounds(400, 450, 200, 64);
        commands.setSize(200, 64);
        commands.setFocusPainted(false);
        commands.setForeground(Color.black);
        commands.setBackground(Color.lightGray);
        commands.setBorder(BorderFactory.createLineBorder(Color.gray, 5));
        commands.addActionListener(this::CommandsActionPerformed);

        credits = new JButton("Crediti");
        credits.setBounds(400, 550, 200, 64);
        credits.setSize(200, 64);
        credits.setFocusPainted(false);
        credits.setForeground(Color.black);
        credits.setBackground(Color.lightGray);
        credits.setBorder(BorderFactory.createLineBorder(Color.gray, 5));
        credits.addActionListener(this::CreditsActionPerformed);

        ImageIcon img2 = new ImageIcon("src/main/resources/audio_icon.png");
        audio = new JButton(img2);
        audio.setBounds(800, 250, 200, 64);
        audio.setSize(64, 64);
        audio.setFocusPainted(false);
        audio.setForeground(Color.black);
        audio.setBackground(Color.lightGray);
        audio.setBorder(BorderFactory.createLineBorder(Color.gray, 5));
        audio.addActionListener(this::AudioActionPerformed);

        ImageIcon img3 = new ImageIcon("src/main/resources/tavolozza_bn.png");
        site = new JButton(img3);
        site.setBounds(800, 350, 200, 50);
        site.setSize(64, 64);
        site.setFocusPainted(false);
        site.setForeground(Color.black);
        site.setBackground(Color.lightGray);
        site.setBorder(BorderFactory.createLineBorder(Color.gray, 5));
        site.addActionListener(this::SiteActionPerformed);

        ImageIcon img4 = new ImageIcon("src/main/resources/backgroundBN.jpg");
        background = new JLabel(img4);
        background.setHorizontalAlignment(JLabel.CENTER);
        background.setVerticalAlignment(JLabel.CENTER);

        ImageIcon img1 = new ImageIcon("src/main/resources/icona_pennello_bn.jpg");
        this.setTitle("The colors within your soul");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setSize(1000, 700);
        this.setVisible(true);
        this.setIconImage(img1.getImage());
        this.add(start);
        this.add(load);
        this.add(commands);
        this.add(credits);
        this.add(audio);
        this.add(site);
        this.add(background);
    }

    private void StartActionPerformed(ActionEvent evt) {
        //placeholder
    }

    private void LoadActionPerformed(ActionEvent evt) {
        //placeholder
    }

    private void CommandsActionPerformed(ActionEvent evt) {
        //placeholder
    }

    private void CreditsActionPerformed(ActionEvent evt) {
        //placeholder
    }

    private void AudioActionPerformed(ActionEvent evt) {
        //placeholder
    }

    private void SiteActionPerformed(ActionEvent evt) {
        //placeholder
    }
}