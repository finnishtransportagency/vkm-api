package fi.livi.tloik.viitekehysmuunninpalvelu;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class ViitekehysmuunninApplication {

    public static void main(String[] args) {
        ApplicationContext ctx = SpringApplication.run(ViitekehysmuunninApplication.class, args);
    }

}