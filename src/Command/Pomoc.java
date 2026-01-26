package Command;

public class Pomoc implements Command {
    @Override
    public boolean exit() {
        return false;
    }

    @Override
    public String execute(String smer) {
        return "Prikazy ktere muzes provast:" +
                "jdi smer";
    }
}
