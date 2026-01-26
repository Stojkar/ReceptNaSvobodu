package Predmety;

import java.util.ArrayList;

public class Inventar {


    private ArrayList<Predmet> predmety;
    private int maxPredmetu;



    public ArrayList<Predmet> vypisInventare() {
        return null;
    }

    public Predmet pridatPredmet(Predmet predmet) {
        predmety.add(predmet);
        return predmet;
    }

    public boolean odebratPredmet(Predmet predmet) {
        return false;
    }


    public Inventar(int maxPredmetu) {
        this.predmety = new  ArrayList<>();
        this.maxPredmetu = maxPredmetu;
    }

    @Override
    public String toString() {
        return "Inventar = " + predmety;
    }
}
