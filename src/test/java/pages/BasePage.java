package pages;

import com.github.javafaker.Faker;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import utilities.Driver;

import java.time.Duration;
import java.util.Random;

public abstract class BasePage {
    protected final Actions actions;
    protected final WebDriverWait wait;
    protected final Random random;
    protected final JavascriptExecutor js;
    protected final Faker faker;
    protected final HomePage homePage;
    protected final LoginPage loginPage;
    protected final CartPage cartPage;

    public BasePage() {
        PageFactory.initElements(Driver.getDriver(), this);
        actions = new Actions(Driver.getDriver());
        wait = new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(10));
        random = new Random();
        js = (JavascriptExecutor) Driver.getDriver();
        faker = new Faker();
        homePage = new HomePage();
        loginPage = new LoginPage();
        cartPage = new CartPage();
    }


}
