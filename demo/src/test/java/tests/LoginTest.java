package tests;

import org.testng.annotations.Test;

import base.BaseTest;
import pages.LoginPage;

public class LoginTest extends BaseTest {

    @Test
    public void successfulLogin() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(core.CredentialManager.getUsername(), core.CredentialManager.getPassword());
        // Add assertions here to verify successful login
    }
}
