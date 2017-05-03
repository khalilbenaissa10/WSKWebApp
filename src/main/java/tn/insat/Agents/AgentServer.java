/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.insat.Agents;


import tn.insat.Repositories.*;
import tn.insat.ontologies.*;

import jade.content.*;
import jade.content.lang.*;
import jade.content.lang.sl.*;
import jade.content.onto.*;
import jade.content.onto.basic.*;
import jade.core.*;
import jade.core.behaviours.*;
import jade.domain.*;
import jade.domain.FIPAAgentManagement.*;
import jade.lang.acl.*;
import jade.util.leap.*;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author saif
 */
public class AgentServer extends Agent implements Vocabulary {

   private ICoursRepository repo = new CoursRepository();
   private ICoursEtudiantRepository repo_coursetudiant = new CoursEtudiantRepository();
   private IEtudiantRepository repo_etudiant = new EtudiantRepository();
   private IEnseignantRepository repo_enseignant = new EnseignantRepository();
   private ITestRepository repo_test = new TestRepository();
   ICoursRepository repo_cours = new CoursRepository();
   IQuestionRepository repo_question = new QuestionRepository();
   IPropositionRepository repo_proposition =new PropositionRepository();
   ITestEtudiantRepository repo_test_etudiant = new TestEtudiantRepository();
   IReponseEtudiantRepository repo_reponse_question = new ReponseEtudiantRepository();

    
   private int idCnt = 0;
   private Map listcours = new HashMap();
   private Map operations = new HashMap();
   private Codec codec = new SLCodec();
   private Ontology ontology = OntologyWSK.getInstance();

   protected void setup() {
// ------------------------

      // Register language and ontology
      getContentManager().registerLanguage(codec);
      getContentManager().registerOntology(ontology);

      // Set this agent main behaviour
      SequentialBehaviour sb = new SequentialBehaviour();
      sb.addSubBehaviour(new RegisterInDF(this));
      sb.addSubBehaviour(new ReceiveMessages(this));
      addBehaviour(sb);
   }

   class RegisterInDF extends OneShotBehaviour {
// ---------------------------------------------  Register in the DF for the client agent
//                                                be able to retrieve its AID
      RegisterInDF(Agent a) {
         super(a);
      }

      public void action() {

         ServiceDescription sd = new ServiceDescription();
         sd.setType(SERVER_AGENT);
         sd.setName(getName());
         sd.setOwnership("Prof6802");
         DFAgentDescription dfd = new DFAgentDescription();
         dfd.setName(getAID());
         dfd.addServices(sd);
         try {
            DFAgentDescription[] dfds = DFService.search(myAgent, dfd);
            if (dfds.length > 0 ) {
               DFService.deregister(myAgent, dfd);
            }
            DFService.register(myAgent, dfd);
            System.out.println(getLocalName() + " is ready.");
         }
         catch (Exception ex) {
            System.out.println("Failed registering with DF! Shutting down...");
            ex.printStackTrace();
            doDelete();
         }
      }
   }

   class ReceiveMessages extends CyclicBehaviour {
// -----------------------------------------------  Receive requests and queries from client
//                                                  agent and launch appropriate handlers

      public ReceiveMessages(Agent a) {

         super(a);
      }

