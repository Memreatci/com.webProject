package stepDefinitions;

import io.cucumber.java.en.Given;
import org.junit.Assert;
import pages.HomePage;
import utilities.ConfigReader;
import utilities.Driver;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

public class HomePageStep {

    HomePage homePage = new HomePage();

    @Given("User goes to Lee.com.tr page.Pay attention to notifications and pop ups.")
    public void user_goes_to_lee_com_tr_page_pay_attention_to_notifications_and_pop_ups() throws InterruptedException {
        Driver.getDriver().get("https://www.lee.com.tr/");
        homePage.popupReject.click();
        Thread.sleep(1000);
    }

    @Given("The user logs in to the existing membership with valid {string} and {string} information.")
    public void the_user_logs_in_to_the_existing_membership_with_valid_mail_and_password_information(String mail, String password) throws InterruptedException {
        homePage.login(ConfigReader.getProperty(mail), ConfigReader.getProperty(password));
        Thread.sleep(1000);
        try {
            homePage.getAccountIcon().click();
            System.out.println("Succussfully logged in");
        } catch (Exception e) {
            System.err.println("Unsuccessfully tried. Warning !");
        }
    }

    @Given("The user goes to the cart and checks it.")
    public void the_user_goes_to_the_cart_and_checks_it() throws InterruptedException {
        homePage.clearTheCart();
    }
    @Given("Randomly select any of the Jean Kadin Erkek categories.")
    public void randomly_select_any_of_the_jean_kadin_erkek_categories() {
        homePage.clickRandom();
    }
}
