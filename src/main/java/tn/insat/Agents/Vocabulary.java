/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.insat.Agents;

/**
 *
 * @author saif
 */
public interface Vocabulary {
    
    public static final int COURS_INTROUVABLE = 8;
    public static final int ETUDIANT_INTROUVABLE = 9 ;
    
    public static final String MSG_COURS_INTOUVABLE = "Cours Introuvable !!!";
    public static final String SERVER_AGENT = "Server Agent";
     
    
    
    //cours
   public static final String COURS = "Cours";
   public static final String COURS_ID = "Id_cours";
   public static final String COURS_INTITULE = "intitule";
   public static final String COURS_DUREE = "duree";
   public static final String COURS_ENSEIGNANT = "enseignant";
   public static final String COURS_SEANCES = "seances";
   public static final String COURS_TESTS = "tests";
   public static final String COURS_COURSETUDIANT= "coursetudiant";
   public static final String COURS_DESCRIPTION= "description";

   //SujetForum
   public static final String SUJET_FORUM = "SujetForum";
   public static final String SUJET_FORUM_ID = "id_sujetforum";
   public static final String SUJET_FORUM_TITRE = "titre_sujetforum";
   public static final String SUJET_FORUM_TEXT = "text_sujetforum";
   public static final String SUJET_FORUM_ENSEIGNANT= "enseignant_sujetforum";
   public static final String SUJET_FORUM_ETUDIANT = "etudiant_sujetforum";
   public static final String SUJET_FORUM_COURS = "cours_sujetforum";

   //ReponseForum
   public static final String REPONSE_FORUM = "ReponseForum";
   public static final String REPONSE_FORUM_ID = "id_reponseforum";
   public static final String REPONSE_FORUM_TEXT = "text_reponseforum";
   public static final String REPONSE_FORUM_SUJET_FORUM = "sujetforum_reponseforum";
   public static final String REPONSE_FORUM_APPRECIATED_FORUM = "appreciated_reponseforum";
   public static final String REPONSE_FORUM_ENSEIGNANT= "enseignant_reponseforum";
   public static final String REPONSE_FORUM_ETUDIANT = "etudiant_reponseforum";
   //test
   public static final String TEST = "Test";
   public static final String TEST_ID = "id_test";
   public static final String TEST_NOM = "nom_test";
   public static final String TEST_DUREE = "duree_test";
   public static final String TEST_COURS = "cours_test";
   public static final String TEST_ETUDIANTS = "testetudiant";
   public static final String TEST_TEST_QUESTIONS = "testquestions";

   //Proposition
   public static final String PROPOSITION = "Proposition";
   public static final String PROPOSITION_ID = "id_proposition";
   public static final String PROPOSITION_TEXT_PROPOSITION = "text_proposition";
   public static final String PROPOSITION_VALID_PROPOSITION = "valid_proposition";
   public static final String PROPOSITION_QUESTION_PROPOSITION = "question_proposition";

   //create Reponse Etudiant
   public static final String REPONSEETUDIANT = "Reponse_etudiant";
   public static final String REPONSE_ETUDIANT_ID = "id_reponse_etudiant";
   public static final String REPONSE_ETUDIANT_TEXT = "text_reponse_etudiant";
   public static final String REPONSE_ETUDIANT_VALID = "valid_reponse_etudiant";
   public static final String REPONSE_ETUDIANT_TEST_ETUDIANT = "testetudiant";

   //create cours
   public static final String CREATE_COURS = "Create_cours";
   public static final String CREATE_COURS_ID = "id_cours";
   public static final String CREATE_COURS_INTITULE = "intitule";
   public static final String CREATE_COURS_DUREE = "duree";
   public static final String CREATE_COURS_DESCRIPTION = "DESCRIPTION";
   public static final String CREATE_COURS_ENSEIGNANT = "enseignant";



   //create SujetForum
   public static final String CREATE_SUJET_FORUM = "Create_sujet_forum";
   public static final String CREATE_SUJET_FORUM_ID = "id_sujetforum";
   public static final String CREATE_SUJET_FORUM_TITRE = "titre_sujetforum";
   public static final String CREATE_SUJET_FORUM_TEXT = "text_sujetforum";
   public static final String CREATE_SUJET_FORUM_ENSEIGNANT= "enseignant_sujetforum";
   public static final String CREATE_SUJET_FORUM_ETUDIANT = "etudiant_sujetforum";
   public static final String CREATE_SUJET_FORUM_COURS = "cours_sujetforum";

