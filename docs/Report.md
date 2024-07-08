# The Colors Within Your Soul - Report

## Indice
### [Caratteristiche del progetto](#Caratteristiche-del-progetto)
> #### [1. Introduzione generale](#Introduzione-generale)
> #### [2. Caratteristiche principali](#Caratteristiche-principali)
> #### [3. Trama del gioco](#Trama-del-gioco)
> #### [4. Walkthrough](#Walkthrough-del-gioco)
> #### [5. Implementazione del progetto](#Implementazione-del-progetto)
### [Utilizzo dei vari argomenti del corso](#Utilizzo-dei-vari-argomenti-del-corso)
> #### [1. OOP, diagramma delle classi e specifiche algebriche](#OOP)
> #### [2. File](#File)
> #### [3. Database](#Database)
> #### [4. Thread](#Thread)
> #### [5. REST e Socket](#REST-e-Socket)
> #### [6. GUI](#GUI)
> #### [7. Lambda expressions/functions](#Lambda-expressions-e-functions)

## Caratteristiche del progetto
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

### OOP

### File

### Database

### Thread

### REST e Socket

### GUI

### Lambda expressions e functions
