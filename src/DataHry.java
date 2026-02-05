import Mapa.Mistnost;
import Mapa.Zed;
import Postavy.NPC;
import Predmety.Predmet;
import Pribeh.Konec;
import Pribeh.Volba;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.*;

import java.util.ArrayList;

import java.io.InputStream;



public class DataHry {


    /**
     * Represents the game data loaded from a JSON file.
     * This class serves as a data container for all static game content, such as items, characters, locations, and quests.
     *
     */


    public ArrayList<Predmet> predmety;
    public ArrayList<Mistnost> mistnosti;
    public ArrayList<Zed> zdi;
    public String uvodniText;
    public ArrayList<Volba> volby;



    public static DataHry loadGameDataFromResources(InputStream file) {
        ObjectMapper mapper = new ObjectMapper();

        try {
            return mapper.readValue(file, DataHry.class);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }


    public DataHry() {
    }

    public ArrayList<Mistnost> getMistosti() {
        return mistnosti;
    }

    @Override
    public String toString() {
        return "GameData{" +
                "predmety=" + predmety +
                ", mistosti=" + mistnosti +
                ", zdi=" + zdi +
                '}';
    }

    public ArrayList<Predmet> getPredmety() {
        return predmety;
    }

    public void setPredmety(ArrayList<Predmet> predmety) {
        this.predmety = predmety;
    }

    public ArrayList<Mistnost> getMistnosti() {
        return mistnosti;
    }

    public void setMistnosti(ArrayList<Mistnost> mistnosti) {
        this.mistnosti = mistnosti;
    }

    public ArrayList<Zed> getZdi() {
        return zdi;
    }

    public void setZdi(ArrayList<Zed> zdi) {
        this.zdi = zdi;
    }

    public String getUvodniText() {
        return uvodniText;
    }

    public void setUvodniText(String uvodniText) {
        this.uvodniText = uvodniText;
    }

    public ArrayList<Volba> getVolby() {
        return volby;
    }

    public void setVolby(ArrayList<Volba> volby) {
        this.volby = volby;
    }

}
