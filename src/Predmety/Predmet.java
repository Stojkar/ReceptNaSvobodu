package Predmety;

public class Predmet {

    public enum SpecialniSchopnost {
        MUZE_BOJOVAT,
        MUZE_NICIT_ZDI,
        MUZE_SROUBOVAT,
        MUZE_PILOVAT,
        MUZE_KOURIT,
        MUZE_TELEPORTOVAT,
        MUZE_ZAPALOVAT
    }



    private String nazev;
    private String popis;
    private SpecialniSchopnost schopnost;
    private int sila;
    private boolean nicetelnost;



    public Predmet(String nazev, String popis) {
        this.nazev = nazev;
        this.popis = popis;
    }


    public String vypisPopisu(){
        return null;
    }


    @Override
    public String toString() {
        return "Predmet{" +
                "nazev='" + nazev + '\'' +
                ", popis='" + popis + '\'' +
                ", schopnost=" + schopnost +
                ", sila=" + sila +
                ", nicetelnost=" + nicetelnost +
                '}';
    }
}
