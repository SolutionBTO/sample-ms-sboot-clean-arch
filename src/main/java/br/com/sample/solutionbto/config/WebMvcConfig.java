package br.com.sample.solutionbto.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
            .allowedOrigins("*")
            .allowedMethods("GET", "POST", "PUT", "DELETE", "PATCH", "OPTIONS")
            .allowedHeaders("*")
            .maxAge(3600);
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry
            .addResourceHandler("/static/**")
            .addResourceLocations("classpath:/static/")
            .setCachePeriod(31536000);

        registry
            .addResourceHandler("/css/**")
            .addResourceLocations("classpath:/static/css/")
            .setCachePeriod(31536000);

        registry
            .addResourceHandler("/js/**")
            .addResourceLocations("classpath:/static/js/")
            .setCachePeriod(31536000);

        registry
            .addResourceHandler("/images/**")
            .addResourceLocations("classpath:/static/images/")
            .setCachePeriod(31536000);
    }
}
