package tests;

import org.testng.annotations.Test;

import base.BaseTest;
import pages.LoginPage;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;

@Epic("Authentication")
@Feature("Login")
public class LoginTest extends BaseTest {

    @Test
    @Story("Successful Login")
    @Description("This test attempts to log in with valid credentials and verifies successful login.")
    public void successfulLogin() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(core.CredentialManager.getUsername(), core.CredentialManager.getPassword());
        // Add assertions here to verify successful login
    }
}
