/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.insat.Agents;

import com.fasterxml.jackson.databind.annotation.JsonAppend;
import jade.content.*;
import jade.content.lang.*;
import jade.content.lang.sl.*;
import jade.content.onto.*;
import jade.content.onto.basic.*;
import jade.core.*;
import jade.core.behaviours.*;
import jade.domain.*;
import jade.domain.FIPAAgentManagement.*;
import jade.gui.*;
import jade.lang.acl.*;
import jade.util.leap.*;
import tn.insat.Client.ExampleController;
import tn.insat.Client.SemaphoreClass;
import tn.insat.Client.SingletonQuestion;
import tn.insat.ontologies.*;

/**
 *
 * @author saif
 */
public class AgentTest extends Agent implements Vocabulary, IAgentTest {
    
     private AID server;
    private Codec codec = new SLCodec();
   private Ontology ontology = OntologyWSK.getInstance();
    
     protected void setup() {
// ------------------------

      // Register language and ontology
      getContentManager().registerLanguage(codec);
      getContentManager().registerOntology(ontology);
         setEnabledO2ACommunication(true, 10);

         addBehaviour(new CyclicBehaviour() {

             public void action() {
                 // Retrieve the first object in the queue and print it on
                 // the standard output
                 Object obj = getO2AObject();
                 if(obj != null) {
                     System.out.println("Got an object from the queue: [" + obj + "]");
                     if(obj instanceof TestByCours){
                         addBehaviour(new OneShotBehaviour() {

                             @Override
                             public void action() {
                                 TestByCours aff = (TestByCours) obj ;
                                 listerTestByCours(aff);

                             }
                         });
                     }
                     else if(obj instanceof CreateTest){
                         addBehaviour(new OneShotBehaviour() {

                             @Override
                             public void action() {
                                 CreateTest cc = (CreateTest)obj ;
                                 createTest(cc);
                             }
                         });
                     }
                     else if(obj instanceof CreateQuestion){
                         addBehaviour(new OneShotBehaviour() {

                             @Override
                             public void action() {
                                 CreateQuestion cc = (CreateQuestion)obj ;
                                 createQuestion(cc);
                             }
                         });
                     }
                     else if(obj instanceof CreateProposition){
                         addBehaviour(new OneShotBehaviour() {

                             @Override
                             public void action() {
                                 CreateProposition cc = (CreateProposition)obj ;
                                 createProposition(cc);
                             }
                         });
                     }
                     else if(obj instanceof CreateReponseEtudiant){
                         addBehaviour(new OneShotBehaviour() {

                             @Override
                             public void action() {
                                 CreateReponseEtudiant cc = (CreateReponseEtudiant)obj ;
                                 createReponseEtudiant(cc);
                             }
                         });
                     }
                     else if(obj instanceof QuestionByTest){
                         addBehaviour(new OneShotBehaviour() {

                             @Override
                             public void action() {
                                 QuestionByTest aff = (QuestionByTest) obj ;
                                 listerQuestionByTest(aff);

                             }
                         });
                     }
                     else if(obj instanceof PropositionByQuestion){
                         addBehaviour(new OneShotBehaviour() {

                             @Override
                             public void action() {
                                 PropositionByQuestion aff = (PropositionByQuestion) obj ;
                                 listerPropostionsByQuestion(aff);

                             }
                         });
                     }
                     else if(obj instanceof ListAllTestEtudiant){
                         addBehaviour(new OneShotBehaviour() {

                             @Override
                             public void action() {
                                 ListAllTestEtudiant aff = (ListAllTestEtudiant) obj ;
                                 listerAllTestEtudiant(aff);

                             }
                         });
                     }
                     else if(obj instanceof ListTestEtudiant){
                         addBehaviour(new OneShotBehaviour() {

                             @Override
                             public void action() {
                                 ListTestEtudiant aff = (ListTestEtudiant) obj ;
                                 ListTestEtudiantByIdEtudiant(aff);

                             }
                         });
                     }
                     else if(obj instanceof ListTestEtudiantByTest){
                         addBehaviour(new OneShotBehaviour() {

                             @Override
                             public void action() {
                                 ListTestEtudiantByTest aff = (ListTestEtudiantByTest) obj ;
                                 ListTestEtudiantByIdTest(aff);

                             }
                         });
                     }



                 }
                 else
                     block();
             }

         });
      
   }

