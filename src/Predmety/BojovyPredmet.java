package Predmety;

public class BojovyPredmet extends Predmet {

    private int silaVBoji;
    private boolean nicetelnost;

    public BojovyPredmet(String nazev, String popis, int silaVBoji,boolean nicetelnost) {
        super(nazev, popis);
        this.silaVBoji = silaVBoji;
        this.nicetelnost = nicetelnost;
    }

}

