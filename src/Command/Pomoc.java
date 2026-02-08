package Command;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Pomoc implements Command {
    @Override
    public boolean exit() {
        return false;
    }

    @Override
    public String execute(String smer) {
        StringBuilder pomoc = new StringBuilder();

        try (BufferedReader reader = new BufferedReader(
                new FileReader("res/pomoc.txt"))) {

            String line;
            while ((line = reader.readLine()) != null) {
                pomoc.append(line).append("\n");
            }

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return pomoc.toString();
    }
}
