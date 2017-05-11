package tn.insat.Repositories;

import tn.insat.ontologies.ReponseForum;

import java.util.List;

/**
 * Created by saif on 11/05/2017.
 */
public interface IReponseForumRepository{

 List<ReponseForum> findBySujet(int id_sujet);

    Boolean create(ReponseForum reponseForum);

    ReponseForum findById(int id_reponseforum);
}
