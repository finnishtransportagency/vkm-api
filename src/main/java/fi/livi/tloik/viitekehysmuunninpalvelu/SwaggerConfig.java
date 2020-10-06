package fi.livi.tloik.viitekehysmuunninpalvelu;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;


@Configuration
@EnableSwagger2
public class SwaggerConfig {
	
	private String contactOperator = "viitekehysmuunnin@vayla.fi";
	private String rajapintaKuvaus = "<a href=\"\\..\\resources\\viitekehysmuunnin-2020-rajapintakuvaus.pdf\">Rajapintakuvaus</a>";
	
    @Bean
    public Docket productApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .enableUrlTemplating(false)
                .select()
                .apis(RequestHandlerSelectors.basePackage("fi.livi.tloik.viitekehysmuunninpalvelu.controller"))
                .paths(PathSelectors.any())
                .build()
                .apiInfo(apiInfo());
    }
    
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("VIITEKEHYSMUUNNIN")
                .version("uusi")
                .description("Tuki: " + contactOperator + "<br/>" + "Rajapintakuvaus: " + rajapintaKuvaus)
                .build();
        }

}