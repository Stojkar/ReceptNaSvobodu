package Command;

import Mapa.Mistnost;
import Mapa.Zed;
import Postavy.Hrac;

import java.util.*;

public class Mapa implements Command {

    private static final String NORMAL = "\u001B[0m";
    private static final String CERVENA = "\u001B[31m";
    private static final String ZELENA = "\u001B[32m";
    private static final String ZLUTA = "\u001B[33m";
    private static final String MODRA = "\u001B[36m";
    private static final String FIALOVA = "\u001B[35m";

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
            pridejSouseda(m.getSeverZed(), m, new int[] { p[0], p[1] - 1 }, souradnice, objekty, fronta);
            pridejSouseda(m.getJizniZed(), m, new int[] { p[0], p[1] + 1 }, souradnice, objekty, fronta);
            pridejSouseda(m.getVychodniZed(), m, new int[] { p[0] + 1, p[1] }, souradnice, objekty, fronta);
            pridejSouseda(m.getZapadniZed(), m, new int[] { p[0] - 1, p[1] }, souradnice, objekty, fronta);
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
        sb.append("\n=== MAPA VEZNICE ===\n\n");

        for (int r = 0; r < vyska; r++) {
            StringBuilder radekMistnosti = new StringBuilder();
            StringBuilder radekSpojeni = new StringBuilder();

            for (int c = 0; c < sirka; c++) {
                Mistnost m = grid[r][c];

                if (m == null) {
                    radekMistnosti.append("            ");
                } else if (m.getId().equals(aktId)) {
                    radekMistnosti.append("[>>>ZDE<<<] ");
                } else {
                    String nazev = m.getNazev();
                    if (nazev.length() > 8)
                        nazev = nazev.substring(0, 8);
                    radekMistnosti.append(String.format("[%-8s] ", nazev));
                }

                if (r < vyska - 1) {
                    Mistnost dolu = grid[r + 1][c];
                    if (m != null && dolu != null) {
                        Zed zed = getWallBetween(m, dolu);
                        radekSpojeni.append("      ").append(formatVZed(zed)).append("     ");
                    } else {
                        radekSpojeni.append("            ");
                    }
                }

                if (c < sirka - 1) {
                    Mistnost prava = grid[r][c + 1];
                    if (m != null && prava != null) {
                        Zed zed = getWallBetween(m, prava);
                        radekMistnosti.append(formatHZed(zed));
                    } else {
                        radekMistnosti.append("   ");
                    }

                    if (r < vyska - 1) {
                        radekSpojeni.append("   ");
                    }
                }
            }

            sb.append(radekMistnosti).append("\n");
            if (r < vyska - 1) {
                sb.append(radekSpojeni).append("\n");
            }
        }

        sb.append("\n");
        sb.append(ZELENA + ">>>" + NORMAL + " vychod  ");
        sb.append(MODRA + "~~~" + NORMAL + " sachta  ");
        sb.append(ZLUTA + "===" + NORMAL + " mrize  ");
        sb.append(FIALOVA + "###" + NORMAL + " dynamit  ");
        sb.append(CERVENA + "xxx" + NORMAL + " neznicitelna  ");
        sb.append("--- normalni dvere\n");

        return sb.toString();
    }

    private Zed getWallBetween(Mistnost a, Mistnost b) {
        for (Zed zed : new Zed[] { a.getSeverZed(), a.getJizniZed(), a.getVychodniZed(), a.getZapadniZed() }) {
            if (zed != null && zed.getDruhouMistnost(a) == b)
                return zed;
        }
        return null;
    }

    private String formatHZed(Zed zed) {
        if (zed == null)
            return "   ";
        if (zed.getKonec() != null)
            return ZELENA + ">>>" + NORMAL;
        if (zed.getspVlastnost() == Zed.SpecialniVlastnost.JE_VENTILACE)
            return MODRA + "~~~" + NORMAL;
        if (zed.getspVlastnost() == Zed.SpecialniVlastnost.JSOU_MRIZE)
            return ZLUTA + "===" + NORMAL;
        if (zed.getSila() >= 20)
            return CERVENA + "xxx" + NORMAL;
        if (zed.getSila() >= 10)
            return FIALOVA + "###" + NORMAL;
        if (zed.isPruchodnost())
            return "---";
        return "...";
    }

    private String formatVZed(Zed zed) {
        if (zed == null)
            return " ";
        if (zed.getKonec() != null)
            return ZELENA + "^" + NORMAL;
        if (zed.getspVlastnost() == Zed.SpecialniVlastnost.JE_VENTILACE)
            return MODRA + "~" + NORMAL;
        if (zed.getspVlastnost() == Zed.SpecialniVlastnost.JSOU_MRIZE)
            return ZLUTA + "=" + NORMAL;
        if (zed.getSila() >= 20)
            return CERVENA + "x" + NORMAL;
        if (zed.getSila() >= 10)
            return FIALOVA + "#" + NORMAL;
        if (zed.isPruchodnost())
            return "|";
        return ".";
    }

    private void pridejSouseda(Zed zed, Mistnost aktualni, int[] noveBod,
                               Map<String, int[]> souradnice,
                               Map<String, Mistnost> objekty,
                               Queue<Mistnost> fronta) {
        if (zed == null)
            return;
        Mistnost soused = zed.getDruhouMistnost(aktualni);
        if (soused != null && soused.isNavstivena() && !souradnice.containsKey(soused.getId())) {
            souradnice.put(soused.getId(), noveBod);
            objekty.put(soused.getId(), soused);
            fronta.add(soused);
        }
    }
}
