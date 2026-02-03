package Postavy;

import Predmety.Predmet;

import java.util.ArrayList;

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
    private Predmet predmeNPC;

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

    public Predmet getPredmeNPC() {
        return predmeNPC;
    }

    public void setPredmeNPC(Predmet predmeNPC) {
        this.predmeNPC = predmeNPC;
    }
}
