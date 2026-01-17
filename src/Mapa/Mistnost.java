package Mapa;

import Postavy.NPC;
import Predmety.Predmet;

import java.util.ArrayList;

public class Mistnost {

    private String nazev;
    private String popis;
    private ArrayList<Predmet> PredmetyMistnosti;
    private ArrayList<NPC> NPCMistnosti;


    private Zed severZed;
    private Zed vychodniZed;
    private Zed zapaadniZed;
    private Zed jizniZed;


    public String popisMistnosti(){
        return null;
    }

    public Zed poskytnutiZdi(String starna){
        return null;
    }

    public boolean pridejPredmet(Predmet predmet){
        return false;
    }

    public boolean odeberiPredmet(Predmet predmet){
        return false;
    }

    public Mistnost(String nazev, String popis, Zed severZed, Zed vychodniZed, Zed zapaadniZed, Zed jizniZed) {
        this.nazev = nazev;
        this.popis = popis;
        this.PredmetyMistnosti = new  ArrayList<>();
        this.NPCMistnosti = new  ArrayList<>();
        this.severZed = severZed;
        this.vychodniZed = vychodniZed;
        this.zapaadniZed = zapaadniZed;
        this.jizniZed = jizniZed;
    }
}
