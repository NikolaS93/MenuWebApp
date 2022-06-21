package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import Utilities.ElementWait;
import java.util.List;

public class Homepage extends BasePage {

    public static final By closeButton = By.xpath("//*[@id=\"app\"]/div[3]/div/div/div/div[1]/button/span");
    //public static final By languageButton = By.id("languageButton");
    //public static final By languageButton = By.xpath("//*[@id=\"languageButton\"]/span");
    public static final By languageButton = By.cssSelector("#languageButton");

    public static final By languages = By.className("//*[@id=\"language-modal\"]/form/div[2]");

    public static final By mainFooter = By.xpath("//*[@id=\"main-footer\"]/*");

    ElementWait elementWait;

    public Homepage(WebDriver driver) {
        super(driver);
        this.elementWait = new ElementWait(driver);
    }

    public void checkModalDialogPresence() {

        try{
            if (driver.findElement(closeButton).isDisplayed()) {
                elementWait.waitForElementToBeClickable(closeButton);
                driver.findElement(closeButton).click();
            }
        }
        catch (Exception e){
            System.out.println("Modal dialog not visible" + e);
        }
    }

    public void checkLanguage(){

        //TODO click language button

            String language = driver.findElement(By.tagName("html")).getAttribute("lang");
            if (!language.equals("en")) {

               elementWait.waitForElementToBeClickable(languageButton);

//                driver.findElement(languageButton).click();
//
//                System.out.println(driver.findElements(languages));

//                elementWait.waitForVisibilityOfElement(mainFooter);
//
//                List <WebElement> mainFooterElements;
//                WebElement languageButton;
//
//                mainFooterElements = driver.findElements(mainFooter);
//
//                languageButton = mainFooterElements.get(0);
//                elementWait.waitForVisibilityOfElement(languageButton);
//                languageButton.click();

                JavascriptExecutor js = (JavascriptExecutor)driver;
                js.executeScript("arguments[0].click();", languageButton);

            }
    }
}
