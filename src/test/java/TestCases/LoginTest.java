package TestCases;

import org.testng.annotations.Test;
import pages.LoginPage;

public class LoginTest {


    @Test
    public void iniciarSesionIncorrecto() {
       LoginPage loginPage = new LoginPage();

       String user = "locked_out_user";
       String pass = "secret_sauce";

       loginPage.navigateToLoginPage();
       loginPage.iniciarSesion(user, pass);
       loginPage.verficarErrorInicioSesion();
    }



}
