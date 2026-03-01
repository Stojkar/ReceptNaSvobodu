import Command.Pohyb;
import Command.Souboj;
import Mapa.Mistnost;
import Mapa.Zed;
import Postavy.Hrac;
import Postavy.NPC;
import Predmety.Predmet;
import Predmety.Predmet.SpecialniSchopnost;
import Pribeh.Konec;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test scenare: utik z vezeni pomoci rukojmiho.
 *
 * Sekvence:
 * 1. Hrac zajme sefa pistoli jako rukojmiho
 * 2. S rukojmim automaticky vyhrava souboj se straznym
 * 3. Dojde k vychodu a hra skonci spravnym koncem
 */
class UtekSRukojmimTest {

    Hrac hrac;
    Mistnost mistnostSefa;
    Mistnost mistnostStraze;
    NPC sef;
    NPC strazny;
    Predmet pistole;

    /**
     * Pripravi prostredi:
     * - mistnostSefa: hrac + sef (nepritell) + pistole v inventari
     * - mistnostStraze: strazny (nepritel), zed na sever ma Konec (vychod)
     * - mistnostSefa je propojeni se mistnostStraze pres pruchozi zed
     */
    @org.junit.jupiter.api.BeforeEach
    void setUp() {
        // Pistole
        pistole = new Predmet();
        pistole.setNazev("pistole");
        pistole.setSchopnost(SpecialniSchopnost.MUZE_BOJOVAT);
        pistole.setSila(10);

        // Sef - nepritel v mistnosti sefa
        sef = new NPC();
        sef.setJmeno("sef");
        sef.setBoj(true);
        sef.setSila(5);

        // Strazny - nepritel v jedlalsi mistnosti
        strazny = new NPC();
        strazny.setJmeno("strazny");
        strazny.setBoj(true);
        strazny.setSila(3);

        // Mistnost se sefem - startovni pozice hrace
        mistnostSefa = new Mistnost();
        mistnostSefa.setPredmetyMistnosti(new ArrayList<>());
        mistnostSefa.setMistnostiNPC(new ArrayList<>());
        mistnostSefa.getMistnostiNPC().add(sef);

        // Mistnost se straznym
        mistnostStraze = new Mistnost();
        mistnostStraze.setPredmetyMistnosti(new ArrayList<>());
        mistnostStraze.setMistnostiNPC(new ArrayList<>());
        mistnostStraze.getMistnostiNPC().add(strazny);

        // Zed: mistnostSefa -> mistnostStraze (smer sever, pruchozi)
        Zed zedKeStrazi = new Zed();
        zedKeStrazi.setMistnost1(mistnostSefa);
        zedKeStrazi.setMistnost2(mistnostStraze);
        zedKeStrazi.setPruchodnost(true);
        mistnostSefa.setSeverZed(zedKeStrazi);
        mistnostStraze.setJizniZed(zedKeStrazi);

        // Zed: mistnostStraze -> vychod (smer sever, ma Konec)
        Konec konec = new Konec();
        konec.setPopis("Utekl jsi!");
        konec.setPopisRukojmi("Utekl jsi s rukojmim!");
        Zed zedVychod = new Zed();
        zedVychod.setMistnost1(mistnostStraze);
        zedVychod.setMistnost2(null);
        zedVychod.setPruchodnost(false);
        zedVychod.setKonec(konec);
        mistnostStraze.setSeverZed(zedVychod);

        // Hrac startuje u sefa a ma pistoli
        hrac = new Hrac(mistnostSefa);
        hrac.inventarPridat(pistole);
    }

    /**
     * Test 1: Hrac zajme sefa pistoli -- dostane se k nemu rukojmi flag.
     */
    @org.junit.jupiter.api.Test
    void zajmoutSefaJakoRukojmiho() {
        Souboj souboj = new Souboj(hrac);
        String vysledek = souboj.execute("pistole");

        assertTrue(hrac.isMaRukojmi(),
                "Po zajeti sefa by mel hrac mit rukojmiho");
        assertTrue(vysledek.contains("Zajal jsi"),
                "Zprava by mela obsahovat potvrzeni zajeti");
        assertFalse(mistnostSefa.getMistnostiNPC().contains(sef),
                "Sef by mel byt odebran z mistnosti po zajeti");
    }

    /**
     * Test 2: S rukojmim hrac automaticky vyhrava souboj se straznym.
     */
    @org.junit.jupiter.api.Test
    void sRukojmimAutoVyhraSoubojSeStraznym() {
        // Hrac ma rukojmiho, jde do mistnosti se straznym
        hrac.setMaRukojmi(true);
        hrac.setAktMistnost(mistnostStraze);

        Souboj souboj = new Souboj(hrac);
        String vysledek = souboj.execute("pistole");

        assertTrue(vysledek.contains("bojí zakročit") || vysledek.contains("boji zakrocit"),
                "Straz by se mela bat s rukojmim");
        assertFalse(mistnostStraze.getMistnostiNPC().contains(strazny),
                "Strazny by mel byt odebran z mistnosti");
    }
}
