package Controller;

import Modele.Kurs;
import Modele.Student;
import Repository.KursRepository;
import Repository.StudentRepository;

import java.io.IOException;
import java.util.List;

public class KursController implements Controller<Kurs> {

    private KursRepository kursRepo;
    private StudentRepository studentenRepo;

    public KursController(KursRepository kursRepo, StudentRepository studentenRepo) {
        this.kursRepo = kursRepo;
        this.studentenRepo = studentenRepo;
    }

    public KursRepository getKursRepo() {
        return kursRepo;
    }

    public void setKursRepo(KursRepository kursRepo) {
        this.kursRepo = kursRepo;
    }

    public StudentRepository getStudentenRepo() {
        return studentenRepo;
    }

    public void setStudentenRepo(StudentRepository studentenRepo) {
        this.studentenRepo = studentenRepo;
    }

    @Override
    public Kurs create(Kurs obj) throws IOException {
        return kursRepo.create(obj);
    }

    @Override
    public List<Kurs> getAll() {
        return kursRepo.getAll();
    }

    @Override
    public Kurs update(Kurs obj) throws IOException {
        return kursRepo.update(obj);
    }

    @Override
    public boolean delete(Long objID) throws IllegalAccessException, IOException {

        if(kursRepo.delete(objID))
            return true;

        return false;
    }

    @Override
    public List<Kurs> readFromFile() throws IOException {
        return kursRepo.readFromFile();
    }

    @Override
    public void writeToFile() throws IOException {
        kursRepo.writeToFile();
    }

    @Override
    public Kurs findOne(Long id) throws IOException {
        return kursRepo.findOne(id);
    }


    public List<Kurs> filter()
    {
        return kursRepo.filterList();
    }


    public void sort()
    {
        kursRepo.sortList();
    }


    public void getKurseFreiePlatzen()
    {
        kursRepo.getKurseFreiePlatzen();
    }


    public void kurseFreiePlatzenUndAnzahl()
    {
        kursRepo.kurseFreiePlatzenUndAnzahl();
    }


    public boolean andernECTS(int ECTS, Long idKurs) throws IOException
    {
        int alteECTS = kursRepo.andernECTS(ECTS, idKurs);
        if(alteECTS != -1)
        {
            studentenRepo.andernECTS(ECTS, idKurs, alteECTS);
            return true;
        }
        return false;
    }


    public boolean register(Student student, Kurs kurs) throws IOException {
        if(kursRepo.validationFreiePlatzen(kurs.getID()) && studentenRepo.validationAddKurs(student, kurs))
        {
            kursRepo.addStudent(student, kurs.getID());
            studentenRepo.addKurs(student, kurs);
            return true;
        }
        return false;
    }

    public boolean containsID(long id)
    {
        return kursRepo.containsID(id);
    }
}
