package tn.insat;



import java.util.ArrayList;
import java.util.List;

import com.sun.org.apache.regexp.internal.RE;
import jade.content.AgentAction;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import tn.insat.Agents.AgentOperator;
import tn.insat.Client.ExampleController;
import tn.insat.Client.SemaphoreClass;
import tn.insat.Client.SingletonQuestion;
import tn.insat.Repositories.ITestEtudiantRepository;
import tn.insat.Repositories.IUtilityRepository;
import tn.insat.Repositories.TestEtudiantRepository;
import tn.insat.Repositories.UtilityRepository;
import tn.insat.ontologies.*;

@Controller
@RequestMapping("/tests")
public class TestController {
    ITestEtudiantRepository repo = new TestEtudiantRepository();




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


    @RequestMapping(value = "/listeQuestionByTest/{id}",method=RequestMethod.GET)
    public @ResponseBody List<Question> listerQuestionByTest(@PathVariable( "id" ) int id) throws InterruptedException {
        QuestionByTest question_by_test = new QuestionByTest();
        question_by_test.setId_test(id);
        operator.send_to_test(question_by_test);
        SemaphoreClass.available.acquire();
        List<Question> list = SingletonQuestion.getQuestions();
        SingletonQuestion.setQuestions(null);
        return list;
    }

    @RequestMapping(value = "/listePropositionByQuestion/{id}",method=RequestMethod.GET)
    public @ResponseBody List<Proposition> listerPropositionByQuestion(@PathVariable( "id" ) int id) throws InterruptedException {
        PropositionByQuestion classe = new PropositionByQuestion();
        classe.setId_question(id);
        operator.send_to_test(classe);
        SemaphoreClass.available.acquire();
        List<Proposition> list = SingletonQuestion.getPropositions();
        SingletonQuestion.setPropositions(null);
        return list;
    }

    @RequestMapping(value = "/listeTousTestEtudiant",method=RequestMethod.GET)
    public @ResponseBody List<TestEtudiant> listertoutTestEtudiant() throws InterruptedException {
        ListAllTestEtudiant classe = new ListAllTestEtudiant();

        operator.send_to_test(classe);
        SemaphoreClass.available.acquire();
        List<TestEtudiant> list = SingletonQuestion.getTestEtudiantList();
        SingletonQuestion.setTestEtudiantList(null);
        return list;
    }

    @RequestMapping(value = "/listeTousTestEtudiantById/{id}",method=RequestMethod.GET)
    public @ResponseBody List<TestEtudiant> listerTestEtudiantById(@PathVariable( "id" ) int id) throws InterruptedException {
        ListTestEtudiant classe = new ListTestEtudiant();
        classe.setId_etudiant(id);
        operator.send_to_test(classe);
        SemaphoreClass.available.acquire();
        List<TestEtudiant> list = SingletonQuestion.getTestEtudiantList();
        SingletonQuestion.setTestEtudiantList(null);
        return list;
    }

    @RequestMapping(value = "/listeNotesByTest/{id}",method=RequestMethod.GET)
    public @ResponseBody List<TestEtudiant> listerTestEtudiantByTest(@PathVariable( "id" ) int id) throws InterruptedException {
        ListTestEtudiantByTest classe = new ListTestEtudiantByTest();
        classe.setId_test(id);
        operator.send_to_test(classe);
        SemaphoreClass.available.acquire();
        List<TestEtudiant> list = SingletonQuestion.getTestEtudiantList();
        SingletonQuestion.setTestEtudiantList(null);
        return list;
    }


    @RequestMapping( value = "/creerTest/{idCours}",method = RequestMethod.POST )
    @ResponseStatus( HttpStatus.CREATED )
    @ResponseBody
    public List<Proposition> create( @PathVariable( "idCours" ) int idCours,@RequestBody Test resource ) throws InterruptedException {




        List<Question> liste = new ArrayList<Question>();
        List<Proposition> liste_prop = new ArrayList<Proposition>();
        CreateTest createTest = new CreateTest(idCours,resource.getId_test(),resource.getNom_test(),resource.getDuree_test());
        operator.send_to_test(createTest);
        SemaphoreClass.available.acquire();
        Test test = SingletonQuestion.getTest();


        for (Question q: resource.getTestquestions()
             ) {
                liste.add(q);
                CreateQuestion cq = new CreateQuestion(q.getId_question(),q.getEnonce_question(),test);
                operator.send_to_test(cq);
                SemaphoreClass.available.acquire();



            for (Proposition p: q.getPropositions()
                 ) {
                liste_prop.add(p);
                CreateProposition cp = new CreateProposition(q,p.getId_proposition(),p.getValid_proposition(),p.getText_proposition());
                operator.send_to_test(cp);
                SemaphoreClass.available2.acquire();
            }


        }
        SingletonQuestion.setQuestion(null);
        SingletonQuestion.setTest(null);
        Cours cours = new Cours();


        return liste_prop ;

    }


    @RequestMapping( value = "/calculerNote/{idTestEtudiant}",method = RequestMethod.POST )
    @ResponseStatus( HttpStatus.CREATED )
    @ResponseBody
    public TestEtudiant create( @RequestBody TestEtudiant resource ) throws InterruptedException {


        int compteur = 0 ;
        int valide = 0;
        List<ReponseEtudiant> liste = new ArrayList<ReponseEtudiant>();
        ReponseEtudiant reponse = new ReponseEtudiant();
        for (ReponseEtudiant re: resource.getReponseEtudiants()
                ) {
            liste.add(re);
            CreateReponseEtudiant cq = new CreateReponseEtudiant(re.getId_reponse_etudiant(),re.getText_reponse_etudiant(),re.getValid_reponse_etudiant(),re.getTestetudiant());
            operator.send_to_test(cq);
            SemaphoreClass.available2.acquire();
            reponse = SingletonQuestion.getReponseEtudiant();
            compteur++ ;
            if(reponse.getValid_reponse_etudiant()){
                valide++ ;
            }


        }
        System.out.println(compteur);
        System.out.println(valide);
        resource.setNote_test(Integer.toString((20*valide)/compteur));
        repo.update(resource);

        SingletonQuestion.setQuestion(null);



        return resource ;

    }

}