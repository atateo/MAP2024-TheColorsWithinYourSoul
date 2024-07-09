package it.uniba.map.giocotestuale.socket;

import java.io.IOException;

import it.uniba.map.giocotestuale.database.domain.Score;

public class TestSocket {

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        GameClient client = new GameClient();
        //String response = null;
        try {
            client.startConnection("localhost", 3999);
            Score score = new Score();
            score.setPlayer("readiPlayerOne");
            long time = System.currentTimeMillis();
            score.setTime(time);
            client.sendScore(score);
            client.stopConnection();

            client.startConnection("localhost", 3999);
            client.getScores();
            client.stopConnection();

            client.startConnection("localhost", 3999);
            client.end();
            client.stopConnection();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        //System.out.println(response);
    }

    /**
     * Metodo che restituisce il tempo formattato (hh:mm:ss)
     *
     * @return record
     */
    public static String timeFormatted(long timeTaken) {
        long second = (timeTaken / 1000) % 60;
        long minute = (timeTaken / (1000 * 60)) % 60;
        long hour = (timeTaken / (1000 * 60 * 60)) % 24;
        return String.format("%02d:%02d:%02d", hour, minute, second);
    }
}
