package tn.insat.Repositories;

import tn.insat.ontologies.Cours;
import tn.insat.ontologies.SujetForum;
import tn.insat.ontologies.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Khalil on 10/05/2017.
 */
public interface ISujetForumRepository {

    ArrayList<SujetForum> findAll();
    List<SujetForum> findByCours(int id_cours);
    SujetForum findById(int id);
}
