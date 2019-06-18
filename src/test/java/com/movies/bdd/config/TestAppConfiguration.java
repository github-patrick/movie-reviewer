package com.movies.bdd.config;

import com.movies.bdd.steps.Context;
import org.fluttercode.datafactory.impl.DataFactory;
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

    @Bean
    public DataFactory dataFactory() {
        return new DataFactory();
    }
}
