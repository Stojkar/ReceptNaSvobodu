package Command;

import Postavy.Hrac;

/**
 * Příkaz pro prozkoumání aktuální místnosti.
 * Zobrazí detaily o zdích ve všech směrech, předmětech a NPC v místnosti.
 *
 * @author Marek
 */
public class Prohledat implements Command {
    private Hrac hrac;

    /**
     * Prohledá aktuální místnost a vrátí přehled o jejím obsahu.
     *
     * @param prikaz Parametr není využit
     * @return Textový popis zdí, předmětů a postav v místnosti
     */
    @Override
    public String execute(String prikaz) {
        return hrac.getAktMistnost().prohledat();
    }

    @Override
    public boolean exit() {
        return false;
    }

    public Prohledat(Hrac hrac) {
        this.hrac = hrac;
    }
}
