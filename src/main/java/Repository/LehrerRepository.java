package Repository;
import Modele.Lehrer;
import Modele.Kurs;
import Modele.Student;
import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class LehrerRepository extends InMemoryRepository<Lehrer> implements FileRepository<Lehrer>{


    public LehrerRepository() {
        super();
    }

    @Override
    public String toString() {
        return "LehrerRepository{" +
                "repoList=" + repoList +
                '}';
    }

    /**
     * ich andere die Attribute eines Lehrers, wenn die Liste leer ist oder der Lehrer nicht in der Liste ist → Exception
     * @param obj, der Lehrer, den ich andern will
     * @return der neue Lehrer
     * @throws IndexOutOfBoundsException
     */
    @Override
    public Lehrer update(Lehrer obj) throws IOException {

        if(repoList.isEmpty())
            throw  new IndexOutOfBoundsException("Die Liste ist leer");

        Lehrer lehrerToUpdate = this.repoList.stream()
                .filter(lehrer -> lehrer.getLehrerID() == obj.getLehrerID())
                .findFirst()
                .orElseThrow();

        lehrerToUpdate.setNachname(obj.getNachname());
        lehrerToUpdate.setVorname(obj.getVorname());
        lehrerToUpdate.setKurse(obj.getKurse());

        writeToFile();

        return lehrerToUpdate;
    }


    @Override
    public List<Lehrer> readFromFile() throws IOException {
        Reader reader = new BufferedReader(new FileReader("lehrer.json"));
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode parser = objectMapper.readTree(reader);

        for (JsonNode pm : parser) {

            int id = pm.path("lehrerID").asInt();

            String vorName = pm.path("vorname").asText();

            String nachName = pm.path("nachname").asText();

            List<Long> kurse = new ArrayList<>();
            for (JsonNode v : pm.path("kurse"))
            {
                kurse.add(v.asLong());
            }


            Lehrer lehrer = new Lehrer(vorName,nachName,kurse,id);
            repoList.add(lehrer);
        }

        return repoList;
    }


    @Override
    public void writeToFile() throws IOException {

        ObjectMapper objectMapper = new ObjectMapper();

        ObjectWriter writer = objectMapper.writer(new DefaultPrettyPrinter());

        writer.writeValue(new File("lehrer.json"), repoList);

    }


    @Override
    public Lehrer findOne(Long id) throws IOException {
        Reader reader = new BufferedReader(new FileReader("lehrer.json"));
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode parser = objectMapper.readTree(reader);

        for (JsonNode pm : parser)
        {

            long idLehrer = pm.path("lehrerID").asInt();

            if(id == idLehrer)
            {
                String vorName = pm.path("vorname").asText();

                String nachName = pm.path("nachname").asText();

                List<Long> kurse = new ArrayList<>();
                for (JsonNode v : pm.path("kurse"))
                {
                    kurse.add(v.asLong());
                }


            return new Lehrer(vorName,nachName,kurse,idLehrer);


            }
        }

        return null;
    }


    @Override
    public boolean delete(Long idLehrer) throws IllegalAccessException, IOException {

        if(repoList.isEmpty())
            throw new IndexOutOfBoundsException("Die Liste ist leer");

        for(Lehrer lehrer : repoList)
        {
            if(lehrer.getLehrerID() == idLehrer)
            {
                repoList.remove(lehrer);
                writeToFile();
                return true;
            }

        }

        return false;
    }


    @Override
    public Lehrer create(Lehrer obj) throws IOException {

        for(Lehrer lehrer : repoList)
        {
            if(lehrer.getLehrerID() == obj.getLehrerID())
                throw new IllegalArgumentException("Das Object ist in der Liste.");
        }

        this.repoList.add(obj);
        writeToFile();
        return obj;
    }


    public boolean loschenKurs(Lehrer lehrer,Kurs kurs) throws IOException {
        if(!lehrer.getKurse().contains(kurs.getID()))
            return false;

        lehrer.loschenKurs(kurs);
        writeToFile();
        return true;
    }

    public boolean containsID(Long id)
    {
        for(Lehrer lehrer : repoList)
        {
            if(lehrer.getLehrerID() == id)
                return true;
        }
        return false;
    }

    public boolean containsKurs(Lehrer lehrer, Long id)
    {
        return lehrer.containsKurs(id);
    }

    public void deleteKursFromAll(Kurs kurs)
    {
        for(Lehrer lehrer : repoList)
        {
            lehrer.loschenKurs(kurs);
        }
    }
}