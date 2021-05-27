package repository;

public interface Repository<ID,T> {

    void add(T e);
    Iterable<T> findAll();
    void update(ID id, T e2);
    T delete(ID id);
    T findOne(ID id);

}
