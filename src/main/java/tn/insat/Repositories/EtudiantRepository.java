package tn.insat.Repositories;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import tn.insat.Client.ExampleController;
import tn.insat.Utilities.HibernateUtil;
import tn.insat.ontologies.Cours;
import tn.insat.ontologies.Etudiant;

import java.util.ArrayList;

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

    public Boolean create(Etudiant c) {
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

    public Etudiant findByLoginAndPassword(String email,String password){
        Session session = HibernateUtil.createSessionFactory().openSession();

        // Work with the session
        Query query = session.createQuery("SELECT a FROM Etudiant a WHERE a.email_etudiant=:email and a.password_etudiant=:password");
        query.setParameter("email", email);
        query.setParameter("password", password);
        Etudiant etd = (Etudiant)query.uniqueResult();
        if(etd==null){
            etd = new Etudiant();
            etd.setId_etudiant(0);
            etd.setNom_etudiant("dummy ben dummy");
            etd.setAge_etudiant(0);
            etd.setCategory_etudiant("dummy");
            etd.setInstitut_etudiant("dummy institute");
            etd.setPassword_etudiant("dummy password");
            etd.setEmail_etudiant("dummy email");
        }

        // Clean up !
        session.close();

        return etd;
    }

    public ArrayList<Etudiant> findAll(){

        ArrayList<Etudiant> lc = null;

        Session session = HibernateUtil.createSessionFactory().openSession();
        lc = (ArrayList<Etudiant>)session.createCriteria(Etudiant.class).list();

        // Clean up !
        session.close();

        ArrayList<Etudiant> uniques = ExampleController.rendreUniquesEtudiants(lc);

        return uniques;
    }


}
