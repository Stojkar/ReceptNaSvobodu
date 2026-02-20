import Postavy.Hrac;
import Pribeh.Volba;
import System.DataHry;
import System.ConsoleApp;

/**
 * Hlavní herní třída, která řídí inicializaci a průběh hry.
 * Spravuje hráče, načítání herních dat a životní cyklus konzolové aplikace.
 * Poskytuje dva režimy spuštění: rychlý start bez úvodu a plný start s příběhovým úvodem.
 */
public class Hra {


    private Volba uvod;
    private Hrac hrac;
    private ConsoleApp console;

    /**
     * Rychlý test - načte hru bez příběhového úvodu.
     * Spustí hru přímo v první místnosti.
     */
    public void test(){

        DataHry data = DataHry.loadGameDataFromResources(getClass().getResourceAsStream("data.json"));
        hrac = new Hrac(data.getMistosti().getFirst());
        this.console.start();

    }

    /**
     * Plný start - načte hru s příběhovým úvodem a výběrem předmětů.
     * Zobrazí úvod, umožní hráči vybrat cestu a počáteční předměty.
     */
    public void start(){
        DataHry data = DataHry.loadGameDataFromResources(getClass().getResourceAsStream("data.json"));
        DataHry pribeh = DataHry.loadGameDataFromResources(getClass().getResourceAsStream("pribeh.json"));

        console = new ConsoleApp(null);
        Volba vybranaVolba = console.zobrazUvod(pribeh);

        if(vybranaVolba.getCesta().equals("poctivost")){
            System.out.println("Pekárna zkrachovala a rodina skončila na ulici.");
            System.out.println("\nKONEC HRY");
            return;
        }

        hrac = new Hrac(data.getMistnosti().getFirst());
        hrac.setDataHry(data);
        console = new ConsoleApp(hrac);

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
