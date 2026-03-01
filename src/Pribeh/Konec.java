package Pribeh;

import Postavy.Hrac;

/**
 * Reprezentuje konec hry přiřazený k určité zdi (východu).
 * V závislosti na stavu hráče (má/nemá rukojmího) zobrazí
 * různý závěrečný text.
 *
 * @author Marek
 */
public class Konec {

    private String popis;
    private String popisRukojmi;

    /**
     * Spustí konec hry a vrátí příslušný závěrečný text.
     * Pokud hráč drží rukojmího, zobrazí speciální variantu konce.
     *
     * @param hrac Hráč, jehož stav rozhodne o výsledku
     * @return Textový popis konce hry
     */
    public String spustitKonec(Hrac hrac) {
        if (hrac.isMaRukojmi()) {
            return popisRukojmi;
        }
        return popis;
    }

    public Konec() {
    }

    public String getPopis() {
        return popis;
    }

    public void setPopis(String popis) {
        this.popis = popis;
    }

    public String getPopisRukojmi() {
        return popisRukojmi;
    }

    public void setPopisRukojmi(String popisRukojmi) {
        this.popisRukojmi = popisRukojmi;
    }
}
