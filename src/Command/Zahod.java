package Command;

import Postavy.Hrac;
import Predmety.Predmet;

public class Zahod implements Command{
    private Hrac hrac;

    @Override
    public String execute(String prikaz) {
        Predmet predmet =  hrac.najdiPredmetInventar(prikaz);
        if(predmet!=null){
            hrac.getInventar().odebratPredmet(predmet);
            hrac.getAktMistnost().pridejPredmet(predmet);
            return "Zahodil jsi předmět na zem";
        }
        return "Nemáš žádný předmět " + prikaz;
    }

    @Override
    public boolean exit() {
        return false;
    }

    public Zahod(Hrac hrac) {
        this.hrac = hrac;
    }
}
