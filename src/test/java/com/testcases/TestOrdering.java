package com.testcases;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import pages.BaseTest;
import pages.Homepage;
import pages.Delivery;

public class TestOrdering extends BaseTest {

    public String DeliveryAddress = "Železnička 7, Novi Sad";

    public Homepage homepage;
    public Delivery delivery;

    @BeforeClass
    public void setUpClass(){

        initialization();
        homepage = new Homepage(getDriver());
        delivery = new Delivery(getDriver());
    }

    @Test
    public void addDeliveryAddress(){

        homepage.checkModalDialogPresence();
        homepage.checkLanguage();

//        delivery.openDelivery();
//        delivery.selectDeliveryAddress(DeliveryAddress);
//        delivery.clickSaveAddressDeliveryButton();
//        delivery.openMoreInfo();
//        delivery.VerifySundayOpeningHours();
//        delivery.clickCloseMoreInfoButton();
//        delivery.selectItemFromMenu("Quesadilla");
//        delivery.increaseQuantity(11);
//        delivery.addOrder();


    }
}
