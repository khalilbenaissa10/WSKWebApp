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
import tn.insat.ontologies.*;

/**
 *
 * @author saif
 */
public class AgentPlanCours extends Agent implements Vocabulary, IAgentPlanCours {
    
    
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
                     if(obj instanceof ListeAllCours){
                         addBehaviour(new OneShotBehaviour() {

                             @Override
                             public void action() {
                                 ListeAllCours aff = (ListeAllCours) obj ;
                                 listerAllCours();

                             }
                         });
                     }
                     else if(obj instanceof ListeCoursSearchDescription){
                         addBehaviour(new OneShotBehaviour() {

                             @Override
                             public void action() {
                                 ListeCoursSearchDescription aff = (ListeCoursSearchDescription) obj ;
                                 listerCoursSearchDescription(aff.getSearch());

                             }
                         });

                     }
                     else if(obj instanceof InformationCours) {
                         addBehaviour(new OneShotBehaviour() {

                             @Override
                             public void action() {
                                 InformationCours aff = (InformationCours) obj;
                                 infoCours(aff);

                             }
                         });
                     }
                     else {
                         addBehaviour(new OneShotBehaviour() {

                             @Override
                             public void action() {
                                 ListeCoursSearch aff = (ListeCoursSearch) obj ;
                                 listerListCoursSearch(aff.getSearch());

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


    public void infoCours(InformationCours ic) {


        sendMessage(ACLMessage.QUERY_REF, ic);

    }


    public void listerAllCours(){

       ListeAllCours lc = new ListeAllCours();
        sendMessage(ACLMessage.QUERY_REF, lc);


    }

    public void listerListCoursSearch(String search){
        ListeCoursSearch liste_search = new ListeCoursSearch();
        liste_search.setSearch(search);
        sendMessage(ACLMessage.QUERY_REF, liste_search);

    }

    public void listerCoursSearchDescription(String search){
        ListeCoursSearchDescription liste_search = new ListeCoursSearchDescription();
        liste_search.setSearch(search);
        sendMessage(ACLMessage.QUERY_REF, liste_search);

    }


   
    class WaitServerResponse extends ParallelBehaviour {
// ----------------------------------------------------  launch a SimpleBehaviour to receive
//                                                       servers response and a WakerBehaviour
//                                                       to terminate the waiting if there is
//                                                       no response from the server
      WaitServerResponse(Agent a) {

         super(a, 1);

         addSubBehaviour(new AgentPlanCours.ReceiveResponse(myAgent));

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
                      else if (result.getValue()  instanceof Cours) {

                          Cours crs = (Cours)result.getValue() ;

                              ExampleController.setCours(crs);
                              SemaphoreClass.informationCours_sem.release();



                      }
                      else if (result.getValue()  instanceof ArrayList) {

                          ArrayList lcs = (ArrayList) result.getValue() ;
                          if (lcs.get(0) instanceof Cours) {
                              java.util.ArrayList<Cours> L = (java.util.ArrayList<Cours>)lcs.toList();
                              ExampleController.setListe_cours(L);
                              SemaphoreClass.listeAllcours_sem.release();

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

      public int onEnd() {  return 0; }
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
         addBehaviour(new AgentPlanCours.WaitServerResponse(this));
      }
      catch (Exception ex) { ex.printStackTrace(); }
   }
    
    
    
    
}
