package tn.insat.ontologies;


import jade.content.Concept;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;


/**
 * Created by Khalil on 16/04/2017.
 */

@Entity
@Table(name="enseignant"
        ,catalog="wskdb"
)
public class Enseignant  implements Concept {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="idEnseignant", nullable=false)
    private int id_enseignant;

    @Column(name="NomEnseignant",  length=45)
    private String nom_enseignant;

    @Column(name="EmailEnseignant",  length=45)
    private String email_enseignant;

    @Column(name="PasswordEnseignant",  length=45)
    private String password_enseignant;

    @Column(name="AgeEnseignant",  length=45)
    private int age_enseignant;

    @Column(name="InstitutEnseignant",  length=45)
    private String institut_enseignant;

    @Column(name="CategoryEnseignant",  length=45)
    private String category_enseignant;




    @OneToMany(fetch=FetchType.EAGER, mappedBy="enseignant")
    private Set<Cours> cours = new HashSet<Cours>(0);

    @OneToMany(fetch=FetchType.LAZY, mappedBy="enseignant_sujetforum")
    private Set<SujetForum> sujetforumenseignant = new HashSet<SujetForum>(0);

    @OneToMany(fetch=FetchType.LAZY, mappedBy="enseignant_reponseforum")
    private Set<ReponseForum> reponseforumenseignant = new HashSet<ReponseForum>(0);


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

    public Set<Cours> getCours() {
        return cours;
    }

    public void setCours(Set<Cours> cours) {
        this.cours = cours;
    }

    public Set<SujetForum> getSujetforumenseignant() { return sujetforumenseignant;   }

    public void setSujetforumenseignant(Set<SujetForum> sujetforumenseignant) { this.sujetforumenseignant = sujetforumenseignant;    }

    public Set<ReponseForum> getReponseforumenseignant() {
        return reponseforumenseignant;
    }

    public void setReponseforumenseignant(Set<ReponseForum> reponseforumenseignant) {
        this.reponseforumenseignant = reponseforumenseignant;
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
