import Postavy.Hrac;
import Predmety.Predmet;
import Pribeh.Uvod;


public class Hra {


    private Uvod uvod;
    private Hrac hrac;
    private ConsoleApp console;






    public void zacitUvod(){}


    public void zacitHru(){}


    public void test(){

        DataHry data = DataHry.loadGameDataFromResources(getClass().getResourceAsStream("data.json"));
        hrac = new Hrac(data.getMistosti().getFirst(),5);
        ConsoleApp console = new ConsoleApp(hrac);
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
