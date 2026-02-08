package Pribeh;

public class Volba {

    private String cesta;
    private String popis;
    private int obtiznost;
    private int pocetPredmetu;

    public Volba(String cesta, String popis, int obtiznost) {
        this.cesta = cesta;
        this.popis = popis;
        this.obtiznost = obtiznost;
    }

    public Volba() {
    }

    public String getCesta() {
        return cesta;
    }

    public void setCesta(String cesta) {
        this.cesta = cesta;
    }

    public String getPopis() {
        return popis;
    }

    public void setPopis(String popis) {
        this.popis = popis;
    }

    public int getObtiznost() {
        return obtiznost;
    }

    public void setObtiznost(int obtiznost) {
        this.obtiznost = obtiznost;
    }

    public int getPocetPredmetu() {
        return pocetPredmetu;
    }

    public void setPocetPredmetu(int pocetPredmetu) {
        this.pocetPredmetu = pocetPredmetu;
    }
}
