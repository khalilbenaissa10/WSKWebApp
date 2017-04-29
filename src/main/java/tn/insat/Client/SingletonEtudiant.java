package tn.insat.Client;

import tn.insat.ontologies.Etudiant;

/**
 * Created by Khalil on 29/04/2017.
 */
public class SingletonEtudiant {



   public static Etudiant etudiant ;

    public static Etudiant getEtudiant() {
        return etudiant;
    }

    public static void setEtudiant(Etudiant etudiant) {
        SingletonEtudiant.etudiant = etudiant;
    }

}
