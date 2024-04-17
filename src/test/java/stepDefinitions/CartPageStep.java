package stepDefinitions;

import io.cucumber.java.en.Given;
import pages.BasePage;

public class CartPageStep extends BasePage {


    @Given("The user goes to the cart and checks it.")
    public void the_user_goes_to_the_cart_and_checks_it() throws InterruptedException {
        cartPage.clearTheCart();
    }

    @Given("Randomly add the selected size to the cart and go to cart.")
    public void randomly_add_the_selected_size_to_the_cart_and_go_to_cart() throws InterruptedException {
        homePage.addAndGoCart();
    }

    @Given("Go back to the cart and increase the quantity by {int}.")
    public void go_back_to_the_cart_and_increase_the_quantity_by(Integer int1) {
        cartPage.goBackCartAndIncrease(int1);
    }
}
