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

public class FormSubmissionMaximizedTest {
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
        options.addArguments("--start-maximized");
        driver = new ChromeDriver(options);
    }

    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    public void testFormSubmissionInMaximizedMode() {
        driver.get(BASE_URL);

        String testName = "Сергей";
        String testEmail = "asdf@sdfg.rt";

        WebElement nameInput = driver.findElement(By.id("name"));
        WebElement emailInput = driver.findElement(By.id("email"));

        nameInput.sendKeys(testName);
        emailInput.sendKeys(testEmail);

        WebElement submitBtn = driver.findElement(By.xpath("//button[@type='submit']"));
        submitBtn.click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement successMessage = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.className("message"))
        );

        String expectedMessage = String.format(
                "Форма отправлена с именем: %s и email: %s", testName, testEmail
        );

        Assertions.assertEquals(expectedMessage, successMessage.getText(),
                "Сообщение об успешной отправке не соответствует ожидаемому");
    }
}