# The Colors Within Your Soul - Report

## Indice
### [Caratteristiche del progetto](#Caratteristiche-del-progetto)
> #### [1. Introduzione generale](#Introduzione-generale)
> #### [2. Caratteristiche principali](#Caratteristiche-principali)
> #### [3. Trama del gioco](#Trama-del-gioco)
> #### [4. Walkthrough](#Walkthrough-del-gioco)
> #### [5. Implementazione del progetto](#Implementazione-del-progetto)
### [Utilizzo dei vari argomenti del corso](#Utilizzo-dei-vari-argomenti-del-corso)
> #### [1. OOP, diagramma delle classi e specifiche algebriche](#Progettazione-OOP)
> #### [2. File](#File)
> #### [3. Database](#Database)
> #### [4. Thread](#Thread)
> #### [5. REST e Socket](#REST-e-Socket)
> #### [6. GUI](#GUI)
> #### [7. Lambda expressions/functions](#Lambda-expressions-e-functions)

## Caratteristiche del progetto
In questa sezione spieghiamo meglio cos'è questo progetto, il suo scopo e alcuni approfondimenti sulla struttura del gioco.
### Introduzione generale
Questo progetto è stato sviluppato come esame per il corso *Metodi Avanzati di Programmazione*, tenuto dal Prof. *Pierpaolo Basile* presso l'Università degli Studi di Bari Aldo Moro. Il titolo del progetto è *The Colors Within Your Soul*. 

L'obiettivo del corso è stato quello di introdurre, conoscere e approfondire un particolare paradigma di programmazione, ossia la Programmazione ad Oggetti e di andare ad utilizzare i concetti appresi per la realizzazione di un progetto software. In particolare, questo è stato fatto mediante lo sviluppo di un'avventura testuale.

Un'avventura testuale è un tipo di videogioco in cui il giocatore interagisce con il mondo di gioco tramite comandi testuali. Questi giochi erano particolarmente popolari nei primi anni dell'era informatica, quando l'elaborazione grafica era limitata.

### Caratteristiche Principali
- Interfaccia Testuale: l'interazione avviene tramite comandi scritti, come "nord", "prendi torcia" o "osserva".
- Descrizioni Dettagliate: il gioco descrive le scene, gli oggetti e le azioni attraverso testi dettagliati, stimolando l'immaginazione del giocatore.
- Enigmi e Puzzle: i giocatori devono risolvere enigmi o puzzle per avanzare nel gioco, utilizzando azioni varie, oggetti e colori a loro disposizione.

### Trama del gioco
In quest'avventura testuale, giochi nelle vesti di un ragazzo che è cresciuto con suo nonno in una villa a Vieste. Nella speranza di fare carriera, questo ragazzo decide di partire per gli USA, nello specifico a New York, subito dopo la laurea.

Dopo tanti anni, il ragazzo riesce ad ottenere un lavoro d'ufficio abbastanza prestigioso, ma pian piano si rende conto di essersi abituato ad una vita monotona di città che non gli dà lo spazio necessario per esprimersi come persona.

Un giorno, riceve una lettera dalla quale apprende la notizia della morte di suo nonno. Per di più, gli ha lasciato in eredità la villa dov'è cresciuto. Triste per la morte di suo nonno ma deciso a dare una nuova piega alla sua vita, coglie l'occasione per tornare in Italia. Tornato a casa, il ragazzo intraprenderà un'avventura nel piano segreto della villa che gli permetterà di riacquisire i colori perduti della sua anima.

![Mappa](./img/MappaGioco.png)

### Walkthrough del gioco
Per la fruibilità del gioco e per dare un aiuto ai player che dovessero rimanere bloccati, abbiamo registrato una playthrough completa del gioco. Consigliamo anche il frequente utilizzo del comando "Osserva", in quanto abbiamo cercato di lasciare dei piccoli aiuti nelle descrizioni di stanze e oggetti. Cliccare l'immagine sotto porterà al video Youtube con la playthrough completa, che ha una durata di circa 6 minuti.

