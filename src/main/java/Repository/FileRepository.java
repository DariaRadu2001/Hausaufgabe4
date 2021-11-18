package Repository;

import Modele.Kurs;

import java.io.IOException;
import java.util.List;

public interface FileRepository<T> extends ICrudRepository<T>{

    /**
     * liest alle Attribute eines Objekts von einem File
     */
    List<T> readFromFile() throws IOException;

    /**
     * schreit neue Objekte in dem File
     */
    void writeToFile() throws IOException;

    Kurs findOne(Long id) throws IOException;
}
