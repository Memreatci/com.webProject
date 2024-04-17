package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class LoginPage extends BasePage{

    @FindBy(xpath = "//*[@id='Email']")
    private WebElement mailTextBox;
    @FindBy(xpath = "//*[@id='Password']")
    private WebElement passwordTextBox;
    @FindBy(xpath = "//*[@class='login-button']")
    private WebElement girisYapButton;

    public void login(String mail, String password) {
        homePage.getAccountIcon().isDisplayed();
        homePage.getAccountIcon().click();
        wait.until(ExpectedConditions.elementToBeClickable(mailTextBox));
        actions
                .click(mailTextBox)
                .sendKeys(mail)
                .click(passwordTextBox)
                .sendKeys(password)
                .click(girisYapButton)
                .perform();

    }
}
