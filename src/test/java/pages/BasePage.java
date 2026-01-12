package pages;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class BasePage {

    protected static WebDriver driver;

    private WebDriverWait getWait(Duration timeout) {
        return new WebDriverWait(driver, timeout);
    }

    private WebElement findPresent(String xpath, Duration timeout) {
        return getWait(timeout).until(ExpectedConditions.presenceOfElementLocated(By.xpath(xpath)));
    }

    static {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    public BasePage(WebDriver driver) {
        BasePage.driver = driver;
    }

    protected WebElement waitForVisible(By locator, Duration timeout) {
        return getWait(timeout).until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    // Versión por XPath
    protected WebElement waitForVisible(String xpath, Duration timeout) {
        return waitForVisible(By.xpath(xpath), timeout);
    }

    public static void navigateTo(String url) {
        driver.get(url);
    }

    public void clickElement(String xpath) {
        // Asegura visibilidad antes de hacer clic
        waitForVisible(xpath, Duration.ofSeconds(10)).click();
    }

    public void write(String xpath, String keysToSend) {
        WebElement el = waitForVisible(xpath, Duration.ofSeconds(10));
        el.clear();
        el.sendKeys(keysToSend);
    }

    public boolean elementIsDisplayed(String xpath) {
        try {
            return waitForVisible(xpath, Duration.ofSeconds(10)).isDisplayed();
        } catch (TimeoutException e) {
            return false;
        }
    }
}
