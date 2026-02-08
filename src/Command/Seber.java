package Command;

import Predmety.Predmet;
import Postavy.Hrac;

public class Seber implements Command {

    Hrac hrac;
    @Override
    public String execute(String predmet) {
        Predmet p = hrac.najdiPredmetMistnost(predmet);
        if(p == null){
            return "V této místnosti není žádný předmět " + predmet;
        }
        if(hrac.inventarPridat(p)){
            hrac.getAktMistnost().odberPredmet(predmet);
            return "Seberal jsi predmet " + p.toString();
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
