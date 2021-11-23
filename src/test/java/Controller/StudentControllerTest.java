package Controller;

import Modele.Kurs;
import Modele.Lehrer;
import Modele.Student;
import Repository.KursRepository;
import Repository.LehrerRepository;
import Repository.StudentRepository;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class StudentControllerTest {

    StudentRepository studentRepository ;
    StudentController studentController;
    Student daria;
    Student ana;

    void input() throws IOException {

        studentRepository = new StudentRepository("C:\\Users\\User\\IdeaProjects\\Hausaufgabe4\\src\\test\\java\\Controller\\originalStudent.json");
        studentRepository.readFromFile();
        studentController = new StudentController(studentRepository);
    }

    @Test
    void create() {
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
    void findOne() {
    }

    @Test
    void filter() {
    }

    @Test
    void sort() {
    }

    @Test
    void getStudentenAngemeldetBestimmtenKurs() {
    }

    @Test
    void containsID() {
    }

    @Test
    void containsKurs() {
    }
}