<a href="https://youtu.be/8sHqEgjV5qA">
  <img src="./img/Thumbnail.png" alt="Walkthrough">
</a>

### Implementazione del Progetto
Il progetto è stato realizzato utilizzando il linguaggio di programmazione Java affiancato al framework Maven per la gestione delle dipendenze e la compilazione del progetto e comprende le seguenti funzionalità principali:

- Parser: trattandosi di un'avventura testuale, il parser è il componente principale del gioco, responsabile di interpretare i comandi inseriti dall'utente e di tradurli in azioni.
- Database: il gioco include un database, in particolare H2, utilizzato per contenere le descrizioni delle stanze, degli oggetti, dei colori e persino i dialoghi di gioco. Lato server, invece, c'è un DB che conserva la classifica dei giocatori.
- Thread: il gioco utilizza i thread per la gestione del tempo di gioco, della musica e per qualche altra chicca come la barra di caricamento. Inoltre, abbiamo lavorato direttamente sull'Event Dispatch Thread per la gestione delle componenti grafiche del gioco.
- File: il gioco utilizza dei file JSON per il salvataggio della configurazione iniziale di gioco e per la gestione dei salvataggi.
- Socket / REST: il gioco effettua una chiamata a un'API per recuperare alcune opere d'arte famose con cui ricompensare il player a fine gioco. I Socket, invece, vengono utilizzati per aprire una comunicazione con un server, permettendo di partecipare alla classifica dei giocatori sulla base del tempo impiegato a finire il gioco.
- GUI: nonostante il gioco sia un'avventura testuale, è stata comunque implementata una finestra di gioco apposita sulla quale vengono stampate alcune informazioni di gioco come inventario del giocatore, stanza corrente e così via.

## Utilizzo dei vari argomenti del corso
In questa sezione approfondiamo l'utilizzo dei vari argomenti di questo corso all'interno di questo corso, spiegando come sono stati implementati e a quali funzionalità corrispondono all'interno del codice. Verranno mostrati anche degli stralci di codice presi dal progetti come esempio, però si noti bene che sono stati inseriti solo a scopo informativo, di conseguenza sono stati semplificati in funzione della spiegazione e non necessariamente riflettono completamente il codice sorgente del nostro gioco.

### Progettazione OOP
Questo progetto è stato sviluppato con l'intento di scrivere un codice quanto più riutilizzabile ed estendibile per il futuro. Sono stati applicati molti principi della OOP tra cui incapsulamento, ereditarietà e polimorfismo. Sono state scritte dunque molte classi con l'intento di dare una definizione generica ad un determinato concetto, eventualmente ereditando poi da tali classi per definire una maggiore specializzazione di quella classe. Ad esempio, abbiamo definito la classe <code>GameEngine</code> come classe astratta che definisce gli attributi e i metodi che un'avventura testuale basata sul nostro codice deve avere. La classe di gioco principale <code>ColorsWithinYourSoulGame</code>, infatti, è una classe che eredita da <code>GameEngine</code> definendo il comportamento di ciascun metodo. Con l'organizzazione in classi che abbiamo scelto, è possibile usufruire di molti metodi già scritti semplicemente ereditando dalle classi che abbiamo già scritto. Ad esempio, la classe <code>GameToGUICommunication</code> usa una generica istanza di <code>GameEngine</code> e, di conseguenza, è possibile usarla su qualunque classe che sia sottoclasse di <code>GameEngine</code> grazie al *principio di sostituibilità delle sottoclassi*. Abbiamo inoltre cercato di scrivere la logica di gioco in modo da poterla rendere quanto più flessibile possibile. Ora, una singola interazione di gioco è definita mediante un oggetto di tipo <code>Interaction</code>, così che l'intera logica di gioco possa essere descritta mediante una serie di oggetti di questa classe. Per aggiungere un'interazione, basterà quindi definire un oggetto di <code>Interaction</code> e aggiungerlo alla lista di interazioni del gioco.

