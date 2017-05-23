package tn.insat.Repositories;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import tn.insat.Utilities.HibernateUtil;
import tn.insat.ontologies.ConnaissanceEtudiant;
import tn.insat.ontologies.Cours;
import tn.insat.ontologies.CoursEtudiant;
import tn.insat.ontologies.Etudiant;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;

/**
 * Created by saif on 17/04/2017.
 */
public class CoursEtudiantRepository implements ICoursEtudiantRepository {

    @Override
    public Boolean AffecterEtudiant_Cours(int id_cours,int id_etudiant){
        boolean a_retourner = false;
        Transaction transaction = null;
        try {
            Session session = HibernateUtil.createSessionFactory()
                    .openSession();
            transaction = session.beginTransaction();

            Cours cr = (Cours) session.get(Cours.class,id_cours);
            Etudiant et = (Etudiant) session.get(Etudiant.class,id_etudiant);

            CoursEtudiant cret = new CoursEtudiant();
            cret.setCours_asso(cr);
            cret.setEtudiant_asso(et);

            if(cr!=null && et!=null)
            session.save(cret);


            ConnaissanceEtudiant con_et = new ConnaissanceEtudiant();
            con_et.setEtudiant_asso_connaissance(et);
            con_et.setConnaissance_asso(cr.getConnaissance());
            if(cr.getDescription().equals(et.getCategory_etudiant())){
                con_et.setRating(3);
            }
            else{
                con_et.setRating(0);
            }

            session.save(con_et);

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

    public ArrayList<Etudiant> findEtudiantsbyCours(int id_cours){

        ArrayList<Etudiant> le = null;

        Session session = HibernateUtil.createSessionFactory().openSession();
        Cours c = (Cours) session.get(Cours.class, id_cours);

        le = new ArrayList<Etudiant>();

        for (CoursEtudiant ce:c.getCoursetudiant()) {
            le.add(ce.getEtudiant_asso());
        }


        // Clean up !
        session.close();

        return le;


    }


}
