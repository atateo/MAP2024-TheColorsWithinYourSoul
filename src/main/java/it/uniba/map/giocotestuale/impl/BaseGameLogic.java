package it.uniba.map.giocotestuale.impl;

import it.uniba.map.giocotestuale.database.impl.DialogDaoImpl;
import it.uniba.map.giocotestuale.entities.game.ColorClass;
import it.uniba.map.giocotestuale.entities.game.GameObject;
import it.uniba.map.giocotestuale.entities.game.Item;
import it.uniba.map.giocotestuale.logic.interaction.Interaction;
import it.uniba.map.giocotestuale.logic.interaction.InteractionFactory;
import it.uniba.map.giocotestuale.type.Command;

import java.util.ArrayList;

/**
 * Classe contenente il metodo che definisce la logica (tutte le interazioni) di The Colors Within Your Soul.
 */
public class BaseGameLogic {

    /**
     * Costruttore di default di BaseGameLogic.
     */
    public BaseGameLogic() {}

    /**
     * Metodo che restituisce tutte le interactions che compongono la
     * logica di gioco sulla base di ciò che è presente nel salvataggio.
     *
     * @param objects La lista di items, colori e stanze disponibili.
     * @return ArrayList contenente tutte le interactions di gioco.
     */
    public ArrayList<Interaction> getGameLogic(ArrayList<GameObject> objects) {
        DialogDaoImpl dialog = new DialogDaoImpl();
        ArrayList<Interaction> gameLogic = new ArrayList<>();

        //Sblocca il colore rosso
        if (getObjectByName("PennelloRosso", objects) != null) {
            gameLogic.add(InteractionFactory.buildInteraction(
                    getObjectByName("PennelloRosso", objects), Command.PRENDI, "Neutro", "Neutro",
                    (gameObjects, targetStates, gameEngine) -> {
                        ((ColorClass) getObjectByName("Rosso", objects)).setUnlocked(true);
                        GameToGUICommunication.getInstance().toGUI(dialog.getTestoById(1));//1

                        GameToGUICommunication.getInstance().toGUI(dialog.getTestoById(2));//2

                        GameToGUICommunication.getInstance().unlockColor((ColorClass) getObjectByName("Rosso", objects));
                    }
            ));
        }

        //Sblocca il colore blu
        if (getObjectByName("PennelloBlu", objects) != null) {
            gameLogic.add(InteractionFactory.buildInteraction(
                    getObjectByName("PennelloBlu", objects), Command.PRENDI, "Neutro", "Neutro",
                    (gameObjects, targetStates, gameEngine) -> {
                        ((ColorClass) getObjectByName("Blu", objects)).setUnlocked(true);
                        GameToGUICommunication.getInstance().toGUI(dialog.getTestoById(3));//3

                        GameToGUICommunication.getInstance().toGUI("Alcuni oggetti interagiscono con più colori. " +
                                "Ad esempio, prova il blu sempre sulla torcia dopo averla accesa.");

                        GameToGUICommunication.getInstance().unlockColor((ColorClass) getObjectByName("Blu", objects));
                    }
            ));
        }

        //Sblocca il colore giallo
        if (getObjectByName("PennelloGiallo", objects) != null) {
            gameLogic.add(InteractionFactory.buildInteraction(
                    getObjectByName("PennelloGiallo", objects), Command.PRENDI, "Neutro", "Neutro",
                    (gameObjects, targetStates, gameEngine) -> {
                        ((ColorClass) getObjectByName("Giallo", objects)).setUnlocked(true);
                        GameToGUICommunication.getInstance().toGUI(dialog.getTestoById(4));//4

                        GameToGUICommunication.getInstance().unlockColor((ColorClass) getObjectByName("Giallo", objects));
                    }
            ));
        }

        //Sblocca il colore verde
        if ((getObjectByName("PennelloVerde", objects) != null)) {
            gameLogic.add(InteractionFactory.buildInteraction(
                    getObjectByName("PennelloVerde", objects), Command.PRENDI, "Neutro", "Neutro",
                    (gameObjects, targetStates, gameEngine) -> {
                        ((ColorClass) getObjectByName("Verde", objects)).setUnlocked(true);
                        GameToGUICommunication.getInstance().toGUI(dialog.getTestoById(5));//5

                        GameToGUICommunication.getInstance().unlockColor((ColorClass) getObjectByName("Verde", objects));
                    }
            ));
        }

        //Sblocca il colore marrone
        if (getObjectByName("PennelloMarrone", objects) != null) {
            gameLogic.add(InteractionFactory.buildInteraction(
                    getObjectByName("PennelloMarrone", objects), Command.PRENDI, "Neutro", "Neutro",
                    (gameObjects, targetStates, gameEngine) -> {
                        ((ColorClass) getObjectByName("Marrone", objects)).setUnlocked(true);
                        GameToGUICommunication.getInstance().toGUI(dialog.getTestoById(6));//6

                        GameToGUICommunication.getInstance().unlockColor((ColorClass) getObjectByName("Marrone", objects));
                    }
            ));
        }

        //Sblocca il colore viola
        if (getObjectByName("PennelloViola", objects) != null) {
            gameLogic.add(InteractionFactory.buildInteraction(
                    getObjectByName("PennelloViola", objects), Command.PRENDI, "Neutro", "Neutro",
                    (gameObjects, targetStates, gameEngine) -> {
                        ((ColorClass) getObjectByName("Viola", objects)).setUnlocked(true);
                        GameToGUICommunication.getInstance().toGUI(dialog.getTestoById(7));//7

                        GameToGUICommunication.getInstance().unlockColor((ColorClass) getObjectByName("Viola", objects));
                    }
            ));
        }

        //Accendi la torcia colorandola di rosso
        gameLogic.add(InteractionFactory.buildInteraction(
                getObjectByName("Torcia", objects), getObjectByName("Rosso", objects), Command.COLORA, "Spento", "Acceso",
                (gameObjects, targetStates, gameEngine) -> {
                    gameObjects.getFirst().setStatus(targetStates.get(1));

                    GameToGUICommunication.getInstance().toGUI(dialog.getTestoById(8));//8
                }
        ));

        //Spegni la torcia colorandola di blu
        gameLogic.add(InteractionFactory.buildInteraction(
                getObjectByName("Torcia", objects), getObjectByName("Blu", objects), Command.COLORA, "Acceso", "Spento",
                (gameObjects, targetStates, gameEngine) -> {
                    gameObjects.getFirst().setStatus(targetStates.get(1));

                    GameToGUICommunication.getInstance().toGUI(dialog.getTestoById(9));//9
                }
        ));

        //Spingi le macerie per liberare il passaggio nell'attico
        gameLogic.add(InteractionFactory.buildInteraction(
                getObjectByName("Macerie", objects), Command.SPINGI, "NonSpostato", "Spostato",
                (gameObjects, targetStates, gameEngine) -> {
                    gameObjects.getFirst().setStatus(targetStates.get(1));
                    gameEngine.getRoomByName("AtticoCentrale").getRoomConnection(Command.OVEST).unlock();

                    GameToGUICommunication.getInstance().toGUI(dialog.getTestoById(10));//10
                }
        ));

        //Accendi il primo camino
        gameLogic.add(InteractionFactory.buildInteraction(
                getObjectByName("CaminoDestro", objects), getObjectByName("Rosso", objects), Command.COLORA, "Spento", "Acceso",
                (gameObjects, targetStates, gameEngine) -> {
                    gameObjects.getFirst().setStatus(targetStates.get(1));
                    GameToGUICommunication.getInstance().toGUI(dialog.getTestoById(11));//11

                    if (gameEngine.getItemByName("CaminoSinistro").getStatus().equals("Acceso")) {
                        gameEngine.getRoomByName("StanzaRosso").getRoomConnection(Command.SUD).unlock();
                        gameEngine.getRoomByName("StanzaColoriPrimari").getRoomConnection(Command.OVEST).unlock();
                        GameToGUICommunication.getInstance().toGUI(dialog.getTestoById(65));//65
                    }
                }
        ));

        //Metti i legnetti nel secondo camino
        if (getObjectByName("Legnetti", objects) != null) {
            gameLogic.add(InteractionFactory.buildInteraction(
                    getObjectByName("Legnetti", objects), getObjectByName("CaminoSinistro", objects), Command.USA, "Neutro", "Spento",
                    (gameObjects, targetStates, gameEngine) -> {
                        if (gameObjects.get(1).getStatus().equals("SenzaLegna")) {
                            gameObjects.get(1).setStatus(targetStates.get(1));
                            gameEngine.removeItem((Item) getObjectByName("Legnetti", objects));
                            GameToGUICommunication.getInstance().toGUI(dialog.getTestoById(12));//12
                        } else {
                            GameToGUICommunication.getInstance().toGUI(dialog.getTestoById(13));//13
                        }
                    }
            ));
        }

        //Accendi il secondo camino
        gameLogic.add(InteractionFactory.buildInteraction(
                getObjectByName("CaminoSinistro", objects), getObjectByName("Rosso", objects), Command.COLORA, "Spento", "Acceso",
                (gameObjects, targetStates, gameEngine) -> {
                    gameObjects.getFirst().setStatus(targetStates.get(1));
                    GameToGUICommunication.getInstance().toGUI(dialog.getTestoById(14));//14

                    if (gameEngine.getItemByName("CaminoDestro").getStatus().equals("Acceso")) {
                        gameEngine.getRoomByName("StanzaRosso").getRoomConnection(Command.SUD).unlock();
                        gameEngine.getRoomByName("StanzaColoriPrimari").getRoomConnection(Command.OVEST).unlock();
                        GameToGUICommunication.getInstance().toGUI(dialog.getTestoById(15));//15
                    }
                }
        ));

        //Colorare l'alberello di blu lo fa crescere
        gameLogic.add(InteractionFactory.buildInteraction(
                getObjectByName("Albero", objects), getObjectByName("Blu", objects), Command.COLORA, "NonCresciuto", "Cresciuto",
                (gameObjects, targetStates, gameEngine) -> {
                    gameObjects.getFirst().setStatus(targetStates.get(1));
                    GameToGUICommunication.getInstance().toGUI(dialog.getTestoById(16));//16
                }
        ));

        //Colorare la statua di blu la fa attivare
        gameLogic.add(InteractionFactory.buildInteraction(
                getObjectByName("StatuaDrago", objects), getObjectByName("Blu", objects), Command.COLORA, "SenzaAcqua", "ConAcqua",
                (gameObjects, targetStates, gameEngine) -> {
                    gameObjects.getFirst().setStatus(targetStates.get(1));
                    GameToGUICommunication.getInstance().toGUI(dialog.getTestoById(17));//17
                }
        ));

        //Tagliare l'albero con l'ascia
        gameLogic.add(InteractionFactory.buildInteraction(
                getObjectByName("Ascia", objects), getObjectByName("Albero", objects), Command.USA, "Neutro", "Tagliato",
                (gameObjects, targetStates, gameEngine) -> {
                    if (getObjectByName("Albero", objects).getStatus().equals("Cresciuto")) {
                        ((Item) gameObjects.get(1)).setMovable(true);
                        gameObjects.get(1).setStatus(targetStates.get(1));
                        GameToGUICommunication.getInstance().toGUI(dialog.getTestoById(18));//18
                    } else {
                        GameToGUICommunication.getInstance().toGUI(dialog.getTestoById(13));//13
                    }
                }
        ));

        //Spingi l'albero in acqua per attivare l'interruttore
        gameLogic.add(InteractionFactory.buildInteraction(
                getObjectByName("Albero", objects), Command.SPINGI, "Tagliato", "Spinto",
                (gameObjects, targetStates, gameEngine) -> {
                    if (gameEngine.getItemByName("StatuaDrago").getStatus().equals("ConAcqua")) {
                        gameObjects.getFirst().setStatus(targetStates.get(1));
                        gameEngine.getRoomByName("StanzaBlu").getRoomConnection(Command.EST).unlock();
                        gameEngine.getRoomByName("StanzaColoriPrimari").getRoomConnection(Command.SUD).unlock();
                        GameToGUICommunication.getInstance().toGUI(dialog.getTestoById(19));//19
                        GameToGUICommunication.getInstance().toGUI(dialog.getTestoById(20));//20
                    } else {
                        GameToGUICommunication.getInstance().toGUI(dialog.getTestoById(21));//21
                    }
                }
        ));

        //Spegni l'interruttore
        gameLogic.add(InteractionFactory.buildInteraction(
                getObjectByName("Interruttore", objects), getObjectByName("Giallo", objects), Command.COLORA, "Acceso", "Spento",
                (gameObjects, targetStates, gameEngine) -> {
                    gameObjects.getFirst().setStatus(targetStates.get(1));

                    GameToGUICommunication.getInstance().toGUI(dialog.getTestoById(22));//22
                }
        ));

        //Spegnere l'interruttore toglie la corrente al blocco
        gameLogic.add(InteractionFactory.buildInteraction(
                getObjectByName("Interruttore", objects), getObjectByName("BloccoDiFerro", objects), "Spento", "Spento",
                (gameObjects, targetStates, gameEngine) -> {
                    GameToGUICommunication.getInstance().toGUI(dialog.getTestoById(23));//23

                    if (gameObjects.get(1).getStatus().contains("NonSpostato")) {
                        gameObjects.get(1).setStatus("NonSpostato" + targetStates.get(1));
                    } else {
                        gameObjects.get(1).setStatus("Spostato" + targetStates.get(1));
                    }

                    ((Item) gameObjects.get(1)).setMovable(true);
                }
        ));

        //Accendi l'interruttore
        gameLogic.add(InteractionFactory.buildInteraction(
                getObjectByName("Interruttore", objects), getObjectByName("Giallo", objects), Command.COLORA, "Spento", "Acceso",
                (gameObjects, targetStates, gameEngine) -> {
                    gameObjects.getFirst().setStatus(targetStates.get(1));

                    GameToGUICommunication.getInstance().toGUI(dialog.getTestoById(24));//24
                }
        ));

        //Accendere l'interruttore dà la corrente al blocco
        gameLogic.add(InteractionFactory.buildInteraction(
                getObjectByName("Interruttore", objects), getObjectByName("BloccoDiFerro", objects), "Acceso", "Acceso",
                (gameObjects, targetStates, gameEngine) -> {
                    GameToGUICommunication.getInstance().toGUI(dialog.getTestoById(25));//25

                    if (gameObjects.get(1).getStatus().contains("NonSpostato")) {
                        gameObjects.get(1).setStatus("NonSpostato" + targetStates.get(1));
                    } else {
                        gameObjects.get(1).setStatus("Spostato" + targetStates.get(1));
                        ((Item) gameObjects.get(0)).setPaintable(false);
                        GameToGUICommunication.getInstance().toGUI(dialog.getTestoById(26));//26

                        gameEngine.getRoomByName("StanzaGiallo").getRoomConnection(Command.NORD).unlock();
                        gameEngine.getRoomByName("StanzaColoriSecondari").getRoomConnection(Command.NORD).unlock();
                    }

                    ((Item) gameObjects.get(1)).setMovable(false);
                }
        ));

        //Spingere il blocco
        gameLogic.add(InteractionFactory.buildInteraction(
                getObjectByName("BloccoDiFerro", objects), Command.SPINGI, "NonSpostatoSpento", "SpostatoSpento",
                (gameObjects, targetStates, gameEngine) -> {
                    gameObjects.getFirst().setStatus(targetStates.get(1));

                    GameToGUICommunication.getInstance().toGUI(dialog.getTestoById(27));//27
                }
        ));

        //Fa crescere la liana
        gameLogic.add(InteractionFactory.buildInteraction(
                getObjectByName("Liana", objects), getObjectByName("Verde", objects), Command.COLORA, "NonCresciuto", "Cresciuto",
                (gameObjects, targetStates, gameEngine) -> {
                    gameObjects.getFirst().setStatus(targetStates.get(1));
                    ((Item) gameObjects.getFirst()).setPickable(true);

                    GameToGUICommunication.getInstance().toGUI(dialog.getTestoById(28));//28
                }
        ));

        //Fa crescere le piante nell'aiuola
        gameLogic.add(InteractionFactory.buildInteraction(
                getObjectByName("Aiuola", objects), getObjectByName("Verde", objects), Command.COLORA, "NonCresciuto", "Cresciuto",
                (gameObjects, targetStates, gameEngine) -> {
                    gameObjects.getFirst().setStatus(targetStates.get(1));

                    GameToGUICommunication.getInstance().toGUI(dialog.getTestoById(29));//29
                }
        ));

        //Usa la liana per raccogliere il frutto dall'albero
        gameLogic.add(InteractionFactory.buildInteraction(
                getObjectByName("Liana", objects), getObjectByName("Aiuola", objects), Command.USA, "Cresciuto", "SenzaFrutta",
                (gameObjects, targetStates, gameEngine) -> {
                    if (gameObjects.get(1).getStatus().equals("Cresciuto")) {
                        gameObjects.get(1).setStatus(targetStates.get(1));
                        gameEngine.getRoomByName("StanzaVerde").getRoomConnection(Command.SUD).unlock();
                        gameEngine.getRoomByName("StanzaColoriSecondari").getRoomConnection(Command.EST).unlock();

                        GameToGUICommunication.getInstance().toGUI(dialog.getTestoById(30));//30
                    } else {
                        GameToGUICommunication.getInstance().toGUI(dialog.getTestoById(13));//13
                    }
                }
        ));

        //Colora il piedistallo per far comparire il blocco di pietra
        gameLogic.add(InteractionFactory.buildInteraction(
                getObjectByName("Piedistallo", objects), getObjectByName("Marrone", objects), Command.COLORA, "NonColorato", "Colorato",
                (gameObjects, targetStates, gameEngine) -> {
                    gameObjects.getFirst().setStatus(targetStates.get(1));

                    GameToGUICommunication.getInstance().toGUI(dialog.getTestoById(31));//31
                }
        ));

        //Usa lo scalpello per scolpire una statua sul piedistallo
        gameLogic.add(InteractionFactory.buildInteraction(
                getObjectByName("Scalpello", objects), getObjectByName("Piedistallo", objects), Command.USA, "Neutro", "NonSpostatoStatua",
                (gameObjects, targetStates, gameEngine) -> {
                    if (gameObjects.get(1).getStatus().equals("Colorato")) {
                        gameObjects.get(1).setStatus(targetStates.get(1));
                        ((Item) gameObjects.getFirst()).setMovable(true);

                        GameToGUICommunication.getInstance().toGUI(dialog.getTestoById(32));//32
                    } else {
                        GameToGUICommunication.getInstance().toGUI(dialog.getTestoById(13));//13
                    }
                }
        ));

        //Sposta la statua
        gameLogic.add(InteractionFactory.buildInteraction(
                getObjectByName("Piedistallo", objects), Command.SPINGI, "NonSpostatoStatua", "SpostatoStatua",
                (gameObjects, targetStates, gameEngine) -> {
                    gameObjects.getFirst().setStatus(targetStates.get(1));
                    ((Item) gameObjects.getFirst()).setMovable(false);

                    GameToGUICommunication.getInstance().toGUI(dialog.getTestoById(33));//33
                }
        ));

        //Spostando la statua, si attiva la pedana a pressione e la porta si apre
        gameLogic.add(InteractionFactory.buildInteraction(
                getObjectByName("Piedistallo", objects), getObjectByName("PedanaAPressione", objects), "SpostatoStatua", "Premuta",
                (gameObjects, targetStates, gameEngine) -> {
                    if (gameObjects.get(1).getStatus().equals("NonPremuta")) {
                        gameObjects.get(1).setStatus(targetStates.get(1));
                        gameEngine.getRoomByName("StanzaMarrone").getRoomConnection(Command.OVEST).unlock();
                        gameEngine.getRoomByName("StanzaColoriSecondari").getRoomConnection(Command.SUD).unlock();

                        GameToGUICommunication.getInstance().toGUI(dialog.getTestoById(34));//34
                    }
                }
        ));

        //Colora la scala di viola per aggiustarla
        gameLogic.add(InteractionFactory.buildInteraction(
                getObjectByName("Scala", objects), getObjectByName("Viola", objects), Command.COLORA, "Rotto", "Aggiustato",
                (gameObjects, targetStates, gameEngine) -> {
                    gameObjects.getFirst().setStatus(targetStates.get(1));

                    GameToGUICommunication.getInstance().toGUI(dialog.getTestoById(35));//35
                }
        ));

        //Aggiustare la scala rende raggiungibile l'orologio
        gameLogic.add(InteractionFactory.buildInteraction(
                getObjectByName("Scala", objects), getObjectByName("Orologio", objects), "Aggiustato", "Rotto",
                (gameObjects, targetStates, gameEngine) -> {
                    if (!((Item) gameObjects.get(1)).getPickable()) {
                        ((Item) gameObjects.get(1)).setPickable(true);
                        ((Item) gameObjects.get(1)).setPaintable(true);

                        GameToGUICommunication.getInstance().toGUI(dialog.getTestoById(36));//36
                    }
                }
        ));

        //Colorare l'orologio di viola per aggiustarlo
        gameLogic.add(InteractionFactory.buildInteraction(
                getObjectByName("Orologio", objects), getObjectByName("Viola", objects), Command.COLORA, "Rotto", "Aggiustato",
                (gameObjects, targetStates, gameEngine) -> {
                    gameObjects.getFirst().setStatus(targetStates.get(1));

                    GameToGUICommunication.getInstance().toGUI(dialog.getTestoById(37));//37
                }
        ));

        //Usare l'orologio aggiustato sull'incavo per riempirlo
        gameLogic.add(InteractionFactory.buildInteraction(
                getObjectByName("Orologio", objects), getObjectByName("Incavo", objects), Command.USA, "Aggiustato", "Pieno",
                (gameObjects, targetStates, gameEngine) -> {
                    if (gameObjects.get(1).getStatus().equals("Vuoto")) {
                        gameObjects.get(1).setStatus(targetStates.get(1));
                        ((Item) gameObjects.getFirst()).setPickable(false);
                        gameEngine.getRoomByName("StanzaViola").getRoomConnection(Command.NORD).unlock();
                        gameEngine.getRoomByName("StanzaViola").addItem((Item) getObjectByName("Orologio", objects));
                        gameEngine.getRoomByName("AtticoCentrale").getRoomConnection(Command.NORD).unlock();
                        gameEngine.removeItem((Item) getObjectByName("Orologio", objects));

                        GameToGUICommunication.getInstance().toGUI(dialog.getTestoById(38));//38
                    }
                }
        ));

        //Colorare il vivaio di rosso per sciogliere il ghiaccio
        gameLogic.add(InteractionFactory.buildInteraction(
                getObjectByName("Vivaio", objects), getObjectByName("Rosso", objects), Command.COLORA, "Ghiacciato", "SenzaTerra",
                (gameObjects, targetStates, gameEngine) -> {
                    gameObjects.getFirst().setStatus(targetStates.get(1));

                    GameToGUICommunication.getInstance().toGUI(dialog.getTestoById(39));//39
                }
        ));

        //Colorare il vivaio di marrone per aggiungere la terra
        gameLogic.add(InteractionFactory.buildInteraction(
                getObjectByName("Vivaio", objects), getObjectByName("Marrone", objects), Command.COLORA, "SenzaTerra", "ConTerra",
                (gameObjects, targetStates, gameEngine) -> {
                    gameObjects.getFirst().setStatus(targetStates.get(1));

                    GameToGUICommunication.getInstance().toGUI(dialog.getTestoById(40));//40
                }
        ));

        //Colorare il vivaio di verde per farlo fiorire
        gameLogic.add(InteractionFactory.buildInteraction(
                getObjectByName("Vivaio", objects), getObjectByName("Verde", objects), Command.COLORA, "ConTerra", "ConPianta",
                (gameObjects, targetStates, gameEngine) -> {
                    gameObjects.getFirst().setStatus(targetStates.get(1));

                    GameToGUICommunication.getInstance().toGUI(dialog.getTestoById(41));//41
                }
        ));

        //Colorare il vivaio di blu per annaffiare la pianta
        gameLogic.add(InteractionFactory.buildInteraction(
                getObjectByName("Vivaio", objects), getObjectByName("Blu", objects), Command.COLORA, "ConPianta", "ConInsetti",
                (gameObjects, targetStates, gameEngine) -> {
                    gameObjects.getFirst().setStatus(targetStates.get(1));

                    GameToGUICommunication.getInstance().toGUI(dialog.getTestoById(42));//42
                }
        ));

        //Colorare il giallo per scacciare gli insetti
        gameLogic.add(InteractionFactory.buildInteraction(
                getObjectByName("Vivaio", objects), getObjectByName("Giallo", objects), Command.COLORA, "ConInsetti", "SenzaInsetti",
                (gameObjects, targetStates, gameEngine) -> {
                    gameObjects.getFirst().setStatus(targetStates.get(1));

                    GameToGUICommunication.getInstance().toGUI(dialog.getTestoById(43));//43
                }
        ));

        //Colorare il vivaio di viola per accelerare la crescita della pianta
        gameLogic.add(InteractionFactory.buildInteraction(
                getObjectByName("Vivaio", objects), getObjectByName("Viola", objects), Command.COLORA, "SenzaInsetti", "Cresciuto",
                (gameObjects, targetStates, gameEngine) -> {
                    gameObjects.getFirst().setStatus(targetStates.get(1));

                    GameToGUICommunication.getInstance().toGUI(dialog.getTestoById(44));//44
                }
        ));

        //Taglia l'albero maestro usando l'ascia (interazione finale)
        gameLogic.add(InteractionFactory.buildInteraction(
                getObjectByName("Ascia", objects), getObjectByName("Vivaio", objects), Command.USA, "Neutro", "Cresciuto",
                (gameObjects, targetStates, gameEngine) -> {
                    if (getObjectByName("Vivaio", objects).getStatus().equals("Cresciuto")) {
                        gameObjects.get(1).setStatus(targetStates.get(1));
                        GameToGUICommunication.getInstance().toGUI(dialog.getTestoById(45));//45

                        //Imposta la stanza a null perché il gioco è finito
                        gameEngine.setCurrentRoomToNull();
                    } else {
                        GameToGUICommunication.getInstance().toGUI(dialog.getTestoById(13));//13
                    }
                }
        ));

        return gameLogic;
    }

    /**
     * Data una stringa come parametro, questo metodo restituisce l'oggetto di gioco che ha quella stringa come nome.
     *
     * @param name             Nome da cercare.
     * @param availableObjects Lista degli oggetti di gioco in cui cercare.
     * @return Oggetto col nome cercato. Se non esiste, restituisce null.
     */
    public GameObject getObjectByName(final String name, ArrayList<GameObject> availableObjects) {
        for (GameObject gameObject : availableObjects) {
            if (gameObject.getName().equalsIgnoreCase(name)) {
                return gameObject;
            }
        }
        return null;
    }
}