import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class WebDriverHeadlessTest {
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
        options.addArguments("--headless=new");
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
    }

    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    public void testTextInputInHeadlessMode() {
        driver.get(BASE_URL);

        var inputField = driver.findElement(By.id("textInput"));
        inputField.sendKeys("ОТУС");

        String actualText = inputField.getAttribute("value");
        Assertions.assertEquals("ОТУС", actualText,
                "Текст не соответствует введенному: ОТУС!");
    }
}