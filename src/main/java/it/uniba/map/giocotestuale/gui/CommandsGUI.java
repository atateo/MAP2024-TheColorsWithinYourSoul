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
    * Metodo getter per ottenere la singola istanza della classe.
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
        text.setForeground(Color.BLACK);
        text.setBorder(BorderFactory.createLineBorder(new Color(168,129,50), 5));
        text.setText("<html>" + "<center>" +
                "<p><b>Nord</b>: Permette di muoversi in avanti</p><br>"+
                "<p><b>Sud</b>: Permette di muoversi indietro</p><br>" +
                "<p><b>Ovest</b>: Permette di muoversi a sinistra</p><br>" +
                "<p><b>Est</b>: Permette di muoversi a destra</p><br>" +
                "<p><b>Aiuto</b>: Mostra l'elenco comandi</p><br>" +
                "<p><b>Osserva</b>: Mostra la descrizione della stanza e degli oggetti che risaltano all'occhio</p><br>"+
                "<p><b>Osserva</b> [<i>oggetto</i>] :  Mostra la descrizione dell'oggetto se presente nella stanza</p><br>"+
                "<p><b>Inventario</b>: Mostra l'inventario</p><br>" +
                "<p><b>Prendi</b> [<i>oggetto</i>] : Prendi l'oggetto specificato, andrà nell'inventario</p><br>"+
                "<p><b>Lascia</b> [<i>oggetto</i>]: Lascia l'oggetto specificato, deve essere presente nell'inventario</p><br>"+
                "<p><b>Usa</b> [<i>oggetto</i>]: Utilizza l'oggetto specificato</p><br>"+
                "<p><b>Usa</b> [<i>oggetto1</i>] [<i>oggetto2</i>] : Utilizza l'oggetto 1 sull'oggetto 2</p><br>"+
                "<p><b>Spingi</b> [<i>oggetto</i>] : Spingi l'oggetto selezionato</p><br>"+
                "<p><b>Colora</b> [<i>oggetto</i>] : Colora l'oggetto selezionato</p><br>" +
                "</center>"+
                "</html>"
        );

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
