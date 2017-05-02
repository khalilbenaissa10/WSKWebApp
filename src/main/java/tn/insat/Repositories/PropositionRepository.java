package tn.insat.Repositories;

import org.hibernate.Session;
import org.hibernate.Transaction;
import tn.insat.Utilities.HibernateUtil;
import tn.insat.ontologies.Proposition;
import tn.insat.ontologies.Question;
import tn.insat.ontologies.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Khalil on 01/05/2017.
 */
public class PropositionRepository implements IPropositionRepository{

    public List<Proposition> findByQuestion(int id){

        ArrayList<Proposition> lt = new ArrayList<Proposition>();
        Session session = HibernateUtil.createSessionFactory().openSession();
        Question question = (Question) session.get(Question.class,id);
        for (Proposition t: question.getPropositions()
                ) {
            lt.add(t);

        }
        // Clean up !
        session.close();
        return lt;
    }

    public Boolean create(Proposition q) {
        boolean a_retourner = false;
        Transaction transaction = null;
        try {
            Session session = HibernateUtil.createSessionFactory()
                    .openSession();
            transaction = session.beginTransaction();

            session.save(q);
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

    @Override
    public Proposition findById(int id){

        Session session = HibernateUtil.createSessionFactory().openSession();

        // Work with the session
        Proposition c = (Proposition) session.get(Proposition.class, id);

        // Clean up !
        session.close();

        return c;
    }

}
