package System;

import Mapa.Mistnost;
import Mapa.Zed;
import Postavy.NPC;
import Predmety.Predmet;
import Pribeh.Volba;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.*;

import java.util.ArrayList;

import java.io.InputStream;
import java.util.Random;

/**
 * Reprezentuje herní data načtená z JSON souboru.
 * Tato třída slouží jako datový kontejner pro veškerý statický herní obsah,
 * jako jsou předměty, postavy, místnosti a příběhové volby.
 */
public class DataHry {
    public ArrayList<Predmet> predmety;
    public ArrayList<Mistnost> mistnosti;
    public ArrayList<Zed> zdi;
    public String uvodniText;
    public ArrayList<Volba> volby;
    public Predmet zeleznaTyc;
    public NPC ozbrojenaOchranka;

    /**
     * Načte herní data z JSON souboru pomocí Jackson ObjectMapper.
     * @param file InputStream s JSON daty
     * @return Instanci DataHry s načtenými daty
     * @throws RuntimeException pokud dojde k chybě při čtení JSON
     */
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

    public Mistnost nahodnaMistnost() {
        Random rand = new Random();
        int cislo =  rand.nextInt(mistnosti.size());
        return mistnosti.get(cislo);
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

    public Predmet getZeleznaTyc() {
        return zeleznaTyc;
    }

    public void setZeleznaTyc(Predmet zeleznaTyc) {
        this.zeleznaTyc = zeleznaTyc;
    }

    public NPC getOzbrojenaOchranka() {
        return ozbrojenaOchranka;
    }

    public void setOzbrojenaOchranka(NPC ozbrojenaOchranka) {
        this.ozbrojenaOchranka = ozbrojenaOchranka;
    }
}
