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


    public int getLastReponseForumId(){

        Session session = HibernateUtil.createSessionFactory().openSession();


        int id = 0 ;

        DetachedCriteria maxId = DetachedCriteria.forClass(ReponseForum.class)
                .setProjection( Projections.max("id_reponseforum") );
        ArrayList<ReponseForum> result = (ArrayList<ReponseForum>) session.createCriteria(ReponseForum.class)
                .add( Property.forName("id_reponseforum").eq(maxId) )
                .list();

        id = result.get(0).getId_reponseforum();

        // Clean up !
        session.close();

        return id;

    }

    public int getLastSujetForumId(){

        Session session = HibernateUtil.createSessionFactory().openSession();


        int id = 0 ;

        DetachedCriteria maxId = DetachedCriteria.forClass(SujetForum.class)
                .setProjection( Projections.max("id_sujetforum") );
        ArrayList<SujetForum> result = (ArrayList<SujetForum>) session.createCriteria(SujetForum.class)
                .add( Property.forName("id_sujetforum").eq(maxId) )
                .list();

        id = result.get(0).getId_sujetforum();

        // Clean up !
        session.close();

        return id;

    }

    public int getLastEtudiantsId(){

        Session session = HibernateUtil.createSessionFactory().openSession();


        int id = 0 ;

        DetachedCriteria maxId = DetachedCriteria.forClass(Etudiant.class)
                .setProjection( Projections.max("id_etudiant") );
        ArrayList<Etudiant> result = (ArrayList<Etudiant>) session.createCriteria(Etudiant.class)
                .add( Property.forName("id_etudiant").eq(maxId) )
                .list();

        id = result.get(0).getId_etudiant();

        // Clean up !
        session.close();

        return id;

    }


    public int getLastEnseignantsId(){

        Session session = HibernateUtil.createSessionFactory().openSession();


        int id = 0 ;

        DetachedCriteria maxId = DetachedCriteria.forClass(Enseignant.class)
                .setProjection( Projections.max("id_enseignant") );
        ArrayList<Enseignant> result = (ArrayList<Enseignant>) session.createCriteria(Enseignant.class)
                .add( Property.forName("id_enseignant").eq(maxId) )
                .list();

        id = result.get(0).getId_enseignant();

        // Clean up !
        session.close();

        return id;

    }
}
