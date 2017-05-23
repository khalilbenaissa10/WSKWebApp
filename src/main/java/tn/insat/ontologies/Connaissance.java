package tn.insat.ontologies;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Khalil on 17/04/2017.
 */

@Entity
@Table(name="conaissance"
        ,catalog="wskdb"
)
public class Connaissance {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="idConaissance", unique=true, nullable=false)
    int id_connaissance ;

    @Column(name="DescriptionConaissance", length=450)
    String description_connsaissance ;



    @OneToOne
    @JoinColumn(name = "Cours_idCours",referencedColumnName="idCours", nullable=true)
    private Cours cours_connaissance;


    @OneToMany(fetch=FetchType.EAGER, mappedBy="connaissance_asso")
    private Set<ConnaissanceEtudiant> connaissancesetudiants = new HashSet<ConnaissanceEtudiant>(0);

    public int getId_connaissance() {
        return id_connaissance;
    }

    public void setId_connaissance(int id_connaissance) {
        this.id_connaissance = id_connaissance;
    }

    public String getDescription_connsaissance() {
        return description_connsaissance;
    }

    public void setDescription_connsaissance(String description_connsaissance) {
        this.description_connsaissance = description_connsaissance;
    }

    public Cours getCours_connaissance() {
        return cours_connaissance;
    }

    public void setCours_connaissance(Cours cours_connaissance) {
        this.cours_connaissance = cours_connaissance;
    }

    public Set<ConnaissanceEtudiant> getConnaissancesetudiants() {
        return connaissancesetudiants;
    }

    public void setConnaissancesetudiants(Set<ConnaissanceEtudiant> connaissancesetudiants) {
        this.connaissancesetudiants = connaissancesetudiants;
    }
}
