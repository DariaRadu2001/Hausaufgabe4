package Repository;

import Modele.Kurs;
import Modele.Student;
import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class StudentRepository extends InMemoryRepository<Student> implements FileRepository<Student>
{
    public StudentRepository() {
        super();
    }

    @Override
    public String toString() {
        return "StudentRepository{" +
                "repoList=" + repoList +
                '}';
    }

    @Override
    public List<Student> readFromFile() throws IOException {
        Reader reader = new BufferedReader(new FileReader("student.json"));
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode parser = objectMapper.readTree(reader);

        for (JsonNode pm : parser) {

            Long ID = pm.path("studentID").asLong();

            String vorName = pm.path("vorname").asText();

            String nachName = pm.path("nachname").asText();

            int totalKredits = pm.path("totalKredits").asInt();

            List<Long> angeschriebeneKurse = new ArrayList<>();
            for (JsonNode v : pm.path("angeschriebeneKurse"))
            {
                angeschriebeneKurse.add(v.asLong());
            }


            Student student = new Student(vorName,nachName,ID,totalKredits,angeschriebeneKurse);
            repoList.add(student);
        }

        return repoList;
    }

    @Override
    public void writeToFile() throws IOException {

        ObjectMapper objectMapper = new ObjectMapper();

        ObjectWriter writer = objectMapper.writer(new DefaultPrettyPrinter());

        writer.writeValue(new File("student.json"), repoList);

    }

    @Override
    public Student findOne(Long id) throws IOException {
        Reader reader = new BufferedReader(new FileReader("student.json"));
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode parser = objectMapper.readTree(reader);

        for (JsonNode pm : parser)
        {

            Long ID = pm.path("studentID").asLong();
            if(id == ID)
            {

                String vorName = pm.path("vorname").asText();

                String nachName = pm.path("nachname").asText();

                int totalKredits = pm.path("totalKredits").asInt();

                List<Long> angeschriebeneKurse = new ArrayList<>();
                for (JsonNode v : pm.path("angeschriebeneKurse"))
                {
                    angeschriebeneKurse.add(v.asLong());
                }

                return new Student(vorName,nachName,ID,totalKredits,angeschriebeneKurse);

            }
        }

        return null;
    }

    @Override
    public Student create(Student obj) throws IOException {
        for(Student student : repoList)
        {
            if(student.getStudentID() == obj.getStudentID())
                throw new IllegalArgumentException("Das Object ist in der Liste.");
        }

        this.repoList.add(obj);
        writeToFile();
        return obj;
    }

    @Override
    public Student update(Student obj) throws IOException {
        if(repoList.isEmpty())
            throw  new IndexOutOfBoundsException("Die Liste ist leer");

        Student studentToUpdate = this.repoList.stream()
                .filter(student -> student.getStudentID() == obj.getStudentID())
                .findFirst()
                .orElseThrow();

        studentToUpdate.setNachname(obj.getNachname());
        studentToUpdate.setVorname(obj.getVorname());
        studentToUpdate.setTotalKredits(obj.getTotalKredits());
        studentToUpdate.setAngeschriebeneKurse(obj.getAngeschriebeneKurse());

        writeToFile();
        return studentToUpdate;
    }

    @Override
    public boolean delete(Long objID) throws IllegalAccessException, IOException {
        if(repoList.isEmpty())
            throw new IndexOutOfBoundsException("Die Liste ist leer");

        for(Student student : repoList)
        {
            if(student.getStudentID() == objID)
            {
                repoList.remove(student);
                writeToFile();
                return true;
            }

        }
        return false;
    }
}
