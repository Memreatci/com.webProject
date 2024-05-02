package stepDefinitions;


import org.apache.log4j.xml.DOMConfigurator;
import org.apache.poi.ss.formula.functions.T;
import org.junit.Rule;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.CartPage;
import pages.HomePage;
import pages.LoginPage;
import utilities.Driver;
import utilities.MyLogClass;
import utilities.TestLogger;

import java.time.Duration;


public class BaseStep {
    @Rule
    public TestLogger testLogger = new TestLogger();
    protected MyLogClass logClass;
    protected final WebDriverWait wait;
    protected final HomePage homePage;
    protected final LoginPage loginPage;
    protected final CartPage cartPage;


    public BaseStep() {

        wait = new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(10));
        homePage = new HomePage();
        loginPage = new LoginPage();
        cartPage = new CartPage();
        logClass = new MyLogClass();
    }

}
