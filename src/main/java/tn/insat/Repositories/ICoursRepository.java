package tn.insat.Repositories;


import tn.insat.ontologies.Cours;

import java.util.ArrayList;

/**
 * Created by Khalil on 16/04/2017.
 */
public interface ICoursRepository {

    Boolean create(Cours c);
    Cours findById(int id);
    ArrayList<Cours> findByEnseignant(int id_enseignant);
    ArrayList<Cours> findAll();
    ArrayList<Cours> findCoursLikkSearch(String search);
    ArrayList<Cours> findCoursLikkSearchDescription(String search);

}
