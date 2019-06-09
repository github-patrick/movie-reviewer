package com.movies.bdd;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(tags = { "@UserRegistration"},
        plugin = {"pretty"},
        glue = {"com.movies.bdd.steps", "com.movies.bdd.hooks"},
        features = "src/test/resources/features")
public class RunCukesTestIT {
}
