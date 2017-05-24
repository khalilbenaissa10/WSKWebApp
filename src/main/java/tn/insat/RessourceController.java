package tn.insat;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import tn.insat.Agents.AgentOperator;
import tn.insat.Client.SemaphoreClass;
import tn.insat.Client.SingletonEtudiant;
import tn.insat.Client.SingletonQuestion;
import tn.insat.ontologies.*;

import java.util.List;

/**
 * Created by Khalil on 24/05/2017.
 */

@CrossOrigin(origins = "*")
@Controller
@RequestMapping("/ressources")
public class RessourceController {

    AgentOperator operator = AgentOperator.getInstance();

    @RequestMapping( value = "/creerRessource",method = RequestMethod.POST )
    @ResponseStatus( HttpStatus.CREATED )
    @ResponseBody
    public Ressource create(@RequestBody Ressource resource ) throws InterruptedException {
        CreateRessource cc = new CreateRessource();
        cc.setId_ressource(resource.getId_ressource());
        cc.setDescription_ressource(resource.getDescription_ressource());
        cc.setType_ressource(resource.getType_ressource());
        cc.setCours_ressource(resource.getCours_ressource());

        operator.send_to_planCours(cc);
        SemaphoreClass.ressourceInformation_sem.acquire();
        Ressource rss = SingletonEtudiant.getRessource();
        SingletonEtudiant.setRessource(null);
        return rss ;

    }

    @RequestMapping(value = "/listeRessourceByCours/{id}",method=RequestMethod.GET)
    public @ResponseBody
    List<Ressource> listerRessourceByCours(@PathVariable( "id" ) int id) throws InterruptedException {
        RessourceByCours rbc = new RessourceByCours();
        rbc.setId_cours(id);
        operator.send_to_planCours(rbc);
        SemaphoreClass.listeRessources_sem.acquire();
        List<Ressource> list = SingletonQuestion.getRessources();
        SingletonQuestion.setRessources(null);
        return list;
    }
}
