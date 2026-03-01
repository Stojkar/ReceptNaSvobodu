package Predmety;

import java.util.ArrayList;

/**
 * Reprezentuje inventář hráče s omezenou kapacitou.
 * Spravuje kolekci předmětů s maximálním limitem 5 položek.
 * Poskytuje metody pro přidávání a odebírání předmětů s kontrolou limitu.
 *
 * @author Marek
 */
public class Inventar {
    private ArrayList<Predmet> predmety;
    private int maxPredmetu;

    /**
     * Přidá předmět do inventáře, pokud není plný a předmět není {@code null}.
     *
     * @param predmet Předmět, který má být přidán
     * @return {@code true} pokud byl předmět úspěšně přidán, {@code false} pokud je
     *         inventář plný nebo předmět je {@code null}
     */
    public boolean pridatPredmet(Predmet predmet) {
        if(predmet==null){
            return false;
        }
        if(predmety.size()>=maxPredmetu){
            return false;
        }
        return predmety.add(predmet);
    }

    /**
     * Odebere předmět z inventáře.
     *
     * @param predmet Předmět, který má být odebrán
     * @return {@code true} pokud byl předmět nalezen a odebrán, {@code false} pokud
     *         předmět není v inventáři nebo je {@code null}
     */
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
