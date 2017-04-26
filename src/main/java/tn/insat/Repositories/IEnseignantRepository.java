package tn.insat.Repositories;

import tn.insat.ontologies.Enseignant;

/**
 * Created by saif on 17/04/2017.
 */
public interface IEnseignantRepository {

    Enseignant findById(int id);
}
