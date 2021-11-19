package Controller;
import Exception.DasElementExistiertException;
import Exception.ListIsEmptyException;
import Modele.Kurs;
import Modele.Lehrer;
import Repository.KursRepository;
import Repository.LehrerRepository;
import Repository.StudentRepository;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class KursControllerTest {

    KursRepository kursRepository ;
    LehrerRepository lehrerRepository ;
    StudentRepository studentRepository ;
    KursController kursController;
    Lehrer pop;
    Lehrer dancu;
    Kurs algebra;
    Kurs dataBase;
    Kurs map;
    Kurs fp;
    Kurs algebra2;

    void input()
    {
        pop = new Lehrer("Marcel", "Pop",1);
        algebra = new Kurs(1,"Algebra", 1, 1, 5);
        dataBase = new Kurs(2,"Baze de date", 1, 30, 26);
        map = new Kurs(3,"Map", 1, 100, 5);
        fp = new Kurs(4,"fp", 1, 35, 6);
        algebra2 = new Kurs(1,"Algebra", 2, 10, 15);
        dancu = new Lehrer("Ingrid","Dancu",2);


        KursRepository kursRepository = new KursRepository("originalKurs.json");
        LehrerRepository lehrerRepository = new LehrerRepository("originalLehrer.json");
        StudentRepository studentRepository = new StudentRepository("originalStudent.json");
        KursController kursController = new KursController(kursRepository,studentRepository,lehrerRepository);
    }

    @Test
    void create() throws IOException, DasElementExistiertException {
        input();


    }

    @Test
    void getAll() {

    }

    @Test
    void update() {
    }

    @Test
    void delete() {
    }

    @Test
    void readFromFile() {
        input();

    }

    @Test
    void writeToFile() {
    }

    @Test
    void findOne() {
    }

    @Test
    void filter() {
    }

    @Test
    void sort() {
    }

    @Test
    void getKurseFreiePlatzen() {
    }

    @Test
    void kurseFreiePlatzenUndAnzahl() {
    }

    @Test
    void andernECTS() {
    }

    @Test
    void register() {
    }

    @Test
    void containsID() {
    }
}