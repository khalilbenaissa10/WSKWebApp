package tn.insat.Repositories;

import tn.insat.ontologies.Enseignant;
import tn.insat.ontologies.Etudiant;

import java.util.ArrayList;

/**
 * Created by saif on 17/04/2017.
 */
public interface IEnseignantRepository {

    Enseignant findById(int id);

    ArrayList<Etudiant> findEtudiantsbyid(int id_enseignant);

    Boolean create(Enseignant c);

     Enseignant findByLoginAndPassword(String email,String password);
}
