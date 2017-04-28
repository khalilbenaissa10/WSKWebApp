package tn.insat.Client;


import tn.insat.ontologies.Cours;
import tn.insat.ontologies.CoursEtudiant;
import tn.insat.ontologies.Etudiant;

import java.util.List;

/**
 * Created by Khalil on 26/04/2017.
 */
public  class ExampleController {
    public static CoursEtudiant getCours_etudiant() {
        return cours_etudiant;
    }

    public static void setCours_etudiant(CoursEtudiant cours_etudiant) {
        ExampleController.cours_etudiant = cours_etudiant;
    }

    public static List<Cours> liste_cours;
    public static List<Etudiant> liste_etudiant;
   public static Cours cours ;
   public static CoursEtudiant cours_etudiant ;

    public static List<Cours> getListe_cours() {
        return liste_cours;
    }

    public static void setListe_cours(List<Cours> liste_cours) {
        ExampleController.liste_cours = liste_cours;
    }

    public static Cours getCours() {
        return cours;
    }

    public static void setCours(Cours cours) {
        ExampleController.cours = cours;
    }

    public static List<Etudiant> getListe_etudiant() {
        return liste_etudiant;
    }

    public static void setListe_etudiant(List<Etudiant> liste_etudiant) {
        ExampleController.liste_etudiant = liste_etudiant;
    }
}
