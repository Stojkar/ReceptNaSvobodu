package Command;

import Postavy.Hrac;
import Postavy.NPC;
import Predmety.Predmet;

public class Podej implements Command {
    private Hrac hrac;

    @Override
    public String execute(String prikazy) {
        String[] prikaz = prikazy.split(" ",2);
        if(prikaz.length < 2){
            return "Musíš zadat osobu i předmět (podej <osoba> <predmet>)";
        }
        String osoba = prikaz[0];
        String predmetString = prikaz[1];
        NPC npc = hrac.najdiNPCMistnost(osoba);
        if(npc == null){
            return "V místnosti není žádná osoba jménem " + osoba;
        }

        if(npc.isBoj()) {
            return "gg";
        }

        Predmet predmet = hrac.najdiPredmetInventar(predmetString);
        if(predmet == null){
            return "Nemáš předmět '" + predmetString + "' v inventáři";
        }

        switch (npc.getJmeno()){
            case "nasvalenec":
                if(!predmet.getNazev().equals("obusek")){
                    hrac.setMapaBitelostiZdi(true);
                    return "Díky tady máš za to mapu celé věznice a bytelosti všech zdí v ní";
                }
                return "To nechci k čemu mi to bude";
            case "snílek":
                return "Hrozne děkuji, víš to že za sprchama má šéf tohohle vězení kancelář.\nStrasně rád bych mu dal pistol k hlavě a odešel odcud";
            case "nemluva":
                //TODO

                return "...";
            case "vyklepanec":
                if(predmet.getNazev().equals("cigarety")){
                    return "Jednou nějakej blázen jako ty si sem dokonce přinesl dynamit a odpalil to v jidelně.\nKdyzby měl dva možná by se mu to povedlo.";
                }else{
                    return "Já chci cígo";
                }
            case "panikar":
                if(predmet.getNazev().equals("pilnik")){
                    hrac.setMapaVentilaci(true);
                    hrac.presunStrazi();
                    hrac.getAktMistnost().odebratNPC(npc);
                    return "Díky jsem svobodný";
                }
                    hrac.setMapaVentilaci(false);
                    return "Tady za to máš mapu vězeni s ventilacemi";
        }
        return "chyba";
    }

    @Override
    public boolean exit() {
        return false;
    }

    public Podej(Hrac hrac) {
        this.hrac = hrac;
    }
}