      public void action() {

         ACLMessage msg = receive();
         if (msg == null) { block(); return; }
         try {
            ContentElement content = getContentManager().extractContent(msg);
            Concept action = ((Action)content).getAction();

            switch (msg.getPerformative()) {

               case (ACLMessage.REQUEST):

                  System.out.println("message srver :Request from " + msg.getSender().getLocalName());

                  if (action instanceof CreateCours)
                     addBehaviour(new HandleCreateCours(myAgent, msg));
                  else if (action instanceof AffecterCours)
                    addBehaviour(new HandleAffecterCours(myAgent, msg));
                  else if (action instanceof AffecterTest)
                     addBehaviour(new HandleAffecterTest(myAgent, msg));
                  else if (action instanceof CreateTest)
                     addBehaviour(new HandleCreateTest(myAgent, msg));
                  else if (action instanceof CreateQuestion)
                     addBehaviour(new HandleCreateQuestion(myAgent, msg));
                  else if (action instanceof CreateProposition)
                     addBehaviour(new HandleCreationProposition(myAgent, msg));
                  else if (action instanceof CreateReponseEtudiant)
                     addBehaviour(new HandleCreateReponseEtudiant(myAgent, msg));
                  else
                     replyNotUnderstood(msg);
                  break;

               case (ACLMessage.QUERY_REF):

                  System.out.println("message server : Query from " + msg.getSender().getLocalName());

                  if (action instanceof InformationCours)
                     addBehaviour(new HandleInformationCours(myAgent, msg));
                  else if (action instanceof ListCoursEnseignant)
                     addBehaviour(new HandleListCoursEnseignant(myAgent, msg));
                  else if (action instanceof ListEtudiantCours)
                     addBehaviour(new HandleListEtudiantsCours(myAgent, msg));
                  else if (action instanceof ListEtudiantEnseignant)
                     addBehaviour(new HandleListEtudiantsEnseignant(myAgent, msg));
                  else if (action instanceof ListeAllCours)
                     addBehaviour(new HandleListAllCours(myAgent, msg));
                  else if (action instanceof ListeCoursSearch)
                     addBehaviour(new HandleListCoursSearch(myAgent,msg));
                  else if (action instanceof ListeCoursSearchDescription)
                     addBehaviour(new HandleListCoursSearchDescription(myAgent,msg));
                  else if (action instanceof InformationEtudiant)
                     addBehaviour(new HandleInformationEtudiant(myAgent,msg));
                  else if (action instanceof ListeCoursByEtudiant)
                     addBehaviour(new HandleListCoursByEtudiant(myAgent,msg));
                  else if (action instanceof TestByCours)
                     addBehaviour(new HandleListCoursByTest(myAgent,msg));
                  else if (action instanceof QuestionByTest)
                     addBehaviour(new HandleListeQuestionByTest(myAgent,msg));
                  else if (action instanceof PropositionByQuestion)
                     addBehaviour(new HandleListePropositionByQuestion(myAgent,msg));
                  else if (action instanceof ListAllTestEtudiant)
                     addBehaviour(new HandleListAllTestEtudiant(myAgent,msg));
                  else if (action instanceof ListTestEtudiant)
                     addBehaviour(new HandleListTestEtudiant(myAgent,msg));
                  else if (action instanceof ListTestEtudiantByTest)
                     addBehaviour(new HandleListTestEtudiantByTest(myAgent,msg));
                  else

                     replyNotUnderstood(msg);
                  break;

               default:
                     replyNotUnderstood(msg);
            }
         }
         catch(Exception ex) { ex.printStackTrace(); }
      }
   }

   class HandleCreateCours extends OneShotBehaviour {
// ----------------------------------------------------  Handler for a CreateAccount request

      private ACLMessage request;

      HandleCreateCours(Agent a, ACLMessage request) {

         super(a);
         this.request = request;
      }

      public void action() {

         try {
            ContentElement content = getContentManager().extractContent(request);
            CreateCours ca = (CreateCours)((Action)content).getAction();
            Cours cours = new Cours();

            cours.setId_cours(ca.getId_cours());
            cours.setIntitule(ca.getIntitule());
            cours.setDescription(ca.getDescription());
            cours.setDuree(ca.getDuree());
            cours.setEnseignant(ca.getEnseignant());
            repo.create(cours);
            cours = repo.findById(ca.getId_cours());
            Result result = new Result((Action)content, (Cours)cours);
            ACLMessage reply = request.createReply();
            reply.setPerformative(ACLMessage.INFORM);
            getContentManager().fillContent(reply, result);
            send(reply);
            System.out.println("Cours [" + cours.getIntitule() + " # " +
                               cours.getId_cours() + "] created!");
         }
         catch(Exception ex) { ex.printStackTrace(); }
      }
   }


   class HandleCreateTest extends OneShotBehaviour {
// ----------------------------------------------------  Handler for a CreateAccount request

      private ACLMessage request;

      HandleCreateTest(Agent a, ACLMessage request) {

         super(a);
         this.request = request;
      }

