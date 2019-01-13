package fi.livi.tloik.viitekehysmyynninpalvelu;

import java.time.format.DateTimeFormatterBuilder;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;

import fi.livi.vkm.IViitekehysmuunnin;
import fi.livi.vkm.PropertiesAlustusException;
import fi.livi.vkm.VkmLib;
import oracle.jdbc.OracleDriver;

@Configuration
public class VkmApplicationContext {

    @Autowired
    private Environment env;
    
    @Autowired
    private Jackson2ObjectMapperBuilder builder;

    @PostConstruct
    public void postConstruct() {
        this.builder.serializers(new LocalDateSerializer(new DateTimeFormatterBuilder()
                .appendPattern("dd.MM.yyyy").toFormatter()));
    }

    @Bean
    public IViitekehysmuunnin viitekehysmuunninNGPalvelu() {
        try {
            return VkmLib.getPalvelu(getProperties());
        } catch (PropertiesAlustusException e) {
            throw new IllegalStateException(e);
        }
    }

    /**
     * application.properties
     */
    private Map<String, Object> getProperties() {
        Map<String, Object> properties = new HashMap<String, Object>();
        properties.put(VkmLib.JDBC_DRIVER_CLASS_NAME_PROPERTY, OracleDriver.class.getName());
        properties.put(VkmLib.JDBC_URL_PROPERTY, env.getProperty("fi.livi.vkm.jdbc.url"));
        properties.put(VkmLib.JDBC_USER_PROPERTY, env.getProperty("fi.livi.vkm.jdbc.user"));
        properties.put(VkmLib.JDBC_PASSWORD_PROPERTY, env.getProperty("fi.livi.vkm.jdbc.pass"));
        properties.put(VkmLib.JDBC_TR_URL_PROPERTY, env.getProperty("fi.livi.tr.jdbc.url"));
        properties.put(VkmLib.JDBC_TR_USER_PROPERTY, env.getProperty("fi.livi.tr.jdbc.user"));
        properties.put(VkmLib.JDBC_TR_PASSWORD_PROPERTY, env.getProperty("fi.livi.tr.jdbc.pass"));
        properties.put(VkmLib.SHOW_SQL_PROPERTY, env.getProperty("fi.livi.vkm.hibernate.show_sql"));
        return properties;
    }




}
