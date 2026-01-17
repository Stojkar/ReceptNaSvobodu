package Postavy;

import Mapa.Mistnost;
import Predmety.Predmet;

import java.util.ArrayList;

public class PratelskeNPC extends NPC {


    private String dialog;
    private ArrayList<Predmet> predmety;


    public PratelskeNPC(String jmeno, Mistnost mistnost, String dialog) {
        super(jmeno, mistnost);
        this.dialog = dialog;
        this.predmety = new ArrayList<>();
    }


    public String dialog(){
        return null;
    }

    public String daniPredmetu(){
        return null;
    }

    public String ziskaniPredmetu(){
        return null;
    }


}
