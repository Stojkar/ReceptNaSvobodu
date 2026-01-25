import Mapa.Svet;
import Postavy.Hrac;
import Pribeh.Uvod;


public class Hra {


    private Uvod uvod;
    private Svet svet;
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
                ", svet=" + svet +
                ", hrac=" + hrac +
                ", console=" + console +
                '}';
    }
}
