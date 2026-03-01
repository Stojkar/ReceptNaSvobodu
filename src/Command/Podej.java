package Command;

import Postavy.Hrac;
import Postavy.NPC;
import Predmety.Predmet;

/**
 * Příkaz pro předání předmětu NPC v aktuální místnosti.
 * Zpracovává různé scénáře výměny předmětů s konkrétními postavami (nasvalenec,
 * snílek, nemluva, vyklepanec, panikar, chrabrich), přičemž každá postava
 * reaguje odlišně v závislosti na obdrženém předmětu.
 *
 * @author Marek
 */
public class Podej implements Command {
    private Hrac hrac;

    /**
     * Předá předmět z inventáře hráče dané NPC.
     * Formát vstupu: {@code <jméno_osoby> <název_předmětu>}
     *
     * @param prikazy Řetězec obsahující jméno osoby a název předmětu oddělené
     *                mezerou
     * @return Textová odpověď NPC nebo chybová zpráva
     */
    @Override
    public String execute(String prikazy) {
        String[] prikaz = prikazy.split(" ", 2);
        if (prikaz.length < 2) {
            return "Musíš zadat osobu i předmět (podej <osoba> <predmet>)";
        }
        String osoba = prikaz[0];
        String predmetString = prikaz[1];
        NPC npc = hrac.najdiNPCMistnost(osoba);
        if (npc == null) {
            return "V místnosti není žádná osoba jménem " + osoba;
        }

        if (npc.isBoj()) {
            return "gg";
        }

        Predmet predmet = hrac.najdiPredmetInventar(predmetString);
        if (predmet == null) {
            return "Nemáš předmět '" + predmetString + "' v inventáři";
        }

        switch (npc.getJmeno()) {
            case "nasvalenec":
                if (!predmet.getNazev().equals("obusek")) {
                    hrac.inventarOdebrat(predmet);
                    hrac.setMapaBitelostiZdi(true);
                    return "Díkuji ti brachu, tady máš za to mapu celé věznice a pevnosti všech zdí, snad se ti bude hodit!";
                }
                return "Hele, to fakt nechci, k čemu mi to bude??";
            case "snílek":
                hrac.inventarOdebrat(predmet);
                return "Hrozně ti děkuji... víš to, že za sprchama má šéf tohohle vězení kancelář?\nStrasně rád bych mu dal pistol k hlavě a odešel odsud...";
            case "nemluva":
                hrac.inventarOdebrat(predmet);
                return "ha ha";
            case "vyklepanec":
                if (predmet.getNazev().equals("cigarety")) {
                    hrac.inventarOdebrat(predmet);
                    return "Jjednou nějakej blázen jako si ty si sem ddokonce přinesl dynamit a odpálil tto v jidelně.\nKdyzby měl dva, možná by se mu to povedlo, hhaha.";
                } else {
                    return "Jjá chci cígo";
                }
            case "panikar":
                if (predmet.getNazev().equals("pilnik")) {
                    hrac.inventarOdebrat(predmet);
                    hrac.setMapaVentilaci(true);
                    hrac.presunStrazi();
                    hrac.getAktMistnost().odebratNPC(npc);
                    return "Díky jsem svobodný!!?";
                }
                hrac.setMapaVentilaci(false);
                return "Tady za to máš mapu vězeni s ventilacemi, teď už jenom rychle utéct pryč!\n\n... ...bum bum... VY hHAJzLo-...";
            case "chrabrich":
                if (predmet.getNazev().equals("obusek")) {
                    return "K čemu mi byl pendrek?";
                } else {
                    hrac.inventarOdebrat(predmet);
                    hrac.inventarPridat(npc.getPredmetNPC());
                    return "Za to ti ukážu takové trýček s mýdlem a ponožko, koukej tímhle kliďo sejmeš dva zaráz";
                }
        }
        return "tato osoba není";
    }

    @Override
    public boolean exit() {
        return false;
    }

    public Podej(Hrac hrac) {
        this.hrac = hrac;
    }
}
