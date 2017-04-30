/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.insat.Agents;

/**
 *
 * @author saif
 */
public interface IAgentEnseignant {
    
    void createCours(int cours_id,String intitulecours,int duree,String Description, int id_enseignant);

    public void infoCours(int idcours);

    public void listCours(int id_enseignant);
    
}