```java
public abstract class GameEngine {
    private ArrayList<Room> rooms;
    private ArrayList<Item> inventory;
    private ArrayList<Interaction> gameInteractions;
    //...

    public abstract void welcomePlayer();
    public abstract void update();
    public abstract void defineGameInteractions();
    //...
}

public class ColorsWithinYourSoulGame extends GameEngine {
    //Definizione metodi
    //...
    
    public void defineGameInteractions() {
        //Crea e aggiunge un'interazione di gioco
        gameInteractions.add(InteractionFactory.buildInteraction(oggetto1, oggetto2, comando, "stato1", "stato2", () -> {
            //Definizione del comportamento dell'interazione
            //...
        });
    }
}
```

#### Diagramma delle classi
Qui sotto riportiamo i diagrammi delle classi della porzione di programma che si è deciso di rappresentare, nello specifico la parte di programma che si occupa della definizione del gioco vero e proprio e della gestione della logica di gioco.
![Diagramma delle classi](./img/UML_completo.png)
![Diagramma delle interactions](./img/UML_interaction.png)

#### Specifiche Algebriche
Nel nostro progetto, abbiamo spesso fatto uso delle strutture dati **Mappa** e **Lista**. Ad esempio, le stanze del gioco sono conservate in una lista e ogni stanza ha una lista con gli item in essa contenuta. La mappa, invece, è usata per conservare i comandi di gioco e le stopwords all'interno del parser. In questa sezione definiremo le specifiche algebriche per queste due strutture dati.

#### Specifica algebrica della Lista
La lista è una struttura dati che permette di memorizzare e recuperare informazioni sfruttando l'indice di posizione degli elementi contenuti.

#### Specifica sintattica
<table>
    <thead>
        <tr>
            <th colspan="2">Tipi</th>
        </tr>
    </thead>
    <tbody>
        <tr>
            <td colspan="2"><code>List</code>, <code>Item</code>, <code>Integer</code>, <code>Boolean</code></td>
        </tr>
        <tr>
            <td colspan="2" align="center"><strong>Operatori</strong></td>
        </tr>
        <tr>
            <td><code>newList() -> List</code></td>
            <td>Crea una nuova lista vuota</td>
        </tr>
        <tr>
            <td><code>add(List, Item, Integer) -> List</code></td>
            <td>Aggiunge un elemento alla lista nella posizione specificata</td>
        </tr>
        <tr>
            <td><code>isEmpty(List) -> Boolean</code></td>
            <td>Restituisce <code>true</code> se la lista è vuota altrimenti <code>false</code></td>
          </tr>
            <tr>
                <td><code>getSize(List) -> Integer</code></td>
                <td>Restituisce l'ultima posizione occupata da un elemento</td>
            </tr> 
            <tr>
                <td><code>getIndex(List, Item) -> Integer</code></td>
                <td>Restituisce la posizione dell'elemento specificato</td>
            </tr> 
            <tr>
                <td><code>getItem(List, Integer) -> Item</code></td>
                <td>Restituisce l'elemento nella posizione specificata</td> 
            </tr> 
            <tr>
                <td><code>remove(List, Integer) -> List</code></td>
                <td>Rimuove dalla lista l'elemento nella posizione specificata</td>  
            </tr>
            <tr>
                <td><code>contains(List, Item) -> Boolean</code></td>
                <td>Restituisce <code>true</code> se l'elemento specificato è contenuto nella lista</td>
            </tr>
    </tbody>
</table>
Alcuni appunti: <code>Item</code> è un tipo generico usato come placeholder. Può essere rimpiazzato da qualunque tipo di dato. <code>Interger</code> (numeri interi) e <code>Boolean</code> (valori di verità <code>true</code> e <code>false</code>) invece, sono tipi ausiliari alla definizione della specifica algebrica della lista.

#### Osservazioni e Costruttori

