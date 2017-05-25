/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.insat.Agents;

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
import tn.insat.Repositories.*;
import tn.insat.ontologies.*;

/**
 *
 * @author saif
 */
public class AgentForum extends Agent implements Vocabulary, IAgentForum {

    IEnseignantRepository repo_enseignant = new EnseignantRepository();
    IEtudiantRepository repo_etudiant = new EtudiantRepository();
    ISujetForumRepository repo_sujetforum = new SujetForumRepository();
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

                     if(obj instanceof CreateSujetForum){
                         addBehaviour(new OneShotBehaviour() {

                             @Override
                             public void action() {
                                 CreateSujetForum cc = (CreateSujetForum)obj ;
                                 createSujetForum(cc);
                             }
                         });
                     }
                     if(obj instanceof CreateReponseForum){
                         addBehaviour(new OneShotBehaviour() {

                             @Override
                             public void action() {
                                 CreateReponseForum cc = (CreateReponseForum)obj ;
                                 createReponseForum(cc);
                             }
                         });
                     }
                     else if(obj instanceof ListeAllSujetsForum){
                         addBehaviour(new OneShotBehaviour() {

                             @Override
                             public void action() {
                                 ListeAllSujetsForum aff = (ListeAllSujetsForum) obj ;
                                 listerAllSujetsCours();

                             }
                         });
                     }
                     else if(obj instanceof SujetForumByCours){
                         addBehaviour(new OneShotBehaviour() {

                             @Override
                             public void action() {
                                 SujetForumByCours aff = (SujetForumByCours) obj ;
                                 listerSujetForumByCours(aff);

                             }
                         });
                     }
                     else if(obj instanceof InformationSujetForum) {
                         addBehaviour(new OneShotBehaviour() {

                             @Override
                             public void action() {
                                 InformationSujetForum aff = (InformationSujetForum) obj;
                                 infoSujetForum(aff);

                             }
                         });
                     }
                     else if(obj instanceof ReponseForumBySujet) {
                         addBehaviour(new OneShotBehaviour() {

                             @Override
                             public void action() {
                                 ReponseForumBySujet aff = (ReponseForumBySujet) obj;
                                 reponsesforumBysujet(aff);

                             }
                         });
                     }
                     else if(obj instanceof ApprecierReponseForum) {
                         addBehaviour(new OneShotBehaviour() {

                             @Override
                             public void action() {
                                 ApprecierReponseForum aff = (ApprecierReponseForum) obj;
                                 apprecierReponseForum(aff);

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


    public void createSujetForum(CreateSujetForum ca) {
// ----------------------  Process to the server agent the request
//                         to create a new account
        System.out.println("create sujetforum from enseignant");
        if(ca.getEnseignant_sujetforum()!= null) {
            ca.setEnseignant_sujetforum(repo_enseignant.findById(ca.getEnseignant_sujetforum().getId_enseignant()));
        }
        else{
            ca.setEtudiant_sujetforum(repo_etudiant.findById(ca.getEtudiant_sujetforum().getId_etudiant()));
        }
        sendMessage(ACLMessage.REQUEST, ca);

    }

    public void createReponseForum(CreateReponseForum ca) {
// ----------------------  Process to the server agent the request
//                         to create a new account
        System.out.println("create reponseforum");
        if(ca.getEnseignant_reponseforum()!= null) {
            ca.setEnseignant_reponseforum(repo_enseignant.findById(ca.getEnseignant_reponseforum().getId_enseignant()));
        }
        else{
            ca.setEtudiant_reponseforum(repo_etudiant.findById(ca.getEtudiant_reponseforum().getId_etudiant()));
        }



        sendMessage(ACLMessage.REQUEST, ca);

    }



    public void apprecierReponseForum(ApprecierReponseForum lc){


        sendMessage(ACLMessage.REQUEST, lc);


    }

    public void listerAllSujetsCours(){

        ListeAllSujetsForum lc = new ListeAllSujetsForum();
        sendMessage(ACLMessage.QUERY_REF, lc);


    }

    public void listerSujetForumByCours(SujetForumByCours aff){
        sendMessage(ACLMessage.QUERY_REF, aff);

    }

    public void infoSujetForum(InformationSujetForum ic) {


        sendMessage(ACLMessage.QUERY_REF, ic);

    }

    public void reponsesforumBysujet(ReponseForumBySujet ic) {


        sendMessage(ACLMessage.QUERY_REF, ic);

    }
   
   
    class WaitServerResponse extends ParallelBehaviour {
// ----------------------------------------------------  launch a SimpleBehaviour to receive
//                                                       servers response and a WakerBehaviour
//                                                       to terminate the waiting if there is
//                                                       no response from the server
      WaitServerResponse(Agent a) {

         super(a, 1);

         addSubBehaviour(new AgentForum.ReceiveResponse(myAgent));

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
                         else if (result.getValue()  instanceof SujetForum) {

                             SujetForum frm = (SujetForum)result.getValue() ;

                             ExampleController.setSujet_forum(frm);
                             SemaphoreClass.informationSujetForum_sem.release();



                         }
                         else if (result.getValue()  instanceof ReponseForum) {

                             ReponseForum frm = (ReponseForum)result.getValue() ;

                             ExampleController.setReponse_forum(frm);
                             SemaphoreClass.informationReponseForum_sem.release();



                         }
                         else if (result.getValue()  instanceof ArrayList) {

                             ArrayList lcs = (ArrayList) result.getValue() ;
                             if (lcs.get(0) instanceof SujetForum) {
                                 java.util.ArrayList<SujetForum> L = (java.util.ArrayList<SujetForum>)lcs.toList();
                                 ExampleController.setSujets_forums(L);
                                 SemaphoreClass.listeAllsujetsForum_sem.release();

                             }

                             if (lcs.get(0) instanceof ReponseForum) {
                                 java.util.ArrayList<ReponseForum> L = (java.util.ArrayList<ReponseForum>)lcs.toList();
                                 ExampleController.setReponses_forum(L);
                                 SemaphoreClass.listeReponseForum_sem.release();

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
         addBehaviour(new AgentForum.WaitServerResponse(this));
      }
      catch (Exception ex) { ex.printStackTrace(); }
   }
    
    
    
}
