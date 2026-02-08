package Command;

import Postavy.Hrac;
import Postavy.NPC;
import Predmety.Predmet;
import Predmety.Predmet.SpecialniSchopnost;

/**
 * Příkaz pro boj s nepřáteli.
 * Zpracovává logiku souboje včetně speciálních případů (pistole, rukojmí, cigarety).
 * Podporuje alarmový systém při použití pistole.
 */
public class Souboj implements Command{
    private Hrac hrac;
    private boolean konec = false;
    private boolean Odhalen = false;

    /**
     * Vykoná bojový příkaz.
     * @param nazevP Název zbraně a volitelně "cigarety" pro bonus k útoku
     * @return Výsledek souboje jako textový popis
     */
    @Override
    public String execute(String nazevP) {
        StringBuilder zprava = new StringBuilder();

        String[] args = nazevP.split(" ", 2);
        String nazevZbrane = args[0];
        boolean pouzitCigarety = args.length > 1 && args[1].equalsIgnoreCase("cigarety");

        NPC nepritel = hrac.najdiNepriatelNPC();
        if(nepritel == null){
            return "V místnosti není žádný nepřítel";
        }

        Predmet zbran = hrac.najdiPredmetInventar(nazevZbrane);
        if(zbran == null){
            return "Nemáš takový předmět v inventáři";
        }

        if(zbran.getSchopnost() != SpecialniSchopnost.MUZE_BOJOVAT){
            return "Tento předmět nemůžeš použít k boji";
        }

        if(zbran.getNazev().equals("pistole")){
            hrac.setHlucneOdhaleni(true);
            Odhalen = true;
        }

        String vysledekPistole = zkusitZajmoutRukojmi(zbran, nepritel);
        if(vysledekPistole != null){
            return vysledekPistole;
        }

        if(hrac.isMaRukojmi()){
            return vyhraCelaBojSRukojmim(nepritel);
        }

        int silaUtoku = aplikovatBonusCigaret(zbran.getSila(), pouzitCigarety, zprava);

        if(silaUtoku > nepritel.getSila()){
            return vyhratBoj(nepritel, zbran, zprava);
        }else{
            konec = true;
            return "Prohrál jsi souboj s " + nepritel.getJmeno() + ", konec hry";
        }
    }

    /**
     * Pokusí se zajmout šéfa jako rukojmí pomocí pistole.
     * @param zbran Použitá zbraň
     * @param nepritel Cílový nepřítel
     * @return Text výsledku pokud byl šéf zajat, null pokud nebyla použita pistole nebo cíl není šéf
     */
    private String zkusitZajmoutRukojmi(Predmet zbran, NPC nepritel){
        if(!zbran.getNazev().equals("pistole")){
            return null;
        }

        if(nepritel.getJmeno().equals("sef")){
            hrac.getAktMistnost().getMistnostiNPC().remove(nepritel);
            hrac.setMaRukojmi(true);

            return "Zajal jsi " + nepritel.getJmeno() + " jako rukojmí!\n" +
                    "Stráže se bojí zakročit. Můžeš bezpečně utéct!";
        }

        return null;
    }

    private String vyhraCelaBojSRukojmim(NPC nepritel){
        StringBuilder zprava = new StringBuilder();
        hrac.getAktMistnost().getMistnostiNPC().remove(nepritel);

        zpravaPridejPredmetOdNepritele(nepritel, zprava);

        zprava.append("\nStráže se bojí zakročit kvůli rukojmí!\n");
        zprava.append("Vyhrál jsi souboj s ").append(nepritel.getJmeno()).append("!");
        return zprava.toString();
    }
    /**
     * Aplikuje bonus k útoku z cigaret a zapalovače (zdvojnásobení síly).
     * @param silaPredmetu Základní síla zbraně
     * @param pouzitCigarety Zda hráč použil cigarety
     * @param zprava StringBuilder pro přidání informací o bonusu
     * @return Finální síla útoku
     */
    private int aplikovatBonusCigaret(int silaPredmetu, boolean pouzitCigarety, StringBuilder zprava){
        if(!pouzitCigarety){
            return silaPredmetu;
        }

        Predmet cigarety = hrac.najdiPredmetInventar("cigarety");
        Predmet zapalovac = hrac.najdiPredmetInventar("zapalovac");

        if(cigarety != null && zapalovac != null){
            hrac.inventarOdebrat(cigarety);
            zprava.append("Bonus! Cigarety + Zapalovač zdvojnásobují tvůj útok!\n");
            zprava.append("Cigarety byly vykouřeny a zmizely z inventáře.\n");
            return silaPredmetu * 2;
        }

        if(cigarety == null && zapalovac == null){
            zprava.append("Nemáš cigarety ani zapalovač!\n");
        } else if(cigarety == null){
            zprava.append("Nemáš cigarety!\n");
        } else {
            zprava.append("Nemáš zapalovač!\n");
        }

        return silaPredmetu;
    }

    /**
     * Zpracuje výhru v souboji - odebere nepřítele z místnosti a přidá jeho předmět hráči.
     * @param nepritel Porazený nepřítel
     * @param zbran Použitá zbraň
     * @param zprava StringBuilder se zprávou o souboji
     * @return Text výsledku souboje
     */
    private String vyhratBoj(NPC nepritel, Predmet zbran, StringBuilder zprava){
        hrac.getAktMistnost().getMistnostiNPC().remove(nepritel);

        zpravaPridejPredmetOdNepritele(nepritel, zprava);

        if(zbran.isNicitelnost()){
            hrac.inventarOdebrat(zbran);
            zprava.append("Tvoje ").append(zbran.getNazev()).append(" byl zničen v souboji!\n");
        }


        zprava.append("\nVyhrál jsi souboj s ").append(nepritel.getJmeno()).append("!");
        if(Odhalen){
            zprava.append("\nVystřely pistole rozezněly chodby v cele věznici");
        }
        return zprava.toString();
    }

    private void zpravaPridejPredmetOdNepritele(NPC nepritel, StringBuilder zprava){
        if(nepritel.getPredmetNPC() == null){
            return;
        }

        if(hrac.inventarPridat(nepritel.getPredmetNPC())){
            zprava.append("Získal jsi: ").append(nepritel.getPredmetNPC().getNazev()).append("\n");
        } else {
            hrac.getAktMistnost().getPredmetyMistnosti().add(nepritel.getPredmetNPC());
            zprava.append("Inventář je plný, předmět zůstal v místnosti.\n");
        }
    }

    @Override
    public boolean exit() {
        return konec;
    }

    public Souboj(Hrac hrac) {
        this.hrac = hrac;
    }
}