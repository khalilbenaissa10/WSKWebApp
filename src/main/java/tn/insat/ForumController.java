package tn.insat;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import tn.insat.Agents.AgentOperator;
import tn.insat.Client.ExampleController;
import tn.insat.Client.SemaphoreClass;
import tn.insat.ontologies.*;

import java.util.List;

/**
 * Created by Khalil on 10/05/2017.
 */


@CrossOrigin(origins = "*")
@Controller
@RequestMapping("/forum")
public class ForumController {

    AgentOperator operator = AgentOperator.getInstance();


    @RequestMapping(value = "/listeSujetsForum",method= RequestMethod.GET)
    public @ResponseBody
    List<SujetForum> listerAllSujetsForum() throws InterruptedException {
        ListeAllSujetsForum listeAll = new ListeAllSujetsForum();
        operator.send_to_forum(listeAll);
        SemaphoreClass.listeAllsujetsForum_sem.acquire();
        System.out.println("semaphore passé apres liste sujetforum");
        List<SujetForum> list = ExampleController.getSujets_forums();
        ExampleController.setSujets_forums(null);
        return list;
    }


    @RequestMapping(value = "/listeSujetsForumByCours/{id}",method=RequestMethod.GET)
    public @ResponseBody List<SujetForum> listerTestByCours(@PathVariable( "id" ) int id) throws InterruptedException {
        SujetForumByCours sujetForums_by_cours = new SujetForumByCours();
        sujetForums_by_cours.setId_cours(id);
        operator.send_to_forum(sujetForums_by_cours);
        SemaphoreClass.listeAllsujetsForum_sem.acquire();
        List<SujetForum> list = ExampleController.getSujets_forums();
        ExampleController.setSujets_forums(null);
        return list;
    }



    @RequestMapping(value = "/getSujetForumById/{id}",method=RequestMethod.GET)
    public @ResponseBody SujetForum getCoursById(@PathVariable( "id" ) int id) throws InterruptedException {
        InformationSujetForum info_forum = new InformationSujetForum();
        info_forum.setId_Forum(id);
        operator.send_to_forum(info_forum);
        SemaphoreClass.informationSujetForum_sem.acquire();
        SujetForum forum = ExampleController.getSujet_forum();
        ExampleController.setSujet_forum(null);
        return forum;
    }

    @RequestMapping( value = "/creerSujetForum/{creator}/{id}",method = RequestMethod.POST )
    @ResponseStatus( HttpStatus.CREATED )
    @ResponseBody
    public SujetForum create( @PathVariable( "creator" ) int creator,@PathVariable( "id" ) int id,@RequestBody SujetForum resource ) throws InterruptedException {
        CreateSujetForum create = new CreateSujetForum();
        create.setId_sujetForum(resource.getId_sujetforum());
        create.setText_sujetforum(resource.getText_sujetforum());
        create.setTitre_sujetforum(resource.getTitre_sujetforum());
        create.setCours_sujetforum(resource.getCours_sujetforum());
        if(creator == 0){
            Enseignant ens = new Enseignant();
            ens.setId_enseignant(id);
            create.setEnseignant_sujetforum(ens);
            create.setEtudiant_sujetforum(null);
        }
        if(creator == 1){
            Etudiant et = new Etudiant();
            et.setId_etudiant(id);
            create.setEtudiant_sujetforum(et);
            create.setEnseignant_sujetforum(null);
        }

        operator.send_to_forum(create);
        SemaphoreClass.informationSujetForum_sem.acquire();
        SujetForum forum = ExampleController.getSujet_forum();
        ExampleController.setSujet_forum(null);


        return forum ;

    }
}
