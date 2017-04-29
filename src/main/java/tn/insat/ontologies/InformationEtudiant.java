package tn.insat.ontologies;

import jade.content.AgentAction;

/**
 * Created by Khalil on 29/04/2017.
 */
public class InformationEtudiant implements AgentAction {

    private int id_etudiant ;

    public int getId_etudiant() {
        return id_etudiant;
    }

    public void setId_etudiant(int id_etudiant) {
        this.id_etudiant = id_etudiant;
    }
}