<table>
  <thead>
    <tr>
      <th></th>
      <th colspan="2">Costruttori di l'</th>
    </tr>
  </thead>
  <tbody align="center">
    <tr>
      <td><strong>Osservazioni</strong></td>
      <td><code>newList</code></td>
      <td><code>add(l, it, id)</code></td>
    </tr>
    <tr>
      <td><code>isEmpty(l')</code></td>
      <td><code>true</code></td>
      <td><code>false</code></td>
    </tr>
    <tr>
      <td><code>getSize(l')</code></td>
      <td><code>error</code></td>
      <td>if <code>isEmpty(l)</code> then <code>1</code> else <code>getSize(l) + 1</code></td>
    </tr>
    <tr>
      <td><code>getIndex(l', it')</code></td>
      <td><code>error</code></td>
      <td>if <code>it = it'</code> then <code>id</code> else <code>getIndex(l, it')</code></td>
    </tr>
    <tr>
      <td><code>getItem(l', id')</code></td>
      <td><code>error</code></td>
      <td>if <code>id = id'</code> then <code>it</code> else <code>getItem(l, id')</code></td>
    </tr>
    <tr>
      <td><code>remove(l', id')</code></td>
      <td><code>error</code></td>
      <td>if <code>id = id'</code> then <code>l</code> else <code>add(remove(l, id'), it)</code></td>
    </tr>
    <tr>
      <td><code>contains(l', it')</code></td>
      <td><code>false</code></td>
      <td>if <code>it = it'</code> then <code>true</code> else <code>contains(l, it')</code></td>
    </tr>
  </tbody>
</table>

#### Specifica semantica
- **DECLARE**
  - <code>l</code>, <code>l'</code>: <code>List</code>
  - <code>it</code>, <code>it'</code>: <code>Item</code>
  - <code>id</code>, <code>id'</code>: <code>Integer</code>


