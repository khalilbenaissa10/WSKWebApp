package tn.insat.ontologies;

import jade.content.AgentAction;

/**
 * Created by Khalil on 24/05/2017.
 */
public class CreateRessource implements AgentAction {


    int id_ressource ;

    String type_ressource ;

    String description_ressource ;

    private Cours cours_ressource ;

    public Cours getCours_ressource() {
        return cours_ressource;
    }

    public void setCours_ressource(Cours cours_ressource) {
        this.cours_ressource = cours_ressource;
    }

    public int getId_ressource() {
        return id_ressource;
    }

    public void setId_ressource(int id_ressource) {
        this.id_ressource = id_ressource;
    }

    public String getType_ressource() {
        return type_ressource;
    }

    public void setType_ressource(String type_ressource) {
        this.type_ressource = type_ressource;
    }

    public String getDescription_ressource() {
        return description_ressource;
    }

    public void setDescription_ressource(String description_ressource) {
        this.description_ressource = description_ressource;
    }
}
