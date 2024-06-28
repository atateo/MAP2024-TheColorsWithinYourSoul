package it.uniba.map.giocotestuale.gui;

import javax.swing.JPanel;
import javax.swing.JButton;
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
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;



public class GameGUI extends JPanel {

    private JButton back;

    private JButton save;

    private JButton help;

    private static JButton audio;

    private static JLabel timerLabel;

    private JLabel timerImgLabel1;

    private JLabel timerImgLabel2;

    private JLabel inventoryLabel;

    private static JPanel imagePanel;

    private static JTextPane displayTextPane;

    private JScrollPane scrollPaneDisplayText;

    private static JTextArea inventoryTextArea;

    private JScrollPane scrollPaneInventoryText;

    private JTextField userInputField;

    private JToolBar toolBar;

    private static CardLayout cardLayout;

    public GameGUI()
    {
        UIManager.put("ScrollBar.width", 0);
        SwingUtilities.updateComponentTreeUI(this);
        initComponents();
        initCurrentImage();
    }

    private void initComponents()
    {
        this.setVisible(true);
        this.setSize(1000,700);


        imagePanel = new JPanel();
        imagePanel.setSize(400,400);
        imagePanel.setBorder(BorderFactory.createLineBorder(new Color(168, 129, 50), 5));

        toolBar = new JToolBar();
        toolBar.setFloatable(false);
        toolBar.setBorderPainted(false);
        toolBar.setBackground(new Color(50, 168, 156));
        toolBar.add(Box.createHorizontalStrut(10));

        back = new JButton(new ImageIcon("src/main/resources/img/backButton.png"));
        back.setFocusPainted(false);
        back.setBackground(new Color(166, 15, 15));
        back.setForeground(Color.BLACK);
        back.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));
        back.addActionListener(this::BackActionPerformed);
        toolBar.add(back);

        toolBar.add(Box.createHorizontalStrut(20));

        save = new JButton(new ImageIcon("src/main/resources/img/saveButton.png"));
        save.setFocusPainted(false);
        save.setBackground(new Color(166, 15, 15));
        save.setForeground(Color.BLACK);
        save.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));
        toolBar.add(save);

        toolBar.add(Box.createHorizontalStrut(20));

        help = new JButton(new ImageIcon("src/main/resources/img/help_icon.png"));
        help.setFocusPainted(false);
        help.setBackground(new Color(166, 15, 15));
        help.setForeground(Color.BLACK);
        help.setBorder(BorderFactory.createLineBorder(Color.BLACK , 3));
        help.addActionListener(this::HelpActionPerformed);
        toolBar.add(help);

        toolBar.add(Box.createHorizontalStrut(20));

        audio = new JButton(new ImageIcon("src/main/resources/img/audio_icon_game.png"));
        audio.setFocusPainted(false);
        audio.setBackground(new Color(166, 15, 15));
        audio.setForeground(Color.BLACK);
        audio.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));
        toolBar.add(audio);

        toolBar.add(Box.createHorizontalStrut(100));

        timerLabel = new JLabel();
        timerLabel.setHorizontalTextPosition(SwingConstants.CENTER);
        timerLabel.setVerticalTextPosition(SwingConstants.CENTER);
        timerLabel.setText(" 00:00:00 ");
        timerLabel.setForeground(Color.BLACK);
        timerLabel.setOpaque(true);
        timerLabel.setBackground(new Color(168, 129, 50));

        timerImgLabel1 = new JLabel(new ImageIcon("src/main/resources/img/timerImg.png"));
        timerImgLabel2 = new JLabel(new ImageIcon("src/main/resources/img/timerImg.png"));
        toolBar.add(timerImgLabel1);
        toolBar.add(Box.createHorizontalStrut(20));
        toolBar.add(timerLabel);
        toolBar.add(Box.createHorizontalStrut(20));
        toolBar.add(timerImgLabel2);

        ImageIcon inventoryImg = new ImageIcon("src/main/resources/img/sfondoInventario.jpg");

        JViewport inventoryView = new JViewport() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(inventoryImg.getImage(), 0, 0, getWidth(), getHeight(), this);
            }
        };



        inventoryLabel = new JLabel(new ImageIcon("src/main/resources/img/borsaInventario.png"));
        inventoryLabel.setSize(40,40);
        inventoryLabel.setBounds((GroupLayout.PREFERRED_SIZE)+530,(GroupLayout.PREFERRED_SIZE)+10,40,40);


        inventoryTextArea = new JTextArea();
        inventoryTextArea.setEditable(false);
        inventoryTextArea.setAutoscrolls(false);
        inventoryTextArea.setBorder(BorderFactory.createLineBorder(new Color(168, 129, 50), 7));
        inventoryTextArea.setEnabled(false);
        inventoryTextArea.setFocusable(false);
        inventoryTextArea.setOpaque(false);
        inventoryTextArea.setSize(440,440);
        inventoryTextArea.setText("Inventario:");
        inventoryTextArea.setForeground(Color.BLACK);
        inventoryTextArea.add(inventoryLabel);




        scrollPaneInventoryText = new JScrollPane();
        scrollPaneInventoryText.setSize(440,400);
        scrollPaneInventoryText.setViewport(inventoryView);
        scrollPaneInventoryText.setViewportView(inventoryTextArea);


        ImageIcon img = new ImageIcon("src/main/resources/img/sfondoTela.jpg");
        JViewport view = new JViewport() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(img.getImage(), 0, 0, getWidth(), getHeight(), this);
            }
        };

        displayTextPane = new JTextPane();
        displayTextPane.setEditable(false);
        displayTextPane.setFocusable(false);
        displayTextPane.setAutoscrolls(false);
        displayTextPane.setBorder(null);
        displayTextPane.setOpaque(false);
        displayTextPane.setForeground(new Color(0, 0, 0));

        scrollPaneDisplayText = new JScrollPane();
        scrollPaneDisplayText.setViewport(view);
        scrollPaneDisplayText.setViewportView(displayTextPane);
        scrollPaneDisplayText.setSize(400,400);
        scrollPaneDisplayText.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPaneDisplayText.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPaneDisplayText.setBorder(BorderFactory.createLineBorder(new Color(168, 129, 50), 5));

        userInputField = new JTextField();
        userInputField.setOpaque(false);
        userInputField.setSize(400,107);
        userInputField.setBounds(0, 0, 400, 107);
        userInputField.setBorder(BorderFactory.createLineBorder(new Color(168, 129, 50), 5));
        userInputField.addActionListener(this::UserInputActionPerformed);


        JPanel userInputFieldPanel = new JPanel()
        {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(img.getImage(), 0, 0, getWidth(), getHeight(), this);
            }
        };
        userInputFieldPanel.setLayout(null);
        userInputFieldPanel.setSize(400,107);
        userInputFieldPanel.setBorder(BorderFactory.createLineBorder(new Color(168,129,50),7));
        userInputFieldPanel.add(userInputField);


        // Setting the layout of the panel
        GroupLayout layout = new GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(toolBar, 1000, 1000, 1000)
                        .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                        .addGroup(layout.createSequentialGroup()
                                                .addComponent(userInputFieldPanel, 400, 400, 400))
                                        .addComponent(scrollPaneDisplayText,400,400,400))
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addComponent(scrollPaneInventoryText, 587, 587, 587)
                                        .addComponent(imagePanel, 587, 587,587))
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
                                                .addComponent(scrollPaneDisplayText,510,510,510)
                                                .addComponent(userInputFieldPanel,107,107,107)))
                        )
        );




    }

    public void initCurrentImage()
    {

        cardLayout = new CardLayout();
        cardLayout.setVgap(0);
        cardLayout.setVgap(0);
        cardLayout.setHgap(0);
        cardLayout.minimumLayoutSize(this);
        cardLayout.maximumLayoutSize(this);
        cardLayout.preferredLayoutSize(this);

        imagePanel.add(new JPanel(null) {
            @Override
            public void paintComponent(Graphics g) {
                super.paintComponent(g);
                ImageIcon image = new ImageIcon("src/main/resources/img/placeholder_avvio_partita.jpg");
                g.drawImage(image.getImage(), 0, 0, getWidth(), getHeight(), this);
            }
        }, "Placeholder");

        imagePanel.setLayout(cardLayout);

    }

    private void BackActionPerformed(ActionEvent evt)
    {
        CardLayout cl = (CardLayout) getParent().getLayout();
        cl.show(getParent(), "MenuGUI");
    }

    private void HelpActionPerformed(ActionEvent evt) {
        CommandsGUI help = CommandsGUI.getIstance();
        help.setVisible(true);
    }
    private  void UserInputActionPerformed (ActionEvent evt)
    {
        String text = userInputField.getText();
        userInputField.setText("");

    }
}
