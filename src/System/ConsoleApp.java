package System;

import Command.Command;
import Command.Pohyb;
import Command.Seber;
import Command.Pomoc;
import Command.Pouzij;
import Command.Inventar;
import Command.Exit;
import Command.Dialog;
import Command.Souboj;
import Command.Mapa;
import Command.Zahod;
import Command.Prohledat;
import Command.Napoveda;
import Command.Podej;
import Postavy.Hrac;
import Postavy.NPC;
import Pribeh.Volba;

import java.util.HashMap;
import java.util.Scanner;

/**
 * Konzolová aplikace, která řídí hlavní herní smyčku.
 * Zpracovává uživatelské vstupy, vykonává příkazy a zobrazuje výstupy.
 * Obsahuje logiku pro registraci příkazů, zpracování alarmu a rozbalení
 * balíčku na začátku hry.
 *
 * @author Marek
 */
public class ConsoleApp {

    private Scanner scanner;
    private HashMap<String, Command> commands;
    private boolean jeKonec;
    private Hrac hrac;
    private DataHry dataHry;

    public ConsoleApp(Hrac hrac, DataHry dataHry) {
        scanner = new Scanner(System.in);
        commands = new HashMap<>();
        jeKonec = false;
        this.hrac = hrac;
        this.dataHry = dataHry;
    }

    /**
     * Inicializuje a registruje všechny herní příkazy.
     * Voláno při spuštění hry.
     */
    public void inicializace() {
        commands.put("jdi", new Pohyb(hrac));
        commands.put("seber", new Seber(hrac));
        commands.put("pomoc", new Pomoc());
        commands.put("pouzij", new Pouzij(hrac));
        commands.put("inventar", new Inventar(hrac));
        commands.put("konec", new Exit());
        commands.put("mluv", new Dialog(hrac));
        commands.put("zautoc", new Souboj(hrac));
        commands.put("zahod", new Zahod(hrac));
        commands.put("rozhledni", new Prohledat(hrac));
        commands.put("napoveda", new Napoveda());
        commands.put("mapa", new Mapa(hrac));
        commands.put("podej", new Podej(hrac));
    }

    /**
     * Načte a vykoná jeden příkaz od hráče ze standardního vstupu.
     * Pokud je v místnosti nepřítel a hráč nezaútočí a nemá rukojmího, hra skončí.
     */
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

        if (hrac.najdiNepriatelNPC() != null && !hrac.isMaRukojmi()) {
            if (!prikazy[0].equals("zautoc")) {
                System.out.println(hrac.najdiNepriatelNPC().getJmeno() + " je silnější!");
                System.out.println("Byl jsi poražen, konec hry!");
                jeKonec = true;
                return;
            }
        }