      public void action() {

         try {
            ContentElement content = getContentManager().extractContent(request);
            CreateTest ca = (CreateTest)((Action)content).getAction();
            Test test = new Test();
            Cours cours_du_test =repo_cours.findById(ca.getId_cours());
            test.setCours_test(cours_du_test);
            test.setId_test(ca.getId_test());
            test.setNom_test(ca.getNom_test());
            test.setDuree_test(ca.getDuree_test());

            repo_test.create(test);
            test = repo_test.findById(ca.getId_test());
            Result result = new Result((Action)content, (Test)test);
            ACLMessage reply = request.createReply();
            reply.setPerformative(ACLMessage.INFORM);
            getContentManager().fillContent(reply, result);
            send(reply);
         }
         catch(Exception ex) { ex.printStackTrace(); }
      }
   }


   class HandleCreateQuestion extends OneShotBehaviour {
// ----------------------------------------------------  Handler for a CreateAccount request

      private ACLMessage request;

      HandleCreateQuestion(Agent a, ACLMessage request) {

         super(a);
         this.request = request;
      }

      public void action() {

         try {
            ContentElement content = getContentManager().extractContent(request);
            CreateQuestion ca = (CreateQuestion)((Action)content).getAction();
            Question question = new Question();
            question.setEnonce_question(ca.getEnonce_question());
            question.setId_question(ca.getId_question());
            question.setTest_question(ca.getTest());
            repo_question.create(question);

            question = repo_question.findById(ca.getId_question());
            Result result = new Result((Action)content, (Question)question);
            ACLMessage reply = request.createReply();
            reply.setPerformative(ACLMessage.INFORM);
            getContentManager().fillContent(reply, result);
            send(reply);
         }
         catch(Exception ex) { ex.printStackTrace(); }
      }
   }


   class HandleCreationProposition extends OneShotBehaviour {
// ----------------------------------------------------  Handler for a CreateAccount request

      private ACLMessage request;

      HandleCreationProposition(Agent a, ACLMessage request) {

         super(a);
         this.request = request;
      }

      public void action() {

         try {
            ContentElement content = getContentManager().extractContent(request);
            CreateProposition ca = (CreateProposition)((Action)content).getAction();
            Proposition proposition = new Proposition();
           proposition.setId_proposition(ca.getId_proposition());
           proposition.setQuestion_proposition(ca.getQuestion());
           proposition.setText_proposition(ca.getText_proposition());
           proposition.setValid_proposition(ca.getValid_proposition());
           repo_proposition.create(proposition);

            proposition = repo_proposition.findById(ca.getId_proposition());
            Result result = new Result((Action)content, (Proposition)proposition);
            ACLMessage reply = request.createReply();
            reply.setPerformative(ACLMessage.INFORM);
            getContentManager().fillContent(reply, result);
            send(reply);
         }
         catch(Exception ex) { ex.printStackTrace(); }
      }
   }



   class HandleCreateReponseEtudiant extends OneShotBehaviour {
// ----------------------------------------------------  Handler for a CreateAccount request

      private ACLMessage request;

      HandleCreateReponseEtudiant(Agent a, ACLMessage request) {

         super(a);
         this.request = request;
      }

      public void action() {

         try {
            ContentElement content = getContentManager().extractContent(request);
            CreateReponseEtudiant ca = (CreateReponseEtudiant)((Action)content).getAction();
            ReponseEtudiant reponse = new ReponseEtudiant();
            reponse.setId_reponse_etudiant(ca.getId_reponse_etudiant());
            reponse.setTestetudiant(ca.getTestEtudiant());
            reponse.setText_reponse_etudiant(ca.getText_reponse_etudiant());
            reponse.setValid_reponse_etudiant(ca.getValid_reponse_etudiant());
            repo_reponse_question.Create(reponse);

            reponse = repo_reponse_question.findById(ca.getId_reponse_etudiant());
            Result result = new Result((Action)content, (ReponseEtudiant)reponse);
            ACLMessage reply = request.createReply();
            reply.setPerformative(ACLMessage.INFORM);
            getContentManager().fillContent(reply, result);
            send(reply);
         }
         catch(Exception ex) { ex.printStackTrace(); }
      }
   }




   class HandleInformationCours extends OneShotBehaviour {
// --------------------------------------------------  Handler for an Information query

      private ACLMessage query;

