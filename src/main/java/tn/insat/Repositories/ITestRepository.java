package tn.insat.Repositories;


import tn.insat.ontologies.Test ;

import java.util.List;

/**
 * Created by Khalil on 30/04/2017.
 */
public interface ITestRepository {

    List<Test> findByCours(int id_cours);
}
