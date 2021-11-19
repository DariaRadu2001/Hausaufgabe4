package Controller;

import java.io.IOException;
import java.util.List;
import Exception.DasElementExistiertException;
import Exception.ListIsEmptyException;
public interface Controller<T> {

    /**
     * legt ein Objekt der Typ T in der RepoListe
     * @param obj, das Objekt die ich hinlege
     * @return das Objekt
     */
    T create(T obj) throws IOException, DasElementExistiertException;

    /**
     * gibt alle Elementen aus der RepoListe
     * @return eine Liste mit Elementen der Typ T
     */
    List<T> getAll();

    /**
     * Verändert einige Attribute eines Objektes
     * @param obj, das Objekt mit dem switch erledigt
     * @return das alte Objet mit den neuen Attributen
     */
    T update(T obj) throws IOException, ListIsEmptyException;

    /**
     * aus der RepoListe ein Objekt löschen
     * @param objID, das Objekt, das ich löschen will
     */
    boolean delete(Long objID) throws IllegalAccessException, IOException;

    /**
     * liest alle Attribute eines Objekts von einem File
     */
    List<T> readFromFile() throws IOException;

    /**
     * schreit neue Objekte in dem File
     */
    void writeToFile() throws IOException;

    /**
     * sucht in dem File, ob ein Objekt existiert mit Hilfe eines IDs
     * @return das Objekt,wenn man es findet
     * @throws IOException das Objekt existiert nicht
     */
    T findOne(Long id) throws IOException;
}
