package tn.insat.ontologies;

import jade.content.AgentAction;

/**
 * Created by Khalil on 22/05/2017.
 */
public class LoginEtudiant implements AgentAction{

    private String email_etudiant;
    private String password_etudiant;

    public String getEmail_etudiant() {
        return email_etudiant;
    }

    public void setEmail_etudiant(String email_etudiant) {
        this.email_etudiant = email_etudiant;
    }

    public String getPassword_etudiant() {
        return password_etudiant;
    }

    public void setPassword_etudiant(String password_etudiant) {
        this.password_etudiant = password_etudiant;
    }
}
