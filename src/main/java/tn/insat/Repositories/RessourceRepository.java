package tn.insat.Repositories;

import org.hibernate.Session;
import org.hibernate.Transaction;
import tn.insat.Utilities.HibernateUtil;
import tn.insat.ontologies.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Khalil on 24/05/2017.
 */
public class RessourceRepository implements  IRessourceRepository{

    public Boolean create(Ressource c) {
        boolean a_retourner = false;
        Transaction transaction = null;
        try {
            Session session = HibernateUtil.createSessionFactory()
                    .openSession();
            transaction = session.beginTransaction();

            session.save(c);
            transaction.commit();

            a_retourner = true;
        } catch (Exception e) {
            System.out.println("LOG : Exception lors de la creation. Détails :"
                    + e);
            if ((transaction != null) /*&& transaction.isActive()*/)
                transaction.rollback();
        }

        return a_retourner;
    }

    public Ressource findById(int id){

        Session session = HibernateUtil.createSessionFactory().openSession();

        // Work with the session
        Ressource c = (Ressource) session.get(Ressource.class, id);

        // Clean up !
        session.close();

        return c;
    }


    public List<Ressource> findByCours(int id){

        ArrayList<Ressource> lt = new ArrayList<Ressource>();
        Session session = HibernateUtil.createSessionFactory().openSession();
        Cours cours = (Cours) session.get(Cours.class,id);
        for (Ressource t: cours.getRessources()
                ) {
            lt.add(t);

        }
        // Clean up !
        session.close();
        return lt;
    }

}
