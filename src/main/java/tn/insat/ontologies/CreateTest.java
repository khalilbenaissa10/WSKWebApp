/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.insat.ontologies;
import jade.content.*;

/**
 *
 * @author saif
 */
public class CreateTest implements AgentAction {
    
    private  int id_cours  ;
    private int id_test;
    private String nom_test;
    private String duree_test;

    public int getId_cours() {
        return id_cours;
    }

    public void setId_cours(int id_cours) {
        this.id_cours = id_cours;
    }

    public int getId_test() {
        return id_test;
    }

    public void setId_test(int id_test) {
        this.id_test = id_test;
    }

    public String getNom_test() {
        return nom_test;
    }

    public void setNom_test(String nom_test) {
        this.nom_test = nom_test;
    }

    public String getDuree_test() {
        return duree_test;
    }

    public void setDuree_test(String duree_test) {
        this.duree_test = duree_test;
    }

    public CreateTest(int id_cours, int id_test, String nom_test, String duree_test) {
        this.id_cours = id_cours;
        this.id_test = id_test;
        this.nom_test = nom_test;
        this.duree_test = duree_test;
    }

    public CreateTest() {
    }
}
