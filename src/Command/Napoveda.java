package Command;

public class Napoveda implements Command {
    @Override
    public String execute(String smer) {
        return "Tato hra je delana na zaklade opakovenem hraci ziskavani infomaci\nO hre prostredi predmetu a nepratel nez se ti to konecne povede";
    }

    @Override
    public boolean exit() {
        return false;
    }
}
