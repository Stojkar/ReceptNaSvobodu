package Command;

import Postavy.Hrac;
import Mapa.Zed;
import Mapa.Mistnost;

/**
 * Příkaz pro pohyb hráče v daném směru.
 * Ověří průchodnost zdi v požadovaném směru a přesune hráče do sousední
 * místnosti.
 * Pokud zeď obsahuje cíl (konec hry), spustí příslušný konec.
 *
 * @author Marek
 */
public class Pohyb implements Command {

    private Hrac hrac;
    private boolean konec = false;

    public Pohyb(Hrac hrac) {
        this.hrac = hrac;
    }

    /**
     * Přesune hráče ve zvoleném směru, pokud je zeď průchodná.
     * Pokud zeď vede k cíli hry, spustí koncovou sekvenci.
     *
     * @param smer Směr pohybu: {@code sever}, {@code jih}, {@code vychod} nebo
     *             {@code zapad}
     * @return Popis nové místnosti po přesunu, nebo chybová zpráva
     */
    @Override
    public String execute(String smer) {
        if (smer == null || smer.isBlank() || smer.equals("nic")) {
            return "Zadej směr: sever, jih, vychod nebo zapad";
        }
        Zed zedPosunu = hrac.getZedSmer(smer);
        Mistnost mistnost;

        if (zedPosunu == null) {
            return "Tento směr neexistuje";
        }
        if (zedPosunu.getKonec() != null) {
            konec = true;
            return "\n\n" + zedPosunu.getKonec().spustitKonec(hrac);
        }
        if (zedPosunu.isPruchodnost()) {
            mistnost = zedPosunu.getDruhouMistnost(hrac.getAktMistnost());
            hrac.setAktMistnost(mistnost);
            mistnost.setNavstivena(true);
            String popis = mistnost.toString();
            if (hrac.isMaRukojmi() && hrac.najdiNepriatelNPC() != null) {
                popis += "\n[Stráže vidí rukojmího a bojí se zakročit — můžeš projít!]";
            }
            return popis;
        } else {
            return "Zeď není průchodná";
        }
    }

    @Override
    public boolean exit() {
        return konec;
    }
}
