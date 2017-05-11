package tn.insat.ontologies;

import jade.content.AgentAction;

/**
 * Created by Khalil on 11/05/2017.
 */
public class CreateSujetForum implements AgentAction {

    private int id_sujetForum ;
    String titre_sujetforum ;
    String text_sujetforum ;
    private Enseignant enseignant_sujetforum ;
    private Etudiant etudiant_sujetforum ;
    private Cours cours_sujetforum ;

    public int getId_sujetForum() {
        return id_sujetForum;
    }

    public void setId_sujetForum(int id_sujetForum) {
        this.id_sujetForum = id_sujetForum;
    }

    public String getTitre_sujetforum() {
        return titre_sujetforum;
    }

    public void setTitre_sujetforum(String titre_sujetforum) {
        this.titre_sujetforum = titre_sujetforum;
    }

    public String getText_sujetforum() {
        return text_sujetforum;
    }

    public void setText_sujetforum(String text_sujetforum) {
        this.text_sujetforum = text_sujetforum;
    }

    public Enseignant getEnseignant_sujetforum() {
        return enseignant_sujetforum;
    }

    public void setEnseignant_sujetforum(Enseignant enseignant_sujetforum) {
        this.enseignant_sujetforum = enseignant_sujetforum;
    }

    public Etudiant getEtudiant_sujetforum() {
        return etudiant_sujetforum;
    }

    public void setEtudiant_sujetforum(Etudiant etudiant_sujetforum) {
        this.etudiant_sujetforum = etudiant_sujetforum;
    }

    public Cours getCours_sujetforum() {
        return cours_sujetforum;
    }

    public void setCours_sujetforum(Cours cours_sujetforum) {
        this.cours_sujetforum = cours_sujetforum;
    }
}
