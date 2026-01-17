package Postavy;

import Mapa.Mistnost;
import Predmety.Predmet;

public class NepratelskeNPC extends NPC {

        private int sila;

    public NepratelskeNPC(String jmeno, Mistnost mistnost, int sila) {
        super(jmeno, mistnost);
        this.sila = sila;
    }


}