- **OPERATIONS**
  - <code>isEmpty(newList)</code> = <code>true</code>
  - <code>isEmpty(add(l, it, id))</code> = <code>false</code>
  - <code>getSize(add(l, it, id))</code> = if <code>isEmpty(l)</code> then <code>1</code> else <code>getSize(l) + 1</code>
  - <code>getIndex(add(l, it, id), it')</code> = if <code>it = it'</code> then <code>id</code> else <code>getIndex(l, it')</code>
  - <code>getItem(add(l, it, id), id')</code> = if <code>id = id'</code> then <code>it</code> else <code>getItem(l, id')</code>
  - <code>remove(add(l, it, id), id')</code> = if <code>id = id'</code> then <code>l</code> else <code>add(remove(l, id'), it)</code>
  - <code>contains(newList, it')</code> = <code>false</code>
  - <code>contains(add(l, it, id), it')</code> = if <code>it = it'</code> then <code>true</code> else <code>contains(l, it')</code>

#### Specifica di restrizione
- **RESTRICTIONS**
  - <code>getSize(newList)</code> = <code>error</code>
  - <code>getIndex(newList, it')</code> = <code>error</code>
  - <code>getItem(newList, id')</code> = <code>error</code>
  - <code>remove(newList, id')</code> = <code>error</code>

#### Specifica algebrica della Mappa

- La mappa è una struttura dati che associa una chiave ad un valore, permettendo di memorizzare e recuperare informazioni in modo efficiente.

#### Specifica sintattica
<table>
    <thead>
        <tr>
            <th colspan="2">Tipi</th>
        </tr>
    </thead>
    <tbody>
        <tr>
            <td colspan="2">Map, Key, Value, Boolean, Integer</td>
        </tr>
        <tr>
            <td colspan="2"><strong>Operatori</strong></td>
        </tr>
        <tr>
            <td><code>newMap() -> Map</code></td>
            <td>Crea una nuova mappa vuota</td>
        </tr>
        <tr>
            <td><code>isEmpty(Map) -> Boolean</code></td>
            <td>Restituisce <code>true</code> se la mappa è vuota, <code>false</code> altrimenti</td>
        </tr>
        <tr>
            <td><code>put(Map, Key, Value) -> Map</code></td>
            <td>Aggiunge una coppia chiave-valore alla mappa, o, se già presente, ne aggiorna il valore</td>
        </tr>
        <tr>
            <td><code>get(Map, Key) -> Value</code></td>
            <td>Restituisce il valore associato alla chiave specificata</td>
        </tr>
        <tr>
            <td><code>containsKey(Map, Key) -> Boolean</code></td>
            <td>Restituisce <code>true</code> se la chiave specificata è presente nella mappa</td>
        </tr> 
        <tr>
            <td><code>containsValue(Map, Value) -> Boolean</code></td>
            <td>Restituisce <code>true</code> se il valore specificato è presente nella mappa</td> 
        </tr>
        <tr>
            <td><code>remove(Map, Key) -> Map</code></td>
            <td>Rimuove la chiave ed il valore associato ad essa dalla mappa</td>
        </tr> 
        <tr>
            <td><code>size(map) -> Integer</code></td>
            <td>Restituisce il numero di coppie chiave-valore presenti nella mappa</td>  
        </tr>
    </tbody>
</table>

##### Osservazioni e Costruttori

<table>
  <thead>
    <tr>
      <th></th>
      <th colspan="2">Costruttori di m'</th>
    </tr>
  </thead>
  <tbody align="center">
    <tr>
      <td><strong>Osservazioni</strong></td>
      <td><code>newMap</code></td>
      <td><code>put(m, k, v)</code></td>
    </tr>
    <tr>
      <td><code>isEmpty(m')</code></td>
      <td><code>true</code></td>
      <td><code>false</code></td>
    </tr>
    <tr>
      <td><code>containsKey(m', k')</code></td>
      <td><code>false</code></td>
      <td>if <code>k = k'</code> then <code>true</code> else <code>containsKey(m, k')</code></td>
    </tr>
    <tr>
      <td><code>containsValue(m', v')</code></td>
      <td><code>false</code></td>
      <td>if <code>v = v'</code> then <code>true</code> else <code>containsValue(m, v')</code></td>
    </tr>
    <tr>
      <td><code>get(m', k')</code></td>
      <td><code>error</code></td>
      <td>if <code>k = k'</code> then <code>v</code> else <code>get(m, k')</code></td>
    </tr>
    <tr>
      <td><code>remove(m', k')</code></td>
      <td><code>error</code></td>
      <td>if <code>k = k'</code> then <code>m</code> else <code>put(remove(m, k'), k, v)</code></td>
    </tr>
    <tr>
      <td><code>size(m')</code></td>
      <td><code>0</code></td>
      <td>if <code>isEmpty(m)</code> then <code>1</code> else <code>size(m) + 1</code></td>
    </tr>
  </tbody>
</table>

#### Specifica semantica

- **DECLARE**
  - <code>m</code>, <code>m'</code>: <code>Map</code>
  - <code>k</code>, <code>k'</code>: <code>Key</code>
  - <code>v</code>, <code>v'</code>: <code>Value</code>

- **OPERATIONS**
  - <code>isEmpty(newMap)</code> = <code>true</code>
  - <code>isEmpty(put(m, k, v))</code> = <code>false</code>
  - <code>containsKey(newMap, k')</code> = <code>false</code>
  - <code>containsKey(put(m, k, v), k')</code> = if <code>k = k'</code> then <code>true</code> else <code>containsKey(m, k')</code>
  - <code>containsValue(newMap, v')</code> = <code>false</code>
  - <code>containsValue(put(m, k, v), v')</code> = if <code>v = v'</code> then <code>true</code> else <code>containsValue(m, v')</code>
  - <code>get(put(m, k, v), k')</code> = if <code>k = k'</code> then <code>v</code> else <code>get(m, k')</code>
  - <code>remove(put(m, k, v), k')</code> = if <code>k = k'</code> then <code>m</code> else <code>put(remove(m, k'), k, v)</code>
  - <code>size(newMap)</code> = <code>0</code>
  - <code>size(put(m, k, v))</code> = <code>size(m) + 1</code>


#### Specifica di restrizione

- **RESTRICTIONS**
  - <code>get(newMap, k')</code> = <code>error</code>
  - <code>remove(newMap, k')</code> = <code>error</code>
  
### File
All'interno del nostro progetto, abbiamo utilizzato i file per la memorizzazione di alcune informazioni a lungo termine senza utilizzare il DB. La gestione dei file ci ha permesso di memorizzare e recuperare i dati del gioco in modo persistente, garantendo la continuità dell'esperienza di gioco per gli utenti. Per la maggior parte, abbiamo usato file di tipo JSON per memorizzare i dati in modo strutturato e leggibile, facilitandone la lettura e la manipolazione.

Principalmente, facciamo uso dei file JSON per la gestione dei salvataggi e il caricamento iniziale del gioco. Nel nostro progetto abbiamo un file, <code>start.json</code>, che contiene la definizione del gioco nel suo stato iniziale. Contiene quindi i collegamenti tra le stanze e gli oggetti, tutto quanto nello stato iniziale. Inoltre, la gestione dei salvataggi avviene sempre mediante file JSON. Al salvataggio del gioco, viene generato un file che rappresenta lo stato del gioco al momento del salvataggio, conservando quindi informazioni come lo stato delle stanze e gli oggetti al loro interno, quali oggetti sono nell'inventario del player e in che stato si trova ogni oggetto di gioco.

Per la gestione di questi file, abbiamo creato una classe che si occupi di serializzare e deserializzare gli oggetti di gioco, indipendentemente dal loro stato corrente, così da avere un unica interfaccia per qualsiasi caso. Se il player inizia una nuova partita, verrà semplicemente deserializzato il contenuto di <code>start.json</code>. Se il player vuole invece caricare un salvataggio, gli verrà proposta un'interfaccia per la scelta del file implementata mediante la classe di Java <code>JFileChooser</code> e verrà deserializzato il contenuto del file scelto dal player. La serializzazione del file, invece, avviene al momento del salvataggio della partita. Quando il player decide di salvare, sempre mediante interfaccia di <code>JFileChooser</code>, gli verrà chiesto di decidere il file su cui salvare (è anche possibile creare un nuovo file di salvataggio). Su quel file verrà poi serializzato il contenuto della partita. Abbiamo inoltre definito una classe <code>BaseGameDefinition</code> che contiene vari metodi per la ricostruzione del gioco nel suo stato originale. Se ci dovessero essere problemi all'avvio di una nuova partita, quindi alla deserializzazione di <code>start.json</code>, viene richiamata questa classe, nello specifico il metodo <code>createJsonBackup</code> con lo scopo di creare un'istanza del gioco di base e serializzarla sul file. Il file ricostruito viene quindi deserializzato per far cominciare la partita.

```java
public void createJsonBackup() {
    GameToJson game = new GameToJson();
    //Codice che imposta le caratteristiche dell'oggetto game come se fosse una nuova partita
    //...

    JsonUtil.writeJsonToFile("src/main/resources/static/start.json", game);
}
```

La classe che usiamo per la gestione dei file Json è <code>JsonUtil</code>. In questa classe vengono definiti i metodi <code>readJsonFromFile</code> e <code>writeJsonToFile</code> che si occupano rispettivamente della deserializzazione e della serializzazione degli oggetti di gioco. Anche se non vengono usati, nel caso servano per evoluzioni future del programma, sono stati implementati i metodi <code>getObjectFromJsonString</code> e <code>getJsonStringFromObject</code>, che si occupano rispettivamente di convertire una stringa letta da un file JSON in oggetto e di convertire un oggetto in una stringa che può essere poi scritta su un file JSON. Tutti questi metodi lavorano sul path del file passato come parametro, così da permetterne l'utilizzo indipendentemente da se è richiesta una nuova partita o il caricamento di un salvataggio.

```java
public static void writeJsonToFile(String filePath, Object obj) {
    Gson gson = new Gson();
    String jsonString = gson.toJson(obj);

    try (FileWriter fileWriter = new FileWriter(filePath, false)) {
        fileWriter.write(jsonString);
    } catch (IOException e) {
        logger.error("Eccezione in fase di scrittura del file Json: ", e);
    }
}

public static GameToJson readJsonFromFile(String filePath) {
    Gson gson = new Gson();
    GameToJson game = null;

    try (FileReader fileReader = new FileReader(filePath)) {
        game = gson.fromJson(fileReader, GameToJson.class);
    } catch (IOException e) {
        logger.error("Eccezione in fase di deserializzazione: ", e);
    }
    return game;
}
```

Notiamo che la classe che sta venendo serializzata è <code>GameToJson</code> e non una classe come <code>ColorsWithinYourSoulGame</code> o <code>GameEngine</code>. Questo perché la classe <code>GameEngine</code> e di conseguenza anche <code>ColorsWithinYourSoulGame</code>, contiene una lista di oggetti della classe <code>Interaction</code> che definiscono la logica di gioco. Questi oggetti non hanno bisogno di essere salvati su file, in quanto la logica di <code>ColorsWithinYourSoulGame</code> è sempre ben definita nella classe <code>BaseGameLogic</code>. Non avrebbe logicamente senso salvare anche la logica di gioco, quindi. Per questo, viene invece serializzata/deserializzata la classe <code>GameToJson</code> che contiene gli attributi del gioco che vanno effettivamente salvati. Quindi, in fase di serializzazione convertiamo prima l'istanza di gioco in <code>GameToJson</code> e successivamente viene salvata su file. Il viceversa vale per la deserializzazione, quindi da file viene letto un oggetto <code>GameToJson</code> che poi viene convertito in istanza di gioco.

Abbiamo usato i file anche per qualche altra parte dell'applicazione. Ad esempio, abbiamo un file <code>application.properties</code> che, come suggerisce il nome, contiene alcune proprietà generali dell'applicazione, ad esempio la versione, dati per la connessione al DB, dati per la connessione all'API, flag vari e path di file utili al programma. Inoltre, il parser legge le stopwords, cioè le parole che deve ignorare in fase di interpretazione del comando (preposizioni, articoli e così via), da un file di testo.
### Database

### Thread

### REST e Socket

### GUI

### Lambda expressions e functions
In questo progetto, abbiamo utilizzato le lambda functions per una serie di funzioni chiave al funzionamento del programma. Come già anticipato nella sezione OOP, la logica di gioco è composta da una serie di interazioni che determinano cosa accade quando si esegue un'azione su un singolo oggetto (<code>SingleObjectInteraction</code>), cosa accade quando si esegue un'azione che include due oggetti (<code>DirectInteraction</code>) e cosa accade a un oggetto quando un altro oggetto riceve un cambiamento di stato (<code>ChainInteraction</code>). In ognuno di questi casi, le azioni che il gioco deve eseguire quando viene ricevuto un determinato comando su determinati oggetti vengono definite mediante una lambda function. Nello specifico, la classe <code>Interaction</code> ha come attributo un oggetto di tipo <code>Interactable</code>, che è un'interfaccia funzionale che include solo il metodo <code>executeInteraction</code>. Questo metodo è quello che effettivamente esegue l'interazione sugli oggetti interessati prendendo in input l'istanza di <code>GameEngine</code> che rappresenta l'istanza di gioco. Per definire un'interazione, bisogna quindi istanziare un oggetto della categoria di classi <code>Interaction</code> istanziando l'interfaccia funzionale <code>Interactable</code> mediante una lambda function che verrà passata come parametro. Qui sotto riportato un esempio pratico che definisce cosa succede col comando "USA Orologio SU Incavo" quando l'orologio è nello stato "Aggiustato":

```java
public void defineGameInteractions() {
    //Usare l'orologio aggiustato sull'incavo per riempirlo
    gameLogic.add(InteractionFactory.buildInteraction(
            getObjectByName("Orologio", objects), getObjectByName("Incavo", objects), Command.USA, "Aggiustato", "Pieno",
            (gameObjects, targetStates, gameEngine) -> {
                    gameObjects.get(1).setStatus(targetStates.get(1));
                    ((Item) gameObjects.getFirst()).setPickable(false);
    
                    gameEngine.getRoomByName("StanzaViola").getRoomConnection(Command.NORD).unlock();
                    gameEngine.getRoomByName("StanzaViola").addItem((Item) getObjectByName("Orologio", objects));
                    gameEngine.getRoomByName("AtticoCentrale").getRoomConnection(Command.NORD).unlock();
                    gameEngine.removeItem((Item) getObjectByName("Orologio", objects));
    
                    GameToGUICommunication.getInstance().toGUI("Messaggio");
                }
            }
    ));
}
```

Quando il metodo <code>update()</code> scorrerà la lista di <code>Interactions</code> e, quando viene trovata un'interazione che corrisponde all'output ricevuto dal parser (se viene trovata), verrà eseguita l'istanza ddell'interfaccia funzionale <code>Interactable</code> istanziata mediante la lambda function. Questo permette di definire l'intera logica di gioco mediante una serie di lambda functions.

Le lambda expressions sono state usate anche come supporto in altri punti del programma. Nello specifico, sono state utilizzate per compiere queste funzioni:
- Supporto per la creazione e la gestione della GUI: In alcuni casi, abbiamo fatto uso delle lambda expressions per la gestione delle GUI. Ad esempio, abbiamo definito il comportamento dei listener di alcuni pulsanti mediante lambda expressions, oppure abbiamo ridefinito alcuni metodi come <code>paintComponent()</code> di <code>JPanel</code> per l'aggiornamento dinamico della GUI, oppure sono state usate per impostare delle task che la GUI doveva eseguire con un certo delay o con una certa cadenza con i metodi <code>SwingUtilities.invokeLater()</code> e <code>Timer.scheduleAtFixedRate()</code>, ad esempio per l'update delle immagini di gioco al cambio di stanza e per l'update del <code>JLabel</code> contenente il timer di gioco.
- Supporto nella ricerca e nel filtraggio di elementi all'interno delle strutture dati: In alcuni casi, abbiamo fatto uso delle lambda expressions per ciclare delle strutture dati alla ricerca di un determinato elemento o applicando un filtro. Ad esempio, nella classe <code>ClientRest</code>, abbiamo fatto uso delle lambda expressions per ricavare il nome dell'artista dalla risposta ricevuta dall'API, oppure nella classe <code>Parser</code> ne abbiamo fatto uso per dividere la stringa di input in tokens, togliendo tutti i token inclusi nella lista di stopwords.
```java
//Classe ClientRest
private static String getNameArtist(String jsonString) {
      JsonObject jsonObject = JsonParser.parseString(jsonString).getAsJsonObject();
      
      //Lambda function che recupera il nome dell'artista 
      Optional<String> name = Optional.ofNullable(jsonObject)
          .map(obj -> obj.getAsJsonObject("_embedded"))
          .map(embedded -> embedded.getAsJsonArray("artists"))
          .flatMap(artists -> !artists.isEmpty() ? Optional.of(artists.get(0).getAsJsonObject()) : Optional.empty())
          .map(artist -> artist.get("name").getAsString());

      //...
}

//Classe Parser
public ParserOutput parse(String input) {
      String[] tokens;

      //Lambda function che divide la stringa di input in token filtrando le stopwords
      //e convertendo tutto in minuscolo, salvando poi i token nell'array di stringhe tokens
      tokens = Arrays.stream(input.split("\\s+"))
              .map(String::toLowerCase)
              .filter(w -> !stopwords.contains(w))
              .toArray(String[]::new);

      //...
}
```
