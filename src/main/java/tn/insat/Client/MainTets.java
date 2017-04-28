/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.insat.Client;

import tn.insat.Agents.AgentOperator;
import tn.insat.Repositories.CoursEtudiantRepository;
import tn.insat.Repositories.EnseignantRepository;
import tn.insat.ontologies.Cours;
import tn.insat.ontologies.Etudiant;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author saif
 */

public class MainTets {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws InterruptedException {
      
   /*   Runtime rt = Runtime.instance();
		Profile p = new ProfileImpl();
		p.setParameter(Profile.MAIN_HOST"localhost");
		p.setParameter(Profile.GUI, "true");
		p.setParameter(Profile.LOCAL_PORT,"12344");
		ContainerController cc = rt.createMainContainer(p);
		AgentController ac;

		try {
			ac = cc.createNewAgent("Server","Agents.AgentServer", null);
			ac.start();
		} catch (StaleProxyException e1) {
			
			e1.printStackTrace();
			
		}


		try {
			ac = cc.createNewAgent("Enseignant","Agents.AgentEnseignant", new Object[0]);
			ac.start();


			State state = ac.getState();
			while (!(state.getCode() == AgentState.cAGENT_STATE_IDLE)){
				Thread.sleep(100); // wait 100 miliseconds and try it again...
				state = ac.getState();

			}
			ac.putO2AObject("createcours",AgentController.ASYNC);

		} catch (StaleProxyException e1) {

			e1.printStackTrace();

		}*/

	/*	AgentOperator operator = AgentOperator.getInstance();
		operator.start_agents();
		operator.send_to_enseignant("listcours");


		SemaphoreClass.available.acquire();
		List<Cours> list = ExampleController.getListe_cours();
		for (Cours c:list) {
			System.out.println(c);
		}
		ExampleController.setListe_cours(null);
*/


		EnseignantRepository repo = new EnseignantRepository();


		ArrayList<Etudiant> le = null;

		le = repo.findEtudiantsbyid(2);
		for (Etudiant et:le) {
			System.out.println(et.getNom_etudiant());
		}


                
                
                
                
		
	}
        
    }
    

