package Predmety;

import java.util.ArrayList;

public class Inventar {


    private ArrayList<Predmet> predmety;
    private int maxPredmetu;



    public ArrayList<Predmet> vypisInventare() {
        return null;
    }

    public boolean pridatPredmet(Predmet predmet) {
        if(predmet==null){
            return false;
        }
        return predmety.add(predmet);
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

    public ArrayList<Predmet> getPredmety() {
        return predmety;
    }

    public void setPredmety(ArrayList<Predmet> predmety) {
        this.predmety = predmety;
    }

    public int getMaxPredmetu() {
        return maxPredmetu;
    }

    public void setMaxPredmetu(int maxPredmetu) {
        this.maxPredmetu = maxPredmetu;
    }
}