   protected void takeDown() {
// ---------------------------  Terminate the program properly

       System.out.println(getLocalName() + " is now shutting down.");
       
    }

    @Override
    public void createTest(CreateTest cc) {
// ----------------------  Process to the server agent the request
//                         to create a new account
        System.out.println("create test from test");
        sendMessage(ACLMessage.REQUEST,(CreateTest) cc);

    }

    public void createQuestion(CreateQuestion cc) {
// ----------------------  Process to the server agent the request
//                         to create a new account
        System.out.println("create test from test");
        sendMessage(ACLMessage.REQUEST,(CreateQuestion) cc);

    }

    public void createProposition(CreateProposition cc) {
// ----------------------  Process to the server agent the request
//                         to create a new account
        System.out.println("create test from test");
        sendMessage(ACLMessage.REQUEST,(CreateProposition) cc);

    }

    public void createReponseEtudiant(CreateReponseEtudiant cc) {
// ----------------------  Process to the server agent the request
//                         to create a new account
        System.out.println("create reponse etudiant from test");
        sendMessage(ACLMessage.REQUEST,(CreateReponseEtudiant) cc);

    }




    public void listerTestByCours(TestByCours aff){
        sendMessage(ACLMessage.QUERY_REF, aff);

    }

    public void listerQuestionByTest(QuestionByTest aff){
        sendMessage(ACLMessage.QUERY_REF, aff);

    }

    public void listerPropostionsByQuestion(PropositionByQuestion aff){
        sendMessage(ACLMessage.QUERY_REF, aff);

    }

    public void listerAllTestEtudiant(ListAllTestEtudiant aff){
        sendMessage(ACLMessage.QUERY_REF, aff);

    }

    public void ListTestEtudiantByIdEtudiant(ListTestEtudiant aff){
        sendMessage(ACLMessage.QUERY_REF, aff);

    }

    public void ListTestEtudiantByIdTest(ListTestEtudiantByTest aff){
        sendMessage(ACLMessage.QUERY_REF, aff);

    }


    class WaitServerResponse extends ParallelBehaviour {
// ----------------------------------------------------  launch a SimpleBehaviour to receive
//                                                       servers response and a WakerBehaviour
//                                                       to terminate the waiting if there is
//                                                       no response from the server
      WaitServerResponse(Agent a) {

         super(a, 1);

         addSubBehaviour(new AgentTest.ReceiveResponse(myAgent));

         addSubBehaviour(new WakerBehaviour(myAgent, 5000) {

            protected void handleElapsedTimeout() {
               //alertGui("No response from server. Please, try later!");
               //resetStatusGui();
            }
         });
      }
   }


     class ReceiveResponse extends SimpleBehaviour {
// -----------------------------------------------  // Receive and handle server responses

      private boolean finished = false;

      ReceiveResponse(Agent a) {
         super(a);
      }

