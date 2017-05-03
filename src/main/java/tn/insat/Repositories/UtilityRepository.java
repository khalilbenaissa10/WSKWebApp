package tn.insat.Repositories;

import org.hibernate.Session;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Property;
import tn.insat.Utilities.HibernateUtil;
import tn.insat.ontologies.*;

import java.util.ArrayList;

/**
 * Created by saif on 03/05/2017.
 */
public class UtilityRepository implements  IUtilityRepository {

    public int getLastCoursId(){

        Session session = HibernateUtil.createSessionFactory().openSession();


        int id = 0 ;

        DetachedCriteria maxId = DetachedCriteria.forClass(Cours.class)
                .setProjection( Projections.max("id_cours") );
       ArrayList<Cours> result = (ArrayList<Cours>) session.createCriteria(Cours.class)
                .add( Property.forName("id_cours").eq(maxId) )
                .list();

       id = result.get(0).getId_cours();

        // Clean up !
        session.close();

        return id;

    }

    public int getLastTestId(){

        Session session = HibernateUtil.createSessionFactory().openSession();


        int id = 0 ;

        DetachedCriteria maxId = DetachedCriteria.forClass(Test.class)
                .setProjection( Projections.max("id_test") );
        ArrayList<Test> result = (ArrayList<Test>) session.createCriteria(Test.class)
                .add( Property.forName("id_test").eq(maxId) )
                .list();

        id = result.get(0).getId_test();

        // Clean up !
        session.close();

        return id;

    }

    public int getLastTestEtudiantId(){

        Session session = HibernateUtil.createSessionFactory().openSession();


        int id = 0 ;

        DetachedCriteria maxId = DetachedCriteria.forClass(TestEtudiant.class)
                .setProjection( Projections.max("id_test_etudiant") );
        ArrayList<TestEtudiant> result = (ArrayList<TestEtudiant>) session.createCriteria(TestEtudiant.class)
                .add( Property.forName("id_test_etudiant").eq(maxId) )
                .list();

        id = result.get(0).getId_test_etudiant();

        // Clean up !
        session.close();

        return id;

    }

    public int getLastReponseId(){

        Session session = HibernateUtil.createSessionFactory().openSession();


        int id = 0 ;

        DetachedCriteria maxId = DetachedCriteria.forClass(ReponseEtudiant.class)
                .setProjection( Projections.max("id_reponse_etudiant") );
        ArrayList<ReponseEtudiant> result = (ArrayList<ReponseEtudiant>) session.createCriteria(ReponseEtudiant.class)
                .add( Property.forName("id_reponse_etudiant").eq(maxId) )
                .list();

        id = result.get(0).getId_reponse_etudiant();

        // Clean up !
        session.close();

        return id;

    }

    public int getLastQuestionId(){

        Session session = HibernateUtil.createSessionFactory().openSession();


        int id = 0 ;

        DetachedCriteria maxId = DetachedCriteria.forClass(Question.class)
                .setProjection( Projections.max("id_question") );
        ArrayList<Question> result = (ArrayList<Question>) session.createCriteria(Question.class)
                .add( Property.forName("id_question").eq(maxId) )
                .list();

        id = result.get(0).getId_question();

        // Clean up !
        session.close();

        return id;

    }

    public int getLastPropositionId(){

        Session session = HibernateUtil.createSessionFactory().openSession();


        int id = 0 ;

        DetachedCriteria maxId = DetachedCriteria.forClass(Proposition.class)
                .setProjection( Projections.max("id_proposition") );
        ArrayList<Proposition> result = (ArrayList<Proposition>) session.createCriteria(Proposition.class)
                .add( Property.forName("id_proposition").eq(maxId) )
                .list();

        id = result.get(0).getId_proposition();

        // Clean up !
        session.close();

        return id;

    }

}
