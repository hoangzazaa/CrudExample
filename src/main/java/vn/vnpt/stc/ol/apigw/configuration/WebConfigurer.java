package vn.vnpt.stc.ol.apigw.configuration;

import io.github.jhipster.config.JHipsterProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@Configuration
@Slf4j
public class WebConfigurer {
    private final Environment env;
    private final JHipsterProperties jHipsterProperties;

    public WebConfigurer(Environment env, JHipsterProperties jHipsterProperties) {
        this.env = env;
        this.jHipsterProperties = jHipsterProperties;
    }

    @Bean
    public CorsFilter corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = jHipsterProperties.getCors();
        log.debug("Registering CORS filter");
        config.addAllowedHeader("*");
        config.addAllowedMethod("*");
        config.addAllowedOrigin("*");
        config.addExposedHeader("x-total-count, Authorization");
        config.setAllowCredentials(true);
        source.registerCorsConfiguration("/api/**", config);
        source.registerCorsConfiguration("/public/**", config);
        source.registerCorsConfiguration("/~/**", config);
        source.registerCorsConfiguration("/v2/api-docs", config);
        return new CorsFilter(source);
    }
}
