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

         // Enseignant
         cs =(ConceptSchema) getSchema(ENSEIGNANT);
         add(cs, Enseignant.class);
         cs.add(ENSEIGNANT_ID, (PrimitiveSchema) getSchema(BasicOntology.INTEGER), ObjectSchema.MANDATORY);
         cs.add(ENSEIGNANT_NOM, (PrimitiveSchema) getSchema(BasicOntology.STRING), ObjectSchema.MANDATORY);

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
         cs.add(REPONSE_ETUDIANT_TEST_ETUDIANT,(ConceptSchema) getSchema(TestEtudiant.class));

         // ------- Add AgentActions

         // CreateReponseEtudiant
         AgentActionSchema as = new AgentActionSchema(CREATE_REPONSE_ETUDIANT);
         add(as, CreateReponseEtudiant.class);
         as.add(CREATE_REPONSE_ETUDIANT_ID, (PrimitiveSchema) getSchema(BasicOntology.INTEGER), ObjectSchema.MANDATORY);
         as.add(CREATE_REPONSE_ETUDIANT_TEXT, (PrimitiveSchema) getSchema(BasicOntology.STRING), ObjectSchema.MANDATORY);
         as.add(CREATE_REPONSE_ETUDIANT_VALID, (PrimitiveSchema) getSchema(BasicOntology.BOOLEAN), ObjectSchema.MANDATORY);
         as.add(CREATE_REPONSE_ETUDIANT_TEST_ETUDIANT,(ConceptSchema) getSchema(TestEtudiant.class));

         // CreateCours
         add(as = new AgentActionSchema(CREATE_COURS), CreateCours.class);
         as.add(CREATE_COURS_ID, (PrimitiveSchema) getSchema(BasicOntology.INTEGER), ObjectSchema.MANDATORY);
         as.add(CREATE_COURS_INTITULE, (PrimitiveSchema) getSchema(BasicOntology.STRING), ObjectSchema.MANDATORY);
         as.add(CREATE_COURS_DESCRIPTION, (PrimitiveSchema) getSchema(BasicOntology.STRING), ObjectSchema.MANDATORY);
         as.add(CREATE_COURS_DUREE, (PrimitiveSchema) getSchema(BasicOntology.INTEGER), ObjectSchema.MANDATORY);
         as.add(CREATE_COURS_ENSEIGNANT,(ConceptSchema) getSchema(Enseignant.class));

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

         // Test by cours
         add(as = new AgentActionSchema(TEST_BY_COURS), TestByCours.class);
         as.add(TEST_BY_COURS_ID_COURS, (PrimitiveSchema) getSchema(BasicOntology.INTEGER), ObjectSchema.MANDATORY);

         // Question by test
         add(as = new AgentActionSchema(QUESTION_BY_TEST), QuestionByTest.class);
         as.add(QUESTION_BY_TEST_ID_TEST, (PrimitiveSchema) getSchema(BasicOntology.INTEGER), ObjectSchema.MANDATORY);

         // Proposition by question
         add(as = new AgentActionSchema(PROPOSITION_BY_QUESTION), PropositionByQuestion.class);
         as.add(PROPOSITION_BY_QUESTION_ID_QUESTION, (PrimitiveSchema) getSchema(BasicOntology.INTEGER), ObjectSchema.MANDATORY);

         // ListCoursEnseignant
         add(as = new AgentActionSchema(LIST_COURS_ENSEIGNANT), ListCoursEnseignant.class);
         as.add(LIST_COURS_ENSEIGNANT_ID, (PrimitiveSchema) getSchema(BasicOntology.INTEGER), ObjectSchema.MANDATORY);

         // ListAllCours
         add(as = new AgentActionSchema(LIST_ALL_COURS), ListeAllCours.class);

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
