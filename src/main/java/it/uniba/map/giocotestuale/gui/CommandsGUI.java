package it.uniba.map.giocotestuale.gui;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.GroupLayout;
import java.awt.Color;

 /**
 * Classe singleton per la visualizzazione dei comandi di gioco.
 */
public class CommandsGUI extends JFrame {

    private static CommandsGUI instance;

    /**
    * Metodo getter per ottenre la singola istanza della classe.
    * se la classe non è stata ancora istanziata, la istanzia.
    * @return instance istanza di CommandsGUI.
    */
    public static CommandsGUI getIstance()
    {
        if(instance == null){
            instance = new CommandsGUI();
        }
        return instance;
    }

    /**
    * Costruttore privato per l'impostazione del frame
    */
    private  CommandsGUI()
    {
        this.setTitle("Comandi di gioco");
        this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        this.setSize(500,800);
        this.setResizable(false);
        ImageIcon img = new ImageIcon("src/main/resources/img/icona_pennello.jpg");
        this.setIconImage(img.getImage());

        JLabel text = new JLabel();
        text.setOpaque(true);
        text.setBackground(new Color(245, 149, 66));
        text.setForeground(Color.white);
        text.setBorder(BorderFactory.createLineBorder(new Color(168,129,50), 5));
        text.setText("PLACEHOLDER");

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(text, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(text, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );


    }
}
