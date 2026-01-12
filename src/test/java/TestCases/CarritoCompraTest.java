package TestCases;

import org.testng.annotations.Test;
import pages.InventoryPage;
import pages.LoginPage;

public class CarritoCompraTest {

    @Test
    public void agregarProductoAlCarrito() {
        LoginPage loginPage = new LoginPage();
        InventoryPage inventoryPage = new InventoryPage();

        loginPage.iniciarSesion();
        inventoryPage.agregarPrimerProductoDeLista();
        inventoryPage.clickBtnCarrito();
        inventoryPage.verificarProductoAgregadoEnCarrito("Sauce Labs Backpack");
    }
}