        Command command = commands.get(prikazy[0]);
        if (command == null) {
            System.out.println("Neznámý příkaz");
        } else {
            System.out.println(command.execute(datPrikazu));
            jeKonec = command.exit();

            zpracovatHlucneOdhaleni();
        }

    }

    /**
     * Zpracovává alarmový systém pro hlasité předměty (dynamit, pistole).
     * Po třech příkazech po odhalení vytvoří v místnosti ozbrojenou ochranu.
     */
    private void zpracovatHlucneOdhaleni() {
        if (!hrac.isHlucneOdhaleni()) {
            return;
        }

        hrac.zvysitPocetPrikazuPoOdhaleni();
        int zbyva = 3 - hrac.getPocetPrikazuPoOdhaleni();

        if (zbyva > 0) {
            System.out.println("\n Bachaři tě hledají! Zbývají " + zbyva + " příkazy...\n");
        }

        if (hrac.getPocetPrikazuPoOdhaleni() >= 3) {
            stvoreniOzbrojeneOchranky();
            System.out.println("\n PŘIBĚHLA OZBROJENÁ OCHRANKA! Musíš bojovat!\n");
            hrac.setPocetPrikazuPoOdhaleni(0);
            hrac.setHlucneOdhaleni(false);
        }
    }

    /**
     * Přidá ozbrojenou ochranu do aktuální místnosti hráče.
     * Voláno po uplynutí alarmového odpočtu.
     */
    private void stvoreniOzbrojeneOchranky() {
        DataHry data = hrac.getDataHry();
        if (data == null || data.getOzbrojenaOchranka() == null) {
            return;
        }

        NPC OzbrojenaOchranka = data.getOzbrojenaOchranka();
        hrac.getAktMistnost().getMistnostiNPC().add(OzbrojenaOchranka);
    }

    /**
     * Interaktivně nechá hráče vybrat počáteční předměty z balíčku rodiny.
     * Hráč vybírá čísla předmětů ze seznamu, dokud nevybere požadovaný počet.
     *
     * @param pocetPredmetu   Počet předmětů, které hráč musí vybrat
     * @param vsechnyPredmety Seznam všech dostupných předmětů k výběru
     */
    public void rozbalBalicek(int pocetPredmetu, java.util.ArrayList<Predmety.Predmet> vsechnyPredmety) {
        if (pocetPredmetu == 0) {
            return;
        }

        System.out.println("========================================");
        System.out.println("ROZBALENÍ BALÍČKU");
        System.out.println("Dostals balíček od rodiny!");
        System.out.println("Vyber si " + pocetPredmetu + " předmětů:\n");

        for (int i = 0; i < vsechnyPredmety.size(); i++) {
            System.out.println(
                    (i + 1) + ". " + vsechnyPredmety.get(i).getNazev() + " - " + vsechnyPredmety.get(i).getPopis());
        }

        java.util.ArrayList<Predmety.Predmet> vybranePredmety = new java.util.ArrayList<>();

        while (vybranePredmety.size() < pocetPredmetu) {
            System.out.println("\nZadej čísla předmětů oddělené mezerou (zbývá vybrat: "
                    + (pocetPredmetu - vybranePredmety.size()) + "):");
            System.out.print(">>> ");
            String vstup = scanner.nextLine();
            String[] cisla = vstup.split(" ");

            for (String cisloStr : cisla) {
                if (vybranePredmety.size() >= pocetPredmetu) {
                    break;
                }

                try {
                    int index = Integer.parseInt(cisloStr.trim()) - 1;
                    if (index < 0 || index >= vsechnyPredmety.size()) {
                        System.out.println(
                                "Číslo " + (index + 1) + " není platné! Vyber mezi 1-" + vsechnyPredmety.size());
                    } else {
                        Predmety.Predmet predmet = vsechnyPredmety.get(index);
                        if (vybranePredmety.contains(predmet)) {
                            System.out.println(predmet.getNazev() + " už máš vybraný!");
                        } else {
                            vybranePredmety.add(predmet);
                            hrac.inventarPridat(predmet);
                            System.out.println("Přidán: " + predmet.getNazev() + " (" + vybranePredmety.size() + "/"
                                    + pocetPredmetu + ")");
                        }
                    }
                } catch (NumberFormatException e) {
                    System.out.println("'" + cisloStr + "' není číslo!");
                }
            }
        }

        System.out.println("\nBalíček rozbalen! Hra začíná...\n");
    }

    /**
     * Zobrazí příběhový úvod a nechá hráče zvolit svou cestu.
     * Opakuje výzvu dokud hráč nezadá platnou volbu.
     *
     * @param pribeh Data příběhu obsahující úvodní text a seznam voleb
     * @return Vybraná volba hráče
     */
    public Volba zobrazUvod(DataHry pribeh) {
        System.out.println(pribeh.getUvodniText());
        boolean spravnostVolby = true;
        Volba vybranaVolba = null;

        while (spravnostVolby) {
            System.out.println("Vyber svou cestu:");
            for (int i = 0; i < pribeh.getVolby().size(); i++) {
                System.out.println((i + 1) + ". " + pribeh.getVolby().get(i).getCesta());
            }

            System.out.print("\nTvoje volba: ");
            String volba = scanner.nextLine();

            for (Volba v : pribeh.getVolby()) {
                if (v.getCesta().equals(volba)) {
                    vybranaVolba = v;
                    spravnostVolby = false;
                }
            }
        }

        System.out.println("Obtížnost: " + vybranaVolba.getObtiznost());
        System.out.println("Počet předmětů: " + vybranaVolba.getPocetPredmetu());

        return vybranaVolba;
    }

    /**
     * Spustí hlavní herní smyčku.
     * Označí počáteční místnost jako navštívenou, inicializuje příkazy
     * a opakovaně volá {@link #execude()} dokud hra neskončí.
     */
    public void start() {
        if (hrac != null) {
            hrac.getAktMistnost().setNavstivena(true);
        }
        inicializace();
        do {
            execude();
        } while (!jeKonec);
    }

}
