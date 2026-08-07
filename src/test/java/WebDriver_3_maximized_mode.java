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

public class WebDriver_3_maximized_mode {
    WebDriver driver;

    @BeforeAll
    public static void install() {
        WebDriverManager.chromedriver().setup();
    }

    @BeforeEach
    public void startUp() {
//Открыть Chrome в режиме полного экрана
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized");
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
    public void testMaximizedMode() throws InterruptedException{
        //Перейти на ресурс
        driver.get("https://otus.home.kartushin.su/training.html");
        Thread.sleep(2000);
        // Тестовые данные
        String testName = "Сергей";
        String testEmail = "asdf@sdfg.rt";

        WebElement nameInput = driver.findElement(By.id("name"));
        WebElement emailInput = driver.findElement(By.id("email"));
        //В форму ввести имя и почту, нажать «Отправить»
        nameInput.sendKeys(testName);
        Thread.sleep(2000);
        emailInput.sendKeys(testEmail);
        Thread.sleep(2000);
        WebElement submitBtn = driver.findElement(By.xpath("//button[@type='submit']"));
        submitBtn.click();
        Thread.sleep(2000);
        //В поле динамическое сообщение (на зеленом фоне) появится сообщение в формате: «Форма отправлена с именем: фыв и email: asdf@sdfg.rt».
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        WebElement succesMess = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("message")));
        System.out.println(succesMess.getText());
    }

}