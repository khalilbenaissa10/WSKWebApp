package tn.insat;



import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import tn.insat.Agents.AgentOperator;
import tn.insat.Client.ExampleController;
import tn.insat.Client.SemaphoreClass;
import tn.insat.ontologies.*;

@Controller
@RequestMapping("/etudiants")
public class EtudiantController {


    AgentOperator operator = AgentOperator.getInstance();

    @RequestMapping(value = "/listeCours/{id}",method=RequestMethod.GET)
    public @ResponseBody List<Cours> sayHello(@PathVariable( "id" ) int id) throws InterruptedException {
        ListCoursEnseignant list_c_e = new ListCoursEnseignant();
        list_c_e.setId_enseignant(id);
        operator.send_to_enseignant(list_c_e);
        SemaphoreClass.available.acquire();
        List<Cours> list = ExampleController.getListe();
        ExampleController.setListe(null);
        return list;
    }


    @RequestMapping( value = "/affecterCours/{id}",method = RequestMethod.POST )
    @ResponseStatus( HttpStatus.CREATED )
    @ResponseBody
    public CoursEtudiant affecter_cours( @PathVariable( "id" ) int id,@RequestBody Cours resource ) throws InterruptedException {
        AffecterCours cc = new AffecterCours();
        cc.setId_cours(resource.getId_cours());
        cc.setId_etudiant(id);
        operator.send_to_etudiant(cc);
        SemaphoreClass.available.acquire();
        Cours crs = ExampleController.getCours();
        CoursEtudiant crs_et = ExampleController.getCours_etudiant();
        ExampleController.setCours(null);


        return crs_et ;

    }

}