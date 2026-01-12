package TestCases;

import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;
import pages.BasePage;
import pages.InventoryPage;
import pages.LoginPage;
import static org.testng.Assert.assertTrue;


public class FlujoCompraTest {

    @AfterClass
    public static void cleanDriver() {
        BasePage.killDriver();
    }

    @Test
    public void flujoCompletoDeCompra() throws InterruptedException {
        LoginPage loginPage = new LoginPage();
        InventoryPage inventoryPage = new InventoryPage();

        String user = "standard_user";
        String pass = "secret_sauce";
        String firstName = "Nombre Test";
        String lastName = "Apellido Test";
        String postalCode = "12345";

        loginPage.iniciarSesion(user, pass);
        inventoryPage.agregarPrimerProductoDeLista();
        inventoryPage.clickBtnCarrito();
        inventoryPage.clickBtnCheckout();
        inventoryPage.rellenarFormularioYourInformation(firstName, lastName, postalCode);
        inventoryPage.clickBtnContinue();
        inventoryPage.clickBtnFinish();

        String xpathMensajeFinal = "//h2[normalize-space()='Thank you for your order!']";

        assertTrue(inventoryPage.elementIsDisplayed(xpathMensajeFinal), "La compra no se completó correctamente.");
    }
}
