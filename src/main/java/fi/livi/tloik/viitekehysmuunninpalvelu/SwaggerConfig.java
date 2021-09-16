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
	
	private String muutosTiedote = "<a href=\"/viitekehysmuunnin/tiedote\"><b style=\"color:red;\">Tiedote (pdf)</b>: Vanhat viitekehysmuuntimet poistuvat käytöstä 30.11.2021, uuden viitekehysmuuntimen osoite muuttuu</a>";
	private String contactOperator = "viitekehysmuunnin@vayla.fi";
	private String rajapintaKuvaus = "<a href=\"/viitekehysmuunnin/rajapintakuvaus\">Rajapintakuvaus (pdf), lokakuu 2020</a>";
	private String muutoksetLatest = "<a href=\"/viitekehysmuunnin/muutokset-latest\">Kuvaus toimintojen laajennuksista rajapintakuvauksen jälkeen (pdf), toukokuu 2021</a>";
	private String tayttoOhje = "<b>Parametrilomakkeen avaaminen:</b><ul><li>klikkaa ensin tekstiä <i>viitekehysmuunnin-palvelu-controller</i></li><li>klikkaa laatikkoa <i>GET /muunna</i></li><li>klikkaa pikkulaatikkoa <i>Try it out</i></li></ul>";
	
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
                .description(muutosTiedote + "<br/><br/>" + "Tuki: " + contactOperator + "<br/><br/>" + rajapintaKuvaus + "<br/><br/>" + muutoksetLatest + "<br/><br/><br/>" + tayttoOhje)
                .build();
        }

}