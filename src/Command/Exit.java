package Command;

public class Exit implements Command{
    @Override
    public String execute(String smer) {
        return "Vzdal jsi to";
    }

    @Override
    public boolean exit() {
        return true;
    }
}
