package tn.insat;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import tn.insat.Client.ExampleController;
import tn.insat.Client.SemaphoreClass;
import tn.insat.Repositories.IUtilityRepository;
import tn.insat.Repositories.UtilityRepository;
import tn.insat.ontologies.Test;
import tn.insat.ontologies.TestByCours;

import java.util.List;

/**
 * Created by saif on 03/05/2017.
 */

@CrossOrigin(origins = "*")
@Controller
@RequestMapping("/utilite")
public class UtilityController {

    IUtilityRepository repo_util = new UtilityRepository();

    @RequestMapping(value = "/getHighestIdCours",method= RequestMethod.GET)
    public @ResponseBody int listerTestByCours() throws InterruptedException {
               return repo_util.getLastCoursId();
    }

    @RequestMapping(value = "/getHighestIdTest",method= RequestMethod.GET)
    public @ResponseBody int listerTestByTest() throws InterruptedException {
        return repo_util.getLastTestId();
    }

    @RequestMapping(value = "/getHighestIdTestEtudiant",method= RequestMethod.GET)
    public @ResponseBody int listerTestByTestEtudiant() throws InterruptedException {
        return repo_util.getLastTestEtudiantId();
    }

    @RequestMapping(value = "/getHighestIdQuestion",method= RequestMethod.GET)
    public @ResponseBody int listerTestByQuestion() throws InterruptedException {
        return repo_util.getLastQuestionId();
    }

    @RequestMapping(value = "/getHighestIdReponseEtudiant",method= RequestMethod.GET)
    public @ResponseBody int listerTestByReponseEtudiant() throws InterruptedException {
        return repo_util.getLastReponseId();
    }

    @RequestMapping(value = "/getHighestIdProposition",method= RequestMethod.GET)
    public @ResponseBody int listerTestByProposition() throws InterruptedException {
        return repo_util.getLastPropositionId();
    }

    @RequestMapping(value = "/getHighestIdReponseForum",method= RequestMethod.GET)
    public @ResponseBody int listerReponseForumLatestId() throws InterruptedException {
        return repo_util.getLastReponseForumId();
    }

    @RequestMapping(value = "/getHighestIdSujetForum",method= RequestMethod.GET)
    public @ResponseBody int listerSujetForumLatestId() throws InterruptedException {
        return repo_util.getLastSujetForumId();
    }



}
