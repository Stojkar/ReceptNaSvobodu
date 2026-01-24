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
            default:
                //TODO
                System.out.println("chyba ve smeru");
                break;
        }
        if(zedPosunu.getDruhouMistnost(AktMistnost) == null){
            //TODO
            System.out.println("jsi na okraji");
            return AktMistnost;
        }

        if(zedPosunu.isPruchodnost()){
            AktMistnost = zedPosunu.getDruhouMistnost(AktMistnost);
        }else{
            //TODO
            System.out.println("zed je ted pro tebe nepruchodna");
        }

        return AktMistnost;
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
}
