package pages;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import utilities.Driver;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CartPage extends BasePage {

    @FindBy(xpath = "//*[@class='cart-qty']")
    private WebElement cartIcon;
    @FindBy(xpath = "//*[@onclick='deleteItemClick($(this))']")
    private WebElement delIcon;
    @FindBy(xpath = "//*[@onclick='acceptRemove()']")
    private WebElement silButton;
    @FindBy(xpath = "//*[@class='cl-input-plus-button']")
    private WebElement cartIncreaseIcon;
    @FindBy(xpath = "//*[@class='cl-empty-cart-container']")
    private WebElement cartPageEmptyIcon;
    @FindBy(xpath = "//*[@class='cl-product-card-checkbox-container']")
    private WebElement hediyePackCheckBox;
    @FindBy(xpath = "//*[@type='checkbox']")
    private WebElement assertCheckBox;
    @FindBy(id = "discountcouponcode")
    private WebElement indirimKodTextBox;
    @FindBy(className = "cl-input-button")
    private WebElement uygulaButton;
    @FindBy(id = "checkout")
    private WebElement satinAlButton;
    @FindBy(xpath = "//*[@class='toast-message']")
    private WebElement errorMessage;
    @FindBy(xpath = "//*[@class='add-to-cart-button cl-big-button']")
    private WebElement sepeteEkleButton;
    @FindBy(xpath = "//*[@class='cl-product-quantity-input-container']/input")
    private WebElement productQuantity;
    @FindBy(xpath = "//*[@class='fancy-go-to-cart-button cl-big-button'] ")
    private WebElement cartAlert;

    public WebElement getCartIcon() {
        return cartIcon;
    }

    public WebElement getProductQuantity() {
        return productQuantity;
    }

    public WebElement getSatinAlButton() {
        return satinAlButton;
    }

    public WebElement getAssertCheckBox() {
        return assertCheckBox;
    }

    public WebElement getHediyePackCheckBox() {
        return hediyePackCheckBox;
    }

    public WebElement getCartPageEmptyIcon() {
        return cartPageEmptyIcon;
    }

    public void addAndGoCart() throws InterruptedException {

        js.executeScript("arguments[0].scrollIntoView();", sepeteEkleButton);
        sepeteEkleButton.click();
        Thread.sleep(1000);
        cartAlert.click();
        Thread.sleep(1500);
    }

    public void enterPromotion() throws IOException {
        wait.until(ExpectedConditions.elementToBeClickable(indirimKodTextBox));
        indirimKodTextBox.sendKeys(faker.code().imei());
        uygulaButton.click();
        wait.until(ExpectedConditions.elementToBeClickable(errorMessage));
        getScreenshot("warningMessage");
        System.out.println("errorMessage = " + errorMessage.getText());

    }

    public void clearTheCart() throws InterruptedException {
        cartIcon.click();

        while (true) {
            try {
                delIcon.click();
                silButton.click();
                Thread.sleep(500);
            } catch (org.openqa.selenium.NoSuchElementException e) {
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

    public static String getScreenshot(String name) throws IOException {
        String date = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
        TakesScreenshot ts = (TakesScreenshot) Driver.getDriver();
        File source = ts.getScreenshotAs(OutputType.FILE);
        String target = System.getProperty("user.dir") + "/target/Screenshots/" + name + date + ".png";
        File finalDestination = new File(target);
        FileUtils.copyFile(source, finalDestination);
        return target;
    }
}
