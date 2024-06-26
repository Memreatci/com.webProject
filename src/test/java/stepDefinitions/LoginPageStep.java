package stepDefinitions;

import io.cucumber.java.en.Given;

import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.support.ui.ExpectedConditions;
import utilities.ConfigReader;


public class LoginPageStep extends BaseStep {
    @Given("The user logs in to the existing membership with valid {string} and {string} information.")
    public void the_user_logs_in_to_the_existing_membership_with_valid_mail_and_password_information(String mail, String password) throws InterruptedException {

        loginPage.login(ConfigReader.getProperty(mail), ConfigReader.getProperty(password));
        try {
            wait.until(ExpectedConditions.elementToBeClickable(homePage.getAccountIcon()));
            logClass.info("The entry process was done.");
            System.out.println("The entry process was done.");
        } catch (TimeoutException e) {
            throw new AssertionError(" Warning ! Failed to log in. ");
        }
    }
}
