package Command;


import Postavy.Hrac;
import Postavy.NPC;
import Predmety.Predmet;

public class Podej implements Command {
    private Hrac hrac;

    @Override
    public String execute(String prikazy) {
        String[] prikaz = prikazy.split(" ",2);
        String osoba = prikaz[0];
        String predmetString = prikaz[1];
        NPC npc = hrac.najdiNPCMistnost(osoba);
        Predmet predmet = hrac.najdiPredmetInventar(predmetString);

        switch (npc.getAkce()){
            case BOJ ->
            {
                return "gg";
            }
            case VYMENA_PREDMETU ->
                {
                    hrac.invantarOdebrat(predmet);
                    hrac.inventarPridat(npc.getPredmeNPC());
                    npc.setPredmeNPC(predmet);
                    return "Výměna proběhla";
                }
            case DAROVANI ->
                {
                    hrac.invantarOdebrat(predmet);
                    return "Díky to jsi nemusel";
                }
            case VYJEDNAVANI ->
                {
                    return "";
                }
        }



        return "";
    }

    @Override
    public boolean exit() {
        return false;
    }

    public Podej(Hrac hrac) {
        this.hrac = hrac;
    }
}
