package vn.vnpt.stc.ol.apigw.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Arrays;

@Configuration
@EnableSwagger2
public class Swagger2Config {

    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .globalOperationParameters(
                        Arrays.asList(new ParameterBuilder()
                                            .name("Authorization")
                                            .description("token")
                                            .parameterType("header")
                                            .modelRef(new ModelRef("string"))
                                            .build())
                )
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("vn.vnpt.stc.ol.apigw.api.endpoints"))
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder().title("OneLink")
                .description("Swagger API")
                .contact(new Contact("VNPT Technology", "https://www.vnpt-technology.vn/", "onelink@vnpt-technology.vn"))
                .version("1.0.0-SNAPSHOT")
                .build();
    }
}
