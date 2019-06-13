package com.movies.bdd.config;

import com.movies.bdd.steps.Context;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
public class TestAppConfiguration {

    @Bean
    @Scope("cucumber-glue")
    public Context context() {
        return new Context();
    }
}
