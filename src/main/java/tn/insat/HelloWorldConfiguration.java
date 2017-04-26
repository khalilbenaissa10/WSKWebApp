package tn.insat;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import tn.insat.Agents.AgentOperator;

@SpringBootApplication
public class HelloWorldConfiguration {

    public static void main(String[] args) {

        AgentOperator operator =  AgentOperator.getInstance();
        operator.start_agents();
        SpringApplication.run(HelloWorldConfiguration.class, args);
    }

}