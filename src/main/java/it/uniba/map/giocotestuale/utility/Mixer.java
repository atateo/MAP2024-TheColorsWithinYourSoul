package it.uniba.map.giocotestuale.utility;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.util.HashMap;

public class Mixer extends Thread {
    private static final Logger logger = LogManager.getLogger(Mixer.class);
    public static Mixer instance;

    private Clip[] tracks;
    private int currentTrack;
    private boolean isRunning = false;
    private HashMap<String, Integer> roomNameToTrackIndex;

    private Mixer() {
        final int numberOfTracks = 1;

        this.tracks = new Clip[numberOfTracks];

        //Caricare qui la musica del gioco in tracks;
        loadTrack(0, "src/main/resources/music/AA_AJ_DrewMishamStudio.wav");
        loadTrack(1, "src/main/resources/music/Xenoblade3_MakthaWoods.wav");
        loadTrack(2, "src/main/resources/music/ZeldaSkywardSword_EarthTemple.wav");
        loadTrack(3, "src/main/resources/music/ZeldaLinkBetweenWorlds_SwampPalace.wav");
        loadTrack(4, "src/main/resources/music/DKC_FearFactoryHDRestored.wav");
        loadTrack(5, "src/main/resources/music/HollowKnight_QueensGarden.wav");
        loadTrack(6, "src/main/resources/music/ZeldaMajorasMask_StoneTowerTemple.wav");
        loadTrack(7, "src/main/resources/music/Heartbound_SpaceLasers.wav");

        //Scrivere qui il codice che associa l'indice della musica alle stanze
    }

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

    public static Mixer getInstance() {
        if (instance == null) {
            instance = new Mixer();
        }
        return instance;
    }
}