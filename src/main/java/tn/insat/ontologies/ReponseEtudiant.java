package tn.insat.ontologies;

import jade.content.Concept;

import javax.persistence.*;

/**
 * Created by Khalil on 02/05/2017.
 */

@Entity
@Table(name="reponseetudiant"
        ,catalog="wskdb"
)
public class ReponseEtudiant implements Concept {


    @Id
    @Column(name="idReponseEtudiant", unique=true, nullable=false)
  private   int id_reponse_etudiant ;

    @Column(name="TextReponseEtudiant", length=256)
    private String text_reponse_etudiant ;

    @Column(name="ValidReponseEtudiant")
   private Boolean valid_reponse_etudiant ;

    @ManyToOne(fetch= FetchType.EAGER)
    @JoinColumns( {
            @JoinColumn(name="Test_has_Etudiant_idTestEtudiant", referencedColumnName="idTestEtudiant", nullable=true) } )
    private TestEtudiant testetudiant ;

    public int getId_reponse_etudiant() {
        return id_reponse_etudiant;
    }

    public void setId_reponse_etudiant(int id_reponse_etudiant) {
        this.id_reponse_etudiant = id_reponse_etudiant;
    }

    public String getText_reponse_etudiant() {
        return text_reponse_etudiant;
    }

    public void setText_reponse_etudiant(String text_reponse_etudiant) {
        this.text_reponse_etudiant = text_reponse_etudiant;
    }

    public Boolean getValid_reponse_etudiant() {
        return valid_reponse_etudiant;
    }

    public void setValid_reponse_etudiant(Boolean valid_reponse_etudiant) {
        this.valid_reponse_etudiant = valid_reponse_etudiant;
    }

    public TestEtudiant getTestetudiant() {
        return testetudiant;
    }

    public void setTestetudiant(TestEtudiant testetudiant) {
        this.testetudiant = testetudiant;
    }
}
