package tn.insat.Utilities;

import com.fasterxml.classmate.AnnotationConfiguration;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import tn.insat.ontologies.*;

public class HibernateUtil {

    private static final SessionFactory sessionFactory;
    private static final ServiceRegistry serviceRegistry;

    // The code below guaranties that our SessionFactory is a SINGLETON !
    // Reminder : Static initilization blocs are executed once when the
    // classloader
    // loads the class !
    static {
        try {
            // Initialize the hibernate configuration.
           // Configuration configuration = new Configuration().configure();

            Configuration configuration =  new Configuration()
                    .addAnnotatedClass(Enseignant.class)
                    .addAnnotatedClass(Etudiant.class)
                    .addAnnotatedClass(Cours.class)
                    .addAnnotatedClass(Seance.class)
                    .addAnnotatedClass(Test.class)
                    .addAnnotatedClass(TestEtudiant.class)
                    .addAnnotatedClass(CoursEtudiant.class)
                    .addAnnotatedClass(Ressource.class)
                    .addAnnotatedClass(Connaissance.class)
                    .addAnnotatedClass(Question.class)
                    .addAnnotatedClass(Proposition.class)
                    .addAnnotatedClass(SujetForum.class)
                    .addAnnotatedClass(ReponseForum.class)
                    .addAnnotatedClass(ReponseEtudiant.class)
                    .configure();

            // Build a service registry from the properties of the configuration
            // –
            // This is new in Hibernate 4 !
            serviceRegistry = new StandardServiceRegistryBuilder()
                    .applySettings(configuration.getProperties()).build();

            // Build the SessionFactory !
            sessionFactory = configuration.buildSessionFactory(serviceRegistry);

        } catch (Throwable ex) {
            // Log the exception.
            System.err.println("Initial SessionFactory creation failed." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static SessionFactory createSessionFactory() {
        return sessionFactory;
    }

}
