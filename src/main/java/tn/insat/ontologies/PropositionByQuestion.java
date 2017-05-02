package tn.insat.ontologies;

import jade.content.AgentAction;

/**
 * Created by Khalil on 01/05/2017.
 */
public class PropositionByQuestion implements AgentAction{

    private int id_question ;

    public int getId_question() {
        return id_question;
    }

    public void setId_question(int id_question) {
        this.id_question = id_question;
    }
}
