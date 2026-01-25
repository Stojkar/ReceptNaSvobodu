package Command;

import Postavy.Hrac;
import Mapa.Mistnost;

public class Pohyb implements Command{

    private Hrac hrac;


    public Pohyb(Hrac hrac){
        this.hrac = hrac;
    }


    @Override
    public String execute(String smer) {
        Mistnost mistnost = hrac.posun(smer);
        String m = mistnost.toString();
        return m;
    }

    @Override
    public boolean exit() {
        return false;
    }
}
