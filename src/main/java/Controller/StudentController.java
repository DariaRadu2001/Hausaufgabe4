package Controller;

import Modele.Student;
import Repository.StudentRepository;

import java.io.IOException;
import java.util.List;

public class StudentController implements Controller<Student>{

    private StudentRepository studentenRepo;

    public StudentController(StudentRepository studentenRepo) {
        this.studentenRepo = studentenRepo;
    }

    public StudentRepository getStudentenRepo() {
        return studentenRepo;
    }

    public void setStudentenRepo(StudentRepository studentenRepo) {
        this.studentenRepo = studentenRepo;
    }

    @Override
    public Student create(Student obj) throws IOException {
        return studentenRepo.create(obj);
    }


    @Override
    public List<Student> getAll() {
        return studentenRepo.getAll();
    }


    @Override
    public Student update(Student obj) throws IOException {
        return studentenRepo.update(obj);
    }


    @Override
    public boolean delete(Long objID) {
        return delete(objID);
    }


    @Override
    public List<Student> readFromFile() throws IOException {
        return studentenRepo.readFromFile();
    }


    @Override
    public void writeToFile() throws IOException {
            studentenRepo.writeToFile();
    }


    @Override
    public Student findOne(Long id) {
        return findOne(id);
    }


    public void filter()
    {
        studentenRepo.filterList();
    }


    public void sort()
    {
        studentenRepo.sortList();
    }


    public void getStudentenAngemeldetBestimmtenKurs(Long kursId)
    {
        System.out.println(studentenRepo.studentenAngemeldetBestimmtenKurs(kursId));
    }

    public boolean containsID(long id)
    {
        return studentenRepo.containsID(id);
    }
}
