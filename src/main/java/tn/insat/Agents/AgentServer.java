/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.insat.Agents;


import jade.util.leap.ArrayList;
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

import java.lang.reflect.Array;
import java.util.*;
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
   ISujetForumRepository repo_sujet_forum = new SujetForumRepository();
   IReponseForumRepository repo_reponse_forum = new ReponseForumRepository();
   IConnaissanceEtudiantRepository repo_connaissances_etudiant = new ConnaissanceEtudiantRepository();
   IRessourceRepository repo_ressource = new RessourceRepository();

    
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
                  else if (action instanceof CreateRessource)
                     addBehaviour(new HandleCreateRessource(myAgent, msg));
                  else if (action instanceof CreateSujetForum)
                     addBehaviour(new HandleCreateSujetForum(myAgent, msg));
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
                  else if (action instanceof CreateReponseForum)
                     addBehaviour(new HandleCreateReponseForum(myAgent, msg));
                  else if (action instanceof CreateEtudiant)
                     addBehaviour(new HandleCreateEtudiant(myAgent, msg));
                  else if (action instanceof CreateEnseignant)
                     addBehaviour(new HandleCreateEnseignant(myAgent, msg));
                  else
                     replyNotUnderstood(msg);
                  break;

               case (ACLMessage.QUERY_REF):

                  System.out.println("message server : Query from " + msg.getSender().getLocalName());

                  if (action instanceof InformationCours)
                     addBehaviour(new HandleInformationCours(myAgent, msg));
                  else if (action instanceof ListCoursEnseignant)
                     addBehaviour(new HandleListCoursEnseignant(myAgent, msg));
                  else if (action instanceof InformationSujetForum)
                     addBehaviour(new HandleInformationSujetForum(myAgent, msg));
                  else if (action instanceof InformationEnseignant)
                     addBehaviour(new HandleInformationEnseignant(myAgent, msg));
                  else if (action instanceof ListEtudiantCours)
                     addBehaviour(new HandleListEtudiantsCours(myAgent, msg));
                  else if (action instanceof ListEtudiantEnseignant)
                     addBehaviour(new HandleListEtudiantsEnseignant(myAgent, msg));
                  else if (action instanceof ListeAllCours)
                     addBehaviour(new HandleListAllCours(myAgent, msg));
                  else if (action instanceof ListeAllSujetsForum)
                     addBehaviour(new HandleListAllSujetsForum(myAgent, msg));
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
                  else if (action instanceof SujetForumByCours)
                     addBehaviour(new HandleListSujetForumByCours(myAgent,msg));
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
                  else if (action instanceof ReponseForumBySujet)
                     addBehaviour(new HandleListReponseForumBySujet(myAgent,msg));
                  else if (action instanceof LoginEtudiant)
                     addBehaviour(new HandleLoginEtudiant(myAgent,msg));
                  else if (action instanceof LoginEnseignant)
                     addBehaviour(new HandleLoginEnseignant(myAgent,msg));
                  else if (action instanceof SuggererCours)
                     addBehaviour(new HandleSuggererCours(myAgent,msg));
                  else if (action instanceof RessourceByCours)
                     addBehaviour(new HandleRessourceByCours(myAgent,msg));
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

   class HandleCreateRessource extends OneShotBehaviour {
// ----------------------------------------------------  Handler for a CreateAccount request

      private ACLMessage request;

      HandleCreateRessource(Agent a, ACLMessage request) {

         super(a);
         this.request = request;
      }

      public void action() {

         try {
            ContentElement content = getContentManager().extractContent(request);
            CreateRessource ca = (CreateRessource)((Action)content).getAction();
            Ressource ress = new Ressource();

            ress.setId_ressource(ca.getId_ressource());
            ress.setCours_ressource(ca.getCours_ressource());
            ress.setDescription_ressource(ca.getDescription_ressource());
            ress.setType_ressource(ca.getType_ressource());
            repo_ressource.create(ress);
            ress = repo_ressource.findById(ca.getId_ressource());
            Result result = new Result((Action)content, (Ressource)ress);
            ACLMessage reply = request.createReply();
            reply.setPerformative(ACLMessage.INFORM);
            getContentManager().fillContent(reply, result);
            send(reply);
            System.out.println("Ressource [" + ress.getDescription_ressource() + " # " +
                    ress.getId_ressource() + "] created!");
         }
         catch(Exception ex) { ex.printStackTrace(); }
      }
   }


   class HandleCreateSujetForum extends OneShotBehaviour {
// ----------------------------------------------------  Handler for a CreateAccount request

      private ACLMessage request;

      HandleCreateSujetForum(Agent a, ACLMessage request) {

         super(a);
         this.request = request;
      }

      public void action() {

         try {
            ContentElement content = getContentManager().extractContent(request);
            CreateSujetForum ca = (CreateSujetForum) ((Action)content).getAction();
            SujetForum sujetForum = new SujetForum();
            sujetForum.setId_sujetforum(ca.getId_sujetForum());
            sujetForum.setCours_sujetforum(ca.getCours_sujetforum());
            sujetForum.setText_sujetforum(ca.getText_sujetforum());
            sujetForum.setTitre_sujetforum(ca.getTitre_sujetforum());
            sujetForum.setEnseignant_sujetforum(ca.getEnseignant_sujetforum());
            sujetForum.setEtudiant_sujetforum(ca.getEtudiant_sujetforum());
            repo_sujet_forum.create(sujetForum);
            sujetForum = repo_sujet_forum.findById(ca.getId_sujetForum());
            Result result = new Result((Action)content, (SujetForum)sujetForum);
            ACLMessage reply = request.createReply();
            reply.setPerformative(ACLMessage.INFORM);
            getContentManager().fillContent(reply, result);
            send(reply);
         }
         catch(Exception ex) { ex.printStackTrace(); }
      }
   }

   class HandleCreateReponseForum extends OneShotBehaviour {
// ----------------------------------------------------  Handler for a CreateAccount request

      private ACLMessage request;

      HandleCreateReponseForum(Agent a, ACLMessage request) {

         super(a);
         this.request = request;
      }

      public void action() {

         try {
            ContentElement content = getContentManager().extractContent(request);
            CreateReponseForum ca = (CreateReponseForum) ((Action)content).getAction();
            ReponseForum reponseForum = new ReponseForum();
            reponseForum.setId_reponseforum(ca.getId_reponseforum());
            reponseForum.setSujetforum_reponseforum(ca.getSujet_reponseforum());
            reponseForum.setText_reponseforum(ca.getText_reponseforum());
            reponseForum.setEnseignant_reponseforum(ca.getEnseignant_reponseforum());
            reponseForum.setEtudiant_reponseforum(ca.getEtudiant_reponseforum());
            repo_reponse_forum.create(reponseForum);
            reponseForum = repo_reponse_forum.findById(ca.getId_reponseforum());
            Result result = new Result((Action)content, (ReponseForum)reponseForum);
            ACLMessage reply = request.createReply();
            reply.setPerformative(ACLMessage.INFORM);
            getContentManager().fillContent(reply, result);
            send(reply);
         }
         catch(Exception ex) { ex.printStackTrace(); }
      }
   }

   class HandleCreateEtudiant extends OneShotBehaviour {
// ----------------------------------------------------  Handler for a CreateAccount request

      private ACLMessage request;

      HandleCreateEtudiant(Agent a, ACLMessage request) {

         super(a);
         this.request = request;
      }

      public void action() {

         try {
            ContentElement content = getContentManager().extractContent(request);
            CreateEtudiant resource = (CreateEtudiant) ((Action)content).getAction();
            Etudiant etudiant = new Etudiant();
            etudiant.setId_etudiant(resource.getId_etudiant());
            etudiant.setNom_etudiant(resource.getNom_etudiant());
            etudiant.setAge_etudiant(resource.getAge_etudiant());
            etudiant.setEmail_etudiant(resource.getEmail_etudiant());
            etudiant.setPassword_etudiant(resource.getPassword_etudiant());
            etudiant.setCategory_etudiant(resource.getCategory_etudiant());
            etudiant.setInstitut_etudiant(resource.getInstitut_etudiant());
            repo_etudiant.create(etudiant);
            etudiant = repo_etudiant.findById(resource.getId_etudiant());
            Result result = new Result((Action)content, (Etudiant)etudiant);
            ACLMessage reply = request.createReply();
            reply.setPerformative(ACLMessage.INFORM);
            getContentManager().fillContent(reply, result);
            send(reply);
         }
         catch(Exception ex) { ex.printStackTrace(); }
      }
   }

   class HandleCreateEnseignant extends OneShotBehaviour {
// ----------------------------------------------------  Handler for a CreateAccount request

      private ACLMessage request;

      HandleCreateEnseignant(Agent a, ACLMessage request) {

         super(a);
         this.request = request;
      }

      public void action() {

         try {
            ContentElement content = getContentManager().extractContent(request);
            CreateEnseignant resource = (CreateEnseignant) ((Action)content).getAction();
            Enseignant cc = new Enseignant();
            cc.setId_enseignant(resource.getId_enseignant());
            cc.setNom_enseignant(resource.getNom_enseignant());
            cc.setAge_enseignant(resource.getAge_enseignant());
            cc.setEmail_enseignant(resource.getEmail_enseignant());
            cc.setPassword_enseignant(resource.getPassword_enseignant());
            cc.setCategory_enseignant(resource.getCategory_enseignant());
            cc.setInstitut_enseignant(resource.getInstitut_enseignant());
            repo_enseignant.create(cc);
            cc = repo_enseignant.findById(resource.getId_enseignant());
            Result result = new Result((Action)content, (Enseignant)cc);
            ACLMessage reply = request.createReply();
            reply.setPerformative(ACLMessage.INFORM);
            getContentManager().fillContent(reply, result);
            send(reply);
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

   class HandleInformationSujetForum extends OneShotBehaviour {
// --------------------------------------------------  Handler for an Information query

      private ACLMessage query;

      HandleInformationSujetForum(Agent a, ACLMessage query) {

         super(a);
         this.query = query;
      }

      public void action() {

         try {

            ContentElement content = getContentManager().extractContent(query);
            InformationSujetForum info = (InformationSujetForum)((Action)content).getAction();
            SujetForum frm = repo_sujet_forum.findById(info.getId_Forum());
            if (frm == null) replyNotUnderstood(query);
            else {
               ACLMessage reply = query.createReply();
               reply.setPerformative(ACLMessage.INFORM);
               Result result = new Result((Action)content, frm);
                  getContentManager().fillContent(reply, result);
               send(reply);
               System.out.println("information du sujetForum " + frm  + "from server");
            }
         }
         catch(Exception ex) { ex.printStackTrace(); }
      }
   }




   class HandleLoginEtudiant extends OneShotBehaviour {
// --------------------------------------------------  Handler for an Information query

      private ACLMessage query;

      HandleLoginEtudiant(Agent a, ACLMessage query) {

         super(a);
         this.query = query;
      }

      public void action() {

         try {

            ContentElement content = getContentManager().extractContent(query);
            LoginEtudiant login = (LoginEtudiant)((Action)content).getAction();
            Etudiant etd = repo_etudiant.findByLoginAndPassword(login.getEmail_etudiant(),login.getPassword_etudiant());


               ACLMessage reply = query.createReply();
               reply.setPerformative(ACLMessage.INFORM);
               Result result = new Result((Action)content, etd);
               getContentManager().fillContent(reply, result);
               send(reply);
               System.out.println("information de l'etudiant " + etd  + "from server");

         }
         catch(Exception ex) { ex.printStackTrace(); }
      }
   }

   class HandleLoginEnseignant extends OneShotBehaviour {
// --------------------------------------------------  Handler for an Information query

      private ACLMessage query;

      HandleLoginEnseignant(Agent a, ACLMessage query) {

         super(a);
         this.query = query;
      }

      public void action() {

         try {

            ContentElement content = getContentManager().extractContent(query);
            LoginEnseignant login = (LoginEnseignant)((Action)content).getAction();
            Enseignant ens = repo_enseignant.findByLoginAndPassword(login.getEmail_enseignant(),login.getPassword_enseignant());


            ACLMessage reply = query.createReply();
            reply.setPerformative(ACLMessage.INFORM);
            Result result = new Result((Action)content, ens);
            getContentManager().fillContent(reply, result);
            send(reply);
            System.out.println("information de l'enseignant " + ens  + "from server");

         }
         catch(Exception ex) { ex.printStackTrace(); }
      }
   }

   class HandleInformationEnseignant extends OneShotBehaviour {
// --------------------------------------------------  Handler for an Information query

      private ACLMessage query;

      HandleInformationEnseignant(Agent a, ACLMessage query) {

         super(a);
         this.query = query;
      }

      public void action() {

         try {

            ContentElement content = getContentManager().extractContent(query);
            InformationEnseignant info = (InformationEnseignant)((Action)content).getAction();
            Enseignant ens = repo_enseignant.findById(info.getId_enseignant());
            if (ens == null) replyNotUnderstood(query);
            else {
               ACLMessage reply = query.createReply();
               reply.setPerformative(ACLMessage.INFORM);
               Result result = new Result((Action)content, ens);
               getContentManager().fillContent(reply, result);
               send(reply);
               System.out.println("information de l'enseignant " + ens  + "from server");
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

   class HandleListSujetForumByCours extends OneShotBehaviour {
// --------------------------------------------------  Handler for an Information query

      private ACLMessage query;

      HandleListSujetForumByCours(Agent a, ACLMessage query) {

         super(a);
         this.query = query;
      }

      public void action() {

         try {

            ContentElement content = getContentManager().extractContent(query);

            SujetForumByCours info = (SujetForumByCours) ((Action)content).getAction();
            java.util.ArrayList<SujetForum> listForum = null;
            listForum = (java.util.ArrayList<SujetForum>) repo_sujet_forum.findByCours(info.getId_cours());
            jade.util.leap.ArrayList listforumjade = new jade.util.leap.ArrayList(listForum);
            Result result = new Result((Action)content, (jade.util.leap.ArrayList)listforumjade);
            ACLMessage reply = query.createReply();
            reply.setPerformative(ACLMessage.INFORM);
            getContentManager().fillContent(reply, result);
            send(reply);
            System.out.println("liste sujet forum by cours " + listForum  + "from server");

         }
         catch(Exception ex) { ex.printStackTrace(); }
      }
   }

   class HandleListReponseForumBySujet extends OneShotBehaviour {
// --------------------------------------------------  Handler for an Information query

      private ACLMessage query;

      HandleListReponseForumBySujet(Agent a, ACLMessage query) {

         super(a);
         this.query = query;
      }

      public void action() {

         try {

            ContentElement content = getContentManager().extractContent(query);

            ReponseForumBySujet info = (ReponseForumBySujet) ((Action)content).getAction();
            java.util.ArrayList<ReponseForum> listForum = null;
            listForum = (java.util.ArrayList<ReponseForum>) repo_reponse_forum.findBySujet(info.getId_sujet());
            jade.util.leap.ArrayList listforumjade = new jade.util.leap.ArrayList(listForum);
            Result result = new Result((Action)content, (jade.util.leap.ArrayList)listforumjade);
            ACLMessage reply = query.createReply();
            reply.setPerformative(ACLMessage.INFORM);
            getContentManager().fillContent(reply, result);
            send(reply);
            System.out.println("liste sujet forum by cours " + listForum  + "from server");

         }
         catch(Exception ex) { ex.printStackTrace(); }
      }
   }


   class HandleRessourceByCours extends OneShotBehaviour {
// --------------------------------------------------  Handler for an Information query

      private ACLMessage query;

      HandleRessourceByCours(Agent a, ACLMessage query) {

         super(a);
         this.query = query;
      }

      public void action() {

         try {

            ContentElement content = getContentManager().extractContent(query);

            RessourceByCours info = (RessourceByCours) ((Action)content).getAction();
            java.util.ArrayList<Ressource> list = null;
            list = (java.util.ArrayList<Ressource>) repo_ressource.findByCours(info.getId_cours());
            jade.util.leap.ArrayList listQuestionJade = new jade.util.leap.ArrayList(list);
            Result result = new Result((Action)content, (jade.util.leap.ArrayList)listQuestionJade);
            ACLMessage reply = query.createReply();
            reply.setPerformative(ACLMessage.INFORM);
            getContentManager().fillContent(reply, result);
            send(reply);
            System.out.println("liste ressource by cours " + list  + "from server");

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





   class HandleSuggererCours extends OneShotBehaviour {
// ----------------------------------------------------  Handler for a CreateAccount request

      private ACLMessage request;

      HandleSuggererCours(Agent a, ACLMessage request) {

         super(a);
         this.request = request;
      }

      private double calculateSimilarity(Etudiant activeEtudiant,Etudiant other){
         java.util.ArrayList<Cours> listecours = repo_cours.findByEtudiant(activeEtudiant.getId_etudiant());
         java.util.ArrayList<ConnaissanceEtudiant> connaissances_active = repo_connaissances_etudiant.findConnaissancesByEtudiant(activeEtudiant.getId_etudiant());
         java.util.ArrayList<ConnaissanceEtudiant> connaissances_other  = repo_connaissances_etudiant.findConnaissancesByEtudiant(other.getId_etudiant());
         if(listecours.size()!=0){
               int connaissanceInCommon= 0 ;
               double differenceSum = 0;
                for(ConnaissanceEtudiant c1 :connaissances_active){
                   for(ConnaissanceEtudiant c2 : connaissances_other){
                        if(c1.getConnaissance_asso().getId_connaissance()==c2.getConnaissance_asso().getId_connaissance()){
                        connaissanceInCommon++;
                        differenceSum += Math.abs(c1.getRating()-c2.getRating());
                      }
                   }
                }
         if (connaissanceInCommon > 0 ) {
            return differenceSum/connaissanceInCommon;
         }
         else return Integer.MAX_VALUE;



         }
         else{
            if(activeEtudiant.getCategory_etudiant().equals(other.getCategory_etudiant().toString()))
               return 0 ;
            else return Integer.MAX_VALUE;
         }



      }

      java.util.ArrayList<Etudiant> createNeighborhood(Etudiant activeEtudiant,double SIMILARITY_THRESHOLD ){
         java.util.ArrayList<Etudiant> hood = new java.util.ArrayList<Etudiant>();
         for(Etudiant other:repo_etudiant.findAll()){
            if(other.getId_etudiant() != activeEtudiant.getId_etudiant()){
               double similarity = calculateSimilarity(activeEtudiant,other);
               if(similarity <= SIMILARITY_THRESHOLD ){
                  hood.add(other);
               }
            }
         }
         return hood ;
      }

      public void action() {

         try {
            ContentElement content = getContentManager().extractContent(request);
            SuggererCours ca = (SuggererCours) ((Action)content).getAction();
            Etudiant etudiant = repo_etudiant.findById(ca.getId_etudiant());
            //traitement collaborative filtering
            java.util.ArrayList<Etudiant> neighborhood  = createNeighborhood(etudiant,1);
            java.util.ArrayList<Cours> listecourssuggeres = new java.util.ArrayList<Cours>();
            java.util.ArrayList<Cours> listecours = repo_cours.findByEtudiant(etudiant.getId_etudiant());
            for (Etudiant etd : neighborhood){
               for (Cours c : repo_cours.findByEtudiant(etd.getId_etudiant()))
               {
                  if(!listecours.contains(c)){
                     listecourssuggeres.add(c);
                  }
               }
            }

            if(listecourssuggeres.size()==0){
               Cours c = new Cours();
               c.setId_cours(0);
               c.setDuree(00);
               c.setDescription("aucune");
               c.setIntitule("aucun ");
               listecourssuggeres.add(c);
            }





            jade.util.leap.ArrayList listcoursjade = new jade.util.leap.ArrayList(listecourssuggeres);
            Result result = new Result((Action)content, (jade.util.leap.ArrayList)listcoursjade);
            ACLMessage reply = request.createReply();
            reply.setPerformative(ACLMessage.INFORM);
            getContentManager().fillContent(reply, result);
            send(reply);

            System.out.println("List cours suggerés retournée");
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

   class HandleListAllSujetsForum extends OneShotBehaviour {
// ----------------------------------------------------  Handler for a CreateAccount request

      private ACLMessage request;

      HandleListAllSujetsForum(Agent a, ACLMessage request) {

         super(a);
         this.request = request;
      }

      public void action() {

         try {
            ContentElement content = getContentManager().extractContent(request);
            java.util.ArrayList<SujetForum> listSujetsForum = null;
            listSujetsForum = repo_sujet_forum.findAll();
            jade.util.leap.ArrayList listsujetjade = new jade.util.leap.ArrayList(listSujetsForum);
            Result result = new Result((Action)content, (jade.util.leap.ArrayList)listsujetjade);
            ACLMessage reply = request.createReply();
            reply.setPerformative(ACLMessage.INFORM);
            getContentManager().fillContent(reply, result);
            send(reply);

            System.out.println("List All sujet forum retournée");
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
