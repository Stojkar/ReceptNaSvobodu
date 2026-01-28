package Command;


import Postavy.Hrac;

public class Inventar implements Command {
    private Hrac hrac;


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
