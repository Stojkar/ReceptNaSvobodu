package Command;

/**
 * Příkaz pro ukončení hry hráčem.
 * Po provedení tohoto příkazu je hra ukončena.
 *
 * @author Marek
 */
public class Exit implements Command{

    /**
     * Vypíše zprávu o vzdání se a ukončí hru.
     *
     * @param smer Parametr není využit
     * @return Zpráva o vzdání se hráče
     */
    @Override
    public String execute(String smer) {
        return "Vzdal jsi to";
    }

    /**
     * Signalizuje ukončení hry.
     *
     * @return vždy {@code true}, protože tento příkaz hru ukončuje
     */
    @Override
    public boolean exit() {
        return true;
    }
}
