package stepDefinitions;


import io.cucumber.java.en.Given;
import org.openqa.selenium.support.ui.ExpectedConditions;
import utilities.ConfigReader;
import utilities.Driver;

import java.io.IOException;


public class HomePageStep extends BaseStep {

    @Given("The user goes to the {string}. Pay attention to notifications and pop ups.")
    public void user_goes_to_lee_com_tr_page_pay_attention_to_notifications_and_pop_ups(String url) throws InterruptedException {
        info("Initializing the automation test");
        Driver.getDriver().get(ConfigReader.getProperty(url));
        homePage.popupReject.click();
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

    @Given("log out.")
    public void log_out() {
        homePage.getAccountIcon().click();
        wait.until(ExpectedConditions.elementToBeClickable(homePage.getCikisYapLink()));
        homePage.getCikisYapLink().click();
    }

    @Given("Close the browser.")
    public void close_the_browser() {
        Driver.getDriver().close();
    }


}

