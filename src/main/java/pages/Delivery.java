package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import Utilities.ElementWait;
import Utilities.Conversions;

import java.util.List;

public class Delivery extends BasePage {



    public static final By deliveryButton = By.xpath("//*[@id=\"app\"]/div/div[3]/div/div[1]/div/div/div/button[4]");
    public static final By deliveryAddressInput = By.xpath("//input[@placeholder=\"Lieferadresse auswählen\"]");
    public static final By addressResults = By.xpath("//*[@id=\"results\"]/div[2]/*");
    public static final By saveAddressDeliveryButton = By.xpath("//*[@id=\"editDeliveryAddress-modal\"]/div[4]/button");
    public static final By moreInfoButton = By.xpath("//*[@id=\"heroButton\"]/span");
    public static final By sundayOpeningHours = By.xpath("//*[@id=\"venue-info-modal\"]/div[2]/div/div[2]/div/div[3]/div[8]");
    public static final By closeMoreInfoButton = By.xpath("//*[@id=\"venue-info-modal\"]/div[1]/button");
    public static final By restaurantMenu = By.xpath("//*[@id=\"menu\"]/div/div[1]/div/div[2]/*");
    public static final By price = By.xpath("//*[@id=\"focus-content\"]/div/div[2]/div[2]/div[1]/div[1]");
    public static final By increaseQuantityButton = By.xpath("//*[@id=\"focus-content\"]/div/div[2]/div[2]/div[2]/div/div[2]/div/button[2]");
    public static final By addButton = By.xpath("//*[@id=\"focus-content\"]/div/div[2]/div[4]/button");
    public static final By totalPrice = By.xpath("//*[@id=\"app\"]/div[1]/div[3]/div/div/div/div/div[1]/div[2]/aside/div[2]/div/div[5]/h3[2]");
    public static final By vat = By.xpath("//*[@id=\"app\"]/div[1]/div[3]/div/div/div/div/div[1]/div[2]/aside/div[2]/div/div[4]/div[2]/div[3]");


    ElementWait elementWait;

    private double itemPrice;
    private int quantity;

    private double totalPriceFromElement;

    public Delivery(WebDriver driver) {
        super(driver);
        this.elementWait = new ElementWait(driver);
    }

    public void openDelivery(){

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

    public void clickSaveAddressDeliveryButton(){

        elementWait.waitForElementToBeClickable(saveAddressDeliveryButton);
        driver.findElement(saveAddressDeliveryButton).click();
    }

    public void openMoreInfo(){

        elementWait.waitForElementToBeClickable(moreInfoButton);
        driver.findElement(moreInfoButton).click();
    }

    public void VerifySundayOpeningHours(){

        elementWait.waitForVisibilityOfElement(sundayOpeningHours);

        assert driver.findElement(sundayOpeningHours).getText().contains("Sonntag\n24h offen");
    }

    public void clickCloseMoreInfoButton(){

        elementWait.waitForElementToBeClickable(closeMoreInfoButton);
        driver.findElement(closeMoreInfoButton).click();
    }

    public void selectItemFromMenu(String item) {

        elementWait.waitForVisibilityOfElement(restaurantMenu);

        List<WebElement> elements;
        elements = driver.findElements(restaurantMenu);

        for (WebElement element : elements) {

            if (element.getText().contains(item)) {

                try{
                Thread.sleep(3000);
                elementWait.waitForElementToBeClickable(element);
                element.click();
                break;
                }
                catch (Exception e){
                    System.out.println("Can't find element: " + element);
                }
            }
        }

        itemPrice = Double.parseDouble(getItemPrice().substring(0, 6));
        System.out.println("Item price: "+ itemPrice);
    }

    public void increaseQuantity(int quantity){

        this.quantity = quantity;

        elementWait.waitForElementToBeClickable(increaseQuantityButton);

        for (int i = 0; i < quantity-1; i++) {

            driver.findElement(increaseQuantityButton).click();
        }
    }

    public void addOrder(){

        elementWait.waitForElementToBeClickable(addButton);
        driver.findElement(addButton).click();

        //TODO izbaciti u posebne metode:

        verifyOrderValue();

        verifyCalculatedVAT();
    }

    private void verifyOrderValue(){

        elementWait.waitForVisibilityOfElement(totalPrice);

        System.out.println("Total Price WEB:" + driver.findElement(totalPrice).getText());

        totalPriceFromElement = Conversions.convert(driver.findElement(totalPrice).getText().substring(0, 8));

        System.out.println("\ntotalPriceFromElementWEB: " + totalPriceFromElement + "\nCalculated: " + (itemPrice * this.quantity + "\n"));

        assert totalPriceFromElement == itemPrice * this.quantity;

    }

    private void verifyCalculatedVAT(){


        elementWait.waitForVisibilityOfElement(vat);

        double percentage_value = Double.parseDouble(driver.findElement(vat).getText().substring(0, 7));

        System.out.println("PERCENTAGE_VALUE: " + percentage_value);

        // VAT = TOTAL – (TOTAL / (1 + PERCENTAGE_VALUE/100))
        double vatValue = totalPriceFromElement - (totalPriceFromElement / (1 + percentage_value / 100.00));

        System.out.println("VAT Value: " + vatValue);

        //TODO assert vat value

    }

    private String getItemPrice(){

        elementWait.waitForVisibilityOfElement(price);

        return driver.findElement(price).getText();

    }
}

// TODO podeliti klasu na dve Delivery i Order