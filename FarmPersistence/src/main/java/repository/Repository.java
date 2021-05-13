package repository;

public interface Repository<ID,T> {

    void add(T e);
    Iterable<T> findAll();
    void update(T e1, T e2);
    T delete(T e);
    T findOne(ID id);

}
