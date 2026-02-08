package Command;

import Postavy.Hrac;

public class Prohledat implements Command {
    private Hrac hrac;

    @Override
    public String execute(String prikaz) {
        hrac.getAktMistnost().prohledat();
        return "";
    }

    @Override
    public boolean exit() {
        return false;
    }

    public Prohledat(Hrac hrac) {
        this.hrac = hrac;
    }
}
