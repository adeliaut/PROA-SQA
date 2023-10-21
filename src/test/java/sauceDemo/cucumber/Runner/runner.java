package sauceDemo.cucumber.Runner;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/java/sauceDemo/cucumber/features",
        glue = "sauceDemo.cucumber.StepDefinition",
        plugin = {"html:target/HTML_report.html"}
        //tags = ""
)

public class runner{

}