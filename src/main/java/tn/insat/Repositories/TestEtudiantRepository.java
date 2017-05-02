package tn.insat.Repositories;

import org.hibernate.Session;
import org.hibernate.Transaction;
import tn.insat.Utilities.HibernateUtil;
import tn.insat.ontologies.*;

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
}
