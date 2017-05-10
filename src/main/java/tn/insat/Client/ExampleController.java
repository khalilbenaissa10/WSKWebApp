package tn.insat.Client;


import tn.insat.ontologies.*;

import java.util.ArrayList;
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
    public static TestEtudiant testEtudiant ;
    public static List<Etudiant> liste_etudiant;
   public static Cours cours ;
   public static CoursEtudiant cours_etudiant ;
   public static List<SujetForum> sujets_forums ;

   public static SujetForum sujet_forum ;

    public static List<Test> getListeTest() {
        return ListeTest;
    }

    public static void setListeTest(List<Test> listeTest) {
        ListeTest = listeTest;
    }

    private static List<Test> ListeTest ;

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

    public static ArrayList<Cours> rendreUniques(List<Cours> le){
        ArrayList<Cours> uniques = new ArrayList<Cours>();
        for (Cours element : le) {
            if (!uniques.contains(element)) {
                uniques.add(element);
            }
        }
        return uniques;
    }

    public static ArrayList<SujetForum> rendreUniquesSujetsForum(List<SujetForum> le){
        ArrayList<SujetForum> uniques = new ArrayList<SujetForum>();
        for (SujetForum element : le) {
            if (!uniques.contains(element)) {
                uniques.add(element);
            }
        }
        return uniques;
    }

    public static ArrayList<Etudiant> rendreUniquesEtudiants(List<Etudiant> le){
        ArrayList<Etudiant> uniques = new ArrayList<Etudiant>();
        for (Etudiant element : le) {
            if (!uniques.contains(element)) {
                uniques.add(element);
            }
        }
        return uniques;
    }

    public static TestEtudiant getTestEtudiant() {
        return testEtudiant;
    }

    public static void setTestEtudiant(TestEtudiant testEtudiant) {
        ExampleController.testEtudiant = testEtudiant;
    }

    public static List<SujetForum> getSujets_forums() {
        return sujets_forums;
    }

    public static void setSujets_forums(List<SujetForum> sujets_forums) {
        ExampleController.sujets_forums = sujets_forums;
    }

    public static SujetForum getSujet_forum() {
        return sujet_forum;
    }

    public static void setSujet_forum(SujetForum sujet_forum) {
        ExampleController.sujet_forum = sujet_forum;
    }
}
