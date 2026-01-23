package Postavy;
import Mapa.Mistnost;
import Predmety.Inventar;
import Predmety.Predmet;


public class Hrac {


    private Inventar inventar;
    private Mistnost AktMistnost;
    private boolean mapaBitelostiZdi;
    private boolean mapaVentilaci;


    public Mistnost posun(String smer){
        return null;
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
        this.inventar = inventar = new Inventar(maxPredmetu);
    }

    @Override
    public String toString() {
        return "Hrac{" +
                "AktMistnost=" + AktMistnost +
                '}';
    }
}
