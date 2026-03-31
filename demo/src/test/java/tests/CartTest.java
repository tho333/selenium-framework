package tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import base.BaseTest;
import pages.CartPage;
import pages.LoginPage;

public class CartTest extends BaseTest {

    @Test
    public void addItemToCart() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(core.CredentialManager.getUsername(), core.CredentialManager.getPassword());

        CartPage cartPage = new CartPage(driver);
        cartPage.addToCart();

        Assert.assertEquals(cartPage.getCartBadgeText(), "1");
    }
}
