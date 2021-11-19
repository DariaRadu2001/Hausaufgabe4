package Modele;

import java.util.ArrayList;
import java.util.List;

public class Lehrer extends Person{

    private List<Long> kurse;
    private long lehrerID;

    public Lehrer(String vorname, String nachname, List<Long> kurse, long lehrerID) {
        super(vorname, nachname);
        this.kurse = kurse;
        this.lehrerID = lehrerID;
    }

    public Lehrer(String vorname, String nachname, long lehrerID) {
        super(vorname, nachname);
        this.kurse = new ArrayList<>();
        this.lehrerID = lehrerID;
    }

    public List<Long> getKurse() {
        return kurse;
    }

    public void setKurse(List<Long> kurse) {
        this.kurse = kurse;
    }

    public long getLehrerID() {
        return lehrerID;
    }

    public void setLehrerID(long lehrerID) {
        this.lehrerID = lehrerID;
    }

    @Override
    public String toString() {
        return "Lehrer{" +
                "kurse=" + kurse +
                ", lehrerID=" + lehrerID +
                '}';
    }

    /**
     * ein Kurs zu der Liste der unterrichteten Kurse eines Lehrers hinzufügen
     * @param kurs
     */
    public void addKurs(Kurs kurs)
    {
        this.kurse.add(kurs.getID());
    }

    /**
     * ein Kurs von der Liste der unterrichteten Kurse eines Lehrers löschen
     * @param kurs
     */
    public void loschenKurs(Kurs kurs)
    {
        this.kurse.remove(kurs.getID());
    }

    public boolean containsKurs(Long id)
    {
        return this.kurse.contains(id);
    }

}
