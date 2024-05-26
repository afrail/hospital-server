package com.sopnobazz.demo.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @version 1.0.0
 * @Project Demo
 * @Author Afrail Hossain
 * @Since Nov 16, 2022
 */

@Configuration
@PropertySources({
        @PropertySource("classpath:properties/app.properties"),
        @PropertySource("classpath:properties/swagger.properties")
})
public class AppConfig implements WebMvcConfigurer {

    @SuppressWarnings("unused")
    private static final Logger LOG = LoggerFactory.getLogger(AppConfig.class);
}
