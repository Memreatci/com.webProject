package stepDefinitions;

import io.cucumber.java.Scenario;
import io.cucumber.java.en.Given;
import org.junit.Assert;
import org.openqa.selenium.Alert;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.HomePage;
import utilities.ConfigReader;
import utilities.Driver;

import java.io.IOException;
import java.time.Duration;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

public class HomePageStep {

    HomePage homePage;
    private WebDriverWait wait;
    public HomePageStep() {
        homePage = new HomePage();
        wait = new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(10));
    }

    @Given("The user goes to the {string}. Pay attention to notifications and pop ups.")
    public void user_goes_to_lee_com_tr_page_pay_attention_to_notifications_and_pop_ups(String url) throws InterruptedException {
        Driver.getDriver().get(ConfigReader.getProperty(url));
        homePage.popupReject.click();
    }

    @Given("The user logs in to the existing membership with valid {string} and {string} information.")
    public void the_user_logs_in_to_the_existing_membership_with_valid_mail_and_password_information(String mail, String password) throws InterruptedException {

        homePage.login(ConfigReader.getProperty(mail), ConfigReader.getProperty(password));
        try {
            wait.until(ExpectedConditions.elementToBeClickable(homePage.getAccountIcon()));
            System.out.println("Entry Successful");
        } catch (TimeoutException e) {
            throw new AssertionError("Warning ! Failed to log in. ");
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

    @Given("Select ascending sorting by price from the product listing page.")
    public void select_ascending_sorting_by_price_from_the_product_listing_page() throws InterruptedException {
        homePage.clickAndSorting();
    }

    @Given("Go inside the first product.")
    public void go_inside_the_first_product() {
        wait.until(ExpectedConditions.elementToBeClickable(homePage.getProductList().get(0)));
        homePage.getProductList().get(0).click();
    }

    @Given("Randomly select any of the size options from the product detail page.")
    public void randomly_select_any_of_the_size_options_from_the_product_detail_page() throws InterruptedException {
        homePage.selectRandomlySizeOptions();
    }

    @Given("Randomly add the selected size to the cart and go to cart.")
    public void randomly_add_the_selected_size_to_the_cart_and_go_to_cart() throws InterruptedException {
        homePage.addAndGoCart();
    }
    @Given("Click on the Hediye Paketi Istiyorum.")
    public void click_on_the_hediye_paketi_istiyorum() {
        homePage.getHediyePackCheckBox().click();
    }
    @Given("Enter a random code in the promo code field")
    public void enter_a_random_code_in_the_promo_code_field() throws IOException {
        homePage.enterPromotion();
    }
    @Given("Press the buy button and continue")
    public void press_the_buy_button_and_continue() {
        homePage.getSatinAlButton().click();
    }
    @Given("On the delivery information page, add a new address and proceed.")
    public void on_the_delivery_information_page_add_a_new_address_and_proceed() throws InterruptedException {
        homePage.addANewAddress();
    }
    @Given("Enter incorrect credit card details and complete the order.")
    public void enter_incorrect_credit_card_details_and_complete_the_order() throws InterruptedException, IOException {
        homePage.enterCardInfo();
    }
    @Given("Go back to the cart and increase the quantity by {int}.")
    public void go_back_to_the_cart_and_increase_the_quantity_by(Integer int1) {
        homePage.goBackCartAndIncrease(int1);
    }
    @Given("log out.")
    public void log_out() {
        homePage.getAccountIcon().click();
        wait.until(ExpectedConditions.elementToBeClickable(homePage.getCıkısYapLink()));
        homePage.getCıkısYapLink().click();
    }
    @Given("Close the browser.")
    public void close_the_browser() {
        Driver.getDriver().close();
    }


}

