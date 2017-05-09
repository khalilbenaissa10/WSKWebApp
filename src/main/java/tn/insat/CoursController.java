package tn.insat;



import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import tn.insat.Agents.AgentOperator;
import tn.insat.Client.ExampleController;
import tn.insat.Client.SemaphoreClass;
import tn.insat.ontologies.*;

@CrossOrigin(origins = "*")
@Controller
@RequestMapping("/cours")
public class CoursController {


    AgentOperator operator = AgentOperator.getInstance();

    @RequestMapping(value = "/listeCours",method=RequestMethod.GET)
    public @ResponseBody List<Cours> listerAllCours() throws InterruptedException {
       ListeAllCours listeAll = new ListeAllCours();
        operator.send_to_planCours(listeAll);
        SemaphoreClass.listeAllcours_sem.acquire();
        List<Cours> list = ExampleController.getListe_cours();
        ExampleController.setListe_cours(null);
        return list;
    }

    @RequestMapping(value = "/listeCoursByIntitule/{search}",method=RequestMethod.GET)
    public @ResponseBody List<Cours> listerCoursSearch(@PathVariable( "search" ) String search) throws InterruptedException {
        ListeCoursSearch liste_search = new ListeCoursSearch();
        liste_search.setSearch(search);
        operator.send_to_planCours(liste_search);
        SemaphoreClass.listeAllcours_sem.acquire();
        List<Cours> list = ExampleController.getListe_cours();
        ExampleController.setListe_cours(null);
        return list;
    }


    @RequestMapping(value = "/listeCoursByDescription/{search}",method=RequestMethod.GET)
    public @ResponseBody List<Cours> listerCoursSearchByDescription(@PathVariable( "search" ) String search) throws InterruptedException {
        ListeCoursSearchDescription liste_search = new ListeCoursSearchDescription();
        liste_search.setSearch(search);
        operator.send_to_planCours(liste_search);
        SemaphoreClass.listeAllcours_sem.acquire();
        List<Cours> list = ExampleController.getListe_cours();
        ExampleController.setListe_cours(null);
        return list;

    }


    @RequestMapping(value = "/getCoursById/{id}",method=RequestMethod.GET)
    public @ResponseBody Cours getCoursById(@PathVariable( "id" ) int id) throws InterruptedException {
        InformationCours info_cours = new InformationCours();
        info_cours.setId_Cours(id);
        operator.send_to_planCours(info_cours);
        SemaphoreClass.informationCours_sem.acquire();
        Cours cours = ExampleController.getCours();
        ExampleController.setCours(null);
        return cours;
    }

}