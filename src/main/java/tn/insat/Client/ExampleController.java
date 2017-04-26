package tn.insat.Client;


import tn.insat.ontologies.Cours;
import tn.insat.ontologies.CoursEtudiant;

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

    public static List<Cours> liste ;
   public static Cours cours ;
   public static CoursEtudiant cours_etudiant ;

    public static List<Cours> getListe() {
        return liste;
    }

    public static void setListe(List<Cours> liste) {
        ExampleController.liste = liste;
    }

    public static Cours getCours() {
        return cours;
    }

    public static void setCours(Cours cours) {
        ExampleController.cours = cours;
    }
}
