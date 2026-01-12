package pages;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

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

        ChromeOptions options = new ChromeOptions();

        // Desactivar Password Manager, Leak Detection y Autofill
        Map<String, Object> prefs = new HashMap<>();
        prefs.put("credentials_enable_service", false);
        prefs.put("profile.password_manager_enabled", false);
        prefs.put("profile.password_leak_detection.enabled", false);
        prefs.put("autofill.profile_enabled", false);
        prefs.put("autofill.credit_card_enabled", false);
        options.setExperimentalOption("prefs", prefs);

        // Evitar burbujas y UI de credenciales
        options.addArguments("--incognito");
        options.addArguments("--disable-save-password-bubble");
        options.addArguments("--password-store=basic");
        options.addArguments("--disable-features=PasswordLeakDetection,PasswordManagerOnboarding,AutofillServerCommunication");
        options.addArguments("--no-first-run");
        options.addArguments("--no-default-browser-check");
        options.addArguments("--disable-notifications");
        options.addArguments("--disable-infobars");

        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
    }

    public BasePage(WebDriver driver) {
        BasePage.driver = driver;
    }

    public static void killDriver() {
        driver.quit();
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

    public void selectDropdownByValue(String selectXpath, String value) {
        WebElement selectEl = waitForVisible(selectXpath, Duration.ofSeconds(10));
        new Select(selectEl).selectByValue(value);
    }
}
