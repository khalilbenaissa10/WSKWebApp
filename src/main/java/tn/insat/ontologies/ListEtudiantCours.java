package tn.insat.ontologies;

import jade.content.AgentAction;

/**
 * Created by saif on 28/04/2017.
 */
public class ListEtudiantCours implements AgentAction {

    private int id_cours;

    public int getId_cours() {
        return id_cours;
    }

    public void setId_cours(int id_cours) {
        this.id_cours = id_cours;
    }
}
