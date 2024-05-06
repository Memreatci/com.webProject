package pages;

import org.apache.commons.io.FileUtils;
import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import utilities.Driver;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

import static pages.CartPage.getScreenshot;

public class HomePage extends BasePage {

    @FindBy(xpath = "//*[@id='onetrust-reject-all-handler']")
    public WebElement popupReject;
    @FindBy(xpath = "//*[@class='nav-button  cl-account-button']")
    private WebElement accountIcon;
    @FindBy(xpath = "//*[@class='nav-menu']/li")
    private List<WebElement> collections;
    @FindBy(id = "products-orderby")
    private WebElement sortingDropBox;
    @FindBy(xpath = "//*[@id='cl-product-grid']/div")
    private List<WebElement> productList;
    @FindBy(id = "size-select")
    private WebElement sizeOptions;
    @FindBy(xpath = "//*[@class='add-to-cart-button cl-big-button']")
    private WebElement sepeteEkleButton;

    @FindBy(xpath = "(//*[@title='Close'])[2]")
    private WebElement smallCloseIcon;
    @FindBy(xpath = "//*[@class='cl-checkout-tab-section-header']/p")
    private WebElement newAddressName;
    @FindBy(xpath = "//*[@class='add-new-address-button']")
    private WebElement addNewAddress;
    @FindBy(xpath = "(//*[@id='address_attribute_5eae2115f6f875754ba14134'])[2]")
    private WebElement addressKolayAd;
    @FindBy(xpath = "//*[@id='ShippingNewAddress_CountryId']")
    private WebElement addressIlDropDown;
    @FindBy(name = "ShippingNewAddress.StateProvinceId")
    private WebElement addressIlceDropDown;
    @FindBy(name = "ShippingNewAddress.NeighborhoodId")
    private WebElement addressMahalleDropDown;
    @FindBy(id = "ShippingNewAddress_Address1")
    private WebElement addressTextBox;
    @FindBy(id = "ShippingNewAddress_Address2")
    private WebElement address2TextBox;
    @FindBy(id = "ShippingNewAddress_ZipPostalCode")
    private WebElement addressPostCode;
    @FindBy(xpath = "(//*[@value='Kaydet'])[2]")
    private WebElement addressKaydetButton;
    @FindBy(xpath = "//*[@onclick='Shipping.save()']")
    private WebElement devamEtButton;
    @FindBy(id = "CardholderName")
    private WebElement kartSahibAdiText;
    @FindBy(id = "CardNumber")
    private WebElement kartNumarasiText;
    @FindBy(id = "ExpireMonth")
    private WebElement kartTarihAyDrop;
    @FindBy(id = "ExpireYear")
    private WebElement kartTarihYilDrop;
    @FindBy(id = "CardCode")
    private WebElement kartCCVNumText;
    @FindBy(xpath = "(//*[@class='cl-checkmark'])[3]")
    private WebElement onBilgCheckBox;
    @FindBy(id = "completeOrderButton")
    private WebElement siparisiTamamlaButton;
    @FindBy(xpath = "//*[@class='page text-center not-found-page cl-not-found-page']/div")
    private WebElement hataMesajText;
    @FindBy(id = "cancelBtn")
    private WebElement kapatButton;
    @FindBy(id = "treediframe")
    private WebElement iframeElement;
    @FindBy(xpath = "//*[@class='log-out']")
    private WebElement cikisYapLink;
    @FindBy(xpath = "//*[@class='cl-product-price']")
    private List<WebElement> productPrice;

    public WebElement getCikisYapLink() {
        return cikisYapLink;
    }

    public WebElement getIframeElement() {
        return iframeElement;
    }

    public WebElement getKapatButton() {
        return kapatButton;
    }

    public WebElement getHataMesajText() {
        return hataMesajText;
    }

    public List<WebElement> getProductList() {
        return productList;
    }

    public WebElement getAccountIcon() {
        return accountIcon;
    }

    public void clickRandom() {

        List<WebElement> threeChoose = new ArrayList<>();

        for (WebElement chosen : collections) {
            String text = chosen.getText();
            if ("Jean".equals(text) || "Kadın".equals(text) || "Erkek".equals(text)) {
                threeChoose.add(chosen);
            }
        }
        int randomIndex = random.nextInt(threeChoose.size());
        threeChoose.get(randomIndex).click();
    }

