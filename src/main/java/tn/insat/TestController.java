package tn.insat;



import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import tn.insat.Agents.AgentOperator;
import tn.insat.Client.ExampleController;
import tn.insat.Client.SemaphoreClass;
import tn.insat.ontologies.*;

@Controller
@RequestMapping("/tests")
public class TestController {


    AgentOperator operator = AgentOperator.getInstance();

    @RequestMapping(value = "/listeTestByCours/{id}",method=RequestMethod.GET)
    public @ResponseBody List<Test> listerTestByCours(@PathVariable( "id" ) int id) throws InterruptedException {
        TestByCours test_by_cours = new TestByCours();
        test_by_cours.setId_cours(id);
        operator.send_to_test(test_by_cours);
        SemaphoreClass.available.acquire();
        List<Test> list = ExampleController.getListeTest();
        ExampleController.setListeTest(null);
        return list;
    }

}