package repository;

import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class Sesiune {

    private static SessionFactory sessionFactory = initialize();

    private Sesiune(){

    }
    static SessionFactory initialize() {
        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure()
                .build();
        try {
            sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
            return sessionFactory;
        }
        catch (Exception e) {
            e.printStackTrace();
            StandardServiceRegistryBuilder.destroy(registry);
        }
        return null;
    }

    public static SessionFactory getInstance(){
        return sessionFactory;
    }


    static void close(){
        if(sessionFactory != null){
            sessionFactory.close();
        }
    }
}
