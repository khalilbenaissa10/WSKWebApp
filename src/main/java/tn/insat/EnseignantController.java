package tn.insat;



        import java.util.List;
        import java.util.concurrent.atomic.AtomicLong;

        import org.springframework.http.HttpStatus;
        import org.springframework.stereotype.Controller;
        import org.springframework.web.bind.annotation.*;
        import tn.insat.Agents.AgentOperator;
        import tn.insat.Client.ExampleController;
        import tn.insat.Client.SemaphoreClass;
        import tn.insat.ontologies.Cours;
        import tn.insat.ontologies.CreateCours;
        import tn.insat.ontologies.Enseignant;
        import tn.insat.ontologies.ListCoursEnseignant;

@Controller
@RequestMapping("/enseignants")
public class EnseignantController {


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


    @RequestMapping( value = "/listeCours/{id}",method = RequestMethod.POST )
    @ResponseStatus( HttpStatus.CREATED )
    @ResponseBody
    public Cours create( @PathVariable( "id" ) int id,@RequestBody Cours resource ) throws InterruptedException {
        CreateCours cc = new CreateCours();
        cc.setDuree(resource.getDuree());
        cc.setIntitule(resource.getIntitule());
        cc.setDescription(resource.getDescription());
        Enseignant en = new Enseignant();
        en.setId_enseignant(id);
        cc.setEnseignant(en);
        operator.send_to_enseignant(cc);
        SemaphoreClass.available.acquire();
        Cours crs = ExampleController.getCours();
        ExampleController.setCours(null);


        return crs ;

    }

}