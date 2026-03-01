package Command;

import Postavy.Hrac;
import Predmety.Predmet;

/**
 * Příkaz pro zahození předmětu z inventáře hráče na zem v aktuální místnosti.
 * Odebere předmět z inventáře a umístí ho do místnosti.
 *
 * @author Marek
 */
public class Zahod implements Command{
    private Hrac hrac;

    /**
     * Zahodí předmět z inventáře hráče na zem v aktuální místnosti.
     *
     * @param prikaz Název předmětu, který má být zahozen
     * @return Zpráva o úspěšném zahození, nebo chybová zpráva pokud předmět v
     *         inventáři není
     */
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