      HandleInformationCours(Agent a, ACLMessage query) {

         super(a);
         this.query = query;
      }

      public void action() {

         try {

            ContentElement content = getContentManager().extractContent(query);
            InformationCours info = (InformationCours)((Action)content).getAction();
            Cours crs = repo.findById(info.getId_Cours());
            if (crs == null) replyNotUnderstood(query);
            else {
               ACLMessage reply = query.createReply();
               reply.setPerformative(ACLMessage.INFORM);
               Result result = new Result((Action)content, crs);
               getContentManager().fillContent(reply, result);
               send(reply);
               System.out.println("information du cours " + crs  + "from server");
            }
         }
         catch(Exception ex) { ex.printStackTrace(); }
      }
   }

   class HandleInformationEtudiant extends OneShotBehaviour {
// --------------------------------------------------  Handler for an Information query

      private ACLMessage query;

      HandleInformationEtudiant(Agent a, ACLMessage query) {

         super(a);
         this.query = query;
      }

      public void action() {

         try {

            ContentElement content = getContentManager().extractContent(query);
            InformationEtudiant info = (InformationEtudiant)((Action)content).getAction();
            Object obj = processInformationEtudiant(info);
            if (obj == null) replyNotUnderstood(query);
            else {
               ACLMessage reply = query.createReply();
               reply.setPerformative(ACLMessage.INFORM);
               Result result = new Result((Action)content, obj);
               getContentManager().fillContent(reply, result);
               send(reply);
               System.out.println("information du cours " + obj  + "from server");
            }
         }
         catch(Exception ex) { ex.printStackTrace(); }
      }
   }


   class HandleListCoursByEtudiant extends OneShotBehaviour {
// --------------------------------------------------  Handler for an Information query

      private ACLMessage query;

      HandleListCoursByEtudiant(Agent a, ACLMessage query) {

         super(a);
         this.query = query;
      }

      public void action() {

         try {

            ContentElement content = getContentManager().extractContent(query);

            ListeCoursByEtudiant info = (ListeCoursByEtudiant) ((Action)content).getAction();
            java.util.ArrayList<Cours> listcours = null;
            listcours = repo_cours.findByEtudiant(info.getId_etudiant()) ;
            jade.util.leap.ArrayList listcoursjade = new jade.util.leap.ArrayList(listcours);
            Result result = new Result((Action)content, (jade.util.leap.ArrayList)listcoursjade);
               ACLMessage reply = query.createReply();
               reply.setPerformative(ACLMessage.INFORM);
               getContentManager().fillContent(reply, result);
               send(reply);
               System.out.println("liste cours by etudiant " + listcours  + "from server");

         }
         catch(Exception ex) { ex.printStackTrace(); }
      }
   }

   class HandleListCoursByTest extends OneShotBehaviour {
// --------------------------------------------------  Handler for an Information query

      private ACLMessage query;

      HandleListCoursByTest(Agent a, ACLMessage query) {

         super(a);
         this.query = query;
      }

      public void action() {

         try {

            ContentElement content = getContentManager().extractContent(query);

            TestByCours info = (TestByCours) ((Action)content).getAction();
            java.util.ArrayList<Test> listTest = null;
            listTest = (java.util.ArrayList<Test>) repo_test.findByCours(info.getId_cours());
            jade.util.leap.ArrayList listtestjade = new jade.util.leap.ArrayList(listTest);
            Result result = new Result((Action)content, (jade.util.leap.ArrayList)listtestjade);
            ACLMessage reply = query.createReply();
            reply.setPerformative(ACLMessage.INFORM);
            getContentManager().fillContent(reply, result);
            send(reply);
            System.out.println("liste test by cours " + listTest  + "from server");

         }
         catch(Exception ex) { ex.printStackTrace(); }
      }
   }

   class HandleListeQuestionByTest extends OneShotBehaviour {
// --------------------------------------------------  Handler for an Information query

      private ACLMessage query;

      HandleListeQuestionByTest(Agent a, ACLMessage query) {

         super(a);
         this.query = query;
      }

