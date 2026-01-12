package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.math.BigDecimal;
import java.time.Duration;
import java.util.List;
import java.util.stream.Collectors;

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

    public void seleccionarOrdenamientoHighToLow() {
        String dropdownXpath = "//*[@id=\"header_container\"]/div[2]/div/span/select";
        selectDropdownByValue(dropdownXpath, "hilo");
    }

    public List<BigDecimal> obtenerPrecios() {
        By priceLocator = By.cssSelector("[data-test='inventory-item-price']");
        List<WebElement> prices = new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfAllElementsLocatedBy(priceLocator));

        return prices.stream()
                .map(WebElement::getText)
                .map(String::trim)
                .map(t -> t.replace("$", "").replace(",", ""))
                .map(BigDecimal::new)
                .collect(Collectors.toList());
    }

}
