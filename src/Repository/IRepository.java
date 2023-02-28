package Repository;

public interface IRepository<ID,T> {
    public void add(ID id,T elem);
    public void delete(ID id);
    public void updateId(ID id,T elem);
    public T findById(ID id);
    public Iterable<T> findAll();
}