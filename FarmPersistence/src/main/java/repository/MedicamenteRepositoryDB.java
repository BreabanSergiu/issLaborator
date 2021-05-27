package repository;


import domain.Medicament;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;


public class MedicamenteRepositoryDB implements MedicamentRepository {

    SessionFactory sessionFactory ;

    public MedicamenteRepositoryDB(){
        sessionFactory = Sesiune.getInstance();
        System.out.println("MedicamenteRepositoryDB = "+sessionFactory);
    }

    @Override
    public Iterable<Medicament> findAllMedicinesInStock() {
        Iterable<Medicament> medicaments = null;
        try(Session session = sessionFactory.openSession()){
            Transaction transaction = null;
            try{
                transaction = session.beginTransaction();
                medicaments = session.createQuery("from Medicament where cantitatepestoc > ?").setParameter(0,0).list();
                transaction.commit();
            } catch (Exception exception) {
                System.out.println(exception);
                if(transaction!=null){
                    transaction.rollback();
                }
            }
        }
        return medicaments;
    }

    @Override
    public Medicament findOneMedicineByName(String name) {
        try(Session session = sessionFactory.openSession()){
            Transaction transaction = null;
            try{
                transaction = session.beginTransaction();
                Medicament medicament = (Medicament) session.createQuery("from Medicament where numemedicament = :nume ").setParameter("nume",name).uniqueResult();
                transaction.commit();
                return medicament;
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
    public void add(Medicament e) {

    }

    @Override
    public Iterable<Medicament> findAll() {
        return null;
    }

    @Override
    public void update(Long id, Medicament e2) {
        try(Session session = sessionFactory.openSession()){
            Transaction transaction = null;
            try{
                transaction = session.beginTransaction();
                Query query = session.createQuery("update Medicament set cantitatepestoc =:cantitate  where id =:id ").setParameter("id",id).setParameter("cantitate",e2.getCantitatePeStoc());
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
    public Medicament delete(Long id) {
        return null;
    }

    @Override
    public Medicament findOne(Long aLong) {
        return null;
    }
}
