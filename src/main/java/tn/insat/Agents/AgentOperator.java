package tn.insat.Agents;

import jade.core.Profile;
import jade.core.ProfileImpl;
import jade.core.Runtime;
import jade.wrapper.*;

/**
 * Created by saif on 24/04/2017.
 */
public class AgentOperator {

   private static AgentOperator INSTANCE =new AgentOperator();

    private AgentOperator(){

    }
    public static AgentOperator getInstance()
    {	return INSTANCE;
    }

    Runtime runtime = null ;
    Profile profile =  null;
    ContainerController maincontainer = null;

    AgentController servercontroller= null;
    AgentController enseignantcontroller= null;
    AgentController etudiantcontroller= null;
    AgentController planCoursController = null ;
    AgentController testController = null ;

    public void start_agents(){

        runtime = Runtime.instance();
        profile = new ProfileImpl();
        profile.setParameter(Profile.MAIN_HOST,"localhost");
        profile.setParameter(Profile.GUI, "true");
        profile.setParameter(Profile.LOCAL_PORT,"12344");
        maincontainer = runtime.createMainContainer(profile);

        try {
            servercontroller = maincontainer.createNewAgent("Server","tn.insat.Agents.AgentServer", null);
            servercontroller.start();

            enseignantcontroller = maincontainer.createNewAgent("Enseignant","tn.insat.Agents.AgentEnseignant", new Object[0]);
            enseignantcontroller.start();

            etudiantcontroller = maincontainer.createNewAgent("Etudiant","tn.insat.Agents.AgentEtudiant", new Object[0]);
            etudiantcontroller.start();

            planCoursController = maincontainer.createNewAgent("PlanCours","tn.insat.Agents.AgentPlanCours", new Object[0]);
            planCoursController.start();

            testController = maincontainer.createNewAgent("Test","tn.insat.Agents.AgentTest", new Object[0]);
            testController.start();



        } catch (StaleProxyException e1) {

            e1.printStackTrace();

        }
    }

    public void send_to_enseignant(Object obj){

        try {

            State state = enseignantcontroller.getState();
            while (!(state.getCode() == AgentState.cAGENT_STATE_IDLE)) {
                Thread.sleep(100); // wait 100 miliseconds and try it again...
                state = enseignantcontroller.getState();

            }
            enseignantcontroller.putO2AObject(obj, AgentController.ASYNC);


        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public void send_to_etudiant(Object obj){

        try {

            State state = etudiantcontroller.getState();
            while (!(state.getCode() == AgentState.cAGENT_STATE_IDLE)) {
                Thread.sleep(100); // wait 100 miliseconds and try it again...
                state = etudiantcontroller.getState();

            }
            etudiantcontroller.putO2AObject(obj, AgentController.ASYNC);


        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public void send_to_planCours(Object obj){

        try {

            State state = planCoursController.getState();
            while (!(state.getCode() == AgentState.cAGENT_STATE_IDLE)) {
                Thread.sleep(100); // wait 100 miliseconds and try it again...
                state = planCoursController.getState();

            }
            planCoursController.putO2AObject(obj, AgentController.ASYNC);


        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public void send_to_test(Object obj){

        try {

            State state = testController.getState();
            while (!(state.getCode() == AgentState.cAGENT_STATE_IDLE)) {
                Thread.sleep(100); // wait 100 miliseconds and try it again...
                state = testController.getState();

            }
            testController.putO2AObject(obj, AgentController.ASYNC);


        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
