package Command;

import Mapa.Mistnost;
import Mapa.Zed;
import Postavy.Hrac;

import java.util.*;

public class Mapa implements Command {

    private static final String RESET = "\u001B[0m";
    private static final String RED = "\u001B[31m";
    private static final String GREEN = "\u001B[32m";
    private static final String YELLOW = "\u001B[33m";
    private static final String CYAN = "\u001B[36m";
    private static final String PURPLE = "\u001B[35m";
    private static final String BOLD = "\u001B[1m";

    private final Hrac hrac;

    public Mapa(Hrac hrac) {
        this.hrac = hrac;
    }

    @Override
    public boolean exit() {
        return false;
    }

    @Override
    public String execute(String smer) {
        boolean planyBudovy = hrac.isMapaBitelostiZdi(); // vidi celu mapu
        boolean planyVentilaci = hrac.isMapaVentilaci(); // vidi sachy

        Map<String, int[]> souradnice = new LinkedHashMap<>();
        Map<String, Mistnost> objekty = new HashMap<>();
        Queue<Mistnost> fronta = new LinkedList<>();

        Mistnost start = hrac.getAktMistnost();
        souradnice.put(start.getId(), new int[] { 0, 0 });
        objekty.put(start.getId(), start);
        fronta.add(start);

        while (!fronta.isEmpty()) {
            Mistnost m = fronta.poll();
            int[] p = souradnice.get(m.getId());
            pridejSouseda(m.getSeverZed(), m, new int[] { p[0], p[1] - 1 }, souradnice, objekty, fronta,
                    planyBudovy || planyVentilaci);
            pridejSouseda(m.getJizniZed(), m, new int[] { p[0], p[1] + 1 }, souradnice, objekty, fronta,
                    planyBudovy || planyVentilaci);
            pridejSouseda(m.getVychodniZed(), m, new int[] { p[0] + 1, p[1] }, souradnice, objekty, fronta,
                    planyBudovy || planyVentilaci);
            pridejSouseda(m.getZapadniZed(), m, new int[] { p[0] - 1, p[1] }, souradnice, objekty, fronta,
                    planyBudovy || planyVentilaci);
        }

        int minX = 0, maxX = 0, minY = 0, maxY = 0;
        for (int[] p : souradnice.values()) {
            minX = Math.min(minX, p[0]);
            maxX = Math.max(maxX, p[0]);
            minY = Math.min(minY, p[1]);
            maxY = Math.max(maxY, p[1]);
        }

        int sirka = maxX - minX + 1;
        int vyska = maxY - minY + 1;

        Mistnost[][] grid = new Mistnost[vyska][sirka];
        for (Map.Entry<String, int[]> e : souradnice.entrySet()) {
            grid[e.getValue()[1] - minY][e.getValue()[0] - minX] = objekty.get(e.getKey());
        }

        String aktId = start.getId();
        StringBuilder sb = new StringBuilder();

        sb.append("\n").append(BOLD).append("=== MAPA VEZNICE ===").append(RESET);
        if (planyBudovy)
            sb.append("  ").append(YELLOW).append("[PLANKY BUDOVY]").append(RESET);
        if (planyVentilaci)
            sb.append("  ").append(CYAN).append("[MAPA SACHET]").append(RESET);
        sb.append("\n\n");

        for (int r = 0; r < vyska; r++) {
            StringBuilder radekMistnosti = new StringBuilder();
            StringBuilder radekSpojeni = new StringBuilder();

            for (int c = 0; c < sirka; c++) {
                Mistnost m = grid[r][c];

                if (m == null) {
                    radekMistnosti.append("            ");
                } else if (m.getId().equals(aktId)) {
                    radekMistnosti.append(BOLD + "[>>>ZDE<<<]" + RESET + " ");
                } else if (!planyBudovy && !planyVentilaci && !m.isNavstivena()) {
                    // Fog of war - nenavstivena
                    radekMistnosti.append("            ");
                } else if (planyVentilaci && !planyBudovy) {
                    // Rezim sachet - ukazuje vse ale jen sachty maji nazev
                    boolean maSachtu = majeSachtu(m);
                    if (maSachtu) {
                        radekMistnosti.append(CYAN + "[~ SACHTA ~]" + RESET + " ");
                    } else {
                        String nazev = m.getNazev();
                        if (nazev.length() > 8)
                            nazev = nazev.substring(0, 8);
                        radekMistnosti.append(String.format("[%-8s] ", nazev));
                    }
                } else {
                    String nazev = m.getNazev();
                    if (nazev.length() > 8)
                        nazev = nazev.substring(0, 8);
                    radekMistnosti.append(String.format("[%-8s] ", nazev));
                }

                if (r < vyska - 1) {
                    Mistnost dolu = grid[r + 1][c];
                    if (m != null && dolu != null) {
                        boolean zobrazit = planyBudovy || planyVentilaci || (m.isNavstivena() && dolu.isNavstivena());
                        if (zobrazit) {
                            Zed zed = getWallBetween(m, dolu);
                            boolean jenVentilace = planyVentilaci && !planyBudovy;
                            radekSpojeni.append("      ").append(formatVWall(zed, jenVentilace)).append("     ");
                        } else {
                            radekSpojeni.append("            ");
                        }
                    } else {
                        radekSpojeni.append("            ");
                    }
                }

                if (c < sirka - 1) {
                    Mistnost prava = grid[r][c + 1];
                    if (m != null && prava != null) {
                        boolean zobrazit = planyBudovy || planyVentilaci || (m.isNavstivena() && prava.isNavstivena());
                        if (zobrazit) {
                            Zed zed = getWallBetween(m, prava);
                            boolean jenVentilace = planyVentilaci && !planyBudovy;
                            radekMistnosti.append(formatHWall(zed, jenVentilace));
                        } else {
                            radekMistnosti.append("   ");
                        }
                    } else {
                        radekMistnosti.append("   ");
                    }

                    if (r < vyska - 1)
                        radekSpojeni.append("   ");
                }
            }

            sb.append(radekMistnosti).append("\n");
            if (r < vyska - 1)
                sb.append(radekSpojeni).append("\n");
        }

        sb.append("\n");
        sb.append(GREEN + ">>>" + RESET + " vychod  ");
        sb.append(CYAN + "~~~" + RESET + " sachta  ");
        sb.append(YELLOW + "===" + RESET + " mrize  ");
        sb.append(PURPLE + "###" + RESET + " dynamit  ");
        sb.append(RED + "xxx" + RESET + " neznicitelna  ");
        sb.append("--- normalni dvere\n");

        return sb.toString();
    }

