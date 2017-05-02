package tn.insat.Client;

import tn.insat.ontologies.Proposition;
import tn.insat.ontologies.Question;
import tn.insat.ontologies.ReponseEtudiant;
import tn.insat.ontologies.Test;

import java.util.List;

/**
 * Created by Khalil on 01/05/2017.
 */
public class SingletonQuestion {

    public static List<Question> questions ;

    public static Test test ;

    public static ReponseEtudiant reponseEtudiant ;

    public static Question question;

    public static Proposition proposition;

    public static List<Proposition> propositions ;

    public static List<Question> getQuestions() {
        return questions;
    }

    public static void setQuestions(List<Question> questions) {
        SingletonQuestion.questions = questions;
    }


    public static List<Proposition> getPropositions() {
        return propositions;
    }

    public static void setPropositions(List<Proposition> propositions) {
        SingletonQuestion.propositions = propositions;
    }

    public static Test getTest() {
        return test;
    }

    public static void setTest(Test test) {
        SingletonQuestion.test = test;
    }

    public static Question getQuestion() {
        return question;
    }

    public static void setQuestion(Question question) {
        SingletonQuestion.question = question;
    }

    public static Proposition getProposition() {
        return proposition;
    }

    public static void setProposition(Proposition proposition) {
        SingletonQuestion.proposition = proposition;
    }

    public static ReponseEtudiant getReponseEtudiant() {
        return reponseEtudiant;
    }

    public static void setReponseEtudiant(ReponseEtudiant reponseEtudiant) {
        SingletonQuestion.reponseEtudiant = reponseEtudiant;
    }
}
