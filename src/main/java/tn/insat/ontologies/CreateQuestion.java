package tn.insat.ontologies;

import jade.content.AgentAction;

/**
 * Created by Khalil on 02/05/2017.
 */
public class CreateQuestion implements AgentAction {

   private int id_question ;
    private String enonce_question;
    private Test test ;

    public int getId_question() {
        return id_question;
    }

    public void setId_question(int id_question) {
        this.id_question = id_question;
    }

    public String getEnonce_question() {
        return enonce_question;
    }

    public void setEnonce_question(String enonce_question) {
        this.enonce_question = enonce_question;
    }

    public Test getTest() {
        return test;
    }

    public void setTest(Test test) {
        this.test = test;
    }

    public CreateQuestion(int id_question, String enonce_question, Test test) {
        this.id_question = id_question;
        this.enonce_question = enonce_question;
        this.test = test;
    }

    public CreateQuestion() {
    }
}
