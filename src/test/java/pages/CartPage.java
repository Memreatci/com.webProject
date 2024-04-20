package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class CartPage extends BasePage{

    @FindBy(xpath = "//*[@class='cart-qty']")
    private WebElement cartIcon;
    @FindBy(xpath = "//*[@data-id='661fa55833318c54ba1ea415']")
    private WebElement delIcon;
    @FindBy(xpath = "//*[@onclick='acceptRemove()']")
    private WebElement silButton;
    @FindBy(xpath = "//*[@class='cl-input-plus-button']")
    private WebElement cartIncreaseIcon;

    public void clearTheCart() throws InterruptedException {
        cartIcon.click();

        while (true) {
            try {
                delIcon.click();
                silButton.click();
                Thread.sleep(1000);
            } catch (org.openqa.selenium.NoSuchElementException e) {
                System.out.println("Already cart is empty.");
                break;
            }
        }
    }
    public void goBackCartAndIncrease(int amount) {
        wait.until(ExpectedConditions.elementToBeClickable(cartIcon));
        cartIcon.click();
        for (int i = 0; i < amount; i++) {
            cartIncreaseIcon.click();
        }

    }
}