      public void action() {

          ACLMessage msg = receive(MessageTemplate.MatchSender(server));

          if (msg == null) { block(); return; }

          if (msg.getPerformative() == ACLMessage.NOT_UNDERSTOOD){
              System.out.println("Response from server: NOT UNDERSTOOD");
          }
          else if (msg.getPerformative() != ACLMessage.INFORM){
              System.out.println("Unexpected Message ");
          }
          else {
              try {
                  ContentElement content = getContentManager().extractContent(msg);

                  if (content instanceof Result) {

                      Result result = (Result) content;


                      if (result.getValue() instanceof Problem) {

                          Problem prob = (Problem) result.getItems().get(0);
                          System.out.println("Problem : " + prob.getMsg());
                      }
                      else if (result.getValue() instanceof Test) {

                          Test test= (Test) result.getItems().get(0);
                          SingletonQuestion.setTest(test);
                          SemaphoreClass.available.release();
                      }
                      else if (result.getValue() instanceof Question) {

                          Question question= (Question) result.getItems().get(0);
                          SingletonQuestion.setQuestion(question);
                          SemaphoreClass.available.release();
                      }
                      else if (result.getValue() instanceof Proposition) {

                          Proposition proposition= (Proposition) result.getItems().get(0);
                          SingletonQuestion.setProposition(proposition);
                          SemaphoreClass.available2.release();
                      }
                      else if (result.getValue() instanceof ReponseEtudiant) {

                          ReponseEtudiant reponse= (ReponseEtudiant) result.getItems().get(0);
                          SingletonQuestion.setReponseEtudiant(reponse);
                          SemaphoreClass.available2.release();
                      }
                      else if (result.getValue()  instanceof ArrayList) {

                          ArrayList lcs = (ArrayList) result.getValue() ;
                          if (lcs.get(0) instanceof Test) {
                              java.util.ArrayList<Test> L = (java.util.ArrayList<Test>)lcs.toList();
                              ExampleController.setListeTest(L);
                              SemaphoreClass.available.release();

                          }
                          else if (lcs.get(0) instanceof Question) {
                              java.util.ArrayList<Question> L = (java.util.ArrayList<Question>)lcs.toList();
                              SingletonQuestion.setQuestions(L);
                              SemaphoreClass.available.release();

                          }
                          else if (lcs.get(0) instanceof Proposition) {
                              java.util.ArrayList<Proposition> L = (java.util.ArrayList<Proposition>)lcs.toList();
                              SingletonQuestion.setPropositions(L);
                              SemaphoreClass.available.release();

                          }
                          else if (lcs.get(0) instanceof TestEtudiant) {
                              java.util.ArrayList<TestEtudiant> L = (java.util.ArrayList<TestEtudiant>)lcs.toList();
                              SingletonQuestion.setTestEtudiantList(L);
                              SemaphoreClass.available.release();

                          }


                      }
                      else System.out.println("\nUnexpected result from server!");
                  }
                  else {
                      System.out.println("\nUnable de decode response from server!");
                  }
              }
              catch (Exception e) { e.printStackTrace(); }
          }

          finished = true;
      }

      public boolean done() { return finished; }

      public int onEnd() { return 0; }
   }



    void lookupServer() {
// ---------------------  Search in the DF to retrieve the server AID

      ServiceDescription sd = new ServiceDescription();
      sd.setType("Server Agent");
      DFAgentDescription dfd = new DFAgentDescription();
      dfd.addServices(sd);
      try {
         DFAgentDescription[] dfds = DFService.search(this, dfd);
         if (dfds.length > 0 ) {
            server = dfds[0].getName();
            //alertGui("Localized server");
         }
         //else alertGui("Unable to localize server. Please try later!");
      }
      catch (Exception ex) {
         ex.printStackTrace();
         System.out.println("Failed searching int the DF!");
      }
   }


    void sendMessage(int performative, AgentAction action) {
// --------------------------------------------------------
      if (server == null) lookupServer();
      if (server == null) {
         //alertGui("Unable to localize the server! Operation aborted!");
         return;
      }
      ACLMessage msg = new ACLMessage(performative);
      msg.setLanguage(codec.getName());
      msg.setOntology(ontology.getName());
      try {
         getContentManager().fillContent(msg, new Action(server, action));
         msg.addReceiver(server);
         send(msg);
         //alertGui("Contacting server... Please wait!");
         addBehaviour(new AgentTest.WaitServerResponse(this));
      }
      catch (Exception ex) { ex.printStackTrace(); }
   }
    
    
   
    
}
