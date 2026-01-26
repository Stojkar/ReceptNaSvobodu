package Mapa;

import Pribeh.Konec;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id"
)
public class Zed {


    public enum SpecialniVlastnost {
        JE_VENTILACE,
        JSOU_MRIZE
    }

    private String id;
    private boolean pruchodnost;
    @JsonIdentityReference(alwaysAsId = true)
    private Mistnost mistnost1;
    @JsonIdentityReference(alwaysAsId = true)
    private Mistnost mistnost2;
    private Konec konec;
    private int sila;
    private SpecialniVlastnost spVlastnost;
    private String popis;


    public Zed() {
    }

    public void znicitZed(){

    }


    public Mistnost getDruhouMistnost(Mistnost mistnostKdeStoji){
        if(mistnostKdeStoji.equals(mistnost1)){
            return mistnost2;
        }else{
            return mistnost1;
        }
    }



    public Mistnost vypocetPruchodu(){

        //podle pruchodnosti
        //pokud za zdi bude konec spusti to konec
        //vrati mistost kam se hrac presune

        return null;
    }


    @Override
    public String toString() {
        return "Zed{" +
                "nezev='" + id + '\'' +
                ", pruchodnost=" + pruchodnost +
                ", konec=" + konec +
                ", sila=" + sila +
                ", Vlastnost=" + spVlastnost +
                ", popis='" + popis + '\'' +
                '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public boolean isPruchodnost() {
        return pruchodnost;
    }

    public void setPruchodnost(boolean pruchodnost) {
        this.pruchodnost = pruchodnost;
    }

    public Mistnost getMistnost1() {
        return mistnost1;
    }

    public void setMistnost1(Mistnost mistnost1) {
        this.mistnost1 = mistnost1;
    }

    public Mistnost getMistnost2() {
        return mistnost2;
    }

    public void setMistnost2(Mistnost mistnost2) {
        this.mistnost2 = mistnost2;
    }

    public Konec getKonec() {
        return konec;
    }

    public void setKonec(Konec konec) {
        this.konec = konec;
    }

    public int getSila() {
        return sila;
    }

    public void setSila(int sila) {
        this.sila = sila;
    }

    public SpecialniVlastnost getspVlastnost() {
        return spVlastnost;
    }

    public void setSpVlastnost(SpecialniVlastnost spVlastnost) {
        this.spVlastnost = spVlastnost;
    }

    public String getPopis() {
        return popis;
    }

    public void setPopis(String popis) {
        this.popis = popis;
    }
}
