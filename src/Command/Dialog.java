package Command;

import Postavy.Hrac;
import Postavy.NPC;

public class Dialog implements Command{
    private Hrac hrac;

    @Override
    public String execute(String osoba) {
        NPC npc = hrac.najdiNPCMistnost(osoba);
        return npc.getDialog();
    }

    @Override
    public boolean exit() {
        return false;
    }


}
