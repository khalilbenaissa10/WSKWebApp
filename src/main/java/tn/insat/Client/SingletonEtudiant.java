package tn.insat.Client;

import tn.insat.ontologies.Etudiant;
import tn.insat.ontologies.Ressource;

/**
 * Created by Khalil on 29/04/2017.
 */
public class SingletonEtudiant {



   public static Etudiant etudiant ;

   public static Ressource ressource ;

    public static Ressource getRessource() {
        return ressource;
    }

    public static void setRessource(Ressource ressource) {
        SingletonEtudiant.ressource = ressource;
    }

    public static Etudiant getEtudiant() {
        return etudiant;
    }

    public static void setEtudiant(Etudiant etudiant) {
        SingletonEtudiant.etudiant = etudiant;
    }

}
