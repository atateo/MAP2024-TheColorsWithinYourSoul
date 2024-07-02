package it.uniba.map.giocotestuale.utility;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.util.HashMap;

/**
 * Classe che gestice la riproduzione della musica di gioco. È una classe singleton.
 */
public class Mixer extends Thread {
    /**
     * Istanza di Logger usata per i messaggi di log.
     */
    private static final Logger logger = LogManager.getLogger(Mixer.class);
    /**
     * Singola istanza della classe Mixer.
     */
    public static Mixer instance;

    /**
     * Array contenente tutte le tracks caricate nel Mixer.
     */
    private final Clip[] tracks;
    /**
     * Indice della track in esecuzione al momento.
     */
    private int currentTrack;
    /**
     * Booleano che indica se il Mixer sta riproducendo qualcosa o meno.
     */
    private boolean isRunning = false;
    /**
     * HashMap usata per associare le stanze alla loro track da riprodurre.
     */
    private final HashMap<String, Integer> roomNameToTrackIndex;

    /**
     * Costruttore della classe Mixer. Carica i file e inserisce le coppie in roomNameToTrackIndex. È privato.
     */
    private Mixer() {
        final int numberOfTracks = 9;

        this.tracks = new Clip[numberOfTracks];
        roomNameToTrackIndex = new HashMap<>();

        //Caricare qui la musica del gioco in tracks;
        loadTrack(0, "src/main/resources/music/AA_AJ_DrewMishamStudio.wav");
        loadTrack(1, "src/main/resources/music/Xenoblade3_MakthaWoods.wav");
        loadTrack(2, "src/main/resources/music/ZeldaSkywardSword_EarthTemple.wav");
        loadTrack(3, "src/main/resources/music/ZeldaLinkBetweenWorlds_SwampPalace.wav");
        loadTrack(4, "src/main/resources/music/DKC_FearFactoryHDRestored.wav");
        loadTrack(5, "src/main/resources/music/HollowKnight_QueensGarden.wav");
        loadTrack(6, "src/main/resources/music/ZeldaMajorasMask_StoneTowerTemple.wav");
        loadTrack(7, "src/main/resources/music/Heartbound_SpaceLasers.wav");
        loadTrack(8, "src/main/resources/music/Celeste_Prologue.wav");
        loadTrack(9, "src/main/resources/music/SuperMario64_Credits.wav");

        //Scrivere qui il codice che associa l'indice della musica alle stanze
        roomNameToTrackIndex.put("Menu", 0);
        roomNameToTrackIndex.put("AtticoCentrale", 1);
        roomNameToTrackIndex.put("StanzaColoriPrimari", 1);
        roomNameToTrackIndex.put("StanzaColoriSecondari", 1);
        roomNameToTrackIndex.put("StanzaRosso", 2);
        roomNameToTrackIndex.put("StanzaBlu", 3);
        roomNameToTrackIndex.put("StanzaGiallo", 4);
        roomNameToTrackIndex.put("StanzaVerde", 5);
        roomNameToTrackIndex.put("StanzaMarrone", 6);
        roomNameToTrackIndex.put("StanzaViola", 7);
        roomNameToTrackIndex.put("StanzaFinale", 8);
        roomNameToTrackIndex.put("Credits", 9);
    }

    /**
     * Metodo che legge il file audio col percorso specificato e lo carica nell'array, nell'index specificato.
     * @param index Indice dell'elemento dell'array nel quale verrà caricata la track.
     * @param filePath Percorso del file audio da caricare.
     */
    private void loadTrack(int index, String filePath) {
        try {
            File file = new File(filePath);
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(file);
            tracks[index] = AudioSystem.getClip();
            tracks[index].open(audioStream);
        } catch (Exception e) {
            logger.info("Errore nella lettura e caricamento del file musicale.");
        }
    }

    /**
     * Metodo che restituisce l'istanza singola della classe Mixer.
     * @return Istanza della classe Mixer.
     */
    public static Mixer getInstance() {
        if (instance == null) {
            instance = new Mixer();
        }
        return instance;
    }

    /**
     * Override del metodo run. Fa partire il Mixer, cominciando dalla musica del menù.
     */
    @Override
    public void run() {
        isRunning = true;
        try {
            if (tracks[0] != null) {
                tracks[0].start();
                tracks[0].loop(Clip.LOOP_CONTINUOUSLY);
                currentTrack = 0;
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Rimette in esecuzione la track corrente.
     */
    public void startTrack() {
        if (tracks[currentTrack] != null) {
            isRunning = true;
            tracks[currentTrack].start();
        }
    }

    /**
     * Ferma l'esecuzione della track corrente.
     */
    public void stopTrack() {
        if (tracks[currentTrack] != null) {
            isRunning = false;
            tracks[currentTrack].stop();
        }
    }

    /**
     * Cambia la track corrente con quella relativa all'indice passato come parametro.
     * @param index Indice della nuova track da mettere in esecuzione.
     */
    private void changeClip(int index) {
        if (isRunning) {
            if (tracks[currentTrack] != null) {
                tracks[currentTrack].stop();
            }

            if (tracks[index] != null) {
                if (currentTrack != index) {
                    tracks[index].setMicrosecondPosition(0);
                }
                tracks[index].start();
                tracks[index].loop(Clip.LOOP_CONTINUOUSLY);
            }
        }

        currentTrack = index;
    }

    /**
     * Cambia la musica in esecuzione in accordo con la stanza corrente in cui si trova il player.
     * @param roomName Il nome della stanza in cui si trova il player. Lo usa per ricavare l'indice della track dalla
     *                 HashMap. Se non dovesse trovare la track per la stanza passata come parametro, usa 1 di default.
     */
    public void changRoomMusic(String roomName) {
        changeClip(roomNameToTrackIndex.getOrDefault(roomName, 1));
    }
}