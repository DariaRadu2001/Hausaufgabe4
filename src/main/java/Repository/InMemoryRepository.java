package Repository;

import java.util.ArrayList;
import java.util.List;

public abstract class InMemoryRepository<T> implements ICrudRepository<T> {

    protected List<T> repoList;

    /**
     * ich erstelle ein leeres ArrayList fur dem Repo
     */
    public InMemoryRepository() {
        this.repoList = new ArrayList<>();

    }

    public List<T> getAll() {
        if(repoList.isEmpty())
            throw new IndexOutOfBoundsException("Die Liste ist leer");
        return this.repoList;
    }

}
