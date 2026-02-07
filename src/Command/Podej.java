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

        if(npc.isBoj()) {
            return "gg";
        }
        switch (npc.getJmeno()){
            case "nasvalenec":





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