    private boolean majeSachtu(Mistnost m) {
        for (Zed zed : new Zed[] { m.getSeverZed(), m.getJizniZed(), m.getVychodniZed(), m.getZapadniZed() }) {
            if (zed != null && zed.getspVlastnost() == Zed.SpecialniVlastnost.JE_VENTILACE)
                return true;
        }
        return false;
    }

    private Zed getWallBetween(Mistnost a, Mistnost b) {
        for (Zed zed : new Zed[] { a.getSeverZed(), a.getJizniZed(), a.getVychodniZed(), a.getZapadniZed() }) {
            if (zed != null && zed.getDruhouMistnost(a) == b)
                return zed;
        }
        return null;
    }

    private String formatHWall(Zed zed, boolean jenVentilace) {
        if (zed == null)
            return "   ";
        if (jenVentilace) {
            if (zed.getspVlastnost() == Zed.SpecialniVlastnost.JE_VENTILACE)
                return CYAN + "~~~" + RESET;
            return "---";
        }
        if (zed.getKonec() != null)
            return GREEN + ">>>" + RESET;
        if (zed.getspVlastnost() == Zed.SpecialniVlastnost.JE_VENTILACE)
            return CYAN + "~~~" + RESET;
        if (zed.getspVlastnost() == Zed.SpecialniVlastnost.JSOU_MRIZE)
            return YELLOW + "===" + RESET;
        if (zed.getSila() >= 20)
            return RED + "xxx" + RESET;
        if (zed.getSila() >= 10)
            return PURPLE + "###" + RESET;
        if (zed.isPruchodnost())
            return "---";
        return "...";
    }

    private String formatVWall(Zed zed, boolean jenVentilace) {
        if (zed == null)
            return " ";
        if (jenVentilace) {
            if (zed.getspVlastnost() == Zed.SpecialniVlastnost.JE_VENTILACE)
                return CYAN + "~" + RESET;
            return "|";
        }
        if (zed.getKonec() != null)
            return GREEN + "^" + RESET;
        if (zed.getspVlastnost() == Zed.SpecialniVlastnost.JE_VENTILACE)
            return CYAN + "~" + RESET;
        if (zed.getspVlastnost() == Zed.SpecialniVlastnost.JSOU_MRIZE)
            return YELLOW + "=" + RESET;
        if (zed.getSila() >= 20)
            return RED + "x" + RESET;
        if (zed.getSila() >= 10)
            return PURPLE + "#" + RESET;
        if (zed.isPruchodnost())
            return "|";
        return ".";
    }

    private void pridejSouseda(Zed zed, Mistnost aktualni, int[] noveBod,
                               Map<String, int[]> souradnice,
                               Map<String, Mistnost> objekty,
                               Queue<Mistnost> fronta,
                               boolean vsechnyMistnosti) {
        if (zed == null)
            return;
        Mistnost soused = zed.getDruhouMistnost(aktualni);
        if (soused == null)
            return;
        if (!souradnice.containsKey(soused.getId())) {
            if (vsechnyMistnosti || soused.isNavstivena()) {
                souradnice.put(soused.getId(), noveBod);
                objekty.put(soused.getId(), soused);
                fronta.add(soused);
            }
        }
    }
}