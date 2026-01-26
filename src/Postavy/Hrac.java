package Postavy;
import Mapa.Mistnost;
import Mapa.Zed;
import Predmety.Inventar;
import Predmety.Predmet;


public class Hrac {


    private Inventar inventar;
    private Mistnost AktMistnost;
    private boolean mapaBitelostiZdi;
    private boolean mapaVentilaci;


    public Mistnost posun(String smer){
        Zed zedPosunu = new Zed();

        switch(smer){
            case "sever":
                zedPosunu = AktMistnost.getSeverZed();
                break;
            case "jih":
                zedPosunu = AktMistnost.getJizniZed();
                break;
            case "vychod":
                zedPosunu = AktMistnost.getVychodniZed();
                break;
            case "zapad":
                zedPosunu = AktMistnost.getZapadniZed();
                break;
        }
        if(zedPosunu.getDruhouMistnost(AktMistnost) == null){
            return AktMistnost;
        }

        if(zedPosunu.isPruchodnost()){
            AktMistnost = zedPosunu.getDruhouMistnost(AktMistnost);
        }

        return AktMistnost;
    }

    public Inventar pridej(String predmet){
        Predmet sbiPredmet;
        for(Predmet mistPredmet: AktMistnost.getPredmetyMistnosti()){
            if(mistPredmet.getNazev().equals(predmet)){
                sbiPredmet = mistPredmet;
            }
            inventar.pridatPredmet(mistPredmet);
        }
        return inventar;
    }

    public Predmet pouzij(Predmet predmet){
        return null;
    }

    public Predmet zahod(Predmet predmet){
        return null;
    }

    public String mluv(){
        return null;
    }

    public boolean souboj(NPC npc, Predmet predmet){
        return false;
    }

    public Hrac(Mistnost aktMistnost, int maxPredmetu) {
        AktMistnost = aktMistnost;
        this.inventar = new Inventar(maxPredmetu);
    }

    @Override
    public String toString() {
        return "Hrac{" +
                "AktMistnost=" + AktMistnost +
                '}';
    }

    public Inventar getInventar() {
        return inventar;
    }

    public void setInventar(Inventar inventar) {
        this.inventar = inventar;
    }

    public Mistnost getAktMistnost() {
        return AktMistnost;
    }

    public void setAktMistnost(Mistnost aktMistnost) {
        AktMistnost = aktMistnost;
    }

    public boolean isMapaBitelostiZdi() {
        return mapaBitelostiZdi;
    }

    public void setMapaBitelostiZdi(boolean mapaBitelostiZdi) {
        this.mapaBitelostiZdi = mapaBitelostiZdi;
    }

    public boolean isMapaVentilaci() {
        return mapaVentilaci;
    }

    public void setMapaVentilaci(boolean mapaVentilaci) {
        this.mapaVentilaci = mapaVentilaci;
    }
}
