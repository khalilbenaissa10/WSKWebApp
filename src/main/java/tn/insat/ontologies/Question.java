package tn.insat.ontologies;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by saif on 29/04/2017.
 */

@Entity
@Table(name="question"
        ,catalog="wskdb"
)
public class Question {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="idQuestion", unique=true, nullable=false)
    int id_question ;
    @Column(name="EnonceQuestion", length=256)
    String enonce_question ;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumns( {
            @JoinColumn(name="Test_idTest", referencedColumnName="idTest", nullable=true) } )
    private Test test_question ;


    @OneToMany(fetch=FetchType.LAZY, mappedBy="question_proposition")
    private Set<Proposition> propositions = new HashSet<Proposition>(0);

    public int getId_question() {
        return id_question;
    }

    public void setId_question(int id_question) {
        this.id_question = id_question;
    }

    public String getEnonce_question() {
        return enonce_question;
    }

    public void setEnonce_question(String enonce_question) {
        this.enonce_question = enonce_question;
    }

    public Test getTest_question() {
        return test_question;
    }

    public void setTest_question(Test test_question) {
        this.test_question = test_question;
    }
}
