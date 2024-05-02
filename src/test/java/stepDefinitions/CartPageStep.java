package stepDefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.sl.In;
import org.junit.Assert;
import org.openqa.selenium.support.ui.ExpectedConditions;
import utilities.Driver;

import java.io.IOException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;


public class CartPageStep extends BaseStep {

    @Given("The user goes to the cart and checks it.")
    public void the_user_goes_to_the_cart_and_checks_it() throws InterruptedException {
        cartPage.clearTheCart();
        //  wait.until(ExpectedConditions.elementToBeClickable(cartPage.getCartPageEmptyIcon()));
        assertTrue("Warning ! Cart is not empty", cartPage.getCartPageEmptyIcon().isDisplayed());
        logClass.info("Success Cart is empty");
    }

    @Given("Randomly add the selected size to the cart and go to cart.")
    public void randomly_add_the_selected_size_to_the_cart_and_go_to_cart() throws InterruptedException {
        cartPage.addAndGoCart();
        assertTrue(Driver.getDriver().getCurrentUrl().contains("cart"));
    }

    @Given("Click on the Hediye Paketi Istiyorum.")
    public void click_on_the_hediye_paketi_istiyorum() throws InterruptedException {
        cartPage.getHediyePackCheckBox().click();
        Thread.sleep(500);
        assertTrue(cartPage.getAssertCheckBox().isSelected());
    }

    @Given("Enter a random code in the promo code field")
    public void enter_a_random_code_in_the_promo_code_field() throws IOException {
        cartPage.enterPromotion();
        logClass.info("A random promo code was entered.");
    }

    @Given("Press the buy button and continue")
    public void press_the_buy_button_and_continue() {
        cartPage.getSatinAlButton().click();
    }

    @Given("Go back to the cart and increase the quantity by {int}.")
    public void go_back_to_the_cart_and_increase_the_quantity_by(Integer int1) {
        cartPage.goBackCartAndIncrease(int1);
        assertEquals(Integer.parseInt(cartPage.getProductQuantity().getAttribute("value")), 2);
        logClass.info("Product quantity increased.");
    }
}