    public void clickAndSorting() throws InterruptedException {
        wait.until(ExpectedConditions.elementToBeClickable(sortingDropBox));
        sortingDropBox.click();
        Thread.sleep(500);
        Select select = new Select(sortingDropBox);
        select.selectByValue("10");
    }

    public void selectRandomlySizeOptions() throws InterruptedException {
        try {
            wait.until(ExpectedConditions.elementToBeClickable(sizeOptions));
            actions.moveToElement(sizeOptions).click().perform();
            Thread.sleep(2000);
            Select select = new Select(sizeOptions);
            List<WebElement> options = select.getOptions();
            Thread.sleep(1000);

            int index = 0;
            for (int i = 0; i < options.size(); i++) {
                index = random.nextInt(options.size());
                if (!(options.get(index).getText().contains("Haber") || index == 0)) {
                    break;
                } else {

                }
            }
            System.out.println("index = " + index);
            select.selectByIndex(index);

        } catch (TimeoutException e) {
        }
    }


    public void addANewAddressAndAssertion() throws InterruptedException {
        smallCloseIcon.click();
        wait.until(ExpectedConditions.elementToBeClickable(addNewAddress)).click();
        wait.until(ExpectedConditions.elementToBeClickable(addressKolayAd)).sendKeys(faker.name().fullName());
        Thread.sleep(500);
        Select select = new Select(addressIlDropDown);
        select.selectByVisibleText("İZMİR");
        Thread.sleep(500);
        select = new Select(addressIlceDropDown);
        select.selectByVisibleText("BORNOVA");
        Thread.sleep(500);
        select = new Select(addressMahalleDropDown);
        select.selectByVisibleText("MEVLANA MAH");
        Thread.sleep(500);
        addressTextBox.sendKeys(faker.address().fullAddress());
        address2TextBox.sendKeys(faker.address().fullAddress());
        addressPostCode.sendKeys(faker.address().zipCode());
        Thread.sleep(500);
        addressKaydetButton.click();
        Thread.sleep(500);
        Assert.assertTrue(newAddressName.isDisplayed());
        devamEtButton.click();
    }

    public void enterCardInfo() throws InterruptedException, IOException {
        kartSahibAdiText.sendKeys(faker.name().fullName());
        kartNumarasiText.sendKeys(faker.business().creditCardNumber());
        Thread.sleep(500);
        Select select = new Select(kartTarihAyDrop);
        select.selectByIndex(faker.random().nextInt(12));
        Thread.sleep(500);
        select = new Select(kartTarihYilDrop);
        int randomYear = ThreadLocalRandom.current().nextInt(2024, 2039);
        select.selectByVisibleText(String.valueOf(randomYear));
        Thread.sleep(500);
        int randomCVV = ThreadLocalRandom.current().nextInt(100, 1000);
        kartCCVNumText.sendKeys(String.valueOf(randomCVV));
        onBilgCheckBox.click();
        actions.moveToElement(siparisiTamamlaButton).click().perform();
        Thread.sleep(3000);


        try {
            boolean alertHandled = false;
            try {
                String alertText = Driver.getDriver().switchTo().alert().getText();
                System.out.println("Error Message = " + alertText);
                Driver.getDriver().switchTo().alert().accept();
                alertHandled = true;
            } catch (NoAlertPresentException e) {
                //if there are no warnings, do nothing
            }
            if (!alertHandled) {
                try {
                    Driver.getDriver().switchTo().frame(iframeElement);
                    String frameText = getHataMesajText().getText();
                    Thread.sleep(500);
                    System.out.println("Error Message = " + frameText);
                    getScreenshot("Warning Message (3)  ");
                    getKapatButton().click();
                    Driver.getDriver().switchTo().parentFrame();
                    Thread.sleep(500);
                } catch (Exception e) {
                }
            }
        } catch (Exception e) {
        }
    }

    public void assertionSort() {

        List<Double> prodPriceList = new ArrayList<>();
        for (int i = 0; i < productPrice.size(); i++) {
            prodPriceList.add(Double.valueOf(productPrice.get(i).getText().replaceAll("[^\\d,]", "").replace(",", ".")));
        }
        for (int i = 1; i < prodPriceList.size(); i++) {
            Assert.assertTrue(prodPriceList.get(i - 1) <= prodPriceList.get(i));
        }
    }

}
