package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import Utilities.ElementWait;
import org.openqa.selenium.interactions.Actions;

import java.util.List;

public class Delivery extends BasePage {

    public static final By deliveryButton = By.xpath("//*[@id=\"app\"]/div/div[3]/div/div[1]/div/div/div/button[4]");
    public static final By deliveryAddressInput = By.xpath("//input[@placeholder=\"Select delivery address\"]");
    public static final By addressResults = By.xpath("//*[@id=\"results\"]/div[2]/*");
    public static final By saveAddressDeliveryButton = By.xpath("//*[@id=\"editDeliveryAddress-modal\"]/div[4]/button");
    public static final By moreInfoButton = By.xpath("//*[@id=\"heroButton\"]/span");
    public static final By sundayOpeningHours = By.xpath("//*[@id=\"venue-info-modal\"]/div[2]/div/div[2]/div/div[3]/div[8]");
    public static final By closeMoreInfoButton = By.xpath("//*[@id=\"venue-info-modal\"]/div[1]/button");
    public static final By vatPercentage = By.xpath("//*[@id=\"app\"]/div[1]/div[3]/div/div/div/div/div[1]/div[2]/aside/div[2]/div/div[4]/div[2]/div[1]/span");

    ElementWait elementWait;
    Actions actions;

    public Delivery(WebDriver driver) {
        super(driver);
        this.elementWait = new ElementWait(driver);
        this.actions = new Actions(driver);
    }

    public void openDelivery() {

        elementWait.waitForElementToBeClickable(deliveryButton);
        driver.findElement(deliveryButton).click();
    }

    public void selectDeliveryAddress(String address) {

        elementWait.waitForVisibilityOfElement(deliveryAddressInput);
        driver.findElement(deliveryAddressInput).sendKeys(address);

        elementWait.waitForVisibilityOfElement(addressResults);

        List<WebElement> results;
        results = driver.findElements(addressResults);

        for (WebElement element : results) {
            if (element.getText().contains(address)) {
                element.click();
                break;
            }
        }
    }

    public void clickSaveAddressDeliveryButton() {

        elementWait.waitForElementToBeClickable(saveAddressDeliveryButton);
        driver.findElement(saveAddressDeliveryButton).click();
    }

    public void openMoreInfo() {

        elementWait.waitForElementToBeClickable(moreInfoButton);
        driver.findElement(moreInfoButton).click();
    }

    public void VerifySundayOpeningHours() throws InterruptedException {

        Thread.sleep(3000);

        WebElement elementHours = driver.findElement(sundayOpeningHours);
        actions.moveToElement(elementHours);
        actions.perform();

        elementWait.waitForVisibilityOfElement(sundayOpeningHours);

        assert driver.findElement(sundayOpeningHours).getText().contains("Sunday\nOpen 24h");
    }

    public void clickCloseMoreInfoButton() {

        elementWait.waitForElementToBeClickable(closeMoreInfoButton);
        driver.findElement(closeMoreInfoButton).click();
    }

}