package tn.insat.Repositories;

import tn.insat.ontologies.Proposition;
import tn.insat.ontologies.Question;

import java.util.List;

/**
 * Created by Khalil on 01/05/2017.
 */
public interface IPropositionRepository {

    List<Proposition> findByQuestion(int id);
    Boolean create(Proposition p);
    Proposition findById(int id);


}
