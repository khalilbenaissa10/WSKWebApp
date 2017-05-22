package tn.insat.Client;

import java.util.concurrent.Semaphore;

/**
 * Created by Khalil on 26/04/2017.
 */
public class SemaphoreClass {
    static public Semaphore available = new Semaphore(0,true);

    static public Semaphore available2 = new Semaphore(0,true);

    static public Semaphore listeAllcours_sem = new Semaphore(0,true);

    static public Semaphore informationCours_sem = new Semaphore(0,true);

    static public Semaphore listeCoursEnseignant_sem = new Semaphore(0,true);

    static public Semaphore listeEtudiantCours_sem = new Semaphore(0,true);

    static public Semaphore listeCoursEtudiant_sem = new Semaphore(0,true);

    static public Semaphore informationEtudiant_sem = new Semaphore(0,true);

    static public Semaphore listTest_sem = new Semaphore(0,true);

    static public Semaphore listQuestion_sem = new Semaphore(0,true);

    static public Semaphore listProposition_sem = new Semaphore(0,true);

    static public Semaphore listTestEtudiant_sem = new Semaphore(0,true);

    static public Semaphore listeAllsujetsForum_sem = new Semaphore(0,true);

    static public Semaphore informationSujetForum_sem = new Semaphore(0,true);

    static public Semaphore informationEnseignant_sem = new Semaphore(0,true);

    static public Semaphore listeReponseForum_sem = new Semaphore(0,true);

    static public Semaphore informationReponseForum_sem = new Semaphore(0,true);

    static public Semaphore loginEtudiant_sem = new Semaphore(0,true);

}
