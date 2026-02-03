package Command;

import Postavy.Hrac;
import Postavy.NPC;

public class Souboj implements Command{
    private Hrac hrac;
    private boolean konec = false;

    @Override
    public String execute(String nazevP) {
        if(hrac.najdiNepriatelNPC() != null){
            return "V místnosti není žádný nepřítel";
        }

        if(hrac.najdiPredmetInventar(nazevP).getSila() > hrac.najdiNepriatelNPC().getSila()){
            hrac.getAktMistnost().getMistnostiNPC().remove(0);
            hrac.getAktMistnost().pridejPredmet(hrac.najdiNepriatelNPC().getPredmeNPC());
            return "Vyhraj jsi";
        }else{
            konec = true;
            return "Prohral jsi";
        }
    }

    @Override
    public boolean exit() {
        return konec;
    }

    public Souboj(Hrac hrac) {
        this.hrac = hrac;
    }
}