      public void action() {

         try {

            ContentElement content = getContentManager().extractContent(query);

            QuestionByTest info = (QuestionByTest) ((Action)content).getAction();
            java.util.ArrayList<Question> listQuestion = null;
            listQuestion = (java.util.ArrayList<Question>) repo_question.findByTest(info.getId_test());
            jade.util.leap.ArrayList listQuestionJade = new jade.util.leap.ArrayList(listQuestion);
            Result result = new Result((Action)content, (jade.util.leap.ArrayList)listQuestionJade);
            ACLMessage reply = query.createReply();
            reply.setPerformative(ACLMessage.INFORM);
            getContentManager().fillContent(reply, result);
            send(reply);
            System.out.println("liste question by test " + listQuestion  + "from server");

         }
         catch(Exception ex) { ex.printStackTrace(); }
      }
   }


   class HandleListePropositionByQuestion extends OneShotBehaviour {
// --------------------------------------------------  Handler for an Information query

      private ACLMessage query;

      HandleListePropositionByQuestion(Agent a, ACLMessage query) {

         super(a);
         this.query = query;
      }

      public void action() {

         try {

            ContentElement content = getContentManager().extractContent(query);

            PropositionByQuestion info = (PropositionByQuestion) ((Action)content).getAction();
            java.util.ArrayList<Proposition> listProposition = null;
            listProposition = (java.util.ArrayList<Proposition>) repo_proposition.findByQuestion(info.getId_question());
            jade.util.leap.ArrayList listPropositionJade = new jade.util.leap.ArrayList(listProposition);
            Result result = new Result((Action)content, (jade.util.leap.ArrayList)listPropositionJade);
            ACLMessage reply = query.createReply();
            reply.setPerformative(ACLMessage.INFORM);
            getContentManager().fillContent(reply, result);
            send(reply);
            System.out.println("liste question by test " + listProposition  + "from server");

         }
         catch(Exception ex) { ex.printStackTrace(); }
      }
   }





   class HandleAffecterCours extends OneShotBehaviour {
// --------------------------------------------------  Handler for an Information query

      private ACLMessage query;

      HandleAffecterCours(Agent a, ACLMessage query) {

         super(a);
         this.query = query;
      }

      public void action() {

         try {
            ContentElement content = getContentManager().extractContent(query);
            AffecterCours cours_aff = (AffecterCours)((Action)content).getAction();
            Boolean res = repo_coursetudiant.AffecterEtudiant_Cours(cours_aff.getId_cours(),cours_aff.getId_etudiant());
            if (!res){
               String reponse = "Affectation du cours d'id "+cours_aff.getId_cours()+" à l'etudiant d'id "+cours_aff.getId_etudiant()+" a échoue";
               ACLMessage reply = query.createReply();
               reply.setPerformative(ACLMessage.INFORM);
               Result result = new Result((Action)content, (String)reponse);
               getContentManager().fillContent(reply, result);
               send(reply);
               System.out.println("affectation cours de serveur");
            }
            else {
               //String reponse = "Cours d'id "+cours_aff.getId_cours()+" a ete affecté à l'etudiant d'id"+cours_aff.getId_etudiant();
               ACLMessage reply = query.createReply();
               reply.setPerformative(ACLMessage.INFORM);
               CoursEtudiant ce = new CoursEtudiant();
               Cours cours = repo.findById(cours_aff.getId_cours());
               Etudiant etudiant = repo_etudiant.findById(cours_aff.getId_etudiant());
               ce.setCours_asso(cours);
               ce.setEtudiant_asso(etudiant);
               Result result = new Result((Action)content, (CoursEtudiant)ce);
               getContentManager().fillContent(reply, result);
               send(reply);
               System.out.println("affectation cours de serveur");

            }

         }
         catch(Exception ex) { ex.printStackTrace(); }
      }
   }


   class HandleAffecterTest extends OneShotBehaviour {
// --------------------------------------------------  Handler for an Information query

      private ACLMessage query;

      HandleAffecterTest(Agent a, ACLMessage query) {

         super(a);
         this.query = query;
      }