   //create ReponseForum
   public static final String CREATE_REPONSE_FORUM = "Create_reponse_forum";
   public static final String CREATE_REPONSE_FORUM_ID = "id_reponseforum";
   public static final String CREATE_REPONSE_FORUM_TEXT = "text_reponseforum";
   public static final String CREATE_REPONSE_FORUM_ENSEIGNANT= "enseignant_reponseforum";
   public static final String CREATE_REPONSE_FORUM_ETUDIANT = "etudiant_reponseforum";
   public static final String CREATE_REPONSE_FORUM_SUJET = "sujet_reponseforum";


   //affecter cours
   public static final String AFFECTER_COURS = "Affecter_cours";
   public static final String AFFECTER_COURS_ID_COURS = "id_cours";
   public static final String AFFECTER_COURS_ID_ETUDIANT = "id_etudiant";

   //affecter test
   public static final String AFFECTER_TEST = "Affecter_test";
   public static final String AFFECTER_TEST_ID_TEST = "id_test";
   public static final String AFFECTER_TEST_ID_ETUDIANT= "id_etudiant";

   //create test
   public static final String CREATE_TEST = "Create_test";
   public static final String CREATE_TEST_ID_COURS = "id_cours";
   public static final String CREATE_TEST_ID_TEST = "id_test";
   public static final String CREATE_TEST_NOM = "nom_test";
   public static final String CREATE_TEST_DUREE = "duree_test";

   //create Reponse Etudiant
   public static final String CREATE_REPONSE_ETUDIANT = "Create_reponse_etudiant";
   public static final String CREATE_REPONSE_ETUDIANT_ID = "id_reponse_etudiant";
   public static final String CREATE_REPONSE_ETUDIANT_TEXT = "text_reponse_etudiant";
   public static final String CREATE_REPONSE_ETUDIANT_VALID = "valid_reponse_etudiant";
   public static final String CREATE_REPONSE_ETUDIANT_TEST_ETUDIANT = "testetudiant";

   //create question
   public static final String CREATE_QUESTION = "Create_question";
   public static final String CREATE_QUESTION_TEST = "test";
   public static final String CREATE_QUESTION_ID_QUESTION = "id_question";
   public static final String CREATE_QUESTION_ENONCE = "enonce_question";

   //create  Proposition
   public static final String CREATE_PROPOSITION= "Create_proposition";
   public static final String CREATE_PROPOSITION_QUESTION = "question";
   public static final String CREATE_PROPOSITION_VALID_PROPOSITION = "valid_proposition";
   public static final String CREATE_PROPOSITION_TEXT_PROPOSITION = "text_proposition";
   public static final String CREATE_PROPOSITION_ID_PROPOSITION = "id_proposition";


   //passer test
   public static final String PASSER_TEST = "Passer_test";
   public static final String PASSER_TEST_ID_TEST = "id_test";
   public static final String PASSER_TEST_ID_ETUDIANT = "id_etudiant";
   public static final String PASSER_TEST_NOTE = "note";

   //probleme
   public static final String PROBLEM = "Problem";
   public static final String PROBLEM_NUM = "num";
   public static final String PROBLEM_MSG="msg";

   //information cours
   public static final String INFORMATION_COURS = "Information_cours";
   public static final String INFORMATION_COURS_TYPE = "type";
   public static final String INFORMATION_COURS_ID_COURS="Id_cours";

   //information SUJET FORUM
   public static final String INFORMATION_SUJET_FORUM = "Information_sujet_forum";
   public static final String INFORMATION_SUJET_FORUM_ID_FORUM="Id_forum";

   //information Enseignant
   public static final String INFORMATION_ENSEIGNANT = "Information_enseignant";
   public static final String INFORMATION_ENSEIGNANT_ID="Id_enseignant";

   //ApprecierForum
   public static final String APPRECIER_FORUM = "Apprecier_reponse_forum";
   public static final String APPRECIER_FORUM_ID="id_reponseforum";

   //list all cours
   public static final String LIST_ALL_COURS= "liste_All_Cours";

   //list all sujetForums
   public static final String LIST_ALL_SUJETS_FORUM = "liste_All_Cours";

   //list cours search
   public static final String LIST_COURS_SEARCH= "liste_cours_search";
   public static final String LIST_COURS_SEARCH_SEARCH = "search";

