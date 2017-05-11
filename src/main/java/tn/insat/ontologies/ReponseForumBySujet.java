package tn.insat.ontologies;

import jade.content.AgentAction;

/**
 * Created by saif on 11/05/2017.
 */
public class ReponseForumBySujet implements AgentAction {

    private int id_sujet;

    public int getId_sujet() {
        return id_sujet;
    }

    public void setId_sujet(int id_sujet) {
        this.id_sujet = id_sujet;
    }
}
