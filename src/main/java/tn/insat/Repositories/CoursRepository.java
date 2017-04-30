package tn.insat.Repositories;

import org.hibernate.criterion.Criterion;
import tn.insat.Utilities.HibernateUtil;
import tn.insat.ontologies.Cours;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import tn.insat.ontologies.CoursEtudiant;
import tn.insat.ontologies.Etudiant;

import java.util.ArrayList;

/**
 * Created by Khalil on 16/04/2017.
 */
public class CoursRepository implements  ICoursRepository{

    @Override
    public Boolean create(Cours c) {
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

    @Override
    public Cours findById(int id){

        Session session = HibernateUtil.createSessionFactory().openSession();

        // Work with the session
        Cours c = (Cours) session.get(Cours.class, id);

        // Clean up !
        session.close();

        return c;
    }

    @Override
    public ArrayList<Cours> findByEnseignant(int id_enseignant){

        ArrayList<Cours> lc = new ArrayList<Cours>();

        Session session = HibernateUtil.createSessionFactory().openSession();
        Criteria cr = session.createCriteria(Cours.class);
        cr.add(Restrictions.eq("enseignant.id_enseignant",id_enseignant));

        // Work with the session
         lc =  (ArrayList<Cours>) cr.list();

        ArrayList<Cours> uniques = new ArrayList<Cours>();
        for (Cours element : lc) {
            if (!uniques.contains(element)) {
                uniques.add(element);
            }
        }

        // Clean up !
        session.close();

        return uniques;


    }


    public ArrayList<Cours> findAll(){

        ArrayList<Cours> lc = null;

        Session session = HibernateUtil.createSessionFactory().openSession();
        lc = (ArrayList<Cours>)session.createCriteria(Cours.class).list();

        // Clean up !
        session.close();

        return lc;
    }


    public ArrayList<Cours> findCoursLikkSearch(String search){

        ArrayList<Cours> lc = null;

        Session session = HibernateUtil.createSessionFactory().openSession();
        Criteria cr = session.createCriteria(Cours.class);
        Criterion c1 = Restrictions.like("intitule", "%"+search+"%");
        cr.add(c1);

        // Work with the session
        lc =  (ArrayList<Cours>) cr.list();

        // Clean up !
        session.close();

        return lc;
    }

    public ArrayList<Cours> findCoursLikkSearchDescription(String search){

        ArrayList<Cours> lc = null;

        Session session = HibernateUtil.createSessionFactory().openSession();
        Criteria cr = session.createCriteria(Cours.class);
        Criterion c1 = Restrictions.like("description", "%"+search+"%");
        cr.add(c1);

        // Work with the session
        lc =  (ArrayList<Cours>) cr.list();

        // Clean up !
        session.close();

        return lc;
    }

    public ArrayList<Cours> findByEtudiant(int id_etudiant){

        ArrayList<Cours> lc = new ArrayList<Cours>();

        Session session = HibernateUtil.createSessionFactory().openSession();
        Etudiant etudiant = (Etudiant)  session.get(Etudiant.class,id_etudiant);
        for (CoursEtudiant c:etudiant.getCoursetudiant()
                ) {
            lc.add(c.getCours_asso());
        }



        // Clean up !
        session.close();

        return lc;


    }

}