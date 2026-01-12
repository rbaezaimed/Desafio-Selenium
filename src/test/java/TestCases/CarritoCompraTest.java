package TestCases;

import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;
import pages.BasePage;
import pages.InventoryPage;
import pages.LoginPage;

import static org.testng.Assert.assertTrue;

public class CarritoCompraTest {

    @AfterClass
    public static void cleanDriver() {
        BasePage.killDriver();
    }

    @Test
    public void agregarProductoAlCarrito() {
        LoginPage loginPage = new LoginPage();
        InventoryPage inventoryPage = new InventoryPage();

        String user = "standard_user";
        String password = "secret_sauce";

        loginPage.iniciarSesion(user, password);
        loginPage.verificarInicioSesion();
        inventoryPage.agregarPrimerProductoDeLista();
        inventoryPage.clickBtnCarrito();

        String xpathProductoEnCarrito = String.format("//div[@data-test='inventory-item-name' and contains(normalize-space(.), '%s')]", "Sauce Labs Backpack");
        assertTrue(inventoryPage.elementIsDisplayed(xpathProductoEnCarrito));
    }
}
