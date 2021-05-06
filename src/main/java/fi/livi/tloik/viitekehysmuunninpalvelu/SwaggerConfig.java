package fi.livi.tloik.viitekehysmuunninpalvelu;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.google.common.collect.Ordering;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiListingReference;
//import fi.livi.tloik.viitekehysmuunninpalvelu.Operation;
import springfox.documentation.service.Operation;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;


@Configuration
@EnableSwagger2
public class SwaggerConfig {
	
	private String contactOperator = "viitekehysmuunnin@vayla.fi";
	private String rajapintaKuvaus = "<a href=\"/viitekehysmuunnin/rajapintakuvaus\">Rajapintakuvaus (pdf)</a>";
	private String tayttoOhje = "Parametrilomakkeen avaaminen: klikkaa ensin tekstiä <i>viitekehysmuunnin-palvelu-controller</i>, sitten laatikkoa <i>GET /muunna</i> ja sitten pikkulaatikkoa <i>Try it out</i>";
	
    @Bean
    public Docket productApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .enableUrlTemplating(false)
                .select()
                .apis(RequestHandlerSelectors.basePackage("fi.livi.tloik.viitekehysmuunninpalvelu.controller"))
                .paths(PathSelectors.any())
                .build()
                .apiInfo(apiInfo())
                .operationOrdering(new Ordering<Operation>() {
                    @Override
                    public int compare(Operation left, Operation right) {
                        return left.getPosition() - right.getPosition();
                    }
                });
    }
    
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("VIITEKEHYSMUUNNIN")
                .version("uusi")
                .description("Tuki: " + contactOperator + "<br/><br/>" + rajapintaKuvaus + "<br/><br/>" + tayttoOhje)
                .build();
        }

}