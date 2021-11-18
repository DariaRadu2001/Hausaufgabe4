package Modele;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Student extends Person implements Comparable<Student>{

    private long studentID;
    private int totalKredits;
    private List<Long> angeschriebeneKurse;

    public Student(String vorname, String nachname, long studentID, int totalKredits, List<Long> angeschriebeneKurse) {
        super(vorname, nachname);
        this.studentID = studentID;
        this.totalKredits = totalKredits;
        this.angeschriebeneKurse = angeschriebeneKurse;
    }

    public Student(String vorname, String nachname, long studentID) {
        super(vorname, nachname);
        this.studentID = studentID;
        this.totalKredits = 0;
        this.angeschriebeneKurse = new ArrayList<>();
    }

    public long getStudentID() {
        return studentID;
    }

    public void setStudentID(long studentID) {
        this.studentID = studentID;
    }

    public int getTotalKredits() {
        return totalKredits;
    }

    public void setTotalKredits(int totalKredits) {
        this.totalKredits = totalKredits;
    }

    public List<Long> getAngeschriebeneKurse() {
        return angeschriebeneKurse;
    }

    public void setAngeschriebeneKurse(List<Long> angeschriebeneKurse) {
        this.angeschriebeneKurse = angeschriebeneKurse;
    }

    @Override
    public String toString() {
        return "Student{" +
                "studentID=" + studentID +
                ", totalKredits=" + totalKredits +
                ", angeschriebeneKurse=" + angeschriebeneKurse +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return studentID == student.studentID && totalKredits == student.totalKredits && angeschriebeneKurse.equals(student.angeschriebeneKurse);
    }

    /**
     * ich lösche einen Kurs aus der Liste eines Students und subtrahiere die Anzahl des Kurses ECTS von dem Anzahl derKredite des Students
     * @param kurs
     */
    public void loschenKurs(Kurs kurs)
    {
        this.angeschriebeneKurse.remove(kurs.getID());
        this.totalKredits -= kurs.getECTS();
    }

    /**
     * ich berechne wv. Kredite ein Student noch braucht bis er insgesamt 30 hat
     * @return Anzahl notwendigen Krediten
     */
    public int getNotwendigeKredits()
    {
        return (30 - this.getTotalKredits());
    }

    /**
     * Wenn ein Student sich fur einen Kurs anmeldet, dann fuge ich den Kurs in seiner Liste und inkrementiere seine ECTS mit der Anzahl des Kurses ECTS
     * @param kurs
     */
    public void enrolled(Kurs kurs)
    {
        this.angeschriebeneKurse.add(kurs.getID());
        this.totalKredits += kurs.getECTS();
    }

    /**
     * Methode, um nur der Name der Kurse, in denen der Student angemeldet ist
     */
    public void showKurse()
    {
        System.out.println(this.getStudentID() + "ist angeschrieben an :");
        for(Long kurs : angeschriebeneKurse)
        {
            System.out.println(kurs);
        }
    }

    public int getAnzahlKurse()
    {
        return this.angeschriebeneKurse.size();
    }

    @Override
    public int compareTo(Student o) {
        if(this.getAnzahlKurse() < o.getAnzahlKurse())
            return -1;

        if(this.getAnzahlKurse() == o.getAnzahlKurse())
            return 0;

        return 1;

    }


}
