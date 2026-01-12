package pages;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;


public class LoginPage extends BasePage {

    public LoginPage() {
        super(driver);
    }

    public void navigateToLoginPage() {
        navigateTo("https://www.saucedemo.com/");
    }

    public void rellenarFormulario() {

        String user = "standard_user";
        String password = "secret_sauce";

        write("//input[@id='user-name']", user);
        write("//input[@id='password']", password);
        clickElement("//input[@id='login-button']");


    }

    public void verificarInicioSesion() {
        assertTrue(elementIsDisplayed("//span[@data-test='title' and text()='Products']"));
    }

    public void iniciarSesion() {
        navigateToLoginPage();
        rellenarFormulario();
        verificarInicioSesion();
    }

}
