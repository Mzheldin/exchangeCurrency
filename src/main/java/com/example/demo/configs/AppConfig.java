package com.example.demo.configs;

import com.example.demo.services.XMLParseBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.client.RestTemplate;

@Configuration
@PropertySource("classpath:private.properties")
@ComponentScan("com.example.demo")
public class AppConfig {
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Bean(initMethod = "init")
    public XMLParseBean xmlParseBean(){
        return new XMLParseBean();
    }
}