   //information etudiant
   public static final String INFORMATION_ETUDIANT= "Information_etudiant";
   public static final String INFORMATION_ETUDIANT_ID_ETUDIANT = "id_etudiant";

   //list cours search by description
   public static final String LIST_COURS_SEARCH_DESCRIPTION= "liste_cours_search_description";
   public static final String LIST_COURS_SEARCH_DESCRIPTION_SEARCH = "search";

   //list cours enseignant
   public static final String LIST_COURS_ENSEIGNANT = "List_cours_enseignant";
   public static final String LIST_COURS_ENSEIGNANT_ID = "id_enseignant";

   //list cours by etudiant
   public static final String LIST_COURS_BY_ETUDIANT = "List_cours_by_etudiant";
   public static final String LIST_COURS_BY_ETUDIANT_ID = "id_etudiant";

   //list etudiant cours
   public static final String LIST_ETUDIANT_COURS = "List_etudiant_cours";
   public static final String LIST_ETUDIANT_COURS_ID = "id_cours";

   //list etudiant enseignant
   public static final String LIST_ETUDIANT_ENSEIGNANT = "List_etudiant_enseignant";
   public static final String LIST_ETUDIANT_ENSEIGNANT_ID = "id_enseignant";

   //Test by cours
   public static final String TEST_BY_COURS = "Test_by_cours";
   public static final String TEST_BY_COURS_ID_COURS = "id_cours";

   //sUJET FORUM by cours
   public static final String SUJET_FORUM_BY_COURS = "Sujet_forum_by_cours";
   public static final String SUJET_FORUM_BY_COURS_ID_COURS = "id_cours";

   //Reponse FORUM by sujet
   public static final String REPONSE_FORUM_BY_SUJET = "Reponse_forum_by_sujet";
   public static final String REPONSE_FORUM_BY_SUJET_ID_SUJET = "id_sujet";

   //etudiant
   public static final String ETUDIANT="Etudiant";
   public static final String ETUDIANT_ID="id_etudiant";
   public static final String ETUDIANT_NOM="nom_etudiant";
   public static final String ETUDIANT_EMAIL="email_etudiant";
   public static final String ETUDIANT_PASSWORD="password_etudiant";
   public static final String ETUDIANT_AGE="age_etudiant";
   public static final String ETUDIANT_INSTITUT="institut_etudiant";
   public static final String ETUDIANT_CATEGORY="category_etudiant";
   public static final String ETUDIANT_COURS_ETUDIANT="coursetudiant";
   public static final String ETUDIANT_TEST_ETUDIANT="testetudiant";

   //Create etudiant
   public static final String CREATE_ETUDIANT="Create_etudiant";
   public static final String CREATE_ETUDIANT_ID="id_etudiant";
   public static final String CREATE_ETUDIANT_NOM="nom_etudiant";
   public static final String CREATE_ETUDIANT_EMAIL="email_etudiant";
   public static final String CREATE_ETUDIANT_PASSWORD="password_etudiant";
   public static final String CREATE_ETUDIANT_AGE="age_etudiant";
   public static final String CREATE_ETUDIANT_INSTITUT="institut_etudiant";
   public static final String CREATE_ETUDIANT_CATEGORY="category_etudiant";


   //Question
   public static final String QUESTION="Question";
   public static final String QUESTION_ID="id_question";
   public static final String QUESTION_ENONCE="enonce_question";
   public static final String QUESTION_TEST_QUESTION ="test_question";
   public static final String QUESTION_PROPOSITIONS ="propositions";

   //Login etudiant
   public static final String LOGIN_ETUDIANT="login_etudiant";
   public static final String LOGIN_ETUDIANT_EMAIL="email_etudiant";
   public static final String LOGIN_ETUDIANT_PASSWORD="password_etudiant";

   //SuggererCours
   public static final String SUGGERER_COURS="suggerer_cours";
   public static final String SUGGERER_COURS_ID="id_etudiant";

   //Login etudiant
   public static final String LOGIN_ENSEIGNANT="login_enseignant";
   public static final String LOGIN_ENSEIGNANT_EMAIL="email_enseignant";
   public static final String LOGIN_ENSEIGNANT_PASSWORD="password_enseignant";

