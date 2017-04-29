package tn.insat.ontologies;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;


/**
 * Created by saif on 29/04/2017.
 */

@Entity
@Table(name="sujetforum"
        ,catalog="wskdb"
)
public class SujetForum {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="idSujetForum", unique=true, nullable=false)
    int id_sujetforum ;
    @Column(name="TitreSujetForum", length=45)
    String titre_sujetforum ;
    @Column(name="TextSujetForum", length=1028)
    String text_sujetforum ;

   @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumns( {
            @JoinColumn(name="Enseignant_idEnseignant", referencedColumnName="idEnseignant", nullable=true) } )
    private Test enseignant_sujetforum ;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumns( {
            @JoinColumn(name="Etudiant_idEtudiant", referencedColumnName="idEtudiant", nullable=true) } )
    private Test etudiant_sujetforum ;

    @OneToMany(fetch=FetchType.LAZY, mappedBy="sujetforum_reponseforum")
    private Set<ReponseForum> reponsesforum = new HashSet<ReponseForum>(0);

    public int getId_sujetforum() {
        return id_sujetforum;
    }

    public void setId_sujetforum(int id_sujetforum) {
        this.id_sujetforum = id_sujetforum;
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

    public Test getEnseignant_sujetforum() {
        return enseignant_sujetforum;
    }

    public void setEnseignant_sujetforum(Test enseignant_sujetforum) {
        this.enseignant_sujetforum = enseignant_sujetforum;
    }

    public Test getEtudiant_sujetforum() {
        return etudiant_sujetforum;
    }

    public void setEtudiant_sujetforum(Test etudiant_sujetforum) {
        this.etudiant_sujetforum = etudiant_sujetforum;
    }

    public Set<ReponseForum> getReponsesforum() {
        return reponsesforum;
    }

    public void setReponsesforum(Set<ReponseForum> reponsesforum) {
        this.reponsesforum = reponsesforum;
    }
}
