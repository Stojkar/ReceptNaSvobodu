package Mapa;

import Postavy.NPC;
import Predmety.Predmet;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import java.util.ArrayList;
import java.util.Random;

@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id"
)

/**
 * Reprezentuje herní místnost ve věznici.
 * Obsahuje předměty, NPC, zdi ve čtyřech směrech (sever, jih, východ, západ).
 * Poskytuje metody pro manipulaci se zdmi (ničení, pilování, šroubování ventilace).
 */
public class Mistnost {

    private String id;
    private String nazev;
    private String popis;
    private ArrayList<Predmet> predmetyMistnosti;
    private ArrayList<NPC> mistnostiNPC;
    private boolean navstivena = false;




    @JsonIdentityReference(alwaysAsId = true)
    private Zed severZed;
    @JsonIdentityReference(alwaysAsId = true)
    private Zed vychodniZed;
    @JsonIdentityReference(alwaysAsId = true)
    private Zed zapadniZed;
    @JsonIdentityReference(alwaysAsId = true)
    private Zed jizniZed;




    public boolean pridejPredmet(Predmet predmet) {
        return predmetyMistnosti.add(predmet);
    }

    public Zed zedPodleSmeru(String smer){
        Zed zed;
        return switch (smer) {
            case "sever" -> {
                zed = severZed;
                yield zed;
            }
            case "vychod" -> {
                zed = vychodniZed;
                yield zed;
            }
            case "zapad" -> {
                zed = zapadniZed;
                yield zed;
            }
            case "jih" -> {
                zed = jizniZed;
                yield zed;
            }
            default -> null;
        };
    }

    public String prohledat() {
        StringBuilder sb = new StringBuilder();
        predmetyMistnosti.forEach(predmet -> {
            sb.append(predmet.getNazev());
        });
        mistnostiNPC.forEach(npc -> {
            sb.append(npc.getJmeno());
        });
        return sb.toString();
    }

    /**
     * Odšroubuje ventilaci v daném směru, pokud existuje.
     * @param smer Směr (sever/jih/vychod/zapad)
     * @return true pokud byla ventilace úspěšně odšroubována, false jinak
     */
    public boolean srouboVentilace(String smer){
        Zed zed = zedPodleSmeru(smer);
        if(zed == null){
            return false;
        }
        if(zed.getspVlastnost() == Zed.SpecialniVlastnost.JE_VENTILACE){
            zed.setPruchodnost(true);
            return true;
        }
        return false;
    }

    /**
     * Přepíluje mříže v daném směru, pokud existují.
     * @param smer Směr (sever/jih/vychod/zapad)
     * @return true pokud byly mříže úspěšně přepílovány, false jinak
     */
    public boolean pilovatMrize(String smer){
        Zed zed = zedPodleSmeru(smer);
        if(zed == null){
            return false;
        }
        if(zed.getspVlastnost() == Zed.SpecialniVlastnost.JSOU_MRIZE){
            zed.setPruchodnost(true);
            return true;
        }
        return false;
    }

    /**
     * Zničí zeď v daném směru, pokud je síla útoku dostačující.
     * @param sila Síla předmětu použitého k ničení
     * @param smer Směr (sever/jih/vychod/zapad)
     * @return true pokud byla zeď zničena, false pokud je příliš silná nebo neexistuje
     */
    public boolean znicZed(int sila, String smer){
        Zed zed = zedPodleSmeru(smer);
        if(zed == null){
            return false;
        }
        if(zed.getSila() <= sila){
            zed.setSila(0);
            zed.setPruchodnost(true);
            return true;
        }
        return false;
    }

    public boolean odberPredmet(String nazPredmet) {
        for (Predmet predmet : predmetyMistnosti) {
            if (predmet.getNazev().equals(nazPredmet)) {
                predmetyMistnosti.remove(predmet);
                return true;
            }
        }
        return false;
    }

    public boolean odebratNPC(NPC npc) {
        return mistnostiNPC.removeIf(postava -> postava == npc);
    }


    public Mistnost(String id, String nazev, String popis, Zed severZed, Zed vychodniZed, Zed zapaadniZed, Zed jizniZed) {
        this.id = id;
        this.nazev = nazev;
        this.popis = popis;
        this.predmetyMistnosti = new ArrayList<>();
        this.mistnostiNPC = new ArrayList<>();
        this.severZed = severZed;
        this.vychodniZed = vychodniZed;
        this.zapadniZed = zapaadniZed;
        this.jizniZed = jizniZed;
    }

    @Override
    public String toString() {
        StringBuilder output = new StringBuilder();
        output.append(popis);

        for(NPC npc : mistnostiNPC){
            if(npc.isBoj()){
                output.append("\nPozor! V místnosti je nepřítel ").append(npc.getJmeno()).append("!");
                output.append("\nPřiprav se na boj!");
                return output.toString(); // Při boji neukázat ostatní věci
            }
        }

        if(predmetyMistnosti != null && !predmetyMistnosti.isEmpty()){
            output.append("\n V místnosti vidíš:");
            for(Predmet predmet : predmetyMistnosti){
                output.append("\n  • ").append(predmet.getNazev());
            }
        }

        if(mistnostiNPC != null && !mistnostiNPC.isEmpty()){
            boolean hasNonEnemy = false;
            StringBuilder npcList = new StringBuilder();

            for(NPC npc : mistnostiNPC){
                if(!npc.isBoj()){
                    if(!hasNonEnemy){
                        npcList.append("\n V místnosti jsou:");
                        hasNonEnemy = true;
                    }
                    npcList.append("\n  • ").append(npc.getJmeno());
                }
            }

            if(hasNonEnemy){
                output.append(npcList);
            }
        }

        return output.toString();
    }

    public Mistnost() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNazev() {
        return nazev;
    }

    public void setNazev(String nazev) {
        this.nazev = nazev;
    }

    public String getPopis() {
        return popis;
    }

    public void setPopis(String popis) {
        this.popis = popis;
    }

    public ArrayList<Predmet> getPredmetyMistnosti() {
        return predmetyMistnosti;
    }

    public void setPredmetyMistnosti(ArrayList<Predmet> predmetyMistnosti) {
        this.predmetyMistnosti = predmetyMistnosti;
    }

    public ArrayList<NPC> getMistnostiNPC() {
        return mistnostiNPC;
    }

    public void setMistnostiNPC(ArrayList<NPC> mistnostiNPC) {
        this.mistnostiNPC = mistnostiNPC;
    }

    public Zed getSeverZed() {
        return severZed;
    }

    public void setSeverZed(Zed severZed) {
        this.severZed = severZed;
    }

    public Zed getVychodniZed() {
        return vychodniZed;
    }

    public void setVychodniZed(Zed vychodniZed) {
        this.vychodniZed = vychodniZed;
    }

    public Zed getZapadniZed() {
        return zapadniZed;
    }

    public void setZapadniZed(Zed zapadniZed) {
        this.zapadniZed = zapadniZed;
    }

    public Zed getJizniZed() {
        return jizniZed;
    }

    public void setJizniZed(Zed jizniZed) {
        this.jizniZed = jizniZed;
    }

    public boolean isNavstivena() {
        return navstivena;
    }

    public void setNavstivena(boolean navstivena) {
        this.navstivena = navstivena;
    }
}
