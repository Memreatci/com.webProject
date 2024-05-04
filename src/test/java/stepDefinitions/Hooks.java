package stepDefinitions;


import io.cucumber.java.*;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import utilities.Driver;
import utilities.MyLogClass;
import java.time.LocalDateTime;


public class Hooks {
    static MyLogClass log = new MyLogClass();

    @Before
    public static void setupScenario(Scenario scenario) {

        System.out.println("SCENARIO : '" + scenario.getName() + "' - is started " + LocalDateTime.now());
        log.info("SCENARIO : '" + scenario.getName() + "' - is started " + LocalDateTime.now());
    }

    @After
    public void tearDown(Scenario scenario) {

        if (scenario.isFailed()) {
            System.out.println("SCENARIO : " + scenario.getName() + " - is FAILED");
            log.error("SCENARIO : " + scenario.getName() + " - is FAILED");
            final byte[] screenshot = ((TakesScreenshot) Driver.getDriver()).getScreenshotAs(OutputType.BYTES);
            scenario.attach(screenshot, "image/png", "screenshots");
            //Driver.closeDriver();
        } else {
            System.out.println("SCENARIO : " + scenario.getName() + " - is over");
            log.info("SCENARIO : " + scenario.getName() + " - is over");
        }


    }

}
