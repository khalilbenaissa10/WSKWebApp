package tn.insat.ontologies;

import jade.content.AgentAction;

/**
 * Created by Khalil on 02/05/2017.
 */
public class AffecterTest implements AgentAction {

    private int id_test;
    private int id_etudiant;

    public int getId_test() {
        return id_test;
    }

    public void setId_test(int id_test) {
        this.id_test = id_test;
    }

    public int getId_etudiant() {
        return id_etudiant;
    }

    public void setId_etudiant(int id_etudiant) {
        this.id_etudiant = id_etudiant;
    }
}
