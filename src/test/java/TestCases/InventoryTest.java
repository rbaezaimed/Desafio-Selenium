package TestCases;

import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;
import pages.BasePage;
import pages.InventoryPage;
import pages.LoginPage;
import static org.testng.Assert.assertTrue;

import java.math.BigDecimal;
import java.util.List;

public class InventoryTest {

    @AfterClass
    public static void cleanDriver() {
        BasePage.killDriver();
    }

    @Test
    public void ordenamientoProductos() {
        LoginPage loginPage = new LoginPage();
        InventoryPage inventoryPage = new InventoryPage();

        String user = "standard_user";
        String password = "secret_sauce";

        // Login
        loginPage.iniciarSesion(user, password);

        //Lista
        inventoryPage.seleccionarOrdenamientoHighToLow();
        List<BigDecimal> precios = inventoryPage.obtenerPrecios();
        BigDecimal primerPrecio = precios.get(0);
        BigDecimal ultimoPrecio = precios.get(precios.size() - 1);

        assertTrue(primerPrecio.compareTo(ultimoPrecio) > 0);

    }
}
