package repository;

import domain.Farmacist;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;


public class FarmacistRepositoryDB implements FarmacistRepository{

    static SessionFactory sessionFactory;


    public FarmacistRepositoryDB(){
        sessionFactory = Sesiune.getInstance();
        System.out.println("FarmacistRepositoryDb sesionFactory= "+sessionFactory);
    }
    @Override
    public Farmacist login(String username, String parola) {
        Farmacist farmacist = null;
        try(Session session = sessionFactory.openSession() ){
            Transaction transaction = null;
            try{
                transaction = session.beginTransaction();
                Query qq = session.createQuery("From Farmacist where username = ? and parola = ?");
                qq.setParameter(0,username).setParameter(1,parola).uniqueResult();
                farmacist = (Farmacist) qq.uniqueResult();
                transaction.commit();

            } catch (Exception e) {
                e.printStackTrace();
                if(transaction != null){
                    transaction.rollback();
                }
            }
        }
        return farmacist;
    }

    @Override
    public void add(Farmacist e) {
        System.out.println(e);
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
    public Iterable<Farmacist> findAll() {
        return null;
    }

    @Override
    public void update(Farmacist e1, Farmacist e2) {

    }

    @Override
    public Farmacist delete(Farmacist e) {
        return null;
    }

    @Override
    public Farmacist findOne(Long aLong) {
        Farmacist farmacist = null;
        try(Session session = sessionFactory.openSession() ){
            Transaction transaction = null;
            try{
                transaction = session.beginTransaction();
                Query qq = session.createQuery("From Farmacist where id = ?");
                qq.setParameter(0,aLong).uniqueResult();
                farmacist = (Farmacist) qq.uniqueResult();
                transaction.commit();

            } catch (Exception e) {
                e.printStackTrace();
                if(transaction != null){
                    transaction.rollback();
                }
            }
        }
        return farmacist;
    }
}
