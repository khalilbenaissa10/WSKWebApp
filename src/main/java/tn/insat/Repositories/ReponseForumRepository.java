package tn.insat.Repositories;

import org.hibernate.Session;
import org.hibernate.Transaction;
import tn.insat.Utilities.HibernateUtil;
import tn.insat.ontologies.Cours;
import tn.insat.ontologies.ReponseForum;
import tn.insat.ontologies.SujetForum;
import tn.insat.ontologies.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by saif on 11/05/2017.
 */
public class ReponseForumRepository implements  IReponseForumRepository{


    public List<ReponseForum> findBySujet(int id_sujet){


        ArrayList<ReponseForum> lt = new ArrayList<ReponseForum>();
        Session session = HibernateUtil.createSessionFactory().openSession();
        SujetForum sujet = (SujetForum) session.get(SujetForum.class,id_sujet);
        for (ReponseForum t: sujet.getReponsesforum()
                ) {
            lt.add(t);

        }
        // Clean up !
        session.close();
        if (lt.size()==0){
            ReponseForum testdummy = new ReponseForum();
            testdummy.setId_reponseforum(0);
            testdummy.setText_reponseforum("this is not a real response");
            testdummy.setSujetforum_reponseforum(sujet);
            lt.add(testdummy);
        }

        return lt;
    }

    public ReponseForum findById(int id_reponseforum){

        Session session = HibernateUtil.createSessionFactory().openSession();

        // Work with the session
        ReponseForum c = (ReponseForum) session.get(ReponseForum.class, id_reponseforum);

        // Clean up !
        session.close();

        return c;
    }

    public Boolean create(ReponseForum reponseForum) {
        boolean a_retourner = false;
        Transaction transaction = null;
        try {
            Session session = HibernateUtil.createSessionFactory()
                    .openSession();
            transaction = session.beginTransaction();

            session.save(reponseForum);
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

    @Override
    public ReponseForum update(ReponseForum c) {
        ReponseForum a_retourner = null;
        Session session = HibernateUtil.createSessionFactory().openSession();

        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.update(c);
            transaction.commit();
            a_retourner = c;
        } catch (Exception e) {
            System.out.println("LOG : Exception lors de Update. Détails => "+e);
            if (transaction != null)
                transaction.rollback();

        }

        return a_retourner;
    }
}
