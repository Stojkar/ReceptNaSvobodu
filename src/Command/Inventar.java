package Command;


import Postavy.Hrac;

/**
 * Příkaz pro zobrazení obsahu inventáře hráče.
 * Vypíše všechny předměty, které hráč aktuálně nese.
 *
 * @author Marek
 */
public class Inventar implements Command {
    private Hrac hrac;

    /**
     * Zobrazí obsah inventáře hráče.
     *
     * @param smer Parametr není utilit
     * @return Textový výpis obsahu inventáře
     */
    @Override
    public String execute(String smer) {
        return hrac.getInventar().toString();
    }

    @Override
    public boolean exit() {
        return false;
    }

    public Inventar(Hrac hrac) {
        this.hrac = hrac;
    }
}