      public void action() {

         try {
            ContentElement content = getContentManager().extractContent(query);
            AffecterTest test_aff = (AffecterTest)((Action)content).getAction();
            Boolean res = repo_test_etudiant.AffecterEtudiant_Test(test_aff.getId_test(),test_aff.getId_etudiant());
            if (!res){
               String reponse = "Affectation du test d'id "+test_aff.getId_test()+" à l'etudiant d'id "+test_aff.getId_etudiant()+" a échoue";
               ACLMessage reply = query.createReply();
               reply.setPerformative(ACLMessage.INFORM);
               Result result = new Result((Action)content, (String)reponse);
               getContentManager().fillContent(reply, result);
               send(reply);
               System.out.println("affectation cours de serveur");
            }
            else {
               //String reponse = "Cours d'id "+cours_aff.getId_cours()+" a ete affecté à l'etudiant d'id"+cours_aff.getId_etudiant();
               ACLMessage reply = query.createReply();
               reply.setPerformative(ACLMessage.INFORM);
               TestEtudiant ce = new TestEtudiant();
               Test test = repo_test.findById(test_aff.getId_test());
               Etudiant etudiant = repo_etudiant.findById(test_aff.getId_etudiant());
               ce.setTest_asso(test);
               ce.setEtudiant_asso_test(etudiant);
               Result result = new Result((Action)content, (TestEtudiant)ce);
               getContentManager().fillContent(reply, result);
               send(reply);
               System.out.println("affectation test de serveur");

            }

         }
         catch(Exception ex) { ex.printStackTrace(); }
      }
   }

   class HandleListCoursEnseignant extends OneShotBehaviour {
// ----------------------------------------------------  Handler for a CreateAccount request

      private ACLMessage request;

      HandleListCoursEnseignant(Agent a, ACLMessage request) {

         super(a);
         this.request = request;
      }

      public void action() {

         try {
            ContentElement content = getContentManager().extractContent(request);
            ListCoursEnseignant ca = (ListCoursEnseignant) ((Action)content).getAction();
            java.util.ArrayList<Cours> listcours = null;
            listcours = repo.findByEnseignant(ca.getId_enseignant());
            jade.util.leap.ArrayList listcoursjade = new jade.util.leap.ArrayList(listcours);
            Result result = new Result((Action)content, (jade.util.leap.ArrayList)listcoursjade);
            ACLMessage reply = request.createReply();
            reply.setPerformative(ACLMessage.INFORM);
            getContentManager().fillContent(reply, result);
            send(reply);

            System.out.println("List cours retournée");
         }
         catch(Exception ex) { ex.printStackTrace(); }
      }
   }

   class HandleListAllCours extends OneShotBehaviour {
// ----------------------------------------------------  Handler for a CreateAccount request

      private ACLMessage request;

      HandleListAllCours(Agent a, ACLMessage request) {

         super(a);
         this.request = request;
      }

      public void action() {

         try {
            ContentElement content = getContentManager().extractContent(request);
            ListeAllCours ca = (ListeAllCours) ((Action)content).getAction();
            java.util.ArrayList<Cours> listcours = null;
            listcours = repo_cours.findAll();
            jade.util.leap.ArrayList listcoursjade = new jade.util.leap.ArrayList(listcours);
            Result result = new Result((Action)content, (jade.util.leap.ArrayList)listcoursjade);
            ACLMessage reply = request.createReply();
            reply.setPerformative(ACLMessage.INFORM);
            getContentManager().fillContent(reply, result);
            send(reply);

            System.out.println("List All cours retournée");
         }
         catch(Exception ex) { ex.printStackTrace(); }
      }
   }


   class HandleListCoursSearch extends OneShotBehaviour {
// ----------------------------------------------------  Handler for a CreateAccount request

      private ACLMessage request;

      HandleListCoursSearch(Agent a, ACLMessage request) {

         super(a);
         this.request = request;
      }

      public void action() {

         try {
            ContentElement content = getContentManager().extractContent(request);
            ListeCoursSearch ca = (ListeCoursSearch) ((Action)content).getAction();
            java.util.ArrayList<Cours> listcours = null;
            listcours = repo_cours.findCoursLikkSearch(ca.getSearch());
            jade.util.leap.ArrayList listcoursjade = new jade.util.leap.ArrayList(listcours);
            Result result = new Result((Action)content, (jade.util.leap.ArrayList)listcoursjade);
            ACLMessage reply = request.createReply();
            reply.setPerformative(ACLMessage.INFORM);
            getContentManager().fillContent(reply, result);
            send(reply);

            System.out.println("List All cours retournée");
         }
         catch(Exception ex) { ex.printStackTrace(); }
      }
   }

