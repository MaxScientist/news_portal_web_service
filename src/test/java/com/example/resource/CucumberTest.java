package com.example.resource;


import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(plugin="pretty",
glue = "com.example.resource",
features = "classpath:feature",
snippets = CucumberOptions.SnippetType.CAMELCASE)
public class CucumberTest {
}
