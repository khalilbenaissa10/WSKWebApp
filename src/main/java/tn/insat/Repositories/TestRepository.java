package tn.insat.Repositories;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import tn.insat.Utilities.HibernateUtil;
import tn.insat.ontologies.Cours;
import tn.insat.ontologies.CoursEtudiant;
import tn.insat.ontologies.Etudiant;
import tn.insat.ontologies.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Khalil on 30/04/2017.
 */
public class TestRepository implements  ITestRepository {

   public List<Test> findByCours(int id_cours){


        ArrayList<Test> lt = new ArrayList<Test>();
        Session session = HibernateUtil.createSessionFactory().openSession();
        Cours cours = (Cours) session.get(Cours.class,id_cours);
        for (Test t: cours.getTests()
            ) {
                lt.add(t);

        }
        // Clean up !
        session.close();
        if (lt.size()==0){
            Test testdummy = new Test();
            testdummy.setId_test(0);
            testdummy.setNom_test("dummy test");
            testdummy.setDuree_test("00");
            testdummy.setCours_test(cours);
            lt.add(testdummy);
        }
        return lt;
    }

    @Override
    public Boolean create(Test t) {
        boolean a_retourner = false;
        Transaction transaction = null;
        try {
            Session session = HibernateUtil.createSessionFactory()
                    .openSession();
            transaction = session.beginTransaction();

            session.save(t);
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
    public Test findById(int id_test){

        Session session = HibernateUtil.createSessionFactory().openSession();

        // Work with the session
        Test c = (Test) session.get(Test.class, id_test);

        // Clean up !
        session.close();

        return c;
    }

}
