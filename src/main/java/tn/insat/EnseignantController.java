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


@CrossOrigin(origins = "*")
@Controller
@RequestMapping("/enseignants")
public class EnseignantController {


    AgentOperator operator = AgentOperator.getInstance();

    @RequestMapping(value = "/listeCours/{id}",method=RequestMethod.GET)
    public @ResponseBody List<Cours> sayHello(@PathVariable( "id" ) int id) throws InterruptedException {
        ListCoursEnseignant list_c_e = new ListCoursEnseignant();
        list_c_e.setId_enseignant(id);
        operator.send_to_enseignant(list_c_e);
        SemaphoreClass.listeCoursEnseignant_sem.acquire();
        List<Cours> list = ExampleController.getListe_cours();
        ExampleController.setListe_cours(null);
        return list;
    }

    @RequestMapping(value = "/listeEtudiantByCours/{id}",method=RequestMethod.GET)
    public @ResponseBody List<Etudiant> ListeEtudiantByCours(@PathVariable( "id" ) int id) throws InterruptedException {
        ListEtudiantCours list_e_c = new ListEtudiantCours();
        list_e_c.setId_cours(id);
        operator.send_to_enseignant(list_e_c);
        SemaphoreClass.listeEtudiantCours_sem.acquire();
        List<Etudiant> list = ExampleController.getListe_etudiant();
        ExampleController.setListe_etudiant(null);
        return list;
    }

    @RequestMapping(value = "/listeEtudianbyId/{id}",method=RequestMethod.GET)
    public @ResponseBody List<Etudiant> ListeEtudiantByEnseignant(@PathVariable( "id" ) int id) throws InterruptedException {
        ListEtudiantEnseignant list_e_e = new ListEtudiantEnseignant();
        list_e_e.setId_enseignant(id);
        operator.send_to_enseignant(list_e_e);
        SemaphoreClass.listeEtudiantCours_sem.acquire();
        List<Etudiant> list = ExampleController.getListe_etudiant();
        ExampleController.setListe_etudiant(null);
        return list;
    }


    @RequestMapping( value = "/creerCours/{id}",method = RequestMethod.POST )
    @ResponseStatus( HttpStatus.CREATED )
    @ResponseBody
    public Cours create( @PathVariable( "id" ) int id,@RequestBody Cours resource ) throws InterruptedException {
        CreateCours cc = new CreateCours();
        cc.setId_cours(resource.getId_cours());
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


    @RequestMapping(value = "/getEnseignantById/{id}",method=RequestMethod.GET)
    public @ResponseBody Enseignant getEnseignantById(@PathVariable( "id" ) int id) throws InterruptedException {
        InformationEnseignant info_enseignant = new InformationEnseignant();
        info_enseignant.setId_enseignant(id);
        operator.send_to_enseignant(info_enseignant);
        SemaphoreClass.informationEnseignant_sem.acquire();
        System.out.println("apres semaphore information enseingnant");
        Enseignant ens = ExampleController.getEnseignant();
        ExampleController.setEnseignant(null);
        return ens;
    }


    @RequestMapping( value = "/creerEnseignant",method = RequestMethod.POST )
    @ResponseStatus( HttpStatus.CREATED )
    @ResponseBody
    public Enseignant createEnseignant( @RequestBody Enseignant resource ) throws InterruptedException {
        CreateEnseignant cc = new CreateEnseignant();
        cc.setId_enseignant(resource.getId_enseignant());
        cc.setNom_enseignant(resource.getNom_enseignant());
        cc.setAge_enseignant(resource.getAge_enseignant());
        cc.setEmail_enseignant(resource.getEmail_enseignant());
        cc.setPassword_enseignant(resource.getPassword_enseignant());
        cc.setCategory_enseignant(resource.getCategory_enseignant());
        cc.setInstitut_enseignant(resource.getInstitut_enseignant());

        operator.send_to_enseignant(cc);
        SemaphoreClass.informationEnseignant_sem.acquire();
        Enseignant ens = ExampleController.getEnseignant();
        ExampleController.setEnseignant(null);
        return ens ;

    }


    @RequestMapping( value = "/loginenseignant",method = RequestMethod.POST )
    @ResponseBody
    public Enseignant login( @RequestBody Enseignant resource ) throws InterruptedException {
        LoginEnseignant login = new LoginEnseignant();
        login.setEmail_enseignant(resource.getEmail_enseignant());
        login.setPassword_enseignant(resource.getPassword_enseignant());
        operator.send_to_enseignant(login);
        SemaphoreClass.informationEnseignant_sem.acquire();
        Enseignant ens = ExampleController.getEnseignant();
        ExampleController.setEnseignant(null);
        return ens ;
    }

}