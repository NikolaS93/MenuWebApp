package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;


import Utilities.ElementWait;


public class Homepage extends BasePage {

    public static final By closeButton = By.xpath("//*[@id=\"app\"]/div[3]/div/div/div/div[1]/button/span");

    public static final By languageButton = By.id("languageButton");
    public static final By saveButton = By.xpath("//*[@id=\"language-modal\"]/form/button");
    public static final By allLanguages = By.xpath("//*[@id=\"language-modal\"]/form/div[2]/*");
    public static final By englishButton = By.xpath("//*[@id=\"language-modal\"]/form/div[2]/div[3]/button/span/h4");


    ElementWait elementWait;

    public Homepage(WebDriver driver) {
        super(driver);
        this.elementWait = new ElementWait(driver);
    }

    public void checkModalDialogPresence() {

        try {
            if (driver.findElement(closeButton).isDisplayed()) {
                elementWait.waitForElementToBeClickable(closeButton);
                driver.findElement(closeButton).click();
            }
        }
        catch (Exception e){
            System.out.println("ModalDialog not found" + e);
        }
    }

    public void setLanguageToEng() {

        String language = driver.findElement(By.tagName("html")).getAttribute("lang");

        if (language.equals("en")) return;

        JavascriptExecutor js = (JavascriptExecutor) driver;

        int i = 0;
        while(!language.equals("en") || i > 3){

            elementWait.waitForElementToBeClickable(languageButton);
            js.executeScript("document.getElementById('languageButton').click();");

            elementWait.waitForElementToBeClickable(englishButton);
            driver.findElement(englishButton).click();

            elementWait.waitForElementToBeClickable(saveButton);
            driver.findElement(saveButton).click();

            language = driver.findElement(By.tagName("html")).getAttribute("lang");

            i= i+1;
        }
    }
}
