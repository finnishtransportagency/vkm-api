package fi.livi.tloik.viitekehysmyynninpalvelu;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.bedatadriven.jackson.datatype.jts.JtsModule;

@Configuration
public class JtsConfig {
	
    @Bean
    public JtsModule jtsModule() {
        return new JtsModule();
    }
    
}