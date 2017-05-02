package tn.insat.Repositories;

import tn.insat.ontologies.TestEtudiant;

/**
 * Created by Khalil on 02/05/2017.
 */
public interface ITestEtudiantRepository {

    Boolean AffecterEtudiant_Test(int id_cours, int id_etudiant);
    TestEtudiant findById(int id);
    TestEtudiant update(TestEtudiant c);
}
