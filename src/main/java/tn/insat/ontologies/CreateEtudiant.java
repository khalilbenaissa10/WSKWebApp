package tn.insat.ontologies;

import jade.content.AgentAction;

/**
 * Created by Khalil on 13/05/2017.
 */
public class CreateEtudiant implements AgentAction {

    int id_etudiant ;

    private String nom_etudiant;
    private String email_etudiant;
    private String password_etudiant;
    private int age_etudiant;
    private String institut_etudiant;
    private String category_etudiant;


    public int getId_etudiant() {
        return id_etudiant;
    }

    public void setId_etudiant(int id_etudiant) {
        this.id_etudiant = id_etudiant;
    }

    public String getNom_etudiant() {
        return nom_etudiant;
    }

    public void setNom_etudiant(String nom_etudiant) {
        this.nom_etudiant = nom_etudiant;
    }

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

    public int getAge_etudiant() {
        return age_etudiant;
    }

    public void setAge_etudiant(int age_etudiant) {
        this.age_etudiant = age_etudiant;
    }

    public String getInstitut_etudiant() {
        return institut_etudiant;
    }

    public void setInstitut_etudiant(String institut_etudiant) {
        this.institut_etudiant = institut_etudiant;
    }

    public String getCategory_etudiant() {
        return category_etudiant;
    }

    public void setCategory_etudiant(String category_etudiant) {
        this.category_etudiant = category_etudiant;
    }
}
