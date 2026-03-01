package Command;

import Postavy.Hrac;
import Postavy.NPC;

/**
 * Příkaz pro zahájení dialogu s NPC v aktuální místnosti.
 * Vyhledá postavu podle jména a vrátí její dialog.
 *
 * @author Marek
 */
public class Dialog implements Command{
    private Hrac hrac;

    /**
     * Zahájí dialog s NPC zadaným jménem.
     *
     * @param osoba Jméno NPC, se kterou chce hráč mluvit
     * @return Dialog dané postavy, nebo chybová zpráva pokud postava v místnosti
     *         není
     */
    @Override
    public String execute(String osoba) {
        NPC npc = hrac.najdiNPCMistnost(osoba);
        if(npc == null){
            return "Není tu žádná postava s jmenem " + osoba;
        }
        return npc.getDialog();
    }

    @Override
    public boolean exit() {
        return false;
    }

    public Dialog(Hrac hrac) {
        this.hrac = hrac;
    }
}
