package tn.insat.Repositories;

import org.hibernate.Session;
import org.hibernate.Transaction;
import tn.insat.Utilities.HibernateUtil;
import tn.insat.ontologies.Cours;
import tn.insat.ontologies.Question;
import tn.insat.ontologies.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Khalil on 01/05/2017.
 */
public class QuestionRepository implements IQuestionRepository {

    public List<Question> findByTest(int id){

        ArrayList<Question> lt = new ArrayList<Question>();
        Session session = HibernateUtil.createSessionFactory().openSession();
        Test test = (Test) session.get(Test.class,id);
        for (Question t: test.getTestquestions()
                ) {
            lt.add(t);

        }
        // Clean up !
        session.close();
        return lt;
    }


    @Override
    public Boolean create(Question q) {
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
    public Question findById(int id){

        Session session = HibernateUtil.createSessionFactory().openSession();

        // Work with the session
        Question c = (Question) session.get(Question.class, id);

        // Clean up !
        session.close();

        return c;
    }
}
