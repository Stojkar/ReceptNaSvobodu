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
        Zed zedPosunu;

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
                zedPosunu = null;
        }
        if(zedPosunu.getDruhouMistnost(AktMistnost) == null){
            return AktMistnost;
        }

        if(zedPosunu.isPruchodnost()){
            AktMistnost = zedPosunu.getDruhouMistnost(AktMistnost);
        }

        return AktMistnost;
    }

    public Predmet najdiPredmetMistnost(String predmet){
        for(Predmet mistPredmet: AktMistnost.getPredmetyMistnosti()) {
            if (mistPredmet.getNazev().equals(predmet)) {
                return mistPredmet;
            }
        }
        return null;
    }

    public NPC najdiNPCMistnost(String npc){
        for(NPC mistNPC: AktMistnost.getMistnostiNPC()){
            if(mistNPC.getJmeno().equals(npc)){
                return mistNPC;
            }
        }
        return null;
    }

    public NPC getNepriatelNPC() {
        for(NPC npc : AktMistnost.getMistnostiNPC()) {
            if(npc.getAkce() == NPC.AkcePostavy.BOJ) {
                return npc;
            }
        }
        return null;
    }


    public Predmet najdiPredmetInventar(String predmet){
        for(Predmet mistPredmet: inventar.getPredmety()) {
            if (mistPredmet.getNazev().equals(predmet)) {
                return mistPredmet;
            }
        }
        return null;
    }

    public NPC najdiNpc(String npc){
        //TODO
        return null;
    }

    public void rozbaleniBalicku(int pocet){
        //TODO
    }

    public boolean InventarPridat(Predmet predmet){
        return inventar.pridatPredmet(predmet);
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
