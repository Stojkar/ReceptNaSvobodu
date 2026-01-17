package Postavy;

import Mapa.Mistnost;

public abstract class NPC {

    private String jmeno;
    private Mistnost Mistnost;

    public NPC(String jmeno, Mistnost mistnost) {
        this.jmeno = jmeno;
        Mistnost = mistnost;
    }
}
