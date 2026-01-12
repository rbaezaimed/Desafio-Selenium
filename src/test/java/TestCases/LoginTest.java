package TestCases;

import org.testng.annotations.Test;
import pages.LoginPage;

public class LoginTest {

    @Test
    public void iniciarSesion() {
       LoginPage loginPage = new LoginPage();
       loginPage.navigateToLoginPage();
       loginPage.iniciarSesion();
       loginPage.verificarInicioSesion();
    }

}
