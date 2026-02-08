package Predmety;

import java.util.ArrayList;

/**
 * Reprezentuje inventář hráče s omezenou kapacitou.
 * Spravuje kolekci předmětů, přidávání a odebírání s kontrolou limitu.
 */
public class Inventar {
    private ArrayList<Predmet> predmety;
    private int maxPredmetu;

    public boolean pridatPredmet(Predmet predmet) {
        if(predmet==null){
            return false;
        }
        if(predmety.size()>=maxPredmetu){
            return false;
        }
        return predmety.add(predmet);
    }

    public boolean odebratPredmet(Predmet predmet) {
        if(predmet==null){
            return false;
        }
        return predmety.remove(predmet);
    }


    public Inventar() {
        this.predmety = new  ArrayList<>();
        this.maxPredmetu = 5;
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
