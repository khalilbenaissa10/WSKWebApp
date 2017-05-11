package tn.insat.Repositories;

import org.hibernate.Session;
import org.hibernate.Transaction;
import tn.insat.Client.ExampleController;
import tn.insat.Utilities.HibernateUtil;
import tn.insat.ontologies.Cours;
import tn.insat.ontologies.SujetForum;
import tn.insat.ontologies.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Khalil on 10/05/2017.
 */
public class SujetForumRepository implements  ISujetForumRepository {

    public ArrayList<SujetForum> findAll(){

        ArrayList<SujetForum> lc = null;

        Session session = HibernateUtil.createSessionFactory().openSession();
        lc = (ArrayList<SujetForum>)session.createCriteria(SujetForum.class).list();

        // Clean up !
        session.close();

        ArrayList<SujetForum> uniques = ExampleController.rendreUniquesSujetsForum(lc);

        return uniques;
    }

    public List<SujetForum> findByCours(int id_cours){


        ArrayList<SujetForum> lt = new ArrayList<SujetForum>();
        Session session = HibernateUtil.createSessionFactory().openSession();
        Cours cours = (Cours) session.get(Cours.class,id_cours);
        for (SujetForum t: cours.getSujetForums()
                ) {
            lt.add(t);

        }
        // Clean up !
        session.close();
        return lt;
    }

    @Override
    public SujetForum findById(int id){

        Session session = HibernateUtil.createSessionFactory().openSession();

        // Work with the session
        SujetForum c = (SujetForum) session.get(SujetForum.class, id);

        // Clean up !
        session.close();

        return c;
    }

    public Boolean create(SujetForum forum) {
        boolean a_retourner = false;
        Transaction transaction = null;
        try {
            Session session = HibernateUtil.createSessionFactory()
                    .openSession();
            transaction = session.beginTransaction();

            session.save(forum);
            transaction.commit();

            a_retourner = true;
        } catch (Exception e) {
            System.out.println("LOG : Exception lors de la creation du sujet forum. Détails :"
                    + e);
            if ((transaction != null) /*&& transaction.isActive()*/)
                transaction.rollback();
        }

        return a_retourner;
    }
}
