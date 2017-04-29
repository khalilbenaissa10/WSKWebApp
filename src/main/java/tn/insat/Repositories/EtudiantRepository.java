package tn.insat.Repositories;

import org.hibernate.Session;
import tn.insat.Utilities.HibernateUtil;
import tn.insat.ontologies.Etudiant;

/**
 * Created by Khalil on 26/04/2017.
 */
public class EtudiantRepository implements IEtudiantRepository {

   public  Etudiant findById(int id){
        Session session = HibernateUtil.createSessionFactory().openSession();

        // Work with the session
        Etudiant e = (Etudiant) session.get(Etudiant.class, id);

        // Clean up !
        session.close();

        return e;
    }
}
