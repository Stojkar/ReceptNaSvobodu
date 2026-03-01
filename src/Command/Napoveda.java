package Command;

/**
 * Příkaz pro zobrazení nápovědy o průběhu hry.
 * Informuje hráče o základní herní filozofii a přístupu k hraní.
 *
 * @author Marek
 */
public class Napoveda implements Command {

    /**
     * Vrátí nápovědu popisující způsob hraní.
     *
     * @param smer Parametr není využit
     * @return Textová nápověda o hře
     */
    @Override
    public String execute(String smer) {
        return "Tato hra je delana na zaklade opakovenem hraci ziskavani infomaci\nO hre prostredi predmetu a nepratel nez se ti to konecne povede";
    }

    @Override
    public boolean exit() {
        return false;
    }
}
