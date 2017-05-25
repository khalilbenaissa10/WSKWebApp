package tn.insat.Repositories;

import org.hibernate.Session;
import org.hibernate.Transaction;
import tn.insat.Utilities.HibernateUtil;
import tn.insat.ontologies.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Khalil on 23/05/2017.
 */
public class ConnaissanceEtudiantRepository implements IConnaissanceEtudiantRepository{


    public ArrayList<ConnaissanceEtudiant> findConnaissancesByEtudiant(int id_etudiant){

        ArrayList<Connaissance> le = null;

        Session session = HibernateUtil.createSessionFactory().openSession();
        Etudiant c = (Etudiant) session.get(Etudiant.class, id_etudiant);

        ArrayList<ConnaissanceEtudiant> liste_con_etud = new ArrayList<ConnaissanceEtudiant>(c.getConnaissancesetudiant());
        // Clean up !
        session.close();

        return liste_con_etud;


    }

    public ConnaissanceEtudiant findByIdConnaissanceIdEtudiant(int idConaissance,int idEtudiant){

        List<ConnaissanceEtudiant> lc = new ArrayList<ConnaissanceEtudiant>();
        ConnaissanceEtudiant cnn = null ;
        Session session = HibernateUtil.createSessionFactory().openSession();
        lc = (ArrayList<ConnaissanceEtudiant>)session.createCriteria(ConnaissanceEtudiant.class).list();

        for (ConnaissanceEtudiant c:lc
             ) {
            if((c.getConnaissance_asso().getId_connaissance()==idConaissance)&&(c.getEtudiant_asso_connaissance().getId_etudiant()==idEtudiant))
                cnn = c ;
        }

        // Clean up !
        session.close();

        return cnn ;
    }


    public ConnaissanceEtudiant update(ConnaissanceEtudiant c) {
        ConnaissanceEtudiant a_retourner = null;
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
