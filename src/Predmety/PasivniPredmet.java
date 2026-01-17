package Predmety;

public class PasivniPredmet extends Predmet {


    private int silaKopani;
    private boolean moznostSroubovani;


    public PasivniPredmet(String nazev, String popis, int silaKopani, boolean moznostSroubovani) {
        super(nazev, popis);
        this.silaKopani = silaKopani;
        this.moznostSroubovani = moznostSroubovani;
    }
}