package tn.insat.ontologies;

import jade.content.AgentAction;

/**
 * Created by Khalil on 23/05/2017.
 */
public class SuggererCours implements AgentAction {

    public int id_etudiant ;

    public int getId_etudiant() {
        return id_etudiant;
    }

    public void setId_etudiant(int id_etudiant) {
        this.id_etudiant = id_etudiant;
    }
}
