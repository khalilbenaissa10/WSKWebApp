package tn.insat.ontologies;
import javax.persistence.*;

/**
 * Created by saif on 29/04/2017.
 */

@Entity
@Table(name="proposition"
        ,catalog="wskdb"
)

public class Proposition {


    @Id
    @Column(name="idProposition", unique=true, nullable=false)
    int id_proposition ;

    @Column(name="TextProposition", length=256)
    String text_proposition ;

    @Column(name="ValidProposition")
    Boolean valid_proposition ;

    @ManyToOne(fetch=FetchType.EAGER)
    @JoinColumns( {
            @JoinColumn(name="Question_idQuestion", referencedColumnName="idQuestion", nullable=true) } )
    private Question question_proposition ;

    public int getId_proposition() {
        return id_proposition;
    }

    public void setId_proposition(int id_proposition) {
        this.id_proposition = id_proposition;
    }

    public String getText_proposition() {
        return text_proposition;
    }

    public void setText_proposition(String text_proposition) {
        this.text_proposition = text_proposition;
    }

    public Boolean getValid_proposition() {
        return valid_proposition;
    }

    public void setValid_proposition(Boolean valid_proposition) {
        this.valid_proposition = valid_proposition;
    }

   public Question getQuestion_proposition() {
        return question_proposition;
    }

    public void setQuestion_proposition(Question question_proposition) {
        this.question_proposition = question_proposition;
    }
}
