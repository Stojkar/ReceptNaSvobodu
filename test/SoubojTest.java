import Command.Souboj;
import Mapa.Mistnost;
import Postavy.Hrac;
import Postavy.NPC;
import Predmety.Predmet;
import Predmety.Predmet.SpecialniSchopnost;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SoubojTest {

    Hrac hrac;
    Mistnost mistnost;
    NPC nepritel;
    Predmet zbran;

    @BeforeEach
    void setUp() {
        nepritel = new NPC();
        nepritel.setJmeno("strazny");
        nepritel.setBoj(true);
        nepritel.setSila(2);

        zbran = new Predmet("obusek", "Obušek", SpecialniSchopnost.MUZE_BOJOVAT, 5, false);

        mistnost = new Mistnost();
        mistnost.setPredmetyMistnosti(new ArrayList<>());
        mistnost.setMistnostiNPC(new ArrayList<>());
        mistnost.getMistnostiNPC().add(nepritel);

        hrac = new Hrac(mistnost);
        hrac.inventarPridat(zbran);
    }


    /** Zbraň kterou hráč nemá v inventáři → správná chyba. */
    @Test
    void zbranNeniVInventari_vratiChybu() {
        Souboj souboj = new Souboj(hrac);

        String vysledek = souboj.execute("pistole");

        assertTrue(vysledek.contains("nemáš") || vysledek.contains("Nemáš"),
                "Chybějící zbraň musí vrátit chybovou zprávu");
    }

    /** Hráč vyhraje souboj pokud má větší sílu než nepřítel. */
    @Test
    void vyhrasouboje_silnejsiZbran() {
        Souboj souboj = new Souboj(hrac);

        String vysledek = souboj.execute("obusek");

        assertTrue(vysledek.contains("Vyhrál"),
                "S dostatečně silnou zbraní hráč musí vyhrát souboj");
        assertFalse(mistnost.getMistnostiNPC().contains(nepritel),
                "Nepřítel musí být odebrán z místnosti po prohře");
    }
}
