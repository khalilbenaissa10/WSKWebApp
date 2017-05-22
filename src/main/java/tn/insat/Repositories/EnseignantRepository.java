package tn.insat.Repositories;

import org.hibernate.Query;
import org.hibernate.Transaction;
import tn.insat.Client.ExampleController;
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


        ArrayList<Etudiant> uniques = ExampleController.rendreUniquesEtudiants(le);






        // Clean up !
        session.close();

        return uniques;


    }

    public Boolean create(Enseignant c) {
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

    public Enseignant findByLoginAndPassword(String email,String password){
        Session session = HibernateUtil.createSessionFactory().openSession();

        // Work with the session
        Query query = session.createQuery("SELECT a FROM Enseignant a WHERE a.email_enseignant=:email and a.password_enseignant=:password");
        query.setParameter("email", email);
        query.setParameter("password", password);
        Enseignant ens = (Enseignant)query.uniqueResult();
        if(ens==null){
            ens = new Enseignant();
            ens.setId_enseignant(0);
            ens.setNom_enseignant("dummy ben dummy");
            ens.setAge_enseignant(0);
            ens.setCategory_enseignant("dummy");
            ens.setInstitut_enseignant("dummy institute");
            ens.setPassword_enseignant("dummy password");
            ens.setEmail_enseignant("dummy email");
        }

        // Clean up !
        session.close();

        return ens;
    }

}
