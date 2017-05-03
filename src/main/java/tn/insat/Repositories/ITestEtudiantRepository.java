package tn.insat.Repositories;

import tn.insat.ontologies.TestEtudiant;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Khalil on 02/05/2017.
 */
public interface ITestEtudiantRepository {

    Boolean AffecterEtudiant_Test(int id_cours, int id_etudiant);
    TestEtudiant findById(int id);
    TestEtudiant update(TestEtudiant c);
    ArrayList<TestEtudiant> findall();
    ArrayList<TestEtudiant> findBtIdEtudiant(int id_etudiant);
    ArrayList<TestEtudiant> findBtIdTest(int id_test);
}
