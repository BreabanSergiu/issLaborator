package repository;

import domain.Comanda;
import domain.Medicament;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

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
    public void add(Medicament e) {

    }

    @Override
    public Iterable<Medicament> findAll() {
        return null;
    }

    @Override
    public void update(Medicament e1, Medicament e2) {

    }

    @Override
    public Medicament delete(Medicament e) {
        return null;
    }

    @Override
    public Medicament findOne(Long aLong) {
        return null;
    }
}
