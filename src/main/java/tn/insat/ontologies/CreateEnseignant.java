package tn.insat.ontologies;

import jade.content.AgentAction;

/**
 * Created by Khalil on 13/05/2017.
 */
public class CreateEnseignant implements AgentAction {


    private int id_enseignant;

    private String nom_enseignant;

    private String email_enseignant;

    private String password_enseignant;

    private int age_enseignant;

    private String institut_enseignant;

    private String category_enseignant;

    public int getId_enseignant() {
        return id_enseignant;
    }

    public void setId_enseignant(int id_enseignant) {
        this.id_enseignant = id_enseignant;
    }

    public String getNom_enseignant() {
        return nom_enseignant;
    }

    public void setNom_enseignant(String nom_enseignant) {
        this.nom_enseignant = nom_enseignant;
    }

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

    public int getAge_enseignant() {
        return age_enseignant;
    }

    public void setAge_enseignant(int age_enseignant) {
        this.age_enseignant = age_enseignant;
    }

    public String getInstitut_enseignant() {
        return institut_enseignant;
    }

    public void setInstitut_enseignant(String institut_enseignant) {
        this.institut_enseignant = institut_enseignant;
    }

    public String getCategory_enseignant() {
        return category_enseignant;
    }

    public void setCategory_enseignant(String category_enseignant) {
        this.category_enseignant = category_enseignant;
    }
}
