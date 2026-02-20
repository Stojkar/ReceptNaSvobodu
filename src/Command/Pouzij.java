package Command;

import Postavy.Hrac;
import Predmety.Predmet;

public class Pouzij implements Command{

    private Hrac hrac;


    @Override
    public String execute(String prikaz) {
        String[] prikazy = prikaz.split(" ",2);

        Predmet predmet =  hrac.najdiPredmetInventar(prikazy[0]);

        if(predmet == null){
            return "Nemáš žádný předmět " + prikazy[0];
        }


        switch(predmet.getSchopnost()){
            case MUZE_BOJOVAT:
                return "Nic to neudělalo, šetři tento předmět do boje";
            case MUZE_NICIT_ZDI:
                if(prikazy.length < 2){
                    return "Kam chceš použít " + predmet.getNazev() + "? (sever/jih/vychod/zapad)";
                }
                if(hrac.getAktMistnost().znicZed(predmet.getSila(),prikazy[1])){
                    if(hrac.moznaZnicit(predmet)){
                        return "Zničil jsi zeď, ale zničil si předmět";
                    }
                    return "Zničil jsi zeď, cesta je průchodná";
                }
                return "zeď je moc silná na tento předmět";
            case MUZE_SROUBOVAT:
                if(prikazy.length < 2){
                    return "Kam chceš použít " + predmet.getNazev() + "? (sever/jih/vychod/zapad)";
                }
                if(hrac.getAktMistnost().srouboVentilace(prikazy[1])){
                    if(hrac.moznaZnicit(predmet)){
                        return "Ventilace je odšroubovaná, ale zničil jsi předmět";
                    }
                    return "Ventilace je odšroubovaná a máš volnou cestu";
                }
                return "Ve zdi není ventilace";
            case MUZE_PILOVAT:
                if(prikazy.length < 2){
                    return "Kam chceš použít " + predmet.getNazev() + "? (sever/jih/vychod/zapad)";
                }
                if(hrac.getAktMistnost().pilovatMrize(prikazy[1])){
                    Predmet tyc = hrac.getDataHry().getZeleznaTyc();
                    if(tyc != null){
                        if(hrac.inventarPridat(tyc)){
                            return "Povedlo se ti přepilovat mříže a v ruce ti zbyla tyč";
                        }else{
                            hrac.getAktMistnost().pridejPredmet(tyc);
                            return "Povedlo se ti přepilovat mříže ale nemáš dostatek místa na tyč, takže je pohozena v místnosti";
                        }
                    }
                }
                return "Ve zdi nejsou mříže";
            case MUZE_TELEPORTOVAT:
                hrac.setAktMistnost(hrac.getDataHry().nahodnaMistnost());
                return "Teleportoval ses do " + hrac.getAktMistnost().toString();
            default:
                return "předmět nejde použít v tomto případě";
        }
    }

    @Override
    public boolean exit() {
        return false;
    }


    public Pouzij(Hrac hrac) {
        this.hrac = hrac;
    }
}
