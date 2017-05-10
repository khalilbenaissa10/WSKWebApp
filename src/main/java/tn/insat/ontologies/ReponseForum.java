package tn.insat.ontologies;
import javax.persistence.*;


/**
 * Created by saif on 29/04/2017.
 */

@Entity
@Table(name="reponseforum"
        ,catalog="wskdb"
)
public class ReponseForum {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="idReponseForum", unique=true, nullable=false)
    int id_reponseforum ;

    @Column(name="TextReponseForum", length=512)
    String text_reponseforum ;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumns( {
            @JoinColumn(name="SujetForum_idSujetForum", referencedColumnName="idSujetForum", nullable=true) } )
    private SujetForum sujetforum_reponseforum ;


    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumns( {
            @JoinColumn(name="Enseignant_idEnseignant", referencedColumnName="idEnseignant", nullable=true) } )
    private Enseignant enseignant_reponseforum ;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumns( {
            @JoinColumn(name="Etudiant_idEtudiant", referencedColumnName="idEtudiant", nullable=true) } )
    private Etudiant etudiant_reponseforum ;

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

    public SujetForum getSujetforum_reponseforum() {
        return sujetforum_reponseforum;
    }

    public void setSujetforum_reponseforum(SujetForum sujetforum_reponseforum) {
        this.sujetforum_reponseforum = sujetforum_reponseforum;
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
}
