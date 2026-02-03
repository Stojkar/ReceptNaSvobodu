import Command.Command;
import Command.Pohyb;
import Command.Seber;
import Command.Pomoc;
import Command.Pouzij;
import Command.Inventar;
import Command.Exit;
import Postavy.Hrac;
import Postavy.NPC;

import java.util.HashMap;
import java.util.Scanner;

public class ConsoleApp {

    private Scanner scanner;
    private HashMap<String, Command> commands;
    private boolean jeKonec;
    private Hrac hrac;
    private boolean jeVBoji = false;



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

        if (hrac.getNepriatelNPC() != null) {
            if (!jeVBoji) {
                jeVBoji = true;
                System.out.println("Pozor v místnosti je nepřítel " + hrac.getNepriatelNPC());
            } else {
                if (!prikazy[0].equals("zautoc")) {
                    System.out.println("Byl jsi poražen a zbaven všech předmětů, které tě mohly dostat pryč!");                    jeKonec = true;
                }
            }


            if (commands.containsKey(prikazy[0])) {
                System.out.println(commands.get(prikazy[0]).execute(datPrikazu));
                jeKonec = commands.get(prikazy[0]).exit();

            } else {
                System.out.println("Prikaz neexistuje!");
            }
        }
    }


    public void start(){
        inicializace();
        do{
            execude();
        }while(!jeKonec);
    }





}
