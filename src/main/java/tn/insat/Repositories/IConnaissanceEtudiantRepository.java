package tn.insat.Repositories;

import tn.insat.ontologies.Connaissance;
import tn.insat.ontologies.ConnaissanceEtudiant;

import java.util.ArrayList;

/**
 * Created by Khalil on 23/05/2017.
 */
public interface IConnaissanceEtudiantRepository {

     ArrayList<ConnaissanceEtudiant> findConnaissancesByEtudiant(int id_etudiant);

     ConnaissanceEtudiant findByIdConnaissanceIdEtudiant(int idConaissance,int idEtudiant);

     ConnaissanceEtudiant update(ConnaissanceEtudiant c);

}
