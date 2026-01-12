package pages;

import static org.testng.Assert.assertTrue;

public class InventoryPage extends BasePage{

    public InventoryPage() {
        super(driver);
    }

    public void agregarPrimerProductoDeLista() {
        String xpathBotonAgregar = "//button[@id='add-to-cart-sauce-labs-backpack']";
        clickElement(xpathBotonAgregar);
    }

    public void clickBtnCarrito() {
        clickElement("//a[@class='shopping_cart_link']");
    }

    public void verificarProductoAgregadoEnCarrito(String nombreProducto) {
        String xpathProductoEnCarrito = String.format("//div[@data-test='inventory-item-name' and contains(normalize-space(.), '%s')]", nombreProducto);
        assertTrue(elementIsDisplayed(xpathProductoEnCarrito));
    }

}
