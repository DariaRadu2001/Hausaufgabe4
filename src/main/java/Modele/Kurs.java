package Modele;

import java.util.ArrayList;
import java.util.List;

public class Kurs implements Comparable<Kurs>{

    private long ID;
    private String name;
    private int lehrer;
    private int maximaleAnzahlStudenten;
    private List<Long> listeStudenten;
    private int ECTS;

    public Kurs(long ID, String name, int lehrer, int maximaleAnzahlStudenten, List<Long> listeStudenten, int ECTS) {
        this.ID = ID;
        this.name = name;
        this.lehrer = lehrer;
        this.maximaleAnzahlStudenten = maximaleAnzahlStudenten;
        this.listeStudenten = listeStudenten;
        this.ECTS = ECTS;
    }

    public Kurs(long ID, String name, int lehrer, int maximaleAnzahlStudenten, int ECTS) {
        this.ID = ID;
        this.name = name;
        this.lehrer = lehrer;
        this.maximaleAnzahlStudenten = maximaleAnzahlStudenten;
        this.listeStudenten = new ArrayList<>();
        this.ECTS = ECTS;
    }

    public long getID() {
        return ID;
    }

    public void setID(long ID) {
        this.ID = ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getLehrer() {
        return lehrer;
    }

    public void setLehrer(int lehrer) {
        this.lehrer = lehrer;
    }

    public int getMaximaleAnzahlStudenten() {
        return maximaleAnzahlStudenten;
    }

    public void setMaximaleAnzahlStudenten(int maximaleAnzahlStudenten) {
        this.maximaleAnzahlStudenten = maximaleAnzahlStudenten;
    }

    public List<Long> getListeStudenten() {
        return listeStudenten;
    }

    public void setListeStudenten(List<Long> listeStudenten) {
        this.listeStudenten = listeStudenten;
    }

    public int getECTS() {
        return ECTS;
    }

    public void setECTS(int ECTS) {
        this.ECTS = ECTS;
    }


    @Override
    public String toString() {
        return "Kurs{" +
                "ID=" + ID +
                ", name='" + name + '\'' +
                ", lehrer=" + lehrer +
                ", maximaleAnzahlStudenten=" + maximaleAnzahlStudenten +
                ", listeStudenten=" + listeStudenten +
                ", ECTS=" + ECTS +
                '}';
    }

    /**
     * untersucht, ob ein Kurs freie Platzen hat
     * @return true, wenn es noch Platzen gibt/ false, wenn es voll ist
     */
    public boolean istFrei()
    {
        return this.getListeStudenten().size() < this.getMaximaleAnzahlStudenten();
    }


    /**
     * hinzufügt ein bestimmter Student in der Liste der angemeldeten Studenten
     * @param student
     */
    public void addStudent(Student student)
    {
        this.listeStudenten.add(student.getStudentID());
    }

    /**
     * ich berechne der Anzahl den freien Platzen fur dem Kurs
     * @return Anzahl von freien Platzen
     */
    public int getAnzahlFreienPlatze()
    {
        return (this.getMaximaleAnzahlStudenten() - this.getListeStudenten().size());
    }


    /**
     * ich will nur den Namen des Kurses anzeigen
     * @return String mit dem Namen des Kurses
     */
    public String toString2() {
        return "Kurs{" +
                "name='" + name + "'"+
                '}';
    }

    /**
     * vergleicht 2 Kurse nach dem Anzahl der freien Platzen
     * @param o (Kurs2)
     * @return 1, wenn Kurs1 mehrere Platzen hat
     * @return -1, wenn Kurs1 weingere Platzen hat
     * @return 0, wenn Kurs1 = Kurs2 beim Anzahl freien Platzen
     */
    @Override
    public int compareTo(Kurs o) {
        if(this.getAnzahlFreienPlatze() < o.getAnzahlFreienPlatze())
            return -1;

        if(this.getAnzahlFreienPlatze() < o.getAnzahlFreienPlatze())
            return 0;

        return 1;
    }
}
