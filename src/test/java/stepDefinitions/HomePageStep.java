package stepDefinitions;


import io.cucumber.java.en.Given;
import io.cucumber.java.sl.In;
import org.openqa.selenium.support.ui.ExpectedConditions;
import utilities.ConfigReader;
import utilities.Driver;

import java.io.IOException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class HomePageStep extends BaseStep {


    @Given("The user goes to the {string}. Pay attention to notifications and pop ups.")
    public void user_goes_to_lee_com_tr_page_pay_attention_to_notifications_and_pop_ups(String url) throws InterruptedException {
        Driver.getDriver().get(ConfigReader.getProperty(url));
        homePage.popupReject.click();

    }

    @Given("Randomly select any of the Jean Kadin Erkek categories.")
    public void randomly_select_any_of_the_jean_kadin_erkek_categories() {
        homePage.clickRandom();

        String currentUrl = Driver.getDriver().getCurrentUrl();
        boolean urlContainsAllKeywords = currentUrl.contains("jean") || currentUrl.contains("kadin") || currentUrl.contains("erkek");
        assertTrue("Wrong category selection", urlContainsAllKeywords);
        logClass.info("One of the categories 'Kadın', 'Erkek', and 'Jean' was randomly selected.");
    }

    @Given("Select ascending sorting by price from the product listing page.")
    public void select_ascending_sorting_by_price_from_the_product_listing_page() throws InterruptedException {
        homePage.clickAndSorting();
        Thread.sleep(500);
        homePage.assertionSort();
        logClass.info("On the product listing page, products are sorted in ascending order by price.");
    }

    @Given("Go inside the first product.")
    public void go_inside_the_first_product() {
        wait.until(ExpectedConditions.elementToBeClickable(homePage.getProductList().get(0)));
        homePage.getProductList().get(0).click();
        logClass.info("First product selected.");
    }

    @Given("Randomly select any of the size options from the product detail page.")
    public void randomly_select_any_of_the_size_options_from_the_product_detail_page() throws InterruptedException {
        homePage.selectRandomlySizeOptions();
        logClass.info("Any of the size options was randomly selected.");
    }

    @Given("On the delivery information page, add a new address and proceed.")
    public void on_the_delivery_information_page_add_a_new_address_and_proceed() throws InterruptedException {
        homePage.addANewAddressAndAssertion();
        logClass.info("Order information and address information entered.");

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
        assertEquals(Integer.parseInt(cartPage.getCartIcon().getText()), 0);
        logClass.info("Successfully exited");
    }

    @Given("Close the browser.")
    public void close_the_browser() {
        Driver.getDriver().close();
    }


}

