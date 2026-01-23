package Pribeh;

public class Konec {

    private String nazev;
    private boolean rukojmi;
    private String popis;


    public Konec(String popis) {
        this.popis = popis;
    }

    public String spustitKonec(){
        return null;
    }

    public Konec() {
    }


    public String getNazev() {
        return nazev;
    }

    public void setNazev(String nazev) {
        this.nazev = nazev;
    }

    public boolean isRukojmi() {
        return rukojmi;
    }

    public void setRukojmi(boolean rukojmi) {
        this.rukojmi = rukojmi;
    }

    public String getPopis() {
        return popis;
    }

    public void setPopis(String popis) {
        this.popis = popis;
    }
}
