package Mapa;

import Postavy.NPC;
import Predmety.Predmet;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import java.util.ArrayList;

@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id"
)
public class Mistnost {

    private String id;
    private String nazev;
    private String popis;
    private ArrayList<Predmet> predmetyMistnosti;
    private ArrayList<NPC> mistnostiNPC;


    @JsonIdentityReference(alwaysAsId = true)
    private Zed severZed;
    @JsonIdentityReference(alwaysAsId = true)
    private Zed vychodniZed;
    @JsonIdentityReference(alwaysAsId = true)
    private Zed zapadniZed;
    @JsonIdentityReference(alwaysAsId = true)
    private Zed jizniZed;


    public String popisMistnosti() {
        return null;
    }

    public Zed poskytnutiZdi(String starna) {
        return null;
    }

    public boolean pridejPredmet(Predmet predmet) {
        return false;
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

    public boolean znicZed(int sila, String smer){
        Zed zed = zedPodleSmeru(smer);
        if(zed == null){
            System.out.println("zed je null");
            return false;
        }
        if(zed.getSila() <= sila){
            zed.setSila(0);
            zed.setPruchodnost(true);
            System.out.println("zed neni sila");
            return true;
        }
        System.out.println("zed je sila");
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
        return "Mistnost{" +
                "id='" + id + '\'' +
                ", nazev='" + nazev + '\'' +
                ", popis='" + popis + '\'' +
                ", predmetyMistnosti=" + predmetyMistnosti +
                ", mistnostiNPC=" + mistnostiNPC +
                ", severZed=" + severZed +
                ", vychodniZed=" + vychodniZed +
                ", zapadniZed=" + zapadniZed +
                ", jizniZed=" + jizniZed +
                '}';
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
}
