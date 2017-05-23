package tn.insat.Repositories ;

import tn.insat.ontologies.Cours;
import tn.insat.ontologies.Etudiant;

import java.util.ArrayList;

/**
 * Created by Khalil on 26/04/2017.
 */
public interface IEtudiantRepository {

    Etudiant findById(int id);
    Etudiant findByLoginAndPassword(String email,String password);
    Boolean create(Etudiant c);
    ArrayList<Etudiant> findAll();

}
