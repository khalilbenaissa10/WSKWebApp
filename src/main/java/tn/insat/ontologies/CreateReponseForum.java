package tn.insat.ontologies;

import jade.content.AgentAction;

/**
 * Created by saif on 11/05/2017.
 */
public class CreateReponseForum implements AgentAction {

    private int id_reponseforum;

    private String text_reponseforum;

    private Enseignant enseignant_reponseforum;

    private Etudiant etudiant_reponseforum;

    private  SujetForum sujet_reponseforum;

    public int getId_reponseforum() {
        return id_reponseforum;
    }

    public void setId_reponseforum(int id_reponseforum) {
        this.id_reponseforum = id_reponseforum;
    }

    public String getText_reponseforum() {
        return text_reponseforum;
    }

    public void setText_reponseforum(String text_reponseforum) {
        this.text_reponseforum = text_reponseforum;
    }

    public Enseignant getEnseignant_reponseforum() {
        return enseignant_reponseforum;
    }

    public void setEnseignant_reponseforum(Enseignant enseignant_reponseforum) {
        this.enseignant_reponseforum = enseignant_reponseforum;
    }

    public Etudiant getEtudiant_reponseforum() {
        return etudiant_reponseforum;
    }

    public void setEtudiant_reponseforum(Etudiant etudiant_reponseforum) {
        this.etudiant_reponseforum = etudiant_reponseforum;
    }

    public SujetForum getSujet_reponseforum() {
        return sujet_reponseforum;
    }

    public void setSujet_reponseforum(SujetForum sujet_reponseforum) {
        this.sujet_reponseforum = sujet_reponseforum;
    }
}
