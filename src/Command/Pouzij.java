package Command;

import Postavy.Hrac;
import Predmety.Predmet;

public class Pouzij implements Command{

    private Hrac hrac;


    @Override
    public String execute(String prikaz) {
        String[] prikazy = prikaz.split(" ",2);

        Predmet predmet =  hrac.najdiPredmetInventar(prikazy[0]);


        System.out.println(predmet.toString());
        switch(predmet.getSchopnost()){
            case MUZE_BOJOVAT:
                return "Nic to neudělalo, šetři tento předmět do boje";
            case MUZE_NICIT_ZDI:
                if(hrac.getAktMistnost().znicZed(predmet.getSila(),prikazy[1])){
                    return "Zničil jsi zeď, cesta je průchodná";
                }
                return "zaď je moc silná na tento předmět";
            case MUZE_SROUBOVAT:
                if(hrac.getAktMistnost().srouboVentilace(prikazy[1])){
                    return "Ventilace je odšroubovaná a máš volnou cestu";
                }
                return "Ve zdi není ventilace";
            case MUZE_PILOVAT:
                if(hrac.getAktMistnost().pilovatMrize(prikazy[1])){
                    //TODO
                    hrac.InventarPridat(new Predmet("ZeleznaTyc","Ještě teplá od pilování", Predmet.SpecialniSchopnost.MUZE_BOJOVAT,2,true));
                    return "Povedlo se ti přepilovat mříže a v ruce ti zbyla tyč";
                }
                return "Ve zdi nejsou mříže";
        }
        return "neco jsi pokazil";
    }

    @Override
    public boolean exit() {
        return false;
    }


    public Pouzij(Hrac hrac) {
        this.hrac = hrac;
    }
}
