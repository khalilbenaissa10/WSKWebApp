package tn.insat.ontologies;

import jade.content.AgentAction;

/**
 * Created by Khalil on 22/05/2017.
 */
public class LoginEnseignant implements AgentAction {

    private String email_enseignant;
    private String password_enseignant;


    public String getEmail_enseignant() {
        return email_enseignant;
    }

    public void setEmail_enseignant(String email_enseignant) {
        this.email_enseignant = email_enseignant;
    }

    public String getPassword_enseignant() {
        return password_enseignant;
    }

    public void setPassword_enseignant(String password_enseignant) {
        this.password_enseignant = password_enseignant;
    }
}
