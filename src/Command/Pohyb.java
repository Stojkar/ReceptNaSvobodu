package Command;

import Postavy.Hrac;
import Mapa.Zed;
import Mapa.Mistnost;

public class Pohyb implements Command {

    private Hrac hrac;
    private boolean konec = false;

    public Pohyb(Hrac hrac) {
        this.hrac = hrac;
    }

    @Override
    public String execute(String smer) {
        Zed zedPosunu = hrac.getZedSmer(smer);
        Mistnost mistnost;

        if (zedPosunu == null) {
            return "Tento směr neexistuje";
        }
        if (zedPosunu.getKonec() != null) {
            konec = true;
            return zedPosunu.getKonec().spustitKonec(hrac);
        }
        if (zedPosunu.isPruchodnost()) {
            mistnost = zedPosunu.getDruhouMistnost(hrac.getAktMistnost());
            hrac.setAktMistnost(mistnost);
            mistnost.setNavstivena(true);
            return mistnost.toString();
        } else {
            return "Zeď není průchodná";
        }
    }

    @Override
    public boolean exit() {
        return konec;
    }
}
