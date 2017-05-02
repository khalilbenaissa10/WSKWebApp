package tn.insat.ontologies;

import jade.content.AgentAction;

/**
 * Created by Khalil on 02/05/2017.
 */
public class CreateReponseEtudiant implements AgentAction {
    private   int id_reponse_etudiant ;
    private String text_reponse_etudiant ;
    private Boolean valid_reponse_etudiant ;
    private TestEtudiant testEtudiant  ;

    public int getId_reponse_etudiant() {
        return id_reponse_etudiant;
    }

    public void setId_reponse_etudiant(int id_reponse_etudiant) {
        this.id_reponse_etudiant = id_reponse_etudiant;
    }

    public String getText_reponse_etudiant() {
        return text_reponse_etudiant;
    }

    public void setText_reponse_etudiant(String text_reponse_etudiant) {
        this.text_reponse_etudiant = text_reponse_etudiant;
    }

    public Boolean getValid_reponse_etudiant() {
        return valid_reponse_etudiant;
    }

    public void setValid_reponse_etudiant(Boolean valid_reponse_etudiant) {
        this.valid_reponse_etudiant = valid_reponse_etudiant;
    }

    public TestEtudiant getTestEtudiant() {
        return testEtudiant;
    }

    public void setTestEtudiant(TestEtudiant testEtudiant) {
        this.testEtudiant = testEtudiant;
    }

    public CreateReponseEtudiant(int id_reponse_etudiant, String text_reponse_etudiant, Boolean valid_reponse_etudiant, TestEtudiant testEtudiant) {
        this.id_reponse_etudiant = id_reponse_etudiant;
        this.text_reponse_etudiant = text_reponse_etudiant;
        this.valid_reponse_etudiant = valid_reponse_etudiant;
        this.testEtudiant = testEtudiant;
    }

    public CreateReponseEtudiant() {
    }
}
