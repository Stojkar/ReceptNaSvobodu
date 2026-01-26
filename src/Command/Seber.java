package Command;

import Predmety.Inventar;
import Predmety.Predmet;
import Postavy.Hrac;

public class Seber implements Command {

    Hrac hrac;
    @Override
    public String execute(String predmet) {
        Inventar inventar = hrac.pridej(predmet);
        if(hrac.getAktMistnost().odberPredmet(predmet)){
            return inventar.toString();
        }
        return "V této místnosti není žádný předmět " + predmet;
    }

    @Override
    public boolean exit() {
        return false;
    }

    public Seber(Hrac hrac) {
        this.hrac = hrac;
    }
}
