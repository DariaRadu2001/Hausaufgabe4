package Controller;

import Modele.Kurs;
import Modele.Lehrer;
import Repository.LehrerRepository;
import Repository.StudentRepository;

import java.io.IOException;
import java.util.List;

public class LehrerController implements Controller<Lehrer>{

    private LehrerRepository lehrerRepo;
    private StudentRepository studentenRepo;

    public LehrerController(LehrerRepository lehrerRepo, StudentRepository studentenRepo) {
        this.lehrerRepo = lehrerRepo;
        this.studentenRepo = studentenRepo;
    }


    @Override
    public Lehrer create(Lehrer obj) throws IOException {
        return lehrerRepo.create(obj);
    }


    @Override
    public List<Lehrer> getAll() {
        return lehrerRepo.getAll();
    }


    @Override
    public Lehrer update(Lehrer obj) throws IOException {
        return lehrerRepo.update(obj);
    }


    @Override
    public boolean delete(Long objID) throws IllegalAccessException, IOException {
        return lehrerRepo.delete(objID);
    }


    @Override
    public List<Lehrer> readFromFile() throws IOException {
        return lehrerRepo.readFromFile();
    }


    @Override
    public void writeToFile() throws IOException {
        lehrerRepo.writeToFile();
    }


    @Override
    public Lehrer findOne(Long id) throws IOException {
        return lehrerRepo.findOne(id);
    }


    public boolean loschenKurs(Lehrer lehrer, Kurs kurs) throws IOException
    {
        if(lehrerRepo.loschenKurs(lehrer, kurs))
        {
            studentenRepo.loschenKurs(kurs);
            return true;
        }
        return false;
    }
}
