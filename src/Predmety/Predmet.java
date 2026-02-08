package Predmety;

/**
 * Reprezentuje herní předmět s různými schopnostmi.
 * Každý předmět má název, popis, speciální schopnost, sílu a nicitelnost.
 * Předměty mohou být použity k boji, ničení zdí, šroubování, pilování a dalším akcím.
 */
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

    public Predmet(String nazev, String popis, SpecialniSchopnost schopnost, int sila, boolean nicitelnost) {
        this.nazev = nazev;
        this.popis = popis;
        this.schopnost = schopnost;
        this.sila = sila;
        this.nicitelnost = nicitelnost;
    }

    @Override
    public String toString() {
        return nazev;
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
