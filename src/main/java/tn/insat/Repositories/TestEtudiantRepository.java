package tn.insat.Repositories;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import tn.insat.Client.ExampleController;
import tn.insat.Utilities.HibernateUtil;
import tn.insat.ontologies.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Khalil on 02/05/2017.
 */
public class TestEtudiantRepository implements ITestEtudiantRepository{

  public  Boolean AffecterEtudiant_Test(int id_test, int id_etudiant){

          boolean a_retourner = false;
          Transaction transaction = null;
          try {
              Session session = HibernateUtil.createSessionFactory()
                      .openSession();
              transaction = session.beginTransaction();

              Test cr = (Test) session.get(Test.class,id_test);
              Etudiant et = (Etudiant) session.get(Etudiant.class,id_etudiant);

              TestEtudiant cret = new TestEtudiant();
              cret.setEtudiant_asso_test(et);
              cret.setTest_asso(cr);
              

              if(cr!=null && et!=null)
                  session.save(cret);

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

    public  TestEtudiant findById(int id){
        Session session = HibernateUtil.createSessionFactory().openSession();

        // Work with the session
        TestEtudiant e = (TestEtudiant) session.get(TestEtudiant.class, id);

        // Clean up !
        session.close();

        return e;
    }

    @Override
    public TestEtudiant update(TestEtudiant c) {
        TestEtudiant a_retourner = null;
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

    @Override
    public ArrayList<TestEtudiant> findall(){

        ArrayList<TestEtudiant> lc = null;

        Session session = HibernateUtil.createSessionFactory().openSession();
            lc = (ArrayList<TestEtudiant>)session.createCriteria(TestEtudiant.class).list();

        ArrayList<TestEtudiant> uniques = new ArrayList<TestEtudiant>();
        for (TestEtudiant element : lc) {
            if (!uniques.contains(element)) {
                uniques.add(element);
            }
        }

        // Clean up !
        session.close();

       // ArrayList<TestEtudiant> uniques = ExampleController.rendreUniques(lc);

        return uniques;
    }

    @Override
    public ArrayList<TestEtudiant> findBtIdEtudiant(int id_etudiant){

        ArrayList<TestEtudiant> lc = null;

        Session session = HibernateUtil.createSessionFactory().openSession();

       // TestEtudiant e = (TestEtudiant) session.get(TestEtudiant.class, id_etudiant);

        lc = (ArrayList<TestEtudiant>)session.createCriteria(TestEtudiant.class)
                .add(Restrictions.eq("etudiant_asso_test.id_etudiant",id_etudiant)).list();


        ArrayList<TestEtudiant> uniques = new ArrayList<TestEtudiant>();
        for (TestEtudiant element : lc) {
            if (!uniques.contains(element)) {
                uniques.add(element);
            }
        }

        // Clean up !
        session.close();

        return uniques;
    }

    @Override
    public ArrayList<TestEtudiant> findBtIdTest(int id_test){

        ArrayList<TestEtudiant> lc = null;

        Session session = HibernateUtil.createSessionFactory().openSession();

        // TestEtudiant e = (TestEtudiant) session.get(TestEtudiant.class, id_etudiant);

        lc = (ArrayList<TestEtudiant>)session.createCriteria(TestEtudiant.class)
                .add(Restrictions.eq("test_asso.id_test",id_test)).list();


        ArrayList<TestEtudiant> uniques = new ArrayList<TestEtudiant>();
        for (TestEtudiant element : lc) {
            if (!uniques.contains(element)) {
                uniques.add(element);
            }
        }

        // Clean up !
        session.close();

        return uniques;
    }


    public List<TestEtudiant> findByIdTestIdEtudiant(int idCours,int idEtudiant){

        List<TestEtudiant> lc = new ArrayList<TestEtudiant>();
        TestEtudiant cnn = null ;
        Session session = HibernateUtil.createSessionFactory().openSession();
        lc = (ArrayList<TestEtudiant>)session.createCriteria(TestEtudiant.class).list();
        List<TestEtudiant> retour = new ArrayList<TestEtudiant>();

        for (TestEtudiant c:lc
                ) {
            if((c.getTest_asso().getCours_test().getId_cours()==idCours)&&(c.getEtudiant_asso_test().getId_etudiant()==idEtudiant))
                retour.add(c);
        }



        // Clean up !
        session.close();
        return ExampleController.rendreUniquesTests(retour) ;

    }
}
