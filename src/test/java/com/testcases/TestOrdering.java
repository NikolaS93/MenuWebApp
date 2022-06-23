package com.testcases;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import pages.BaseTest;
import pages.Homepage;
import pages.Delivery;
import pages.Order;

public class TestOrdering extends BaseTest {

    public String DeliveryAddress = "Železnička 7, Novi Sad";

    public Homepage homepage;
    public Delivery delivery;
    public Order order;

    @BeforeClass
    public void setUpClass(){

        initialization();
        WebDriver driver = getDriver();

        homepage = new Homepage(driver);
        delivery = new Delivery(driver);
        order = new Order(driver);
    }

    @Test
    public void addDeliveryAddress() throws InterruptedException {

        homepage.checkModalDialogPresence();
        homepage.checkLanguage();

        delivery.openDelivery();
        delivery.selectDeliveryAddress(DeliveryAddress);
        delivery.clickSaveAddressDeliveryButton();
        delivery.openMoreInfo();
        delivery.VerifySundayOpeningHours();
        delivery.clickCloseMoreInfoButton();

        order.selectItemFromMenu("Quesadilla");
        order.increaseQuantity(11);
        order.addOrder();

    }
}
