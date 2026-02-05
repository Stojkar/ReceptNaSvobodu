package Postavy;

import Predmety.Predmet;

public class NPC {

    public enum AkcePostavy{
        BOJ,
        VYMENA_PREDMETU,
        DAROVANI,
        VYJEDNAVANI
    }

    private String jmeno;
    private String dialog;
    private AkcePostavy akce;
    private int sila;
    private Predmet predmetNPC;

    @Override
    public String toString() {
        return jmeno;
    }

    public NPC() {
    }
    public NPC(String jmeno) {
        this.jmeno = jmeno;
    }

    public String getJmeno() {
        return jmeno;
    }

    public void setJmeno(String jmeno) {
        this.jmeno = jmeno;
    }

    public String getDialog() {
        return dialog;
    }

    public void setDialog(String dialog) {
        this.dialog = dialog;
    }

    public AkcePostavy getAkce() {
        return akce;
    }

    public void setAkce(AkcePostavy akce) {
        this.akce = akce;
    }

    public int getSila() {
        return sila;
    }

    public void setSila(int sila) {
        this.sila = sila;
    }

    public Predmet getPredmetNPC() {
        return predmetNPC;
    }

    public void setPredmetNPC(Predmet predmetNPC) {
        this.predmetNPC = predmetNPC;
    }
}
