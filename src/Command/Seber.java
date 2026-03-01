package Command;

import Predmety.Predmet;
import Postavy.Hrac;

/**
 * Příkaz pro sebrání předmětu z aktuální místnosti do inventáře hráče.
 * Ověří, zda předmět v místnosti existuje a zda má hráč volné místo v
 * inventáři.
 *
 * @author Marek
 */
public class Seber implements Command {

    Hrac hrac;

    /**
     * Sebere zadaný předmět z místnosti a přidá ho do inventáře hráče.
     *
     * @param predmet Název předmětu, který má být sebrán
     * @return Zpráva o úspěšném sebrání, nebo chybová zpráva (předmět neexistuje /
     *         inventář plný)
     */
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
        return "Inventář je plný";
    }

    @Override
    public boolean exit() {
        return false;
    }

    public Seber(Hrac hrac) {
        this.hrac = hrac;
    }
}
