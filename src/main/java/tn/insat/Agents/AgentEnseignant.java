/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.insat.Agents;

import tn.insat.Client.ExampleController;
import tn.insat.Client.SemaphoreClass;
import tn.insat.Repositories.EnseignantRepository;
import tn.insat.Repositories.IEnseignantRepository;
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
import jade.util.leap.ArrayList;
import tn.insat.ontologies.*;


/**
 *
 * @author saif
 */
public class AgentEnseignant extends Agent implements Vocabulary, IAgentEnseignant {

    IEnseignantRepository repo_enseignant = new EnseignantRepository();
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
                     if(obj instanceof CreateCours){
                         addBehaviour(new OneShotBehaviour() {

                             @Override
                             public void action() {
                                 CreateCours cc = (CreateCours)obj ;
                                 createCours(cc.getId_cours(),cc.getIntitule(),cc.getDuree(),cc.getDescription(),cc.getEnseignant().getId_enseignant());
                             }
                         });
                     }
                     else if(obj instanceof CreateEnseignant){
                         addBehaviour(new OneShotBehaviour() {

                             @Override
                             public void action() {
                                 CreateEnseignant cc = (CreateEnseignant)obj ;
                                 createEnseignant(cc);
                             }
                         });
                     }
                     else if(obj instanceof ListCoursEnseignant){

                         addBehaviour(new OneShotBehaviour() {

                             @Override
                             public void action() {
                                 listCours(((ListCoursEnseignant) obj).getId_enseignant());
                             }
                         });
                     }
                     else if(obj instanceof ListEtudiantCours){

                         addBehaviour(new OneShotBehaviour() {

                             @Override
                             public void action() {
                                 listEtudiantbyCours(((ListEtudiantCours) obj).getId_cours());
                             }
                         });
                     }
                     else if(obj instanceof ListEtudiantEnseignant){

                         addBehaviour(new OneShotBehaviour() {

                             @Override
                             public void action() {
                                 listEtudiantbyEnseignant(((ListEtudiantEnseignant) obj).getId_enseignant());
                             }
                         });
                     }
                     else if(obj instanceof InformationEnseignant ){

                         addBehaviour(new OneShotBehaviour() {

                             @Override
                             public void action() {
                                 informationEnseignant((InformationEnseignant) obj);
                             }
                         });
                     }
                     else if(obj instanceof LoginEnseignant)
                     {
                         addBehaviour(new OneShotBehaviour() {

                             @Override
                             public void action() {
                                 LoginEnseignant aff = (LoginEnseignant) obj ;
                                 loginEnseignant(aff);

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

       setEnabledO2ACommunication(false, 0);

       System.out.println(getLocalName() + " is now shutting down.");
       
    }
   
   
    @Override
  public void createCours(int cours_id,String intitulecours ,int duree,String description, int id_enseignant) {
// ----------------------  Process to the server agent the request
//                         to create a new account
        System.out.println("create cours from enseignant");
      CreateCours ca = new CreateCours();
      ca.setId_cours(cours_id);
      ca.setDuree(duree);
      ca.setDescription(description);
      ca.setIntitule(intitulecours);
      ca.setEnseignant(repo_enseignant.findById(id_enseignant));
      sendMessage(ACLMessage.REQUEST, ca);
      
   }

    public void loginEnseignant(LoginEnseignant cc) {
// ----------------------  Process to the server agent the request
//                         to create a new account

        sendMessage(ACLMessage.QUERY_REF, cc);

    }

    public void createEnseignant(CreateEnseignant cc) {
// ----------------------  Process to the server agent the request
//                         to create a new account
        System.out.println("helo");
        sendMessage(ACLMessage.REQUEST, cc);

    }

    @Override
  public void infoCours(int idcours) {
     
      InformationCours ic = new InformationCours();
      ic.setId_Cours(idcours);
      sendMessage(ACLMessage.QUERY_REF, ic);
      
  }

    @Override
    public void informationEnseignant(InformationEnseignant obj) {


        sendMessage(ACLMessage.QUERY_REF, obj);

    }

    @Override
  public void listCours(int id_enseignant){

         ListCoursEnseignant lc = new ListCoursEnseignant();
         lc.setId_enseignant(id_enseignant);
         sendMessage(ACLMessage.QUERY_REF, lc);


  }

    public void listEtudiantbyCours(int id_cours){

        ListEtudiantCours lc = new ListEtudiantCours();
        lc.setId_cours(id_cours);
        sendMessage(ACLMessage.QUERY_REF, lc);


    }

    public void listEtudiantbyEnseignant(int id_enseignant){

        ListEtudiantEnseignant lc = new ListEtudiantEnseignant();
        lc.setId_enseignant(id_enseignant);
        sendMessage(ACLMessage.QUERY_REF, lc);


    }



   
   
   
   class WaitServerResponse extends ParallelBehaviour {
// ----------------------------------------------------  launch a SimpleBehaviour to receive
//                                                       servers response and a WakerBehaviour
//                                                       to terminate the waiting if there is
//                                                       no response from the server
      WaitServerResponse(Agent a) {

         super(a, 1);

         addSubBehaviour(new ReceiveResponse(myAgent));

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

                     Problem prob = (Problem)result.getItems().get(0);
                     System.out.println("Problem : "+prob.getMsg());
                  }
                  else if (result.getValue()  instanceof Cours) {

                      Cours crs = (Cours) result.getItems().get(0) ;
                      System.out.println("message enseignant : Cours retourné "+crs.getIntitule());
                      ExampleController.setCours(crs);
                      SemaphoreClass.available.release();
                  }
                  else if (result.getValue()  instanceof Enseignant) {

                      Enseignant ens = (Enseignant) result.getItems().get(0) ;
                      System.out.println("message enseignant : Enseignant retourné "+ens.getNom_enseignant());
                      ExampleController.setEnseignant(ens);
                      SemaphoreClass.informationEnseignant_sem.release();
                  }
                  else if (result.getValue()  instanceof ArrayList) {

                      ArrayList lcs = (ArrayList) result.getValue() ;
                      if (lcs.get(0) instanceof Cours) {
                          java.util.ArrayList<Cours> L = (java.util.ArrayList<Cours>)lcs.toList();
                          ExampleController.setListe_cours(L);
                          SemaphoreClass.listeCoursEnseignant_sem.release();

                      }else if(lcs.get(0) instanceof Etudiant){
                          java.util.ArrayList<Etudiant> L = (java.util.ArrayList<Etudiant>)lcs.toList();
                          ExampleController.setListe_etudiant(L);
                          SemaphoreClass.listeEtudiantCours_sem.release();

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
      sd.setType(SERVER_AGENT);
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
         addBehaviour(new WaitServerResponse(this));
      }
      catch (Exception ex) { ex.printStackTrace(); }
   }
    
}