   class HandleListCoursSearchDescription extends OneShotBehaviour {
// ----------------------------------------------------  Handler for a CreateAccount request

      private ACLMessage request;

      HandleListCoursSearchDescription(Agent a, ACLMessage request) {

         super(a);
         this.request = request;
      }

      public void action() {

         try {
            ContentElement content = getContentManager().extractContent(request);
            ListeCoursSearchDescription ca = (ListeCoursSearchDescription) ((Action)content).getAction();
            java.util.ArrayList<Cours> listcours = null;
            listcours = repo_cours.findCoursLikkSearchDescription(ca.getSearch());
            jade.util.leap.ArrayList listcoursjade = new jade.util.leap.ArrayList(listcours);
            Result result = new Result((Action)content, (jade.util.leap.ArrayList)listcoursjade);
            ACLMessage reply = request.createReply();
            reply.setPerformative(ACLMessage.INFORM);
            getContentManager().fillContent(reply, result);
            send(reply);

            System.out.println("List All cours retournée");
         }
         catch(Exception ex) { ex.printStackTrace(); }
      }
   }




   class HandleListEtudiantsCours extends OneShotBehaviour {
// ----------------------------------------------------  Handler for a CreateAccount request

      private ACLMessage request;

      HandleListEtudiantsCours(Agent a, ACLMessage request) {

         super(a);
         this.request = request;
      }

      public void action() {

         try {
            ContentElement content = getContentManager().extractContent(request);
            ListEtudiantCours ca = (ListEtudiantCours) ((Action)content).getAction();
            java.util.ArrayList<Etudiant> listetudiant = null;
            listetudiant = repo_coursetudiant.findEtudiantsbyCours(ca.getId_cours());
            jade.util.leap.ArrayList listcoursjade = new jade.util.leap.ArrayList(listetudiant);
            Result result = new Result((Action)content, (jade.util.leap.ArrayList)listcoursjade);
            ACLMessage reply = request.createReply();
            reply.setPerformative(ACLMessage.INFORM);
            getContentManager().fillContent(reply, result);
            send(reply);

            System.out.println("List cours retournée");
         }
         catch(Exception ex) { ex.printStackTrace(); }
      }
   }

   class HandleListAllTestEtudiant extends OneShotBehaviour {
// ----------------------------------------------------  Handler for a CreateAccount request

      private ACLMessage request;

      HandleListAllTestEtudiant(Agent a, ACLMessage request) {

         super(a);
         this.request = request;
      }

      public void action() {

         try {
            ContentElement content = getContentManager().extractContent(request);

            java.util.ArrayList<TestEtudiant> listtestetudiant = null;
            listtestetudiant = repo_test_etudiant.findall();
            jade.util.leap.ArrayList listjade = new jade.util.leap.ArrayList(listtestetudiant);
            Result result = new Result((Action)content, (jade.util.leap.ArrayList)listjade);
            ACLMessage reply = request.createReply();
            reply.setPerformative(ACLMessage.INFORM);
            getContentManager().fillContent(reply, result);
            send(reply);


         }
         catch(Exception ex) { ex.printStackTrace(); }
      }
   }

   class HandleListTestEtudiant extends OneShotBehaviour {
// ----------------------------------------------------  Handler for a CreateAccount request

      private ACLMessage request;

      HandleListTestEtudiant(Agent a, ACLMessage request) {

         super(a);
         this.request = request;
      }

      public void action() {

         try {
            ContentElement content = getContentManager().extractContent(request);
            ListTestEtudiant ca = (ListTestEtudiant) ((Action)content).getAction();
            java.util.ArrayList<TestEtudiant> listtestetudiant = null;
            listtestetudiant = repo_test_etudiant.findBtIdEtudiant(ca.getId_etudiant());
            jade.util.leap.ArrayList listjade = new jade.util.leap.ArrayList(listtestetudiant);
            Result result = new Result((Action)content, (jade.util.leap.ArrayList)listjade);
            ACLMessage reply = request.createReply();
            reply.setPerformative(ACLMessage.INFORM);
            getContentManager().fillContent(reply, result);
            send(reply);


         }
         catch(Exception ex) { ex.printStackTrace(); }
      }
   }

