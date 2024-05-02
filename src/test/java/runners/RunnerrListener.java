package runners;


import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.Listeners;
import utilities.CustomTestListener;

@Listeners(CustomTestListener.class)
@CucumberOptions(
        plugin = {//"pretty",
                "html:target/cucumber-report.html",
                "rerun:target/rerun.txt",
                "me.jvt.cucumber.report.PrettyReports:target/cucumber-reports",
        },
        features = "src/test/resources",
        glue = "stepDefinitions",
        tags = "@e2e",
        dryRun = false
)
public class RunnerrListener extends AbstractTestNGCucumberTests {


}
