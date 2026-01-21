package Postavy;

import Mapa.Mistnost;

public class NPC {

    public enum AkcePostavy{
        BOJ,
        VYMENA_PREDMETU,
        DAROVANI,
        VYJEDNAVANI
    }


    private String jmeno;
    private String Popis;
    AkcePostavy akce;

    public NPC(String jmeno) {
        this.jmeno = jmeno;
    }
}
