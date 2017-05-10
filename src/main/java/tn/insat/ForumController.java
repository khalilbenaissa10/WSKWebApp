package tn.insat;

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
}