   class HandleListTestEtudiantByTest extends OneShotBehaviour {
// ----------------------------------------------------  Handler for a CreateAccount request

      private ACLMessage request;

      HandleListTestEtudiantByTest(Agent a, ACLMessage request) {

         super(a);
         this.request = request;
      }

      public void action() {

         try {
            ContentElement content = getContentManager().extractContent(request);
            ListTestEtudiantByTest ca = (ListTestEtudiantByTest) ((Action)content).getAction();
            java.util.ArrayList<TestEtudiant> listtestetudiant = null;
            listtestetudiant = repo_test_etudiant.findBtIdTest(ca.getId_test());
            jade.util.leap.ArrayList listjade = new jade.util.leap.ArrayList(listtestetudiant);
            Result result = new Result((Action)content, (jade.util.leap.ArrayList)listjade);
            ACLMessage reply = request.createReply();
            reply.setPerformative(ACLMessage.INFORM);
            getContentManager().fillContent(reply, result);
            send(reply);


         }
         catch(Exception ex) { ex.printStackTrace(); }
      }
   }





   class HandleListEtudiantsEnseignant extends OneShotBehaviour {
// ----------------------------------------------------  Handler for a CreateAccount request

      private ACLMessage request;

      HandleListEtudiantsEnseignant(Agent a, ACLMessage request) {

         super(a);
         this.request = request;
      }

      public void action() {

         try {
            ContentElement content = getContentManager().extractContent(request);
            ListEtudiantEnseignant ca = (ListEtudiantEnseignant) ((Action)content).getAction();
            java.util.ArrayList<Etudiant> listetudiant = null;
            listetudiant = repo_enseignant.findEtudiantsbyid(ca.getId_enseignant());
            jade.util.leap.ArrayList listcoursjade = new jade.util.leap.ArrayList(listetudiant);
            Result result = new Result((Action)content, (jade.util.leap.ArrayList)listcoursjade);
            ACLMessage reply = request.createReply();
            reply.setPerformative(ACLMessage.INFORM);
            getContentManager().fillContent(reply, result);
            send(reply);

            System.out.println("List cours retournée");
         }
         catch(Exception ex) { ex.printStackTrace(); }
      }
   }



   void replyNotUnderstood(ACLMessage msg) {
// -----------------------------------------

      try {
         ContentElement content = getContentManager().extractContent(msg);
         ACLMessage reply = msg.createReply();
         reply.setPerformative(ACLMessage.NOT_UNDERSTOOD);
         getContentManager().fillContent(reply, content);
         send(reply);
         System.out.println("Not understood!");
      }
      catch(Exception ex) { ex.printStackTrace(); }
   }

  

   Object processInformation(InformationCours info) {
// -------------------------------------------

      //   Cours crs = (Cours)listcours.get(info.getId_Cours());
      Cours crs = repo.findById(info.getId_Cours());
      if (crs == null) return newProblem(COURS_INTROUVABLE);
      return crs;


   }

   Object processInformationEtudiant(InformationEtudiant info) {
// -------------------------------------------


      Etudiant etd = repo_etudiant.findById(info.getId_etudiant());
      if (etd == null) return newProblem(ETUDIANT_INTROUVABLE);
      return etd;


   }

   Object processAffectationCours(AffecterCours cours_aff){
      //traitement pour ajouter un etudiant a un cours
      return null ;
   }

//--------------------------- Utility methods ----------------------------//

   Problem newProblem(int num) {
// -----------------------------

      String msg = "";

      if (num == COURS_INTROUVABLE)
         msg = MSG_COURS_INTOUVABLE;

     // else if (num == NOT_ENOUGH_MONEY)
     //    msg = PB_NOT_ENOUGH_MONEY;

     // else if (num == ILLEGAL_OPERATION)
     //    msg = PB_ILLEGAL_OPERATION;

      Problem prob = new Problem();
      prob.setNum(num);
      prob.setMsg(msg);
      return prob;
   }

   int generateId() {
// ---------------------
      idCnt++;
      return idCnt;
   }

    
}
