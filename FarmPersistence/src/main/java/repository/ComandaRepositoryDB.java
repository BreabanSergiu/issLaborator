package repository;

import domain.Comanda;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public class ComandaRepositoryDB implements ComandaRepository{

    static SessionFactory sessionFactory;

    public ComandaRepositoryDB(){
        sessionFactory = Sesiune.getInstance();
        System.out.println("ComandaRepositoryDB = "+sessionFactory);
    }
    @Override
    public void add(Comanda e) {

    }

    @Override
    public Iterable<Comanda> findAll() {
        Iterable<Comanda> comandas = null;
        try(Session session = sessionFactory.openSession()){
            Transaction transaction = null;
            try{
                transaction = session.beginTransaction();
                comandas = session.createQuery("from Comanda").list();
                transaction.commit();
            } catch (Exception exception) {
                System.out.println(exception);
                if(transaction!=null){
                    transaction.rollback();
                }
            }
        }
        return comandas;
    }

    @Override
    public void update(Comanda e1, Comanda e2) {

    }

    @Override
    public Comanda delete(Comanda e) {
        return null;
    }

    @Override
    public Comanda findOne(Long aLong) {
        return null;
    }

    @Override
    public Iterable<Comanda> findUnfulfilledOrders() {
        Iterable<Comanda> comandas = null;
        try(Session session = sessionFactory.openSession()){
            Transaction transaction = null;
            try{
                transaction = session.beginTransaction();
                comandas = session.createQuery("from Comanda where status = ?").setParameter(0,"nerealizata").list();
                transaction.commit();
            } catch (Exception exception) {
                System.out.println(exception);
                if(transaction!=null){
                    transaction.rollback();
                }
            }
        }
        return comandas;
    }
}
