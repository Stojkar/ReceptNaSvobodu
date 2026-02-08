package Postavy;
import Mapa.Mistnost;
import Mapa.Zed;
import Predmety.Inventar;
import Predmety.Predmet;
import System.DataHry;

/**
 * Reprezentuje hráče ve hře.
 * Spravuje inventář, aktuální pozici, interakce s NPC a předměty.
 * Obsahuje logiku pro alarm systém a rukojmí mechaniku.
 */
public class Hrac {


    private Inventar inventar;
    private Mistnost AktMistnost;
    private boolean maRukojmi;
    private boolean mapaBitelostiZdi;
    private boolean mapaVentilaci;
    private DataHry dataHry;
    private boolean hlucneOdhaleni = false;
    private int pocetPrikazuPoOdhaleni = 0;


    public Zed getZedSmer(String smer){
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
        if(zedPosunu == null){
            return null;
        }
        return zedPosunu;
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

    public NPC najdiNepriatelNPC() {
        for(NPC npc : AktMistnost.getMistnostiNPC()) {
            if(npc.isBoj()) {
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

    public boolean moznaZnicit(Predmet predmet){
        if(predmet.isNicitelnost()){
            return inventar.odebratPredmet(predmet);
        }
        return false;
    }


    public boolean inventarPridat(Predmet predmet){
        return inventar.pridatPredmet(predmet);
    }

    public boolean inventarOdebrat(Predmet predmet){
        return inventar.odebratPredmet(predmet);
    }

    public void zvysitPocetPrikazuPoOdhaleni(){
        if(hlucneOdhaleni){
            pocetPrikazuPoOdhaleni++;
        }
    }

    public void presunStrazi(){
        Mistnost mistnost = AktMistnost.getJizniZed().getDruhouMistnost(AktMistnost).getVychodniZed().getDruhouMistnost(AktMistnost.getJizniZed().getDruhouMistnost(AktMistnost));
        mistnost.getMistnostiNPC().removeAll(mistnost.getMistnostiNPC());
    }

    public Hrac(Mistnost aktMistnost) {
        AktMistnost = aktMistnost;
        this.inventar = new Inventar();
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

    public boolean isMaRukojmi() {
        return maRukojmi;
    }

    public void setMaRukojmi(boolean maRukojmi) {
        this.maRukojmi = maRukojmi;
    }

    public DataHry getDataHry() {
        return dataHry;
    }

    public void setDataHry(DataHry dataHry) {
        this.dataHry = dataHry;
    }

    public boolean isHlucneOdhaleni() {
        return hlucneOdhaleni;
    }

    public void setHlucneOdhaleni(boolean hlucneOdhaleni) {
        this.hlucneOdhaleni = hlucneOdhaleni;
    }

    public int getPocetPrikazuPoOdhaleni() {
        return pocetPrikazuPoOdhaleni;
    }

    public void setPocetPrikazuPoOdhaleni(int pocetPrikazuPoOdhaleni) {
        this.pocetPrikazuPoOdhaleni = pocetPrikazuPoOdhaleni;
    }
}
