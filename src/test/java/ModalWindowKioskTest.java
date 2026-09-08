import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ModalWindowKioskTest {
    WebDriver driver;

    private static final String BASE_URL = System.getProperty("test.url",
            "https://otus.home.kartushin.su/training.html");

    @BeforeAll
    public static void setupClass() {
        WebDriverManager.chromedriver().setup();
    }

    @BeforeEach
    public void setUp() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--kiosk");
        driver = new ChromeDriver(options);
    }

    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    public void testModalWindowInKioskMode() {
        driver.get(BASE_URL);

        WebElement openModalBtn = driver.findElement(By.id("openModalBtn"));
        openModalBtn.click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement modalWindow = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.className("modal-content"))
        );

        Assertions.assertTrue(modalWindow.isDisplayed(),
                "Модальное окно должно быть отображено");

        String modalText = modalWindow.getText();
        System.out.println("Текст модального окна: " + modalText);
    }
}