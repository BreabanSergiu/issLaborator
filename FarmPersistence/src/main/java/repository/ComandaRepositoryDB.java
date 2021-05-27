package repository;

import domain.Comanda;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class ComandaRepositoryDB implements ComandaRepository{

    static SessionFactory sessionFactory;

    public ComandaRepositoryDB(){
        sessionFactory = Sesiune.getInstance();
        System.out.println("ComandaRepositoryDB = "+sessionFactory);
    }
    @Override
    public void add(Comanda e) {
        try(Session session = sessionFactory.openSession()){
            Transaction transaction = null;
            try{
                transaction = session.beginTransaction();
                session.save(e);
                transaction.commit();
            } catch (Exception exception) {
                System.out.println(exception);
                if(transaction!=null){
                    transaction.rollback();
                }
            }
        }
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
    public void update(Long id, Comanda e2) {
        try(Session session = sessionFactory.openSession()){
            Transaction transaction = null;
            try{
                transaction = session.beginTransaction();
                Query query = session.createQuery("update Comanda set status =:status  where id =:id ").setParameter("id",id).setParameter("status",e2.getStatus());
                query.executeUpdate();
                transaction.commit();
            } catch (Exception exception) {
                System.out.println(exception);
                if(transaction!=null){
                    transaction.rollback();
                }
            }
        }
    }

    @Override
    public Comanda delete(Long id) {

        try(Session session = sessionFactory.openSession()){
            Transaction transaction = null;
            try{
                transaction = session.beginTransaction();
                Comanda comandaReturned = (Comanda) session.createQuery("from Comanda where id = :id").setParameter("id",id).setMaxResults(1).uniqueResult();
                session.delete(comandaReturned);
                transaction.commit();
                return  comandaReturned;
            } catch (Exception exception) {
                System.out.println(exception);
                if(transaction!=null){
                    transaction.rollback();
                }
            }
        }
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
