import Command.Command;
import Command.Pohyb;
import Postavy.Hrac;

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
    }

    public void execude(){
        System.out.print(">>> ");
        String prikaz = scanner.next();
        prikaz = prikaz.trim().toLowerCase();

        String smer = scanner.next();
        smer = smer.trim().toLowerCase();



        if(commands.containsKey(prikaz)){
            System.out.println(commands.get(prikaz).execute(smer));
            jeKonec = commands.get(prikaz).exit();

        }else {
            System.out.println("Command not found!");
        }
    }

    public void start(){
        inicializace();
        do{
            execude();
        }while(!jeKonec);
    }





}
