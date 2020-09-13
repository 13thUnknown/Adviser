package edu.suai.recommendations.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MvcConfig implements WebMvcConfigurer {

    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/test").setViewName("test");
        registry.addViewController("/login").setViewName("sing-in");
        registry.addViewController("/js").setViewName("js");
        registry.addViewController("/old-i").setViewName("OLD_index");

    }
}