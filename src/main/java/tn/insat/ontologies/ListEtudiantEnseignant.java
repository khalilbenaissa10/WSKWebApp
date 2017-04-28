package tn.insat.ontologies;

import jade.content.AgentAction;

/**
 * Created by saif on 28/04/2017.
 */
public class ListEtudiantEnseignant implements AgentAction {

    private int id_enseignant;

    public int getId_enseignant() {
        return id_enseignant;
    }

    public void setId_enseignant(int id_enseignant) {
        this.id_enseignant = id_enseignant;
    }
}
