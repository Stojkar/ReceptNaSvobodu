import Command.Pohyb;
import Mapa.Mistnost;
import Mapa.Zed;
import Postavy.Hrac;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class PohybTest {

    Hrac hrac;
    Mistnost mistnost1;
    Mistnost mistnost2;
    Zed zed;
    Pohyb pohyb;

    /**
     * Připraví testovací prostředí:
     * - mistnost1 a mistnost2 spojené zdí na sever/jih
     * - zeď je průchozí (pruchodnost = true)
     * - hráč stojí v mistnost1
     */
    @org.junit.jupiter.api.BeforeEach
    void setUp() {
        mistnost1 = new Mistnost();
        mistnost1.setPredmetyMistnosti(new ArrayList<>());
        mistnost1.setMistnostiNPC(new ArrayList<>());

        mistnost2 = new Mistnost();
        mistnost2.setPredmetyMistnosti(new ArrayList<>());
        mistnost2.setMistnostiNPC(new ArrayList<>());

        zed = new Zed();
        zed.setMistnost1(mistnost1);
        zed.setMistnost2(mistnost2);
        zed.setPruchodnost(true);

        // mistnost1 má na severu zeď vedoucí do mistnost2
        mistnost1.setSeverZed(zed);
        // mistnost2 má na jihu zeď vedoucí zpět do mistnost1
        mistnost2.setJizniZed(zed);

        hrac = new Hrac(mistnost1);
        pohyb = new Pohyb(hrac);
    }

    /**
     * Hráč se přesune do sousední místnosti pokud je zeď průchozí.
     */
    @org.junit.jupiter.api.Test
    void pohybNaPruchodnouZed() {
        pohyb.execute("sever");

        assertEquals(mistnost2, hrac.getAktMistnost(),
                "Hráč by měl být v mistnost2 po pohybu na sever");
    }

    /**
     * Hráč se nepřesune pokud zeď není průchozí.
     */
    @org.junit.jupiter.api.Test
    void pohybNaNepruchodnouZed() {
        zed.setPruchodnost(false);

        String vysledek = pohyb.execute("sever");

        assertEquals(mistnost1, hrac.getAktMistnost(),
                "Hráč by měl zůstat v mistnost1 — zeď není průchozí");
        assertEquals("Zeď není průchodná", vysledek);
    }

    /**
     * Pohyb na směr bez zdi vrátí chybovou hlášku a hráč zůstane na místě.
     */
    @org.junit.jupiter.api.Test
    void pohybNaNeexistujiciSmer() {
        String vysledek = pohyb.execute("vychod");

        assertEquals(mistnost1, hrac.getAktMistnost(),
                "Hráč by měl zůstat v mistnost1 — směr neexistuje");
        assertEquals("Tento směr neexistuje", vysledek);
    }
}