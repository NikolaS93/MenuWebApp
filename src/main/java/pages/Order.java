package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import Utilities.ElementWait;
import Utilities.Conversions;
import org.openqa.selenium.interactions.Actions;

import java.util.List;


public class Order extends BasePage{


    public static final By restaurantMenu = By.xpath("//*[@id=\"menu\"]/div/div[1]/div/div[2]/*");
    public static final By price = By.xpath("//*[@id=\"focus-content\"]/div/div[2]/div[2]/div[1]/div[1]");
    public static final By increaseQuantityButton = By.xpath("//*[@id=\"focus-content\"]/div/div[2]/div[2]/div[2]/div/div[2]/div/button[2]");
    public static final By addButton = By.xpath("//*[@id=\"focus-content\"]/div/div[2]/div[4]/button");
    public static final By totalPrice = By.xpath("//*[@id=\"app\"]/div[1]/div[3]/div/div/div/div/div[1]/div[2]/aside/div[2]/div/div[5]/h3[2]");
    public static final By vat = By.xpath("//*[@id=\"app\"]/div[1]/div[3]/div/div/div/div/div[1]/div[2]/aside/div[2]/div/div[4]/div[2]/div[3]");

    ElementWait elementWait;
    Actions actions;

    private double itemPrice;
    private int quantity;
    private double totalPriceFromElement;

    public Order(WebDriver driver) {
        super(driver);

        this.actions = new Actions(driver);
        this.elementWait = new ElementWait(driver);
    }

    public void selectItemFromMenu(String item) {

        elementWait.waitForVisibilityOfElement(restaurantMenu);

        List<WebElement> elements;
        elements = driver.findElements(restaurantMenu);

        for (WebElement element : elements) {

            if (element.getText().contains(item)) {

                elementWait.waitForElementToBeClickable(element);
                element.click();

                break;
            }
        }
        itemPrice = Double.parseDouble(getItemPrice().split(" ")[0]);
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

    }

    public void verifyOrderValue(){

        elementWait.waitForVisibilityOfElement(totalPrice);

        String text = driver.findElement(totalPrice).getText();

        totalPriceFromElement = Conversions.convert(text.split(" ")[0]);

        System.out.println("\ntotalPriceFromElementWEB: " + totalPriceFromElement + "\nCalculated: " +
                (itemPrice * this.quantity + "\n"));

        assert totalPriceFromElement == itemPrice * this.quantity;

    }

    public void verifyCalculatedVAT(){


        elementWait.waitForVisibilityOfElement(vat);

        double webVatValue = Double.parseDouble(driver.findElement(vat).getText().split(" ")[0]);

        System.out.println("VAT_VALUE_WEB: " + webVatValue);

        String vatPercentage = driver.findElement(Delivery.vatPercentage).getText().trim();
        double webVatPercentage = Double.parseDouble(vatPercentage.substring(1, vatPercentage.length()-2));

        // VAT = TOTAL – (TOTAL / (1 + PERCENTAGE_VALUE/100))
        double calculatedVatValue = totalPriceFromElement - (totalPriceFromElement / (1 + webVatPercentage / 100.00));

        System.out.println("Calculated VAT Value: " + calculatedVatValue);

        assert calculatedVatValue == webVatValue;

    }

    private String getItemPrice(){

        elementWait.waitForVisibilityOfElement(price);

        return driver.findElement(price).getText();

    }
}