package Repository;

import java.util.HashMap;
import java.util.Map;

public class MemoryRepo<ID,T> implements IRepository<ID,T>{
    Map<ID,T>  repo;
    public MemoryRepo(){ repo =new HashMap<>();}

    public Map<ID, T> getRepo() {
        return repo;
    }

    @Override
    public void add(ID id,T elem) {
        if(repo.put(id,elem)!=null) throw new RuntimeException("ID already exists");
    }

    @Override
    public void delete(ID id) {
        if(!repo.containsKey(id)) throw new RuntimeException("ID does not exist");
        repo.remove(id);
    }

    @Override
    public void updateId(ID id, T elem) {
        if(!repo.containsKey(id)) throw new RuntimeException("ID does not exist");
        repo.replace(id,elem);
    }
    @Override
    public T findById(ID id) {
        if(!repo.containsKey(id)) throw new RuntimeException("ID does not exist");
        return repo.get(id);
    }
    @Override
    public Iterable<T> findAll(){
        return repo.values();
    }
}