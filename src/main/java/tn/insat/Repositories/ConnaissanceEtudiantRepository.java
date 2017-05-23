package tn.insat.Repositories;

import org.hibernate.Session;
import tn.insat.Utilities.HibernateUtil;
import tn.insat.ontologies.*;

import java.util.ArrayList;

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

}
