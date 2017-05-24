/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.insat.ontologies;

import tn.insat.Agents.Vocabulary;
import jade.content.onto.*;
import jade.content.schema.*;

/**
 *
 * @author saif
 */
public class OntologyWSK extends Ontology implements Vocabulary {
    
   // ----------> The name identifying this ontology
   public static final String ONTOLOGY_NAME = "WSK-Ontology";

   // ----------> The singleton instance of this ontology
   private static Ontology instance = new OntologyWSK();

   // ----------> Method to access the singleton ontology object
   public static Ontology getInstance() { return instance; }


   // Private constructor
   private OntologyWSK() {

      super(ONTOLOGY_NAME, BasicOntology.getInstance());

      try {
          add(new ConceptSchema(COURS), Cours.class);
          add(new ConceptSchema(TEST), Test.class);
          add(new ConceptSchema(SEANCE), Seance.class);
          add(new ConceptSchema(ENSEIGNANT), Enseignant.class);
          add(new ConceptSchema(ETUDIANT), Etudiant.class);
          add(new ConceptSchema(COURS_ETUDIANT), CoursEtudiant.class);
          add(new ConceptSchema(TEST_ETUDIANT), TestEtudiant.class);
          add(new ConceptSchema(PROBLEM), Problem.class);
          add(new ConceptSchema(RESSOURCE), Ressource.class);
          add(new ConceptSchema(CONNAISSANCE), Connaissance.class);
         add(new ConceptSchema(QUESTION), Question.class);
         add(new ConceptSchema(PROPOSITION), Proposition.class);
         add(new ConceptSchema(REPONSEETUDIANT), ReponseEtudiant.class);
         add(new ConceptSchema(SUJET_FORUM), SujetForum.class);
         add(new ConceptSchema(REPONSE_FORUM), ReponseForum.class);




          // ------- Add Concepts

         // Cours
         ConceptSchema cs = (ConceptSchema) getSchema(COURS);
         add(cs, Cours.class);
         cs.add(COURS_ID, (PrimitiveSchema) getSchema(BasicOntology.INTEGER), ObjectSchema.MANDATORY);
         cs.add(COURS_INTITULE, (PrimitiveSchema) getSchema(BasicOntology.STRING), ObjectSchema.MANDATORY);
         cs.add(COURS_DUREE , (PrimitiveSchema) getSchema(BasicOntology.INTEGER), ObjectSchema.MANDATORY);
         cs.add(COURS_ENSEIGNANT,(ConceptSchema) getSchema(Enseignant.class),ObjectSchema.OPTIONAL);
         cs.add(COURS_DESCRIPTION, (PrimitiveSchema) getSchema(BasicOntology.STRING), ObjectSchema.OPTIONAL);



         // TEST
         cs  = (ConceptSchema) getSchema(TEST);
         add(cs, Test.class);
         cs.add(TEST_ID, (PrimitiveSchema) getSchema(BasicOntology.INTEGER), ObjectSchema.MANDATORY);
         cs.add(TEST_NOM, (PrimitiveSchema) getSchema(BasicOntology.STRING), ObjectSchema.MANDATORY);
         cs.add(TEST_DUREE , (PrimitiveSchema) getSchema(BasicOntology.STRING), ObjectSchema.MANDATORY);
         cs.add(TEST_COURS,(ConceptSchema) getSchema(Cours.class));
       //  cs.add(TEST_ETUDIANTS,(ConceptSchema) getSchema(Etudiant.class));
          // cs.add(TEST_TEST_QUESTIONS,(ConceptSchema) getSchema(Question.class),1,ObjectSchema.UNLIMITED);

         // Question
         cs  = (ConceptSchema) getSchema(QUESTION);
         add(cs, Question.class);
         cs.add(QUESTION_ID, (PrimitiveSchema) getSchema(BasicOntology.INTEGER), ObjectSchema.MANDATORY);
         cs.add(QUESTION_ENONCE, (PrimitiveSchema) getSchema(BasicOntology.STRING), ObjectSchema.MANDATORY);
         cs.add(QUESTION_TEST_QUESTION,(ConceptSchema) getSchema(Test.class));
        // cs.add(QUESTION_PROPOSITIONS,(ConceptSchema) getSchema(Proposition.class));


         // Proposition
         cs  = (ConceptSchema) getSchema(PROPOSITION);
         add(cs, Proposition.class);
         cs.add(PROPOSITION_ID, (PrimitiveSchema) getSchema(BasicOntology.INTEGER), ObjectSchema.MANDATORY);
         cs.add(PROPOSITION_TEXT_PROPOSITION, (PrimitiveSchema) getSchema(BasicOntology.STRING), ObjectSchema.MANDATORY);
         cs.add(PROPOSITION_VALID_PROPOSITION , (PrimitiveSchema) getSchema(BasicOntology.BOOLEAN), ObjectSchema.MANDATORY);
         cs.add(PROPOSITION_QUESTION_PROPOSITION,(ConceptSchema) getSchema(Question.class));

         //SujetForum
         cs  = (ConceptSchema) getSchema(SUJET_FORUM);
         add(cs, SujetForum.class);
         cs.add(SUJET_FORUM_ID, (PrimitiveSchema) getSchema(BasicOntology.INTEGER), ObjectSchema.MANDATORY);
         cs.add(SUJET_FORUM_TITRE, (PrimitiveSchema) getSchema(BasicOntology.STRING), ObjectSchema.MANDATORY);
         cs.add(SUJET_FORUM_TEXT , (PrimitiveSchema) getSchema(BasicOntology.STRING), ObjectSchema.MANDATORY);
         cs.add(SUJET_FORUM_COURS,(ConceptSchema) getSchema(Cours.class));
         cs.add(SUJET_FORUM_ENSEIGNANT,(ConceptSchema) getSchema(Enseignant.class),ObjectSchema.OPTIONAL);
         cs.add(SUJET_FORUM_ETUDIANT,(ConceptSchema) getSchema(Etudiant.class),ObjectSchema.OPTIONAL);

         //ReponseForum
         cs  = (ConceptSchema) getSchema(REPONSE_FORUM);
         add(cs, ReponseForum.class);
         cs.add(REPONSE_FORUM_ID, (PrimitiveSchema) getSchema(BasicOntology.INTEGER), ObjectSchema.MANDATORY);
         cs.add(REPONSE_FORUM_TEXT, (PrimitiveSchema) getSchema(BasicOntology.STRING), ObjectSchema.MANDATORY);
         cs.add(REPONSE_FORUM_SUJET_FORUM,(ConceptSchema) getSchema(SujetForum.class));
         cs.add(REPONSE_FORUM_ETUDIANT,(ConceptSchema) getSchema(Etudiant.class),ObjectSchema.OPTIONAL);
         cs.add(REPONSE_FORUM_ENSEIGNANT,(ConceptSchema) getSchema(Enseignant.class),ObjectSchema.OPTIONAL);

         //PROBLEM
         cs =(ConceptSchema) getSchema(PROBLEM);
         add(cs, Problem.class);
         cs.add(PROBLEM_MSG, (PrimitiveSchema) getSchema(BasicOntology.STRING), ObjectSchema.MANDATORY);
         cs.add(PROBLEM_NUM, (PrimitiveSchema) getSchema(BasicOntology.INTEGER), ObjectSchema.MANDATORY);

         // Etudiant
         cs = (ConceptSchema) getSchema(ETUDIANT);
         add(cs, Etudiant.class);
         cs.add(ETUDIANT_ID, (PrimitiveSchema) getSchema(BasicOntology.INTEGER), ObjectSchema.MANDATORY);
         cs.add(ETUDIANT_NOM, (PrimitiveSchema) getSchema(BasicOntology.STRING), ObjectSchema.MANDATORY);
         cs.add(ETUDIANT_EMAIL, (PrimitiveSchema) getSchema(BasicOntology.STRING), ObjectSchema.MANDATORY);
         cs.add(ETUDIANT_PASSWORD, (PrimitiveSchema) getSchema(BasicOntology.STRING), ObjectSchema.MANDATORY);
         cs.add(ETUDIANT_AGE, (PrimitiveSchema) getSchema(BasicOntology.INTEGER), ObjectSchema.MANDATORY);
         cs.add(ETUDIANT_INSTITUT, (PrimitiveSchema) getSchema(BasicOntology.STRING), ObjectSchema.MANDATORY);
         cs.add(ETUDIANT_CATEGORY, (PrimitiveSchema) getSchema(BasicOntology.STRING), ObjectSchema.MANDATORY);


         // Enseignant
         cs =(ConceptSchema) getSchema(ENSEIGNANT);
         add(cs, Enseignant.class);
         cs.add(ENSEIGNANT_ID, (PrimitiveSchema) getSchema(BasicOntology.INTEGER), ObjectSchema.MANDATORY);
         cs.add(ENSEIGNANT_NOM, (PrimitiveSchema) getSchema(BasicOntology.STRING), ObjectSchema.MANDATORY);
         cs.add(ENSEIGNANT_EMAIL, (PrimitiveSchema) getSchema(BasicOntology.STRING), ObjectSchema.MANDATORY);
         cs.add(ENSEIGNANT_PASSWORD, (PrimitiveSchema) getSchema(BasicOntology.STRING), ObjectSchema.MANDATORY);
         cs.add(ENSEIGNANT_AGE, (PrimitiveSchema) getSchema(BasicOntology.INTEGER), ObjectSchema.MANDATORY);
         cs.add(ENSEIGNANT_INSTITUT, (PrimitiveSchema) getSchema(BasicOntology.STRING), ObjectSchema.MANDATORY);
         cs.add(ENSEIGNANT_CATEGORY, (PrimitiveSchema) getSchema(BasicOntology.STRING), ObjectSchema.MANDATORY);

         // Seance
         cs = (ConceptSchema) getSchema(SEANCE);
         add(cs, Seance.class);
         cs.add(SEANCE_ID, (PrimitiveSchema) getSchema(BasicOntology.INTEGER), ObjectSchema.MANDATORY);
         cs.add(SEANCE_DATE, (PrimitiveSchema) getSchema(BasicOntology.STRING), ObjectSchema.MANDATORY);
         cs.add(SEANCE_DUREE, (PrimitiveSchema) getSchema(BasicOntology.STRING), ObjectSchema.MANDATORY);

         // Cours Etudiant
         cs = (ConceptSchema) getSchema(COURS_ETUDIANT);
         add(cs, CoursEtudiant.class);
         cs.add(COURS_ETUDIANT_ID, (PrimitiveSchema) getSchema(BasicOntology.INTEGER), ObjectSchema.MANDATORY);
         cs.add(COURS_ETUDIANT_ETUDIANT_ASSO,(ConceptSchema) getSchema(Etudiant.class));
         cs.add(COURS_ETUDIANT_COURS_ASSO,(ConceptSchema) getSchema(Cours.class));



         // Test Etudiant
         cs = (ConceptSchema) getSchema(TEST_ETUDIANT);
         add(cs, TestEtudiant.class);
         cs.add(TEST_ETUDIANT_ID, (PrimitiveSchema) getSchema(BasicOntology.INTEGER), ObjectSchema.MANDATORY);
         cs.add(TEST_ETUDIANT_NOTE, (PrimitiveSchema) getSchema(BasicOntology.STRING), ObjectSchema.OPTIONAL);
         cs.add(TEST_ETUDIANT_TEST_ASSO,(ConceptSchema) getSchema(Test.class));
         cs.add(TEST_ETUDIANT_ETUDIANT_ASSO,(ConceptSchema) getSchema(Etudiant.class));


         // Ressource
         cs = (ConceptSchema) getSchema(RESSOURCE);
         add(cs, Ressource.class);
         cs.add(RESSOURCE_ID, (PrimitiveSchema) getSchema(BasicOntology.INTEGER), ObjectSchema.MANDATORY);
         cs.add(RESSOURCE_TYPE, (PrimitiveSchema) getSchema(BasicOntology.STRING), ObjectSchema.MANDATORY);
         cs.add(RESSOURCE_DESCRIPTION, (PrimitiveSchema) getSchema(BasicOntology.STRING), ObjectSchema.MANDATORY);
         cs.add(RESSOURCE_COURS,(ConceptSchema) getSchema(Cours.class));

         // Connaissance
         cs = (ConceptSchema) getSchema(CONNAISSANCE);
         add(cs, Connaissance.class);
         cs.add(CONNAISSANCE_ID, (PrimitiveSchema) getSchema(BasicOntology.INTEGER), ObjectSchema.MANDATORY);
         cs.add(CONNAISSANCE_DESCRIPTION, (PrimitiveSchema) getSchema(BasicOntology.STRING), ObjectSchema.MANDATORY);
         cs.add(CONNAISSANCE_LEVEL, (PrimitiveSchema) getSchema(BasicOntology.STRING), ObjectSchema.MANDATORY);

         //ReponseEtudiant
         cs = (ConceptSchema) getSchema(REPONSEETUDIANT);
         add(cs, ReponseEtudiant.class);
         cs.add(REPONSE_ETUDIANT_ID, (PrimitiveSchema) getSchema(BasicOntology.INTEGER), ObjectSchema.MANDATORY);
         cs.add(REPONSE_ETUDIANT_TEXT, (PrimitiveSchema) getSchema(BasicOntology.STRING), ObjectSchema.MANDATORY);
         cs.add(REPONSE_ETUDIANT_VALID, (PrimitiveSchema) getSchema(BasicOntology.BOOLEAN), ObjectSchema.MANDATORY);
         cs.add(REPONSE_ETUDIANT_TEST_ETUDIANT,(ConceptSchema) getSchema(TestEtudiant.class),ObjectSchema.OPTIONAL);

         // ------- Add AgentActions

         // CreateReponseEtudiant
         AgentActionSchema as = new AgentActionSchema(CREATE_REPONSE_ETUDIANT);
         add(as, CreateReponseEtudiant.class);
         as.add(CREATE_REPONSE_ETUDIANT_ID, (PrimitiveSchema) getSchema(BasicOntology.INTEGER), ObjectSchema.MANDATORY);
         as.add(CREATE_REPONSE_ETUDIANT_TEXT, (PrimitiveSchema) getSchema(BasicOntology.STRING), ObjectSchema.MANDATORY);
         as.add(CREATE_REPONSE_ETUDIANT_VALID, (PrimitiveSchema) getSchema(BasicOntology.BOOLEAN), ObjectSchema.MANDATORY);
         as.add(CREATE_REPONSE_ETUDIANT_TEST_ETUDIANT,(ConceptSchema) getSchema(TestEtudiant.class),ObjectSchema.OPTIONAL);

         // CreateCours
         add(as = new AgentActionSchema(CREATE_COURS), CreateCours.class);
         as.add(CREATE_COURS_ID, (PrimitiveSchema) getSchema(BasicOntology.INTEGER), ObjectSchema.MANDATORY);
         as.add(CREATE_COURS_INTITULE, (PrimitiveSchema) getSchema(BasicOntology.STRING), ObjectSchema.MANDATORY);
         as.add(CREATE_COURS_DESCRIPTION, (PrimitiveSchema) getSchema(BasicOntology.STRING), ObjectSchema.MANDATORY);
         as.add(CREATE_COURS_DUREE, (PrimitiveSchema) getSchema(BasicOntology.INTEGER), ObjectSchema.MANDATORY);
         as.add(CREATE_COURS_ENSEIGNANT,(ConceptSchema) getSchema(Enseignant.class));

         // CREATE Etudiant
         add(as = new AgentActionSchema(CREATE_ETUDIANT), CreateEtudiant.class);
         as.add(CREATE_ETUDIANT_ID, (PrimitiveSchema) getSchema(BasicOntology.INTEGER), ObjectSchema.MANDATORY);
         as.add(CREATE_ETUDIANT_NOM, (PrimitiveSchema) getSchema(BasicOntology.STRING), ObjectSchema.MANDATORY);
         as.add(CREATE_ETUDIANT_EMAIL, (PrimitiveSchema) getSchema(BasicOntology.STRING), ObjectSchema.MANDATORY);
         as.add(CREATE_ETUDIANT_PASSWORD, (PrimitiveSchema) getSchema(BasicOntology.STRING), ObjectSchema.MANDATORY);
         as.add(CREATE_ETUDIANT_AGE, (PrimitiveSchema) getSchema(BasicOntology.INTEGER), ObjectSchema.MANDATORY);
         as.add(CREATE_ETUDIANT_INSTITUT, (PrimitiveSchema) getSchema(BasicOntology.STRING), ObjectSchema.MANDATORY);
         as.add(CREATE_ETUDIANT_CATEGORY, (PrimitiveSchema) getSchema(BasicOntology.STRING), ObjectSchema.MANDATORY);

         // LOGIN ETUDIANT
         add(as = new AgentActionSchema(LOGIN_ETUDIANT), LoginEtudiant.class);
         as.add(LOGIN_ETUDIANT_EMAIL, (PrimitiveSchema) getSchema(BasicOntology.STRING), ObjectSchema.MANDATORY);
         as.add(LOGIN_ETUDIANT_PASSWORD, (PrimitiveSchema) getSchema(BasicOntology.STRING), ObjectSchema.MANDATORY);

         // LOGIN Enseignant
         add(as = new AgentActionSchema(LOGIN_ENSEIGNANT), LoginEnseignant.class);
         as.add(LOGIN_ENSEIGNANT_EMAIL, (PrimitiveSchema) getSchema(BasicOntology.STRING), ObjectSchema.MANDATORY);
         as.add(LOGIN_ENSEIGNANT_PASSWORD, (PrimitiveSchema) getSchema(BasicOntology.STRING), ObjectSchema.MANDATORY);


         // SuggererCours
         add(as = new AgentActionSchema(SUGGERER_COURS),SuggererCours.class);
         as.add(SUGGERER_COURS_ID, (PrimitiveSchema) getSchema(BasicOntology.INTEGER), ObjectSchema.MANDATORY);

         // RessourceByCours
         add(as = new AgentActionSchema(RESSOURCE_BY_COURS),RessourceByCours.class);
         as.add(RESSOURCE_BY_COURS_ID, (PrimitiveSchema) getSchema(BasicOntology.INTEGER), ObjectSchema.MANDATORY);

         // Enseignant
         add(as = new AgentActionSchema(CREATE_ENSEIGNANT), CreateEnseignant.class);
         as.add(CREATE_ENSEIGNANT_ID, (PrimitiveSchema) getSchema(BasicOntology.INTEGER), ObjectSchema.MANDATORY);
         as.add(CREATE_ENSEIGNANT_NOM, (PrimitiveSchema) getSchema(BasicOntology.STRING), ObjectSchema.MANDATORY);
         as.add(CREATE_ENSEIGNANT_EMAIL, (PrimitiveSchema) getSchema(BasicOntology.STRING), ObjectSchema.MANDATORY);
         as.add(CREATE_ENSEIGNANT_PASSWORD, (PrimitiveSchema) getSchema(BasicOntology.STRING), ObjectSchema.MANDATORY);
         as.add(CREATE_ENSEIGNANT_AGE, (PrimitiveSchema) getSchema(BasicOntology.INTEGER), ObjectSchema.MANDATORY);
         as.add(CREATE_ENSEIGNANT_INSTITUT, (PrimitiveSchema) getSchema(BasicOntology.STRING), ObjectSchema.MANDATORY);
         as.add(CREATE_ENSEIGNANT_CATEGORY, (PrimitiveSchema) getSchema(BasicOntology.STRING), ObjectSchema.MANDATORY);

         //CreateSujetForum
         add(as = new AgentActionSchema(CREATE_SUJET_FORUM), CreateSujetForum.class);
         as.add(CREATE_SUJET_FORUM_ID, (PrimitiveSchema) getSchema(BasicOntology.INTEGER), ObjectSchema.MANDATORY);
         as.add(CREATE_SUJET_FORUM_TITRE, (PrimitiveSchema) getSchema(BasicOntology.STRING), ObjectSchema.MANDATORY);
         as.add(CREATE_SUJET_FORUM_TEXT , (PrimitiveSchema) getSchema(BasicOntology.STRING), ObjectSchema.MANDATORY);
         as.add(CREATE_SUJET_FORUM_COURS,(ConceptSchema) getSchema(Cours.class));
         as.add(CREATE_SUJET_FORUM_ENSEIGNANT,(ConceptSchema) getSchema(Enseignant.class),ObjectSchema.OPTIONAL);
         as.add(CREATE_SUJET_FORUM_ETUDIANT,(ConceptSchema) getSchema(Etudiant.class),ObjectSchema.OPTIONAL);

         //CreateRessource
         add(as = new AgentActionSchema(CREATE_RESSOURCE), CreateRessource.class);
         as.add(CREATE_RESSOURCE_ID, (PrimitiveSchema) getSchema(BasicOntology.INTEGER), ObjectSchema.MANDATORY);
         as.add(CREATE_RESSOURCE_DESCRIPTION, (PrimitiveSchema) getSchema(BasicOntology.STRING), ObjectSchema.MANDATORY);
         as.add(CREATE_RESSOURCE_TYPE, (PrimitiveSchema) getSchema(BasicOntology.STRING), ObjectSchema.MANDATORY);
         as.add(CREATE_RESSOURCE_COURS,(ConceptSchema) getSchema(Cours.class),ObjectSchema.MANDATORY);

         //CreateReponseForum
         add(as = new AgentActionSchema(CREATE_REPONSE_FORUM), CreateReponseForum.class);
         as.add(CREATE_REPONSE_FORUM_ID, (PrimitiveSchema) getSchema(BasicOntology.INTEGER), ObjectSchema.MANDATORY);
         as.add(CREATE_REPONSE_FORUM_TEXT , (PrimitiveSchema) getSchema(BasicOntology.STRING), ObjectSchema.MANDATORY);
         as.add(CREATE_REPONSE_FORUM_SUJET,(ConceptSchema) getSchema(SujetForum.class));
         as.add(CREATE_REPONSE_FORUM_ENSEIGNANT,(ConceptSchema) getSchema(Enseignant.class),ObjectSchema.OPTIONAL);
         as.add(CREATE_REPONSE_FORUM_ETUDIANT,(ConceptSchema) getSchema(Etudiant.class),ObjectSchema.OPTIONAL);


         // AffecterCours
         add(as = new AgentActionSchema(AFFECTER_COURS), AffecterCours.class);
         as.add(AFFECTER_COURS_ID_COURS, (PrimitiveSchema) getSchema(BasicOntology.INTEGER), ObjectSchema.MANDATORY);
         as.add(AFFECTER_COURS_ID_ETUDIANT, (PrimitiveSchema) getSchema(BasicOntology.INTEGER), ObjectSchema.MANDATORY);

         // AffecterTest
         add(as = new AgentActionSchema(AFFECTER_TEST), AffecterTest.class);
         as.add(AFFECTER_TEST_ID_ETUDIANT, (PrimitiveSchema) getSchema(BasicOntology.INTEGER), ObjectSchema.MANDATORY);
         as.add(AFFECTER_TEST_ID_TEST, (PrimitiveSchema) getSchema(BasicOntology.INTEGER), ObjectSchema.MANDATORY);
        
         // CreateTest
         add(as = new AgentActionSchema(CREATE_TEST), CreateTest.class);
         as.add(CREATE_TEST_ID_COURS, (PrimitiveSchema) getSchema(BasicOntology.INTEGER), ObjectSchema.MANDATORY);
         as.add(CREATE_TEST_ID_TEST, (PrimitiveSchema) getSchema(BasicOntology.INTEGER), ObjectSchema.MANDATORY);
         as.add(CREATE_TEST_NOM, (PrimitiveSchema) getSchema(BasicOntology.STRING), ObjectSchema.MANDATORY);
         as.add(CREATE_TEST_DUREE, (PrimitiveSchema) getSchema(BasicOntology.STRING), ObjectSchema.MANDATORY);

         // CreateQuestion
         add(as = new AgentActionSchema(CREATE_QUESTION), CreateQuestion.class);
         as.add(CREATE_QUESTION_ID_QUESTION, (PrimitiveSchema) getSchema(BasicOntology.INTEGER), ObjectSchema.MANDATORY);
         as.add(CREATE_QUESTION_ENONCE, (PrimitiveSchema) getSchema(BasicOntology.STRING), ObjectSchema.MANDATORY);
         as.add(CREATE_QUESTION_TEST,(ConceptSchema) getSchema(Test.class));

         // CreateProposition
         add(as = new AgentActionSchema(CREATE_PROPOSITION), CreateProposition.class);
         as.add(CREATE_PROPOSITION_ID_PROPOSITION, (PrimitiveSchema) getSchema(BasicOntology.INTEGER), ObjectSchema.MANDATORY);
         as.add(CREATE_PROPOSITION_TEXT_PROPOSITION, (PrimitiveSchema) getSchema(BasicOntology.STRING), ObjectSchema.MANDATORY);
         as.add(CREATE_PROPOSITION_VALID_PROPOSITION, (PrimitiveSchema) getSchema(BasicOntology.BOOLEAN), ObjectSchema.MANDATORY);
         as.add(CREATE_PROPOSITION_QUESTION,(ConceptSchema) getSchema(Question.class));

         // PasserTest
         add(as = new AgentActionSchema(PASSER_TEST), PasserTest.class);
         as.add(PASSER_TEST_ID_TEST, (PrimitiveSchema) getSchema(BasicOntology.INTEGER), ObjectSchema.MANDATORY);
         as.add(PASSER_TEST_ID_ETUDIANT, (PrimitiveSchema) getSchema(BasicOntology.INTEGER), ObjectSchema.MANDATORY);
         as.add(PASSER_TEST_NOTE, (PrimitiveSchema) getSchema(BasicOntology.STRING), ObjectSchema.MANDATORY);
         
          // InformationCours
         add(as = new AgentActionSchema(INFORMATION_COURS), InformationCours.class);
         as.add(INFORMATION_COURS_TYPE, (PrimitiveSchema) getSchema(BasicOntology.INTEGER), ObjectSchema.MANDATORY);
         as.add(INFORMATION_COURS_ID_COURS, (PrimitiveSchema) getSchema(BasicOntology.INTEGER), ObjectSchema.MANDATORY);

            // InformationSujetForum
         add(as = new AgentActionSchema(INFORMATION_SUJET_FORUM), InformationSujetForum.class);
         as.add(INFORMATION_SUJET_FORUM_ID_FORUM, (PrimitiveSchema) getSchema(BasicOntology.INTEGER), ObjectSchema.MANDATORY);

         // InformationEnseignant
         add(as = new AgentActionSchema(INFORMATION_ENSEIGNANT),InformationEnseignant.class);
         as.add(INFORMATION_ENSEIGNANT_ID, (PrimitiveSchema) getSchema(BasicOntology.INTEGER), ObjectSchema.MANDATORY);

         // Test by cours
         add(as = new AgentActionSchema(TEST_BY_COURS), TestByCours.class);
         as.add(TEST_BY_COURS_ID_COURS, (PrimitiveSchema) getSchema(BasicOntology.INTEGER), ObjectSchema.MANDATORY);

         // SUJET FORUM by cours
         add(as = new AgentActionSchema(SUJET_FORUM_BY_COURS), SujetForumByCours.class);
         as.add(SUJET_FORUM_BY_COURS_ID_COURS, (PrimitiveSchema) getSchema(BasicOntology.INTEGER), ObjectSchema.MANDATORY);

         // Reponse FORUM by sujet
         add(as = new AgentActionSchema(REPONSE_FORUM_BY_SUJET), ReponseForumBySujet.class);
         as.add(REPONSE_FORUM_BY_SUJET_ID_SUJET, (PrimitiveSchema) getSchema(BasicOntology.INTEGER), ObjectSchema.MANDATORY);

         // Question by test
         add(as = new AgentActionSchema(QUESTION_BY_TEST), QuestionByTest.class);
         as.add(QUESTION_BY_TEST_ID_TEST, (PrimitiveSchema) getSchema(BasicOntology.INTEGER), ObjectSchema.MANDATORY);

         // Proposition by question
         add(as = new AgentActionSchema(PROPOSITION_BY_QUESTION), PropositionByQuestion.class);
         as.add(PROPOSITION_BY_QUESTION_ID_QUESTION, (PrimitiveSchema) getSchema(BasicOntology.INTEGER), ObjectSchema.MANDATORY);

         // ListCoursEnseignant
         add(as = new AgentActionSchema(LIST_COURS_ENSEIGNANT), ListCoursEnseignant.class);
         as.add(LIST_COURS_ENSEIGNANT_ID, (PrimitiveSchema) getSchema(BasicOntology.INTEGER), ObjectSchema.MANDATORY);

         // ListTousTestEtudiant
         add(as = new AgentActionSchema(LIST_ALL_TEST_ETUDIANT), ListAllTestEtudiant.class);

         // ListTestEtudiantByIdEtudiant
         add(as = new AgentActionSchema(LIST_TEST_ETUDIANT_BY_ETUDIANT), ListTestEtudiant.class);
         as.add(LIST_TEST_ETUDIANT_BY_ETUDIANT_ID_ETUDIANT, (PrimitiveSchema) getSchema(BasicOntology.INTEGER), ObjectSchema.MANDATORY);


         // ListTestEtudiantByIdTest
         add(as = new AgentActionSchema(LIST_TEST_ETUDIANT_BY_TEST), ListTestEtudiantByTest.class);
         as.add(LIST_TEST_ETUDIANT_BY_TEST_ID_TEST, (PrimitiveSchema) getSchema(BasicOntology.INTEGER), ObjectSchema.MANDATORY);

         // ListAllCours
         add(as = new AgentActionSchema(LIST_ALL_COURS), ListeAllCours.class);

         // ListAllSujetsForum
         add(as = new AgentActionSchema(LIST_ALL_COURS), ListeAllSujetsForum.class);

         // ListCoursSearch
         add(as = new AgentActionSchema(LIST_COURS_SEARCH), ListeCoursSearch.class);
         as.add(LIST_COURS_SEARCH_SEARCH, (PrimitiveSchema) getSchema(BasicOntology.STRING), ObjectSchema.MANDATORY);

         // ListCoursSearchDescription
         add(as = new AgentActionSchema(LIST_COURS_SEARCH_DESCRIPTION), ListeCoursSearchDescription.class);
         as.add(LIST_COURS_SEARCH_DESCRIPTION_SEARCH, (PrimitiveSchema) getSchema(BasicOntology.STRING), ObjectSchema.MANDATORY);

         // ListEtudiantCours
         add(as = new AgentActionSchema(LIST_ETUDIANT_COURS), ListEtudiantCours.class);
         as.add(LIST_ETUDIANT_COURS_ID, (PrimitiveSchema) getSchema(BasicOntology.INTEGER), ObjectSchema.MANDATORY);

         // List Cours by etudiant
         add(as = new AgentActionSchema(LIST_COURS_BY_ETUDIANT), ListeCoursByEtudiant.class);
         as.add(LIST_COURS_BY_ETUDIANT_ID, (PrimitiveSchema) getSchema(BasicOntology.INTEGER), ObjectSchema.MANDATORY);

         // ListEtudiantEnseignant
         add(as = new AgentActionSchema(LIST_ETUDIANT_ENSEIGNANT), ListEtudiantEnseignant.class);
         as.add(LIST_ETUDIANT_ENSEIGNANT_ID, (PrimitiveSchema) getSchema(BasicOntology.INTEGER), ObjectSchema.MANDATORY);

         // InformationEtudiant
         add(as = new AgentActionSchema(INFORMATION_ETUDIANT), InformationEtudiant.class);
         as.add(INFORMATION_ETUDIANT_ID_ETUDIANT, (PrimitiveSchema) getSchema(BasicOntology.INTEGER), ObjectSchema.MANDATORY);













      }
      catch (OntologyException oe) {
         oe.printStackTrace();
      }
   }
}
