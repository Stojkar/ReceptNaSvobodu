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
    private boolean nicitelnost;


    public Predmet() {
    }

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
                ", nicetelnost=" + nicitelnost +
                '}';
    }

    public String getNazev() {
        return nazev;
    }

    public void setNazev(String nazev) {
        this.nazev = nazev;
    }

    public String getPopis() {
        return popis;
    }

    public void setPopis(String popis) {
        this.popis = popis;
    }

    public SpecialniSchopnost getSchopnost() {
        return schopnost;
    }

    public void setSchopnost(SpecialniSchopnost schopnost) {
        this.schopnost = schopnost;
    }

    public int getSila() {
        return sila;
    }

    public void setSila(int sila) {
        this.sila = sila;
    }

    public boolean isNicitelnost() {
        return nicitelnost;
    }

    public void setNicitelnost(boolean niceitelnost) {
        this.nicitelnost = nicitelnost;
    }
}
