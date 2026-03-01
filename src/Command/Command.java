package Command;

/**
 * Rozhraní pro herní příkazy.
 * Každý příkaz musí implementovat metodu {@link #execute(String)} pro provedení
 * akce
 * a metodu {@link #exit()} pro signalizaci ukončení hry.
 *
 * @author Marek
 */
public interface Command {

    /**
     * Vykoná příkaz s daným parametrem.
     *
     * @param prikaz Parametr předaný příkazu (např. směr pohybu, název předmětu)
     * @return Textový výsledek provedení příkazu zobrazený hráči
     */
    String execute(String prikaz);

    /**
     * Zjistí, zda má tento příkaz ukončit hru.
     *
     * @return {@code true} pokud má hra skončit po provedení tohoto příkazu, jinak
     *         {@code false}
     */
    boolean exit();

}
