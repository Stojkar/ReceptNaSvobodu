package Command;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Příkaz pro zobrazení herní pomoci ze souboru {@code pomoc.txt}.
 * Načítá obsah souboru ze zdrojů (resources) a vrátí ho jako textový řetězec.
 *
 * @author Marek
 */
public class Pomoc implements Command {
    @Override
    public boolean exit() {
        return false;
    }

    /**
     * Načte a vrátí obsah souboru {@code pomoc.txt} ze zdrojů aplikace.
     *
     * @return Obsah souboru s nápovědou, nebo chybová zpráva pokud soubor není
     *         dostupný
     */
    @Override
    public String execute(String neco) {
        StringBuilder pomoc = new StringBuilder();
        InputStream is = getClass().getClassLoader().getResourceAsStream("pomoc.txt");

        if (is == null) {
            return "Nápověda není dostupná.";
        }

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(is))) {
            String line;
            while ((line = reader.readLine()) != null) {
                pomoc.append(line).append("\n");
            }
        } catch (IOException e) {
            return "Chyba při načítání nápovědy.";
        }

        return pomoc.toString();
    }
}
