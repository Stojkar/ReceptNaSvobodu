import Postavy.Hrac;
import Pribeh.Volba;


public class Hra {


    private Volba uvod;
    private Hrac hrac;
    private ConsoleApp console;






    public void zacitUvod(){





    }


    public void zacitHru(){}


    public void test(){

        DataHry data = DataHry.loadGameDataFromResources(getClass().getResourceAsStream("data.json"));
        hrac = new Hrac(data.getMistosti().getFirst(),5);
        ConsoleApp console = new ConsoleApp(hrac);
        console.start();

    }

    public void testSUvodem(){
        DataHry data = DataHry.loadGameDataFromResources(getClass().getResourceAsStream("data.json"));
        DataHry pribeh = DataHry.loadGameDataFromResources(getClass().getResourceAsStream("pribeh.json"));

        console = new ConsoleApp(null);
        Volba vybranaVolba = console.zobrazUvod(pribeh);

        if(vybranaVolba.getCesta().equals("poctivost")){
            System.out.println("Pekárna zkrachovala a rodina skončila na ulici.");
            System.out.println("\nKONEC HRY");
            return;
        }

        hrac = new Hrac(data.getMistnosti().getFirst(), vybranaVolba.getPocetPredmetu());
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