   //enseignant
   public static final String ENSEIGNANT="eneignant";
   public static final String ENSEIGNANT_ID="id_enseignant";
   public static final String ENSEIGNANT_NOM="nom_enseignant";
   public static final String ENSEIGNANT_EMAIL="email_enseignant";
   public static final String ENSEIGNANT_PASSWORD="password_enseignant";
   public static final String ENSEIGNANT_AGE="age_enseignant";
   public static final String ENSEIGNANT_INSTITUT="institut_enseignant";
   public static final String ENSEIGNANT_CATEGORY="category_enseignant";
   public static final String ENSEIGNANT_COURS="cours";

   // create enseignant
   public static final String CREATE_ENSEIGNANT="Create_eneignant";
   public static final String CREATE_ENSEIGNANT_ID="id_enseignant";
   public static final String CREATE_ENSEIGNANT_NOM="nom_enseignant";
   public static final String CREATE_ENSEIGNANT_EMAIL="email_enseignant";
   public static final String CREATE_ENSEIGNANT_PASSWORD="password_enseignant";
   public static final String CREATE_ENSEIGNANT_AGE="age_enseignant";
   public static final String CREATE_ENSEIGNANT_INSTITUT="institut_enseignant";
   public static final String CREATE_ENSEIGNANT_CATEGORY="category_enseignant";

   //seance
   public static final String SEANCE ="seance";
   public static final String SEANCE_ID ="id_seance";
   public static final String SEANCE_DATE ="date_seance";
   public static final String SEANCE_DUREE ="duree_seance";
   public static final String SEANCE_COURS ="cours_seance";

   //COURS etudiant
   public static final String COURS_ETUDIANT ="CoursEtudiant";
   public static final String COURS_ETUDIANT_ID="id_cours_etudiant";
   public static final String COURS_ETUDIANT_COURS_ASSO ="cours_asso";
   public static final String COURS_ETUDIANT_ETUDIANT_ASSO ="etudiant_asso";

   //test etudiant
   public static final String TEST_ETUDIANT ="TestEtudiant";
   public static final String TEST_ETUDIANT_ID ="id_test_etudiant";
   public static final String TEST_ETUDIANT_TEST_ASSO ="test_asso";
   public static final String TEST_ETUDIANT_ETUDIANT_ASSO ="etudiant_asso_test";
   public static final String TEST_ETUDIANT_NOTE ="note_test";


   //Ressource
   public static final String RESSOURCE = "Ressource";
   public static final String RESSOURCE_ID = "id_ressource";
   public static final String RESSOURCE_TYPE = "type_ressource";
   public static final String RESSOURCE_DESCRIPTION = "description_ressource";
   public static final String RESSOURCE_COURS = "cours_ressource";

   //CreateRessource
   public static final String CREATE_RESSOURCE = "Create_ressource";
   public static final String CREATE_RESSOURCE_ID = "id_ressource";
   public static final String CREATE_RESSOURCE_TYPE = "type_ressource";
   public static final String CREATE_RESSOURCE_DESCRIPTION = "description_ressource";
   public static final String CREATE_RESSOURCE_COURS = "cours_ressource";

   //Ressource

   public static final String RESSOURCE_BY_COURS = "Ressource_by_cours";
   public static final String RESSOURCE_BY_COURS_ID = "id_cours";

   //Connaissance
   public static final String CONNAISSANCE = "Connaissance";
   public static final String CONNAISSANCE_ID = "id_ressource";
   public static final String CONNAISSANCE_LEVEL = "level_connaissance";
   public static final String CONNAISSANCE_DESCRIPTION = "description_connaissance";


   //Question BY test
   public static final String QUESTION_BY_TEST = "Question_by_test";
   public static final String QUESTION_BY_TEST_ID_TEST = "id_test";

   //Proposition by Question
   public static final String PROPOSITION_BY_QUESTION = "Proposition_by_question";
   public static final String PROPOSITION_BY_QUESTION_ID_QUESTION = "id_question";

   //List tous EtudiantTest
   public static final String LIST_ALL_TEST_ETUDIANT = "List_all_test_etudiant";

   //List  EtudiantTest By Etudiant
   public static final String LIST_TEST_ETUDIANT_BY_ETUDIANT = "List_test_etudiant";
   public static final String LIST_TEST_ETUDIANT_BY_ETUDIANT_ID_ETUDIANT = "id_etudiant";

   //List  EtudiantTest By Test
   public static final String LIST_TEST_ETUDIANT_BY_TEST = "List_test_etudiant_by_test";
   public static final String LIST_TEST_ETUDIANT_BY_TEST_ID_TEST = "id_test";


}
