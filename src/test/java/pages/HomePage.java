package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.Driver;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class HomePage {

    public HomePage() {
        PageFactory.initElements(Driver.getDriver(), this);
    }

    Actions actions = new Actions(Driver.getDriver());

    @FindBy(xpath = "//*[@id='onetrust-reject-all-handler']")
    public WebElement popupReject;
    @FindBy(xpath = "//*[@class='nav-button  cl-account-button']")
    private WebElement accountIcon;
    @FindBy(xpath = "//*[@id='Email']")
    private WebElement mailTextBox;
    @FindBy(xpath = "//*[@id='Password']")
    private WebElement passwordTextBox;
    @FindBy(xpath = "//*[@class='login-button']")
    private WebElement girisYapButton;
    @FindBy(xpath = "//*[@class='cart-qty']")
    private WebElement cartIcon;
    @FindBy(xpath = "//*[@class='cl-product-card-button deleteshoppingcartitem']")
    private WebElement delIcon;
    @FindBy(xpath = "//*[@onclick='acceptRemove()']")
    private WebElement silButton;
    @FindBy(xpath = "//*[@class='nav-menu']/li")
    private List<WebElement> collections;

    public WebElement getAccountIcon() {
        return accountIcon;
    }


    public void login(String mail, String password) {
        accountIcon.isDisplayed();
        accountIcon.click();
        actions
                .click(mailTextBox)
                .sendKeys(mail)
                .click(passwordTextBox)
                .sendKeys(password)
                .click(girisYapButton)
                .perform();

    }

    public void clearTheCart() throws InterruptedException {
        cartIcon.click();

        while (true) {
            try {
                delIcon.click();
                silButton.click();
            } catch (org.openqa.selenium.NoSuchElementException e) {
                System.out.println("Already cart is empty.");
                break;
            }
        }
    }

    public void clickRandom() {

        List<WebElement> threeChoose = new ArrayList<>();

        for (WebElement chosen : collections) {
            String text = chosen.getText();
            if ("Jean".equals(text) || "Kadın".equals(text) || "Erkek".equals(text)) {
                threeChoose.add(chosen);
            }
        }

        Random random = new Random();
        int randomIndex = random.nextInt(threeChoose.size());
        threeChoose.get(randomIndex).click();
    }
}
