package TestCases;

import org.testng.annotations.Test;
import org.testng.annotations.AfterClass;
import pages.BasePage;
import pages.LoginPage;

import static org.testng.Assert.assertTrue;

public class LoginTest {

    @AfterClass
    public static void cleanDriver() {
        BasePage.killDriver();
    }

    @Test
    public void iniciarSesionIncorrecto() {
       LoginPage loginPage = new LoginPage();

       String user = "locked_out_user";
       String pass = "secret_sauce";

       loginPage.navigateToLoginPage();
       loginPage.iniciarSesion(user, pass);

       assertTrue(loginPage.elementIsDisplayed("//h3[@data-test='error' and contains(normalize-space(.), 'Epic sadface: Sorry, this user has been locked out.')]"));
    }



}
