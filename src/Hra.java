import Postavy.Hrac;
import Pribeh.Volba;
import System.DataHry;
import System.ConsoleApp;

/**
 * Hlavní herní třída, která řídí inicializaci a průběh hry.
 * Spravuje hráče, načítání herních dat a životní cyklus konzolové aplikace.
 * Poskytuje dva režimy spuštění: rychlý start bez úvodu a plný start s
 * příběhovým úvodem.
 *
 * @author Marek
 */
public class Hra {


    private Volba uvod;
    private Hrac hrac;
    private ConsoleApp console;

    /**
     * Plný start – načte hru s příběhovým úvodem a výběrem předmětů.
     * Zobrazí úvod, umožní hráči vybrat cestu a počáteční předměty.
     * Pokud hráč zvolí obtížnost 0, hra se ukončí bez spuštění.
     */
    public void start(){
        DataHry data = DataHry.loadGameDataFromResources(getClass().getResourceAsStream("data.json"));
        DataHry pribeh = DataHry.loadGameDataFromResources(getClass().getResourceAsStream("pribeh.json"));

        hrac = new Hrac(data.getMistnosti().getFirst());
        hrac.setDataHry(data);
        console = new ConsoleApp(hrac,data);
        Volba vybranaVolba = console.zobrazUvod(pribeh);

        System.out.println(vybranaVolba.getPopis());
        if(vybranaVolba.getObtiznost() == 0){

            System.out.println("Pekárna zkrachovala a rodina skončila na ulici.");
            System.out.println("\nKONEC HRY");
            return;
        }
        console.rozbalBalicek(vybranaVolba.getPocetPredmetu(), data.getPredmety());

        System.out.println(hrac.getAktMistnost());
        console.start();
    }


    public Hrac getHrac() {
        return hrac;
    }

    @Override
    public String toString() {
        return "Hra{" +
                "uvod=" + uvod +
                ", hrac=" + hrac +
                ", console=" + console +
                '}';
    }
}
