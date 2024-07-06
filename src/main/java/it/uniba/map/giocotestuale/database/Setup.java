package it.uniba.map.giocotestuale.database;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Classe responsabile della creazione e configurazione del database.
 */
public class Setup {
	 /**
     * Logger per la registrazione degli eventi.
     */
    protected static final Logger logger = LogManager.getLogger();
    
    /**
     * Metodo per creare le tabelle necessarie nel database.
     * Viene utilizzata una lista di istruzioni SQL per creare le tabelle 
     * e caricare eventuali dati di configurazione
     */
    public static void setup() {
        List<String> istruzioni = new ArrayList<>();
        
        String dropRoom = "drop table room if exists";
        String dropItem = "drop table item if exists";
        String dropColor = "drop table color if exists";
        String dropDialog = "drop table dialog if exists";
        
        // Creazione della tabella room
        String creaRoom = "CREATE TABLE IF NOT EXISTS room (" +
                "id INT AUTO_INCREMENT PRIMARY KEY, " +
                "stato VARCHAR(20), " +
                "descrizione VARCHAR(600), " +
                "id_room INT)";
     // Creazione della tabella item
        String creaItem = "CREATE TABLE IF NOT EXISTS item (" +
                "id INT AUTO_INCREMENT PRIMARY KEY, " +
                "stato VARCHAR(20), " +
                "descrizione VARCHAR(200), " +
                "id_item INT)";
        
     // Creazione della tabella color
        String creaColor = "CREATE TABLE IF NOT EXISTS color (" +
                "id INT AUTO_INCREMENT PRIMARY KEY, " +
                "descrizione VARCHAR(300))";
        
     // Creazione della tabella dialog
        String creaDialog = "CREATE TABLE IF NOT EXISTS dialog (" +
                "id INT AUTO_INCREMENT PRIMARY KEY, " +
                "testo VARCHAR(500))";
                
     // Creazione della tabella score
        String creaScore = "CREATE TABLE IF NOT EXISTS score (" +
                "id INT AUTO_INCREMENT PRIMARY KEY, " +
                "player VARCHAR(100), " +
                "score INT, " +
                "time VARCHAR(10))";
        
        
        String popolaRoom = "INSERT INTO room(id_room, stato, descrizione) VALUES "
        		+ "(0,'Neutro', 'Ti trovi nella stanza centrale del piano. È un misto tra una serra e un attico vero e proprio ed è enorme. Noti come la vegetazione abbia totalmente preso il sopravvento, ci sono finestre rotte, macerie in giro ed è tutto in disordine. Ci sono tre porte, oltre a quella da cui sei entrato. Alla tua sinistra, una porta normale. Alla tua destra, una porta con un sigillo verde. Davanti a te, una porta con un sigillo viola.'),"
        		+ "(1,'Neutro', 'Ti trovi nella stanza dei colori primari, almeno così c''è scritto sulla porta. È più ordinata dell''attico centrale, non ci sono macerie o mobili, però è comunque piena di vegetazione. Ci sono 3 porte, oltre a quella dell''attico. A nord, una porta marchiata di rosso. A ovest, una porta marchiata di blu. A sud, una porta marchiata di giallo. Sulla porta rossa c''è scritto \"Entra e troverai il primo colore primario\".'),"
        		+ "(2,'Neutro', 'Ti trovi nella stanza dei colori secondari, almeno così c''è scritto sulla porta. È più trasandata rispetto alla stanza dei colori primari, infatti noti qualche maceria e mobile in giro, oltre che alcune sculture e dipinti di tuo nonno, ormai rovinati. C''è ancora più vegetazione rispetto all''attico. Ci sono 3 porte, oltre a quella dell''attico. A nord, una porta marchiata di verde. A est, una porta marchiata di marrone. A sud, una porta marchiata di viola. Sulla porta verde c''è scritto \"Entra e troverai il primo colore secondario\".\'),"
        		+ "(3,'Neutro', 'Questa stanza è stranamente vuota. Ci sono in esposizione al muro alcune delle opere di tuo nonno, tra dipinti e statue, ma a parte quello non c''è altro. Non c''è nemmeno vegetazione o altro, la stanza è immacolata. Sulla porta vedi scritto \"Qui giace la prova finale\". Non ci sono altre porte oltre a quella da cui sei entrato.\'),"
        		+ "(4,'Neutro', 'Ti trovi nella stanza del colore rosso. Ti ricorda molto una vetreria, infatti è piena di strumenti per la lavorazione del vetro e di piccole sculture. Ci sono anche dei camini ai lati della stanza, però noti che la vegetazione non è arrivata fin qui. Non ci sono altre porte oltre a quella da cui sei entrato.'),"
        		+ "(5,'Neutro', 'Ti trovi nella stanza del colore blu. È una delle stanze più trasandate di questo piano. Mezza stanza non ha il pavimento, che è stato invece sostituito da un fossato. Dall''altro lato del fossato c''è un interruttore, forse è collegato in qualche modo alla porta. Non ci sono altre porte oltre a quella da cui sei entrato.'),"
        		+ "(6,'Neutro', 'Ti trovi nella stanza del colore giallo. È completamente diversa dalle stanze vista fino ad ora. Noti macchinari vari, cavi elettrici, nastri trasportatori, interruttori vari, sembra una vera e propria fabbrica. Ti conviene fare attenzione nel maneggiare l''elettricità. Non ci sono altre porte oltre a quella da cui sei entrato.'),"
        		+ "(7,'Neutro', 'Ti trovi nella stanza del colore verde. Sembra una vera e propria giungla, la vegetazione ha completamente preso il sopravvento di questa stanza, tant''è che non sembra nemmeno una stanza vera e propria. Sulla porta c''è scritto \"Recupera il frutto verde per uscire\". Non ci sono altre porte oltre a quella da cui sei entrato.'),"
        		+ "(8,'Neutro', 'Ti trovi nella stanza del colore marrone. È probabilmente la stanza nella quale tuo nonno creava tutte le sue sculture. Vedi tutti gli attrezzi che usava, i tavoli e i piedistalli usurati e persino alcuni dei suoi lavori incompiuti. Non ci sono altre porte oltre a quella da cui sei entrato.'),"
        		+ "(9,'Neutro', 'Ti trovi nella stanza del colore viola. È simile alla stanza del colore giallo, ci sono alcuni macchinari e attrezzi che non avevi visto nel resto della stanza. Però, a differenza di quella stanza, ha un''aria più mistica e misteriosa. Noti dei teli viola in giro, librerie piene di manoscritti ammuffiti e marchingegni strani. Sulla porta vedi scritto \"Trova un modo di riempire l''incavo per uscire\". Non ci sono altre porte oltre a quella da cui sei entrato.')";
        
        String popolaItem = "INSERT INTO item(id_item, stato, descrizione) VALUES "
        		+ "(0,'Spento', 'Una torcia semplice, con un tizzone in cima. È spenta.'),"
        		+ "(0,'Acceso', 'Una torcia semplice, con un tizzone in cima. È accesa.'),"
        		+ "(1,'NonSpostato', 'Un cumulo di macerie e mattonelle rotte. Bloccano la porta sinistra.'),"
        		+ "(1,'Spostato', 'Un cumulo di macerie e mattonelle rotte.'),"
        		+ "(2,'Spento', 'Un camino di mattoni, con della legna dentro. Il fuoco è spento.'),"
        		+ "(2,'Acceso', 'Un camino di mattoni, con della legna dentro. Il fuoco riscalda quel lato della stanza.'),"
        		+ "(3,'SenzaLegna', 'Un camino di mattoni. Non c''è nulla dentro.'),"
        		+ "(3,'Spento', 'Un camino di mattoni, con della legna dentro. Il fuoco è spento.'),"
        		+ "(3,'Acceso', 'Un camino di mattoni, con della legna dentro. Il fuoco riscalda quel lato della stanza.'),"
        		+ "(4,'Neutro', 'Un cumulo di rami e legnetti secchi disposti per terra.'),"
        		+ "(5,'Neutro', 'Sembra un normale pennello con la punta macchiata di colore rosso.'),"
        		+ "(6,'Neutro', 'Un''ascia per tagliare la legna. È abbastanza grande, sicuro che fosse di tuo nonno?'),"
        		+ "(7,'NonCresciuto', 'Un piccolo alberello che è riuscito a crescere nei mattoni del pavimento.'),"
        		+ "(7,'Cresciuto', 'Un robusto albero di quercia. Difficile pensare che bastasse colorarlo di blu per farlo crescere così.'),"
        		+ "(7,'Tagliato', 'Della maestosa quercia ora è rimasto solo il tronco. Forse può aiutarti a premere l''interruttore.'),"
        		+ "(7,'Spinto', 'Il tronco sta tenendo premuto l''interruttore. È zuppo d''acqua.'),"
        		+ "(8,'SenzaAcqua', 'Una statua a forma di testa di drago. Tuo nonno aveva dei gusti stravaganti.'),"
        		+ "(8,'ConAcqua', 'Una statua a forma di testa di drago. L''acqua che esce dalla sua bocca ha creato un fiumiciattolo nel fossato.'),"
        		+ "(9,'Neutro', 'Sembra un normale pennello con la punta macchiata di colore blu.'),"
        		+ "(10,'Acceso', 'L''interruttore del quadro elettrico. È acceso e sta facendo circolare la corrente nel blocco di ferro.'),"
        		+ "(10,'Spento', 'L''interruttore del quadro elettrico. È spento, ora non circola corrente.'),"
        		+ "(11,'NonSpostatoAcceso', 'Un blocco di ferro. Al suo interno sta scorrendo della corrente, sicuro sia una buona idea spostarlo?'),"
        		+ "(11,'NonSpostatoSpento', 'Un blocco di ferro. Non sembra che stia scorrendo corrente al suo interno.'),"
        		+ "(11,'SpostatoSpento', 'Un blocco di ferro. Collega l''interruttore al filo elettrico accanto alla porta, però non circola corrente.'),"
        		+ "(11,'SpostatoAcceso', 'Un blocco di ferro. Ha permesso il passaggio di corrente fino alla porta.'),"
        		+ "(12,'Neutro', 'Sembra un normale pennello con la punta macchiata di colore giallo.'),"
        		+ "(13,'NonCresciuto', 'Da un bocciolo cresciuto per terra sta germogliando una piccola liana.'),"
        		+ "(13,'Cresciuto', 'Ora la liana è cresciuta considerevolmente, forse puoi usarla come corda.'),"
        		+ "(14,'NonCresciuto', 'Una delle vecchie aiuole del giardino di tuo nonno. Ti rattrista vederla senza piante dentro.'),"
        		+ "(14,'Cresciuto', 'Una delle vecchie aiuole del giardino di tuo nonno. Nell''albero che ci è cresciuto dentro, noti un frutto verde.'),"
        		+ "(15,'Neutro', 'Sembra un normale pennello con la punta macchiata di colore verde.'),"
        		+ "(16,'NonColorato', 'Un sostegno che probabilmente usava tuo nonno per scolpire statue. Si vede che era un amante di tutti i tipi d''arte.'),"
        		+ "(16,'Colorato', 'È comparso un blocco di pietra sul piedistallo. Ti senti ispirato, ma non sai di preciso perché...'),"
        		+ "(16,'NonSpostatoStatua', 'Piedistallo con una statua sopra, quasi identica a quella disegnata sulla pedana a pressione. Sei sorpreso delle tue abilità da scultore.'),"
        		+ "(16,'SpostatoStatua', 'Piedistallo con una statua sopra. Sembra essere incastrata nell''incavo della pedana a pressione.'),"
        		+ "(17,'Neutro', 'Uno scalpello da scultore. Probabilmente era nel kit degli attrezzi da scultore di tuo nonno.'),"
        		+ "(18,'NonPremuta', 'Una pedana a pressione situata in fondo alla stanza. Al centro c''è un incavo con dentro disegnata una statua.'),"
        		+ "(18,'Premuta', 'Nell''incavo della pedana a pressione c''è ora la tua replica della statua. Premerla ha aperto la porta della stanza.'),"
        		+ "(19,'Neutro', 'Sembra un normale pennello con la punta macchiata di colore marrone.'),"
        		+ "(20,'Rotto', 'Una scala a pioli poggiata sulla libreria. Al momento è rotta quindi non puoi usarla.'),"
        		+ "(20,'Aggiustato', 'Una scala a pioli poggiata sulla libreria. Ora che l''hai aggiustata, puoi raggiungere la cima della libreria.'),"
        		+ "(21,'Rotto', 'Resti di quello che sembra essere un orologio da taschino molto vecchio. Non funziona più.'),"
        		+ "(21,'Aggiustato', 'Orologio da taschino, che ora è funzionante. Ha una forma circolare.'),"
        		+ "(22,'Vuoto', 'Incavità a forma circolare sulla porta d''ingresso. Ha una forma circolare.'),"
        		+ "(22,'Pieno', 'Hai lasciato l''orologio nell''incavo e ora è pieno. Facendo così, la porta d''ingresso si è riaperta.'),"
        		+ "(23,'Neutro', 'Sembra un normale pennello con la punta macchiata di viola.'),"
        		+ "(24,'Ghiacciato', 'Vivaio situato al centro della stanza. Il centro è congelato.'),"
        		+ "(24,'SenzaTerra', 'Vivaio situato al centro della stanza. Il centro è vuoto, non c''è nemmeno la terra per far crescere le piante.'),"
        		+ "(24,'ConTerra', 'Vivaio situato al centro della stanza. Con la terra dentro è più carino, ma manca ancora qualcosa.'),"
        		+ "(24,'ConPianta', 'Vivaio situato al centro della stanza. Colorarlo di verde ha fatto nascere un picccolo germoglio. Prenditene cura!!'),"
        		+ "(24,'ConInsetti', 'Vivaio situato al centro della stanza. Ora che l''hai annaffiato, sembra che stia attirando dei brutti insettacci. Ci vorrebbe proprio la racchetta anti-insetti elettrica qui.'),"
        		+ "(24,'SenzaInsetti', 'Vivaio situato al centro della stanza. Col tempo, l''alberello al centro crescerà forte e vigoroso.'),"
        		+ "(24,'Cresciuto', 'Vivaio situato al centro della stanza. Grazie alla tua maestria con i colori, ora al centro cresce un albero forte e maestoso. Resta solo una cosa da fare ora.')";
        
        String popolaColors = "insert into color (id, descrizione) values "
        		+ "(0, 'Colore rosso. Si dice che rappresenti la passione, come una fiamma che brucia. Permette di dare fuoco a ciò che tinteggi con questo colore.'),"
        		+ "(1, 'Colore blu. Si dice che rappresenti la pace interiore, come il mare calmo. Permette di annacquare ciò che tinteggi con questo colore, oltre ad attivare alcuni oggetti che generano acqua.'),"
        		+ "(2, 'Colore giallo. Si dice che rappresenti la stravaganza, è un colore eccentrico e attivo come l''elettricità. Permette di scaricare corrente su ciò che tinteggi con questo colore, oltre ad attivare alcuni marchingegni che non puoi normalmente attivare.'),"
        		+ "(3, 'Colore verde. Si dice che rappresenti la vitalità, come una pianta rigogliosa. Permette di far germogliare delle piante e rinvigorisce ciò che tinteggi con questo colore.'),"
        		+ "(4, 'Colore marrone. Si dice che rappresenti la stabilità e la costanza, come le montagne. Fa apparire dei blocchi di terra nei punti che tinteggi con questo colore.'),"
        		+ "(5, 'Colore viola. Si dice che rappresenti la nostalgia e lo scorrere del tempo. Permette o di accelerare lo scorrere del tempo o di far ritornare indietro nel tempo ciò che tinteggi con questo colore.')";
        
        istruzioni.add(dropRoom);
        istruzioni.add(dropItem);
        istruzioni.add(dropColor);
        istruzioni.add(dropDialog);
        istruzioni.add(creaRoom);
        istruzioni.add(creaItem);
        istruzioni.add(creaColor);
        istruzioni.add(creaDialog);
        istruzioni.add(creaScore);
        istruzioni.add(popolaRoom);
        istruzioni.add(popolaItem);
        istruzioni.add(popolaColors);
        
        for (String istruzione : istruzioni) {
            boolean eseguito = eseguiIstruzione(istruzione);
            logger.info(eseguito?"Istruzione :{} eseguita con successo.":"Errore di esecuzione dell'istruzione: {}", istruzione);
        }
        
        try {
        	//provo a creare la directory in cui salvare i file di salvataggio
			getDir();
		} catch (IOException e) {
			logger.error("Cartella saves non creata, i file saranno salvati nella dir principale: ",e);
		}
    }

    /**
     * Esegue l'istruzione SQL passata come parametro, sul database.
     * 
     * @param istruzione la stringa dell'istruzione SQL da eseguire.
     * @return true se l'istruzione è stata eseguita con successo, false in caso di errore.
     */
    private static boolean eseguiIstruzione(String istruzione) {
        boolean successo = false;
        Connection connection = DatabaseConnection.getConnection();
        Statement statement;
        try {
            statement = connection.createStatement();
            statement.execute(istruzione);
            successo = true;
        } catch (SQLException e) {
            logger.error("Eccezione in fase di esecuzione dell'istruzione: {} - ",istruzione, e);
        }
        return successo;
    }
    
    /**
     * metodo che crea la directory "saves" dei salvataggi.
     * 
     */
    private static void getDir() throws IOException{
    	File saves = new File("saves");
    	if(!saves.exists()) {
    		boolean md = saves.mkdir();
    		if(!md)throw new IOException();
    	}
    }
}
