package stepDefinitions;

import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;
import org.junit.Rule;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.CartPage;
import pages.HomePage;
import pages.LoginPage;
import utilities.Driver;
import utilities.TestLogger;

import java.time.Duration;

public class BaseStep  {

   // private static Logger logger;
    protected final WebDriverWait wait;
    protected final HomePage homePage;
    protected final LoginPage loginPage;
    protected final CartPage cartPage;

    public BaseStep(){
        DOMConfigurator.configure("log4j.xml");
        wait = new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(10));
        homePage = new HomePage();
        loginPage = new LoginPage();
        cartPage = new CartPage();
      //  logger = Logger.getLogger(BaseStep.class);
    }
   // public void info(String message){
   //     logger.info(message);
   // }
   // public void debug(String message){
   //     logger.debug(message);
   // }
   // public void warn(String message){
   //     logger.warn(message);
   // }
//
   // public void error(String message){
   //     logger.error(message);
   // }

    @Rule
    public TestLogger testLogger = new TestLogger();
}
