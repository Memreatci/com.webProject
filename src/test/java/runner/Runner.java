package runner;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        plugin = {"html:target/cucumber-reports.html",
                "json:target/json-reports/cucumber.json",
                "junit:target/xml-report/cucumber.xml"},
        features = "C:\\Users\\pc\\IdeaProjects\\com.LeeTask\\src\\test\\resources",
        glue = "stepDefinitions",
        tags="@us1",
        dryRun = true



)
public class Runner {
}
