package tn.insat.ontologies;

import jade.content.Concept;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;

/**
 * Created by Khalil on 16/04/2017.
 */

@Entity
@Table(name="etudiant"
        ,catalog="wskdb"
)
public class Etudiant  implements Concept {


    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="idEtudiant", unique=true, nullable=false)
    int id_etudiant ;

    @Column(name="NomEtudiant", length=450)
    private String nom_etudiant;



    @Column(name="EmailEtudiant", length=450)
    private String email_etudiant;

    @Column(name="PasswordEtudiant", length=450)
    private String password_etudiant;

    @Column(name="AgeEtudiant", length=450)
    private int age_etudiant;

    @Column(name="InstitutEtudiant", length=450)
    private String institut_etudiant;

    @Column(name="CategoryEtudiant", length=450)
    private String category_etudiant;

    @OneToMany(fetch=FetchType.EAGER, mappedBy="etudiant_asso")
    private Set<CoursEtudiant> coursetudiant = new HashSet<CoursEtudiant>(0);

    @OneToMany(fetch=FetchType.EAGER, mappedBy="etudiant_asso_test")
    private Set<TestEtudiant> testetudiant = new HashSet<TestEtudiant>(0);

    @OneToMany(fetch=FetchType.EAGER, mappedBy="etudiant_sujetforum")
    private Set<SujetForum> sujetforumetudiant = new HashSet<SujetForum>(0);

    @OneToMany(fetch=FetchType.EAGER, mappedBy="etudiant_reponseforum")
    private Set<ReponseForum> reponseforumetudiant = new HashSet<ReponseForum>(0);

    @OneToMany(fetch=FetchType.EAGER, mappedBy="etudiant_asso_connaissance")
    private Set<ConnaissanceEtudiant> connaissancesetudiant = new HashSet<ConnaissanceEtudiant>(0);

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

    public Set<CoursEtudiant> getCoursetudiant() {
        return coursetudiant;
    }

    public void setCoursetudiant(Set<CoursEtudiant> coursetudiant) {
        this.coursetudiant = coursetudiant;
    }

    public Set<TestEtudiant> getTestetudiant() {
        return testetudiant;
    }

    public void setTestetudiant(Set<TestEtudiant> testetudiant) {
        this.testetudiant = testetudiant;
    }

    public Set<SujetForum> getSujetforumetudiant() {return sujetforumetudiant;    }

    public void setSujetforumetudiant(Set<SujetForum> sujetforumetudiant) {this.sujetforumetudiant = sujetforumetudiant;    }


    public Set<ReponseForum> getReponseforumetudiant() {
        return reponseforumetudiant;
    }

    public void setReponseforumetudiant(Set<ReponseForum> reponseforumetudiant) {
        this.reponseforumetudiant = reponseforumetudiant;
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

    @Override
    public boolean equals(Object other){
        if (other == null) return false;
        if (other == this) return true;
        if (!(other instanceof Etudiant))return false;
        Etudiant otherMyClass = (Etudiant) other;
        if(otherMyClass.getId_etudiant() == (this.getId_etudiant())) return true;
        else return false;

    }

    public Set<ConnaissanceEtudiant> getConnaissancesetudiant() {
        return connaissancesetudiant;
    }

    public void setConnaissancesetudiant(Set<ConnaissanceEtudiant> connaissancesetudiant) {
        this.connaissancesetudiant = connaissancesetudiant;
    }
}
