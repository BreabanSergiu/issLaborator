package repository;

import domain.Farmacist;
import domain.PersonalMedical;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

public class PersonalMedicalRepositoryDB implements PersonalMedicalRepository {


    static SessionFactory sessionFactory;

    public PersonalMedicalRepositoryDB(){
        sessionFactory  = Sesiune.getInstance();
        System.out.println("PersonalMedicalRepositoryDB sesionFactory= "+sessionFactory);
    }
    @Override
    public PersonalMedical login(String username, String parola) {

        PersonalMedical personalMedical = null;
        try(Session session = sessionFactory.openSession() ){
            Transaction transaction = null;
            try{
                transaction = session.beginTransaction();
                Query qq = session.createQuery("From PersonalMedical where username = ? and parola = ?");
                qq.setParameter(0,username).setParameter(1,parola).uniqueResult();
                personalMedical = (PersonalMedical) qq.uniqueResult();
                transaction.commit();

            } catch (Exception e) {
                e.printStackTrace();
                if(transaction != null){
                    transaction.rollback();
                }
            }
        }
        return personalMedical;
    }

    @Override
    public void add(PersonalMedical e) {

    }

    @Override
    public Iterable<PersonalMedical> findAll() {
        return null;
    }

    @Override
    public void update(PersonalMedical e1, PersonalMedical e2) {

    }

    @Override
    public PersonalMedical delete(PersonalMedical e) {
        return null;
    }

    @Override
    public PersonalMedical findOne(Long aLong) {
        return null;
    }
}
