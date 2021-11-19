import Controller.KursController;
import Controller.LehrerController;
import Controller.StudentController;
import KonsoleView.KonsoleView;

import Repository.KursRepository;
import Repository.LehrerRepository;
import Repository.StudentRepository;
import Exception.DasElementExistiertException;
import Exception.ListIsEmptyException;
import java.io.IOException;


public class Main {

    public static void main(String[] args) throws IOException, IllegalAccessException, ListIsEmptyException, DasElementExistiertException, InterruptedException {
        /*

        LehrerRepository lehrerRepository = new LehrerRepository();
        lehrerRepository.readFromFile();
        System.out.println(lehrerRepository);
        //lehrerRepository.delete(3L);
        List<Long> list = new ArrayList<>();
        list.add(1L);
        list.add(2L);
        list.add(3L);
        list.add(4L);
        Kurs kurs = new Kurs(7L,15);
        Lehrer lehrer = new Lehrer("Martin","Bottesch",list,4L);

        //lehrerRepository.create(lehrer);
        System.out.println(lehrerRepository);
        lehrerRepository.loschenKurs(lehrer,kurs);
        System.out.println(lehrerRepository);
*/

        /*
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
        //studentRepository.update(new Student("Daria","Radu",1L,30,list));
        //System.out.println(studentRepository);
        //studentRepository.sortList();
        //System.out.println(studentRepository);
        //System.out.println(studentRepository.filterList());
        Kurs kurs = new Kurs(3L,15);
        Student student = new Student("Daria","Radu",1L,20,list);
        Student student2 = new Student("Mihai","Pop",2L);
        studentRepository.andernECTS(5,kurs,15);
        System.out.println(studentRepository);
        */

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

        KursRepository kursRepository = new KursRepository();
        kursRepository.readFromFile();
        LehrerRepository lehrerRepository = new LehrerRepository();
        lehrerRepository.readFromFile();
        StudentRepository studentRepository = new StudentRepository();
        studentRepository.readFromFile();
        KursController kursController = new KursController(kursRepository,studentRepository,lehrerRepository);
        LehrerController lehrerController = new LehrerController(lehrerRepository,studentRepository,kursRepository);
        StudentController studentController = new StudentController(studentRepository);
        KonsoleView view = new KonsoleView(kursController,lehrerController,studentController);
        view.start();
    }
}
