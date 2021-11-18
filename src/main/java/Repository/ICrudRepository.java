package Repository;

import java.io.IOException;
import java.util.List;

public interface ICrudRepository<T> {

    /**
     * legt ein Objekt der Typ T in der RepoListe
     * @param obj, das Objekt die ich hinlege
     * @return das Objekt
     */
    T create(T obj) throws IOException;

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
    T update(T obj) throws IOException;

    /**
     * aus der RepoListe ein Objekt löschen
     * @param obj, das Objekt, das ich löschen will
     * @throws IllegalAccessException
     */
    boolean delete(Long objID) throws IllegalAccessException, IOException;

}
