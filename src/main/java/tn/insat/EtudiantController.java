package tn.insat;



import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import tn.insat.Agents.AgentOperator;
import tn.insat.Client.ExampleController;
import tn.insat.Client.SemaphoreClass;
import tn.insat.Client.SingletonEtudiant;
import tn.insat.ontologies.*;

@Controller
@RequestMapping("/etudiants")
public class EtudiantController {


    AgentOperator operator = AgentOperator.getInstance();

    @RequestMapping(value = "/listeCours/{id}",method=RequestMethod.GET)
    public @ResponseBody List<Cours> sayHello(@PathVariable( "id" ) int id) throws InterruptedException {
        ListeCoursByEtudiant list_c_e = new ListeCoursByEtudiant();
        list_c_e.setId_etudiant(id);
        operator.send_to_etudiant(list_c_e);
        SemaphoreClass.available.acquire();
        List<Cours> list = ExampleController.getListe_cours();
        ExampleController.setListe_cours(null);
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


    @RequestMapping( value = "/affecterTest/{id}",method = RequestMethod.POST )
    @ResponseStatus( HttpStatus.CREATED )
    @ResponseBody
    public TestEtudiant affecter_test( @PathVariable( "id" ) int id,@RequestBody Test resource ) throws InterruptedException {
        AffecterTest ac = new AffecterTest();
        ac.setId_test(resource.getId_test());
        ac.setId_etudiant(id);
        operator.send_to_etudiant(ac);
        SemaphoreClass.available.acquire();
        TestEtudiant test_et = ExampleController.getTestEtudiant();
        ExampleController.setTestEtudiant(null);


        return test_et ;

    }

    @RequestMapping(value = "/getEtudiantById/{id}",method=RequestMethod.GET)
    public @ResponseBody Etudiant getEtudiantById(@PathVariable( "id" ) int id) throws InterruptedException {
        InformationEtudiant info_etudiant = new InformationEtudiant();
        info_etudiant.setId_etudiant(id);
        operator.send_to_etudiant(info_etudiant);
        SemaphoreClass.available.acquire();
        Etudiant etudiant = SingletonEtudiant.getEtudiant();
       SingletonEtudiant.setEtudiant(null);
        return etudiant;
    }

}