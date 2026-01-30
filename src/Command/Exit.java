package Command;

public class Exit implements Command{
    @Override
    public String execute(String smer) {
        return "Spáchal jsi sebevraždu";
    }

    @Override
    public boolean exit() {
        return true;
    }
}
