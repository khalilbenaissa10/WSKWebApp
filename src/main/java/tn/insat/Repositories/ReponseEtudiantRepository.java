package tn.insat.Repositories;

import org.hibernate.Session;
import org.hibernate.Transaction;
import tn.insat.Utilities.HibernateUtil;
import tn.insat.ontologies.Cours;
import tn.insat.ontologies.ReponseEtudiant;

/**
 * Created by Khalil on 02/05/2017.
 */
public class ReponseEtudiantRepository implements  IReponseEtudiantRepository{

    @Override
    public Boolean Create(ReponseEtudiant c) {
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
    public ReponseEtudiant findById(int id){

        Session session = HibernateUtil.createSessionFactory().openSession();

        // Work with the session
        ReponseEtudiant c = (ReponseEtudiant) session.get(ReponseEtudiant.class, id);

        // Clean up !
        session.close();

        return c;
    }
}
