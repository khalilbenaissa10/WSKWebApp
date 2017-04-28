package tn.insat.Repositories;

import tn.insat.ontologies.Cours;
import tn.insat.ontologies.Etudiant;

import java.util.ArrayList;

/**
 * Created by saif on 17/04/2017.
 */
public interface ICoursEtudiantRepository {

    Boolean AffecterEtudiant_Cours(int id_cours, int id_etudiant);

    ArrayList<Etudiant> findEtudiantsbyCours(int id_cours);
}
