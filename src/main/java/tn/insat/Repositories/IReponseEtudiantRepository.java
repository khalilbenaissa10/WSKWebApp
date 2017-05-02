package tn.insat.Repositories;

import tn.insat.ontologies.ReponseEtudiant;

/**
 * Created by Khalil on 02/05/2017.
 */
public interface IReponseEtudiantRepository {
    Boolean Create(ReponseEtudiant reponse);
    ReponseEtudiant findById(int id);
}
