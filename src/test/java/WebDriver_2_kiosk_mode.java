import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.lang3.exception.ExceptionContext;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class WebDriver_2_kiosk_mode {
    WebDriver driver;

    @BeforeAll
    public static void install() {
        WebDriverManager.chromedriver().setup();
    }

    @BeforeEach
    public void startUp() {
//Открыть Chrome в режиме киоска
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--kiosk");
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
    public void testKioskMode() throws InterruptedException{
        //Перейти на ресурс
        driver.get("https://otus.home.kartushin.su/training.html");
        Thread.sleep(2000);
        //Нажать на «Открыть модальное окно»
        WebElement openModalBtn = driver.findElement(By.id("openModalBtn"));
        openModalBtn.click();
        Thread.sleep(2000);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        //Проверить, что открылось модальное окно
        WebElement modalWindow = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("modal-content")));
        Assertions.assertTrue(modalWindow.isDisplayed(),"Модальное окно должно быть отображено");
        String modalText = modalWindow.getText();
        System.out.println(modalText);
    }

}