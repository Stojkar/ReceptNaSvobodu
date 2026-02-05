import Command.Command;
import Command.Pohyb;
import Command.Seber;
import Command.Pomoc;
import Command.Pouzij;
import Command.Inventar;
import Command.Exit;
import Command.Dialog;
import Command.Souboj;
import Postavy.Hrac;
import Predmety.Predmet;
import Pribeh.Volba;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class ConsoleApp {

    private Scanner scanner;
    private HashMap<String, Command> commands;
    private boolean jeKonec;
    private Hrac hrac;



    public ConsoleApp(Hrac hrac) {
        scanner = new Scanner(System.in);
        commands = new HashMap<>();
        jeKonec = false;
        this.hrac = hrac;
    }

    public void inicializace(){
        commands.put("jdi", new Pohyb(hrac));
        commands.put("seber", new Seber(hrac));
        commands.put("pomoc", new Pomoc());
        commands.put("pouzij", new Pouzij(hrac));
        commands.put("inventar", new Inventar(hrac));
        commands.put("konec", new Exit());
        commands.put("mluv", new Dialog(hrac));
        commands.put("zautoc", new Souboj(hrac));
    }

    public void execude() {
        System.out.print(">>> ");
        String prikaz = scanner.nextLine();
        prikaz = prikaz.trim().toLowerCase();
        String[] prikazy = prikaz.split(" ", 2);
        String datPrikazu = "";

        if (prikazy.length == 2) {
            datPrikazu = prikazy[1];
        } else {
            datPrikazu = "nic";
        }

        if (hrac.najdiNepriatelNPC() != null) {
            if (!prikazy[0].equals("zautoc")) {
                System.out.println("Pozor v místnosti je nepřítel " + hrac.najdiNepriatelNPC().getJmeno() + "!");
                System.out.println("Byl jsi poražen, konec hry!");
                jeKonec = true;
                return;
            }
        }

        if (commands.containsKey(prikazy[0])) {
            System.out.println(commands.get(prikazy[0]).execute(datPrikazu));
            jeKonec = commands.get(prikazy[0]).exit();
        } else {
            System.out.println("Prikaz neexistuje!");
        }

    }


    public void rozbalBalicek(int pocetPredmetu, java.util.ArrayList<Predmety.Predmet> vsechnyPredmety){
        if(pocetPredmetu == 0){
            return;
        }

        System.out.println("========================================");
        System.out.println("ROZBALENÍ BALÍČKU");
        System.out.println("Dostals balíček od rodiny!");
        System.out.println("Vyber si " + pocetPredmetu + " předmětů:\n");

        for(int i = 0; i < vsechnyPredmety.size(); i++){
            System.out.println((i+1) + ". " + vsechnyPredmety.get(i).getNazev() + " - " + vsechnyPredmety.get(i).getPopis());
        }

        java.util.ArrayList<Predmety.Predmet> vybranePredmety = new java.util.ArrayList<>();

        while(vybranePredmety.size() < pocetPredmetu){
            System.out.println("\nZadej čísla předmětů oddělené mezerou (zbývá vybrat: " + (pocetPredmetu - vybranePredmety.size()) + "):");
            System.out.print(">>> ");
            String vstup = scanner.nextLine();
            String[] cisla = vstup.split(" ");

            for(String cisloStr : cisla){
                if(vybranePredmety.size() >= pocetPredmetu){
                    break;
                }

                try{
                    int index = Integer.parseInt(cisloStr.trim()) - 1;
                    if(index < 0 || index >= vsechnyPredmety.size()){
                        System.out.println("Číslo " + (index + 1) + " není platné! Vyber mezi 1-" + vsechnyPredmety.size());
                    }else{
                        Predmety.Predmet predmet = vsechnyPredmety.get(index);
                        if(vybranePredmety.contains(predmet)){
                            System.out.println(predmet.getNazev() + " už máš vybraný!");
                        }else{
                            vybranePredmety.add(predmet);
                            hrac.inventarPridat(predmet);
                            System.out.println("Přidán: " + predmet.getNazev() + " (" + vybranePredmety.size() + "/" + pocetPredmetu + ")");
                        }
                    }
                }catch(NumberFormatException e){
                    System.out.println("'" + cisloStr + "' není číslo!");
                }
            }
        }

        System.out.println("\nBalíček rozbalen! Hra začíná...\n");
    }

    public Volba zobrazUvod(DataHry pribeh){
        System.out.println(pribeh.getUvodniText());
        boolean spravnostVolby = true;
        Volba vybranaVolba = null;


        while (spravnostVolby){
            System.out.println("Vyber svou cestu:");
            for(int i = 0; i < pribeh.getVolby().size(); i++){
                System.out.println((i+1) + ". " + pribeh.getVolby().get(i).getCesta());
            }

            System.out.print("\nTvoje volba: ");
            String volba = scanner.nextLine();


            for(Volba v : pribeh.getVolby()){
                if(v.getCesta().equals(volba)){
                    vybranaVolba = v;
                    spravnostVolby = false;
                }
            }
        }

        System.out.println("Obtížnost: " + vybranaVolba.getObtiznost());
        System.out.println("Počet předmětů: " + vybranaVolba.getPocetPredmetu());

        return vybranaVolba;
    }

    public void start(){
        inicializace();
        do{
            execude();
        }while(!jeKonec);
    }





}
