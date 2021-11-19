package Repository;

import Modele.Student;
import jdk.jfr.Description;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class StudentRepositoryTest {

    Student ana;
    Student daria;
    Student mark;
    Student daria2;
    StudentRepository repoStudenten;

    /**
     *  die Objekte instanziieren
     */
    void input()
    {
        ana = new Student("Ana","Pop",123);
        daria = new Student("Daria","Radu",423);
        mark = new Student("Mark","Jacobs",200);
        daria2 = new Student("Dariana", "Beckham", 423);

        repoStudenten = new StudentRepository("originalStudent.json");
    }

    /**
     *  die RepoListe instanziieren
     */
    void inputRepo()
    {
        repoStudenten = new StudentRepository("originalStudent.json");
    }

    @Test
    void readFromFile() {
    }

    @Test
    void writeToFile() {
    }

    @Test
    void findOne() {
    }

    @Test
    @Description("Soll Studenten in dem repoListe hinzulegen")
    void create() throws IOException {
        this.input();

        repoStudenten.create(ana);
        repoStudenten.create(daria);
        repoStudenten.create(mark);

        assertEquals(3, repoStudenten.getAll().size());
    }


    @Test
    @Description("Soll Studenten in dem repoListe hinzulegen, wenn der Student in der Liste ist --> Exception.")
    void createStudentInDerListe() throws IOException {

        this.input();
        repoStudenten.create(ana);
        repoStudenten.create(daria);
        repoStudenten.create(mark);

        assertThrows(IllegalArgumentException.class, ()-> repoStudenten.create(ana));

    }


    @Test
    @Description("wenn die Liste leer ist, kann man nichts verändern")
    void updateLeereListe() {

        this.input();

        assertThrows(IndexOutOfBoundsException.class, ()-> repoStudenten.update(ana));

    }

    @Test
    @Description("Ich habe 2 Ausnahmen, wenn die Liste leer ist und wenn das Kurs nicht in der Liste ist")
    void updateObjektNichtInDerListe() throws IOException {
        this.input();

        repoStudenten.create(daria);

        assertThrows(Exception.class, ()-> repoStudenten.update(mark));

    }

    @Test
    @Description("Verändert ein Objekt, das in der Liste ist")
    void updateObjektInDerListe() throws IOException {
        this.input();
        repoStudenten.create(daria);
        try
        {
            repoStudenten.update(daria2);
            assert(true);
        }
        catch(Exception e)
        {
            assert(false);
        }

    }

    @Test
    @Description("wenn die Liste leer ist, kann man nicht löschen.")
    void deleteLeereListe()
    {
        this.input();
        assertThrows(IndexOutOfBoundsException.class, ()-> repoStudenten.delete(mark.getStudentID()));
    }

    @Test
    @Description("wenn das Kurs nicht in der Liste ist, kann man es nicht löschen")
    void deleteObjektNichtInDerListe() throws IOException {

        this.input();
        repoStudenten.create(ana);
        repoStudenten.create(daria);

        assert(! repoStudenten.delete(mark.getStudentID()));

    }

    @Test
    @Description("man prüft, dass das Objekt aus der Liste gelöscht wird")
    void deleteObjektInderListe() throws IllegalAccessException, IOException {
        this.input();
        repoStudenten.create(ana);
        repoStudenten.create(daria);
        repoStudenten.delete(ana.getStudentID());
        assertEquals(1, repoStudenten.getAll().size());

    }

    @Test
    void sortList() {
    }

    @Test
    void filterList() {
    }

    @Test
    void studentenAngemeldetBestimmtenKurs() {
    }

    @Test
    void loschenKurs() {
    }

    @Test
    void andernECTS() {
    }

    @Test
    void validationAddKurs() {
    }

    @Test
    void addKurs() {
    }

    @Test
    void containsID() {
    }

    @Test
    void containsKurs() {
    }
}