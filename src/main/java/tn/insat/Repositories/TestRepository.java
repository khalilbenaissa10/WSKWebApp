package tn.insat.Repositories;

import org.hibernate.Criteria;
import org.hibernate.Session;
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
        return lt;
    }
}
