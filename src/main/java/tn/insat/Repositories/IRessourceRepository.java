package tn.insat.Repositories;

import tn.insat.ontologies.Ressource;

import java.util.List;

/**
 * Created by Khalil on 24/05/2017.
 */
public interface IRessourceRepository {

    Boolean create(Ressource c);

    Ressource findById(int id);

     List<Ressource> findByCours(int id);
}
