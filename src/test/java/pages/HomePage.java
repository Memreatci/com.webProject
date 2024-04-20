package pages;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import utilities.Driver;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

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
    @FindBy(xpath = "//*[@class='fancy-go-to-cart-button cl-big-button'] ")
    private WebElement cartAlert;
    @FindBy(xpath = "//*[@class='cl-product-card-checkbox-container']")
    private WebElement hediyePackCheckBox;
    @FindBy(id = "discountcouponcode")
    private WebElement indirimKodTextBox;
    @FindBy(xpath = "//*[@class='toast-message']")
    private WebElement errorMessage;
    @FindBy(className = "cl-input-button")
    private WebElement uygulaButton;
    @FindBy(id = "checkout")
    private WebElement satinAlButton;
    @FindBy(xpath = "(//*[@title='Close'])[2]")
    private WebElement smallCloseIcon;
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
    @FindBy(xpath = "//*[@class='pagenotfound-content']")
    private WebElement hataMesajText;
    @FindBy(id = "cancelBtn")
    private WebElement kapatButton;
    @FindBy(id = "_hjSafeContext_96454205")
    private WebElement iframeElement;
    @FindBy(xpath = "//*[@class='log-out']")
    private WebElement cikisYapLink;


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

    public WebElement getSatinAlButton() {
        return satinAlButton;
    }

    public WebElement getHediyePackCheckBox() {
        return hediyePackCheckBox;
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
        Thread.sleep(1500);
        Select select = new Select(sortingDropBox);
        select.selectByIndex(0);
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
                if (!(options.get(index).getText().contains("Haber") && index == 0)) {
                    break;
                }
            }
            System.out.println("index = " + index);
            select.selectByIndex(index);

        } catch (TimeoutException e) {
        }
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

    public void addANewAddress() throws InterruptedException {
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
        devamEtButton.click();
    }

    public void enterCardInfo() throws InterruptedException, IOException {
        kartSahibAdiText.sendKeys(faker.name().fullName());
        kartNumarasiText.sendKeys(faker.business().creditCardNumber());
        Thread.sleep(500);
        Select select = new Select(kartTarihAyDrop);
        select.selectByIndex(faker.random().nextInt(12) + 1);
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
            String alertText = Driver.getDriver().switchTo().alert().getText();
            System.out.println("alertText = " + alertText);
            Driver.getDriver().switchTo().alert().accept();
        } catch (Exception e) {

        }
        try {
            String frameText = getHataMesajText().getText();
            System.out.println("frameText = " + frameText);
            getKapatButton().click();
        } catch (Exception e) {
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
