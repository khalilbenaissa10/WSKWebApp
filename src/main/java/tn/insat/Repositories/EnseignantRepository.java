package tn.insat.Repositories;

import tn.insat.Utilities.HibernateUtil;
import tn.insat.ontologies.Cours;
import tn.insat.ontologies.CoursEtudiant;
import tn.insat.ontologies.Enseignant;
import org.hibernate.Session;
import tn.insat.ontologies.Etudiant;

import java.util.ArrayList;

/**
 * Created by saif on 17/04/2017.
 */
public class EnseignantRepository implements  IEnseignantRepository {

    @Override
   public Enseignant findById(int id){

        Session session = HibernateUtil.createSessionFactory().openSession();

        // Work with the session
        Enseignant c = (Enseignant) session.get(Enseignant.class, id);

        // Clean up !
        session.close();

        return c;
    }

    public ArrayList<Etudiant> findEtudiantsbyid(int id_enseignant){

        ArrayList<Etudiant> le = null;

        Session session = HibernateUtil.createSessionFactory().openSession();
        Enseignant en = (Enseignant) session.get(Enseignant.class, id_enseignant);

        le = new ArrayList<Etudiant>();

        for (Cours c:en.getCours()) {

            for (CoursEtudiant ce:c.getCoursetudiant()) {
                le.add(ce.getEtudiant_asso());
            }
        }


        ArrayList<Etudiant> uniques = new ArrayList<Etudiant>();
        for (Etudiant element : le) {
            if (!uniques.contains(element)) {
                uniques.add(element);
            }
        }






        // Clean up !
        session.close();

        return uniques;


    }
}
