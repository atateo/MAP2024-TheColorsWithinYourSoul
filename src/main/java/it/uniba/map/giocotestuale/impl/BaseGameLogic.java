package it.uniba.map.giocotestuale.impl;

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
     * Metodo che restituisce tutte le interactions che compongono la
     * logica di gioco sulla base di ciò che è presente nel salvataggio.
     * @param objects La lista di items, colori e stanze disponibili.
     * @return ArrayList contenente tutte le interactions di gioco.
     */
    public ArrayList<Interaction> getGameLogic(ArrayList<GameObject> objects) {
        ArrayList<Interaction> gameLogic = new ArrayList<>();

        //Sblocca il colore rosso
        if (getObjectByName("PennelloRosso", objects) != null) {
            gameLogic.add(InteractionFactory.buildInteraction(
                    getObjectByName("PennelloRosso", objects), Command.PRENDI, "Neutro", "Neutro",
                    (gameObjects, targetStates, gameEngine) -> {
                        ((ColorClass) getObjectByName("Rosso", objects)).setUnlocked(true);
                        GameToGUICommunication.getInstance().toGUI("Il pennello si dissolve appena lo prendi, " +
                                "però senti qualcosa di diverso in te... Hai sbloccato il colore rosso!");

                        GameToGUICommunication.getInstance().toGUI("Puoi usare il comando Colora per tinteggiare alcuni " +
                                "oggetti ottenendo effetti particolari. Prova il rosso sulla torcia della prima stanza, poi.");

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
                        GameToGUICommunication.getInstance().toGUI("Il pennello si dissolve appena lo prendi, " +
                                "però senti qualcosa di diverso in te... Hai sbloccato il colore blu!");

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
                        GameToGUICommunication.getInstance().toGUI("Il pennello si dissolve appena lo prendi, " +
                                "però senti qualcosa di diverso in te... Hai sbloccato il colore giallo!");

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
                        GameToGUICommunication.getInstance().toGUI("Il pennello si dissolve appena lo prendi, " +
                                "però senti qualcosa di diverso in te... Hai sbloccato il colore verde!");

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
                        GameToGUICommunication.getInstance().toGUI("Il pennello si dissolve appena lo prendi, " +
                                "però senti qualcosa di diverso in te... Hai sbloccato il colore marrone!");

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
                        GameToGUICommunication.getInstance().toGUI("Il pennello si dissolve appena lo prendi, " +
                                "però senti qualcosa di diverso in te... Hai sbloccato il colore viola!");

                        GameToGUICommunication.getInstance().unlockColor((ColorClass) getObjectByName("Viola", objects));
                    }
            ));
        }

        //Accendi la torcia colorandola di rosso
        gameLogic.add(InteractionFactory.buildInteraction(
                getObjectByName("Torcia", objects), getObjectByName("Rosso", objects), Command.COLORA, "Spento", "Acceso",
                (gameObjects, targetStates, gameEngine) -> {
                    gameObjects.getFirst().setStatus(targetStates.get(1));

                    GameToGUICommunication.getInstance().toGUI("Colorandola di rosso, la torcia si accende.");
                }
        ));

        //Spegni la torcia colorandola di blu
        gameLogic.add(InteractionFactory.buildInteraction(
                getObjectByName("Torcia", objects), getObjectByName("Blu", objects), Command.COLORA,"Acceso", "Spento",
                (gameObjects, targetStates, gameEngine) -> {
                    gameObjects.getFirst().setStatus(targetStates.get(1));

                    GameToGUICommunication.getInstance().toGUI("Colorandola di blu, la torcia si spegne.");
                }
        ));

        //Spingi le macerie per liberare il passaggio nell'attico
        gameLogic.add(InteractionFactory.buildInteraction(
                getObjectByName("Macerie", objects), Command.SPINGI, "NonSpostato", "Spostato",
                (gameObjects, targetStates, gameEngine) -> {
                    gameObjects.getFirst().setStatus(targetStates.get(1));
                    gameEngine.getRoomByName("AtticoCentrale").getRoomConnection(Command.OVEST).unlock();

                    GameToGUICommunication.getInstance().toGUI("Sposti le macerie, sbloccando la porta a sinistra.");
                }
        ));

        //Accendi il primo camino
        gameLogic.add(InteractionFactory.buildInteraction(
                getObjectByName("CaminoDestro", objects), getObjectByName("Rosso", objects), Command.COLORA, "Spento", "Acceso",
                (gameObjects, targetStates, gameEngine) -> {
                    gameObjects.getFirst().setStatus(targetStates.get(1));
                    GameToGUICommunication.getInstance().toGUI("Si accende il fuoco nel camino di destra.");

                    if (gameEngine.getItemByName("CaminoSinistro").getStatus().equals("Acceso")) {
                        gameEngine.getRoomByName("StanzaRosso").getRoomConnection(Command.SUD).unlock();
                        gameEngine.getRoomByName("StanzaColoriPrimari").getRoomConnection(Command.OVEST).unlock();
                        GameToGUICommunication.getInstance().toGUI("Si sblocca la porta d'ingresso e la porta della stanza blu.");
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
                            GameToGUICommunication.getInstance().toGUI("Hai messo i legnetti nel camino di sinistra.");
                        } else {
                            GameToGUICommunication.getInstance().toGUI("Non è successo niente.");
                        }
                    }
            ));
        }

        //Accendi il secondo camino
        gameLogic.add(InteractionFactory.buildInteraction(
                getObjectByName("CaminoSinistro", objects), getObjectByName("Rosso", objects), Command.COLORA, "Spento", "Acceso",
                (gameObjects, targetStates, gameEngine) -> {
                    gameObjects.getFirst().setStatus(targetStates.get(1));
                    GameToGUICommunication.getInstance().toGUI("Si accende il fuoco nel camino di sinistra.");

                    if (gameEngine.getItemByName("CaminoDestro").getStatus().equals("Acceso")) {
                        gameEngine.getRoomByName("StanzaRosso").getRoomConnection(Command.SUD).unlock();
                        gameEngine.getRoomByName("StanzaColoriPrimari").getRoomConnection(Command.OVEST).unlock();
                        GameToGUICommunication.getInstance().toGUI("Si sblocca la porta d'ingresso e la porta della stanza blu.");
                    }
                }
        ));

        //Colorare l'alberello di blu lo fa crescere
        gameLogic.add(InteractionFactory.buildInteraction(
                getObjectByName("Albero", objects), getObjectByName("Blu", objects), Command.COLORA, "NonCresciuto", "Cresciuto",
                (gameObjects, targetStates, gameEngine) -> {
                    gameObjects.getFirst().setStatus(targetStates.get(1));
                    GameToGUICommunication.getInstance().toGUI("Dipingendolo di blu, hai annaffiato " +
                            "l'albero e ora è cresciuto.");
                }
        ));

        //Colorare la statua di blu la fa attivare
        gameLogic.add(InteractionFactory.buildInteraction(
                getObjectByName("StatuaDrago", objects), getObjectByName("Blu", objects), Command.COLORA, "SenzaAcqua", "ConAcqua",
                (gameObjects, targetStates, gameEngine) -> {
                    gameObjects.getFirst().setStatus(targetStates.get(1));
                    GameToGUICommunication.getInstance().toGUI("Dipingendola di blu, hai attivato la statua. " +
                            "Da essa inizia a sgorgare dell'acqua che riempie il fossato.");
                }
        ));

        //Tagliare l'albero con l'ascia
        gameLogic.add(InteractionFactory.buildInteraction(
                getObjectByName("Ascia", objects), getObjectByName("Albero", objects), Command.USA, "Neutro", "Tagliato",
                (gameObjects, targetStates, gameEngine) -> {
                    if (getObjectByName("Albero", objects).getStatus().equals("Cresciuto")) {
                        ((Item) gameObjects.get(1)).setMovable(true);
                        gameObjects.get(1).setStatus(targetStates.get(1));
                        GameToGUICommunication.getInstance().toGUI("Hai abbattuto l'albero con l'ascia. " +
                                "Forse ora puoi spingerlo?");
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
                        GameToGUICommunication.getInstance().toGUI("Spingi l'albero nel fossato e la corrente lo spinge sull'interruttore.");
                        GameToGUICommunication.getInstance().toGUI("Si sblocca la porta d'ingresso e la porta della stanza gialla.");
                    } else {
                        GameToGUICommunication.getInstance().toGUI("Forse devi fare qualcos'altro prima di spingerlo.");
                    }
                }
        ));

        //Spegni l'interruttore
        gameLogic.add(InteractionFactory.buildInteraction(
                getObjectByName("Interruttore", objects), getObjectByName("Giallo", objects), Command.COLORA, "Acceso", "Spento",
                (gameObjects, targetStates, gameEngine) -> {
                    gameObjects.getFirst().setStatus(targetStates.get(1));

                    GameToGUICommunication.getInstance().toGUI("Colorandolo di giallo da acceso, l'interruttore si spegne.");
                }
        ));

        //Spegnere l'interruttore toglie la corrente al blocco
        gameLogic.add(InteractionFactory.buildInteraction(
                getObjectByName("Interruttore", objects), getObjectByName("BloccoDiFerro", objects), "Spento", "Spento",
                (gameObjects, targetStates, gameEngine) -> {
                    GameToGUICommunication.getInstance().toGUI("Spegnendo l'interruttore, hai tolto la corrente " +
                            "al blocco di ferro.");

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

                    GameToGUICommunication.getInstance().toGUI("Colorandolo di giallo da spento, l'interruttore si accende.");
                }
        ));

        //Accendere l'interruttore dà la corrente al blocco
        gameLogic.add(InteractionFactory.buildInteraction(
                getObjectByName("Interruttore", objects), getObjectByName("BloccoDiFerro", objects), "Acceso", "Acceso",
                (gameObjects, targetStates, gameEngine) -> {
                    GameToGUICommunication.getInstance().toGUI("Accendendo l'interruttore, hai ridato la corrente " +
                            "al blocco di ferro.");

                    if (gameObjects.get(1).getStatus().contains("NonSpostato")) {
                        gameObjects.get(1).setStatus("NonSpostato" + targetStates.get(1));
                    } else {
                        gameObjects.get(1).setStatus("Spostato" + targetStates.get(1));
                        ((Item) gameObjects.get(0)).setPaintable(false);
                        GameToGUICommunication.getInstance().toGUI("Si attiva un circuito vicino alla porta. " +
                                "Si sblocca la porta d'ingresso. È il momento di cercare i colori secondari.");

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

                    GameToGUICommunication.getInstance().toGUI("Hai spostato il blocco. Ora si collega al " +
                            "resto del circuito");
                }
        ));

        //Fa crescere la liana
        gameLogic.add(InteractionFactory.buildInteraction(
                getObjectByName("Liana", objects), getObjectByName("Verde", objects), Command.COLORA, "NonCresciuto", "Cresciuto",
                (gameObjects, targetStates, gameEngine) -> {
                    gameObjects.getFirst().setStatus(targetStates.get(1));
                    ((Item) gameObjects.getFirst()).setPickable(true);

                    GameToGUICommunication.getInstance().toGUI("Colorandola di verde, la liana cresce considerevolmente.");
                }
        ));

        //Fa crescere le piante nell'aiuola
        gameLogic.add(InteractionFactory.buildInteraction(
                getObjectByName("Aiuola", objects), getObjectByName("Verde", objects), Command.COLORA, "NonCresciuto", "Cresciuto",
                (gameObjects, targetStates, gameEngine) -> {
                    gameObjects.getFirst().setStatus(targetStates.get(1));

                    GameToGUICommunication.getInstance().toGUI("Colorandola di verde, nell'aiuola cresce un albero con dei frutti colorati.");
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

                        GameToGUICommunication.getInstance().toGUI("Usi la liana per raccogliere un frutto verde dall'albero. " +
                                "Il frutto si dissolve, facendo aprire la porta d'ingresso e la porta della stanza marrone.");
                    } else {
                        GameToGUICommunication.getInstance().toGUI("Non è successo niente.");
                    }
                }
        ));

        //Colora il piedistallo per far comparire il blocco di pietra
        gameLogic.add(InteractionFactory.buildInteraction(
                getObjectByName("Piedistallo", objects), getObjectByName("Marrone", objects), Command.COLORA, "NonColorato", "Colorato",
                (gameObjects, targetStates, gameEngine) -> {
                    gameObjects.getFirst().setStatus(targetStates.get(1));

                    GameToGUICommunication.getInstance().toGUI("Colorandolo di marrone, compare un blocco di pietra " +
                            "sul piedistallo.");
                }
        ));

        //Usa lo scalpello per scolpire una statua sul piedistallo
        gameLogic.add(InteractionFactory.buildInteraction(
                getObjectByName("Scalpello", objects), getObjectByName("Piedistallo", objects), Command.USA, "Neutro", "NonSpostatoStatua",
                (gameObjects, targetStates, gameEngine) -> {
                    if (gameObjects.get(1).getStatus().equals("Colorato")) {
                        gameObjects.get(1).setStatus(targetStates.get(1));
                        ((Item) gameObjects.getFirst()).setMovable(true);

                        GameToGUICommunication.getInstance().toGUI("Hai scolpito una statua col blocco di pietra " +
                                "sul piedistallo.");
                    } else {
                        GameToGUICommunication.getInstance().toGUI("Non è successo niente.");
                    }
                }
        ));

        //Sposta la statua
        gameLogic.add(InteractionFactory.buildInteraction(
                getObjectByName("Piedistallo", objects), Command.SPINGI, "NonSpostatoStatua", "SpostatoStatua",
                (gameObjects, targetStates, gameEngine) -> {
                    gameObjects.getFirst().setStatus(targetStates.get(1));
                    ((Item) gameObjects.getFirst()).setMovable(false);

                    GameToGUICommunication.getInstance().toGUI("Hai spinto la statua sulla pedana a pressione. " +
                            "È incastrata nell'incavo ora.");
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

                        GameToGUICommunication.getInstance().toGUI("Si sblocca la porta d'ingresso e la porta " +
                                "della stanza viola.");
                    }
                }
        ));

        //Colora la scala di viola per aggiustarla
        gameLogic.add(InteractionFactory.buildInteraction(
                getObjectByName("Scala", objects), getObjectByName("Viola", objects), Command.COLORA, "Rotto", "Aggiustato",
                (gameObjects, targetStates, gameEngine) -> {
                    gameObjects.getFirst().setStatus(targetStates.get(1));

                    GameToGUICommunication.getInstance().toGUI("Colorandola di viola, hai riportato la scala a " +
                            "quando non era ancora rotta.");
                }
        ));

        //Aggiustare la scala rende raggiungibile l'orologio
        gameLogic.add(InteractionFactory.buildInteraction(
                getObjectByName("Scala", objects), getObjectByName("Orologio", objects), "Aggiustato", "Rotto",
                (gameObjects, targetStates, gameEngine) -> {
                    if (!((Item) gameObjects.get(1)).getPickable()) {
                        ((Item) gameObjects.get(1)).setPickable(true);
                        ((Item) gameObjects.get(1)).setPaintable(true);

                        GameToGUICommunication.getInstance().toGUI("Ora che hai aggiustato la scala, puoi " +
                                "raggiungere la cima della libreria.");
                    }
                }
        ));

        //Colorare l'orologio di viola per aggiustarlo
        gameLogic.add(InteractionFactory.buildInteraction(
                getObjectByName("Orologio", objects), getObjectByName("Viola", objects), Command.COLORA, "Rotto", "Aggiustato",
                (gameObjects, targetStates, gameEngine) -> {
                    gameObjects.getFirst().setStatus(targetStates.get(1));

                    GameToGUICommunication.getInstance().toGUI("Colorandolo di viola, hai riportato l'orologio a " +
                            "quando non era ancora rotto.");
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

                        GameToGUICommunication.getInstance().toGUI("Inserendo l'orologio nell'incavo, la porta si riapre. " +
                                "Ora sei pronto per la prova finale.");
                    }
                }
        ));

        //Colorare il vivaio di rosso per sciogliere il ghiaccio
        gameLogic.add(InteractionFactory.buildInteraction(
                getObjectByName("Vivaio", objects), getObjectByName("Rosso", objects), Command.COLORA, "Ghiacciato", "SenzaTerra",
                (gameObjects, targetStates, gameEngine) -> {
                    gameObjects.getFirst().setStatus(targetStates.get(1));

                    GameToGUICommunication.getInstance().toGUI("Hai sciolto il ghiaccio dell'aiuola. Dentro è vuota, " +
                            "non c'è nemmeno del terriccio.");
                }
        ));

        //Colorare il vivaio di marrone per aggiungere la terra
        gameLogic.add(InteractionFactory.buildInteraction(
                getObjectByName("Vivaio", objects), getObjectByName("Marrone", objects), Command.COLORA, "SenzaTerra", "ConTerra",
                (gameObjects, targetStates, gameEngine) -> {
                    gameObjects.getFirst().setStatus(targetStates.get(1));

                    GameToGUICommunication.getInstance().toGUI("Colorandola di marrone, hai aggiunto del terriccio nell'aiuola. " +
                            "È ancora vuoto, però.");
                }
        ));

        //Colorare il vivaio di verde per farlo fiorire
        gameLogic.add(InteractionFactory.buildInteraction(
                getObjectByName("Vivaio", objects), getObjectByName("Verde", objects), Command.COLORA, "ConTerra", "ConPianta",
                (gameObjects, targetStates, gameEngine) -> {
                    gameObjects.getFirst().setStatus(targetStates.get(1));

                    GameToGUICommunication.getInstance().toGUI("Iniziano a crescere un germoglio nel vivaio. " +
                            "Con le giuste cure, crescerà bene.");
                }
        ));

        //Colorare il vivaio di blu per annaffiare la pianta
        gameLogic.add(InteractionFactory.buildInteraction(
                getObjectByName("Vivaio", objects), getObjectByName("Blu", objects), Command.COLORA, "ConPianta", "ConInsetti",
                (gameObjects, targetStates, gameEngine) -> {
                    gameObjects.getFirst().setStatus(targetStates.get(1));

                    GameToGUICommunication.getInstance().toGUI("Hai annaffiato la piantina, ti sembra più forte ora. " +
                            "Sta iniziando ad attirare degli insetti, però.");
                }
        ));

        //Colorare il giallo per scacciare gli insetti
        gameLogic.add(InteractionFactory.buildInteraction(
                getObjectByName("Vivaio", objects), getObjectByName("Giallo", objects), Command.COLORA, "ConInsetti", "SenzaInsetti",
                (gameObjects, targetStates, gameEngine) -> {
                    gameObjects.getFirst().setStatus(targetStates.get(1));

                    GameToGUICommunication.getInstance().toGUI("Hai scacciato gli insetti usando il giallo. " +
                            "Ora la pianta ha solo bisogno di tempo.");
                }
        ));

        //Colorare il vivaio di viola per accelerare la crescita della pianta
        gameLogic.add(InteractionFactory.buildInteraction(
                getObjectByName("Vivaio", objects), getObjectByName("Viola", objects), Command.COLORA, "SenzaInsetti", "Cresciuto",
                (gameObjects, targetStates, gameEngine) -> {
                    gameObjects.getFirst().setStatus(targetStates.get(1));

                    GameToGUICommunication.getInstance().toGUI("Colorandolo di viola, hai accelerato la crescita " +
                            "della pianta. Ora è diventata un maestoso albero di quercia.");
                }
        ));

        //Taglia l'albero maestro usando l'ascia (interazione finale)
        gameLogic.add(InteractionFactory.buildInteraction(
                getObjectByName("Ascia", objects), getObjectByName("Vivaio", objects), Command.USA, "Neutro", "Cresciuto",
                (gameObjects, targetStates, gameEngine) -> {
                    if (getObjectByName("Vivaio", objects).getStatus().equals("Cresciuto")) {
                        gameObjects.get(1).setStatus(targetStates.get(1));
                        GameToGUICommunication.getInstance().toGUI("Hai abbattuto l'albero maestro con l'ascia.");

                        //Imposta la stanza a null perché il gioco è finito
                        gameEngine.setCurrentRoomToNull();
                    } else {
                        GameToGUICommunication.getInstance().toGUI("Non è successo niente.");
                    }
                }
        ));

        return gameLogic;
    }

    /**
     * Data una stringa come parametro, questo metodo restituisce l'oggetto di gioco che ha quella stringa come nome.
     * @param name Nome da cercare.
     * @param availableObjects Lista degli oggetti di gioco in cui cercare.
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