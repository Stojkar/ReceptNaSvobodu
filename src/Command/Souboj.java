package Command;

import Postavy.Hrac;
import Postavy.NPC;
import Predmety.Predmet;
import Predmety.Predmet.SpecialniSchopnost;

public class Souboj implements Command{
    private Hrac hrac;
    private boolean konec = false;

    @Override
    public String execute(String prikaz) {
        StringBuilder zprava = new StringBuilder();

        String[] prikazy = prikaz.split(" ", 2);
        String nazevZbrane = prikazy[0];
        boolean pouzitCigarety = prikazy.length > 1 && prikazy[1].equals("cigarety");

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

        int silaUtoku = zbran.getSila();

        if(pouzitCigarety){
            Predmet cigarety = hrac.najdiPredmetInventar("Cigarety");
            Predmet zapalovac = hrac.najdiPredmetInventar("Zapalovač");

            if(cigarety != null && zapalovac != null){
                silaUtoku *= 2;
                hrac.invantarOdebrat(cigarety);
                zprava.append("Bonus! Cigarety + Zapalovač zdvojnásobují tvůj útok!\n");
                zprava.append("Cigarety byly vykouřeny a zmizely z inventáře.\n");
            } else if(cigarety == null && zapalovac == null){
                zprava.append("Nemáš cigarety ani zapalovač!\n");
            } else if(cigarety == null){
                zprava.append("Nemáš cigarety!\n");
            } else {
                zprava.append("Nemáš zapalovač!\n");
            }
        }

        if(silaUtoku >= nepritel.getSila()){
            hrac.getAktMistnost().getMistnostiNPC().remove(nepritel);

            if(nepritel.getPredmetNPC() != null){
                if(hrac.inventarPridat(nepritel.getPredmetNPC())){
                    zprava.append("Získal jsi: ").append(nepritel.getPredmetNPC().getNazev()).append("\n");
                } else {
                    hrac.getAktMistnost().getPredmetyMistnosti().add(nepritel.getPredmetNPC());
                    zprava.append("Inventář je plný, předmět zůstal v místnosti.\n");
                }
            }

            if(zbran.isNicitelnost()){
                hrac.invantarOdebrat(zbran);
                zprava.append("Tvoje ").append(zbran.getNazev()).append(" byl zničen v souboji!\n");
            }

            zprava.append("\nVyhrál jsi souboj s ").append(nepritel.getJmeno()).append("!");
            return zprava.toString();
        }else{
            konec = true;
            return "Prohrál jsi souboj s " + nepritel.getJmeno() + ", konec hry";
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
