package Command;

import Postavy.Hrac;
import Predmety.Predmet;

/**
 * Příkaz pro použití předmětu z inventáře hráče.
 * Zpracovává různé typy předmětů podle jejich speciální schopnosti:
 * ničení zdí, šroubování ventilace, pilování mříží a teleportaci.
 * Předměty označené jako nicitelné jsou po použití zničeny.
 *
 * @author Marek
 */
public class Pouzij implements Command{

    private Hrac hrac;

    /**
     * Použije předmět z inventáře hráče.
     * Formát vstupu: {@code <název_předmětu>} nebo {@code <název_předmětu> <směr>}
     * (směr je vyžadován pro předměty pracující se zdmi).
     *
     * @param prikaz Název předmětu a volitelně směr (sever/jih/vychod/zapad)
     * @return Textový popis výsledku použití předmětu
     */
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
                    if (predmet.getNazev().equals("dynamit")) {
                        hrac.setHlucneOdhaleni(true);
                    }
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
                    hrac.moznaZnicit(predmet);
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
                hrac.moznaZnicit(predmet);
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
