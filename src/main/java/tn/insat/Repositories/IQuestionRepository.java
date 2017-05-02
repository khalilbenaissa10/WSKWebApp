package tn.insat.Repositories;

import tn.insat.ontologies.Question;

import java.util.List;

/**
 * Created by Khalil on 01/05/2017.
 */
public interface IQuestionRepository {

    List<Question> findByTest(int id);
    Boolean create(Question question);
    Question findById(int id);


}
