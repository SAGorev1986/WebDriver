
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.time.Duration;

public class WebDriver_1_headless_mode {
    WebDriver driver;

    @BeforeAll
    public static void install() {
        WebDriverManager.chromedriver().setup();
    }

    @BeforeEach
    public void startUp() {
//Открыть Chrome в headless режиме
        ChromeOptions options = new ChromeOptions();
        options.addArguments("headless");
        driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    @AfterEach
    public void close() {
        if (driver != null) {
            driver.close();
        }
    }
    @Test
    public void testHeadlessMode(){
        //Перейти на ресурс
        driver.get("https://otus.home.kartushin.su/training.html");
        //В поле ввода текста ввести ОТУС
        var inputField = driver.findElement(By.id("textInput"));
        inputField.sendKeys("ОТУС");
        //Проверить, что текст соответствует введенному
        String actualText = inputField.getAttribute("value");
        //System.out.println(actualText);
        Assertions.assertEquals("ОТУС1",actualText,"Текст не соответствует введеному: ОТУС!");
    }

}