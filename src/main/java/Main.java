import Modele.Kurs;
import Modele.Student;
import Repository.KursRepository;
import Repository.StudentRepository;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) throws IOException, IllegalAccessException {

        StudentRepository studentRepository = new StudentRepository();
        studentRepository.readFromFile();
        System.out.println(studentRepository);
        //System.out.println(studentRepository.findOne(1L));
        //System.out.println(studentRepository.findOne(100L));
        //studentRepository.create(new Student("c","c",100L));
        //System.out.println(studentRepository);
        //studentRepository.delete(100L);
        List<Long> list = new ArrayList<>();
        list.add(1L);
        list.add(2L);
        list.add(3L);
        list.add(4L);
        studentRepository.update(new Student("Daria","Radu",1L,30,list));
        System.out.println(studentRepository);
        /*
        KursRepository kursRepository = new KursRepository();
        kursRepository.readFromFile();
        System.out.println(kursRepository.getAll());
        System.out.println(kursRepository.findOne(2L));
        //Student student = new Student("Ana","Pop",20L);
        //kursRepository.addStudent(student, 1L);
        //kursRepository.andernECTS(5,2L);
        //System.out.println(kursRepository.getAll());
        //List<Long> list = new ArrayList<>();
        //Kurs kurs = new Kurs(5,"Baze de date",5,0,list,10);
        //kursRepository.create(kurs);
        //System.out.println(kursRepository.kurseFreiePlatzenUndAnzahl());
        //System.out.println(kursRepository.getAll().size());
        //kursRepository.delete(5L);
        //kursRepository.delete(6L);
        //System.out.println(kursRepository.getAll().size());
        //kursRepository.update(kurs);
        //System.out.println(kursRepository.getAll());
        //System.out.println(kursRepository.filterList());
        //kursRepository.sortList();
        //System.out.println(kursRepository);
        //System.out.println( kursRepository.getKurseFreiePlatzen());
        */
    }
}
