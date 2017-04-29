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
    private Test sujetforum_reponseforum ;

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

    public Test getSujetforum_reponseforum() {
        return sujetforum_reponseforum;
    }

    public void setSujetforum_reponseforum(Test sujetforum_reponseforum) {
        this.sujetforum_reponseforum = sujetforum_reponseforum;
    }
}
