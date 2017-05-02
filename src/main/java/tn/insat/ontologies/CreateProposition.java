package tn.insat.ontologies;

import jade.content.AgentAction;

/**
 * Created by Khalil on 02/05/2017.
 */
public class CreateProposition implements AgentAction {

    private Question question ;
    private int id_proposition ;
    private  Boolean valid_proposition ;
    private String text_proposition ;

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }

    public int getId_proposition() {
        return id_proposition;
    }

    public void setId_proposition(int id_proposition) {
        this.id_proposition = id_proposition;
    }

    public Boolean getValid_proposition() {
        return valid_proposition;
    }

    public void setValid_proposition(Boolean valid_proposition) {
        this.valid_proposition = valid_proposition;
    }

    public String getText_proposition() {
        return text_proposition;
    }

    public void setText_proposition(String text_proposition) {
        this.text_proposition = text_proposition;
    }

    public CreateProposition(Question question, int id_proposition, Boolean valid_proposition, String text_proposition) {
        this.question = question;
        this.id_proposition = id_proposition;
        this.valid_proposition = valid_proposition;
        this.text_proposition = text_proposition;
    }

    public CreateProposition() {
    }
}
