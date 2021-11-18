package Repository;

import Modele.Kurs;
import Modele.Student;
import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

import java.io.*;
import java.util.*;

public class KursRepository extends InMemoryRepository<Kurs> implements FileRepository<Kurs>{

    public KursRepository() {
        super();
    }

    @Override
    public String toString() {
        return "KursRepository{" +
                "repoList=" + repoList +
                '}';
    }

    @Override
    public List<Kurs> readFromFile() throws IOException {

        Reader reader = new BufferedReader(new FileReader("kurs.json"));
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode parser = objectMapper.readTree(reader);

        for (JsonNode pm : parser) {

            Long ID = pm.path("id").asLong();

            String name = pm.path("name").asText();

            int lehrer = pm.path("lehrer").asInt();

            int maximaleAnzahlStudenten = pm.path("maximaleAnzahlStudenten").asInt();

            List<Long> listeStudenten = new ArrayList<>();
            for (JsonNode v : pm.path("listeStudenten"))
            {
                listeStudenten.add(v.asLong());
            }

            int ECTS = pm.path("ects").asInt();

            Kurs kurs = new Kurs(ID,name,lehrer,maximaleAnzahlStudenten,listeStudenten,ECTS);
            repoList.add(kurs);
        }

        return repoList;

    }

    @Override
    public void writeToFile() throws IOException {

        ObjectMapper objectMapper = new ObjectMapper();

        ObjectWriter writer = objectMapper.writer(new DefaultPrettyPrinter());

        writer.writeValue(new File("kurs.json"), repoList);

    }

    @Override
    public Kurs findOne(Long idKurs) throws IOException
    {
        Reader reader = new BufferedReader(new FileReader("kurs.json"));
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode parser = objectMapper.readTree(reader);

        for (JsonNode pm : parser)
        {

            Long ID = pm.path("id").asLong();
            if(idKurs == ID)
            {
                String name = pm.path("name").asText();

                int lehrer = pm.path("lehrer").asInt();

                int maximaleAnzahlStudenten = pm.path("maximaleAnzahlStudenten").asInt();

                List<Long> listeStudenten = new ArrayList<>();
                for (JsonNode v : pm.path("listeStudenten"))
                {
                    listeStudenten.add(v.asLong());
                }

                int ECTS = pm.path("ects").asInt();

                return new Kurs(idKurs,name,lehrer,maximaleAnzahlStudenten,listeStudenten,ECTS);
            }
        }

        return null;
    }


    @Override
    public Kurs create(Kurs obj) throws IOException
    {
        for(Kurs kurs : repoList)
        {
            if(kurs.getID() == obj.getID())
                throw new IllegalArgumentException("Das Object ist in der Liste.");
        }

        this.repoList.add(obj);
        writeToFile();
        return obj;
    }


    @Override
    public Kurs update(Kurs obj) throws IOException
    {
        if(repoList.isEmpty())
            throw  new IndexOutOfBoundsException("Die Liste ist leer");

        Kurs kursToUpdate = this.repoList.stream()
                .filter(kurs -> Objects.equals(kurs.getID(), obj.getID()))
                .findFirst()
                .orElseThrow();

        kursToUpdate.setName(obj.getName());
        kursToUpdate.setLehrer(obj.getLehrer());
        kursToUpdate.setMaximaleAnzahlStudenten(obj.getMaximaleAnzahlStudenten());
        kursToUpdate.setECTS(obj.getECTS());
        kursToUpdate.setListeStudenten(obj.getListeStudenten());

        writeToFile();
        return kursToUpdate;
    }

    @Override
    public boolean delete(Long idKurs) throws IllegalAccessException, IOException
    {
        if(repoList.isEmpty())
            throw new IndexOutOfBoundsException("Die Liste ist leer");

        for(Kurs kurs : repoList)
        {
            if(kurs.getID() == idKurs)
            {
                repoList.remove(kurs);
                writeToFile();
                return true;
            }

        }

        return false;

    }

    /**
     * filtert die Liste nach dem Anzahl von ECTS(die Kurse die >5 ECTS haben)
     * @return die gefilterte Liste
     */
    public List<Kurs> filterList()
    {
        return repoList.stream()
                .filter(kurs->kurs.getECTS() > 5).toList();
    }

    /**
     * sortiert die Liste in steigender Reihenfolge nach Anzahl der freien PLatzen
     */
    public void sortList()
    {
        Collections.sort(repoList,Kurs::compareTo);
    }


    /**
     * überprüft, welche Kurse noch frei sind
     * @return eine Liste mit freien Kursen
     */
    public List<Kurs> getKurseFreiePlatzen()
    {
        List<Kurs> freieKurse = new ArrayList<>();
        for(Kurs kurs : repoList)
        {
            if(kurs.istFrei())
            {
                freieKurse.add(kurs);
            }
        }
        return freieKurse;
    }

    /**
     * Ich untersuche, welche Kurse noch freien Platzen haben
     * @return ein Map der als Key den Kurs und als Value die Anzahl den freien Platze hat
     */
    public Map<Long, Integer> kurseFreiePlatzenUndAnzahl()
    {
        Map<Long, Integer> mapFreieKurse = new HashMap<>();
        for(Kurs kurs : repoList)
        {
            if(kurs.istFrei())
            {
                mapFreieKurse.put(kurs.getID(),kurs.getAnzahlFreienPlatze());
            }
        }
        return mapFreieKurse;
    }

    public int andernECTS(int ECTS,Long idKurs) throws IOException
    {
        for(Kurs kurs : repoList)
        {
            if (kurs.getID() == idKurs)
            {
                int alteECTS = kurs.getECTS();
                kurs.setECTS(ECTS);
                this.update(kurs);
                writeToFile();
                return alteECTS;
            }
        }
        return -1;
    }

    public boolean validationFreiePlatzen(Long idKurs)
    {
        boolean valid = false;
        for(Kurs kurs : repoList)
        {
            if (kurs.getID() == idKurs)
                if(kurs.istFrei())
                    valid = true;

        }

        return valid;
    }

    public boolean containsKursStudent(Long idKurs, Long idStudent)
    {
        boolean valid = false;
        for(Kurs kurs : repoList)
        {
            if (kurs.getID() == idKurs)
                if(!kurs.getListeStudenten().contains(idStudent))
                    valid = true;

        }

        return valid;
    }

    public void addStudent(Student student, Long idKurs) throws IOException {
        if(validationFreiePlatzen(idKurs) && containsKursStudent(idKurs, student.getStudentID()))
        {
            for(Kurs kurs : repoList)
            {
                if(idKurs == kurs.getID())
                {
                    kurs.addStudent(student);
                    this.update(kurs);
                    writeToFile();
                }
            }

        }
    }
}
