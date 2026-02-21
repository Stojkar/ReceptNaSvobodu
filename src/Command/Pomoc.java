package Command;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class Pomoc implements Command {
    @Override
    public boolean exit() {
        return false;
    }

    @Override
    public String execute(String smer) {
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
