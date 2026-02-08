package Postavy;

import Predmety.Predmet;


public class NPC {


    private String jmeno;
    private String dialog;
    private boolean boj;
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

    public boolean isBoj() {
        return boj;
    }

    public void setBoj(boolean boj) {
        this.boj = boj;
    }
}
