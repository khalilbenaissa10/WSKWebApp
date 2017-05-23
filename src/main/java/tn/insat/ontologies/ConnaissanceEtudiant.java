package tn.insat.ontologies;

import javax.persistence.*;

/**
 * Created by Khalil on 23/05/2017.
 */

@Entity
@Table(name="etudiant_has_conaissance"
        ,catalog="wskdb"
)
public class ConnaissanceEtudiant {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="idEtudiant_has_Conaissance", unique=true, nullable=false)
    int id_connaissance_etudiant ;

    @ManyToOne(fetch=FetchType.EAGER)
    @JoinColumns( {
            @JoinColumn(name="Conaissance_idConaissance", referencedColumnName="idConaissance", nullable=true) } )
    Connaissance connaissance_asso ;

    @ManyToOne(fetch=FetchType.EAGER)
    @JoinColumns( {
            @JoinColumn(name="Etudiant_idEtudiant", referencedColumnName="idEtudiant", nullable=true) } )
    Etudiant etudiant_asso_connaissance ;

    @Column(name="NiveauEtudiant_has_Conaissance", unique=true, nullable=false)
    int rating ;

    public int getId_connaissance_etudiant() {
        return id_connaissance_etudiant;
    }

    public void setId_connaissance_etudiant(int id_connaissance_etudiant) {
        this.id_connaissance_etudiant = id_connaissance_etudiant;
    }

    public Connaissance getConnaissance_asso() {
        return connaissance_asso;
    }

    public void setConnaissance_asso(Connaissance connaissance_asso) {
        this.connaissance_asso = connaissance_asso;
    }

    public Etudiant getEtudiant_asso_connaissance() {
        return etudiant_asso_connaissance;
    }

    public void setEtudiant_asso_connaissance(Etudiant etudiant_asso_connaissance) {
        this.etudiant_asso_connaissance = etudiant_asso_connaissance;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }


}
