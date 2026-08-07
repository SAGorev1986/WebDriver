import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.time.Duration;

public class Lesson26 {
    WebDriver driver;

    @BeforeAll
    public static void install(){
        WebDriverManager.chromedriver().setup();
    }

    @BeforeEach
    public void startUp(){
        ChromeOptions options = new ChromeOptions();
        options.addArguments("headless");

        driver=new ChromeDriver(options);
        //Настроить неявное ожидание на 30 сек
        //driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
        //Настроить время загрузки страницы на 0 и дождаться падения ошибки
        //driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(0));
    }

    @AfterEach
    public void close(){
        if (driver != null){
            driver.close();
        }
    }

    @Test
    public void cookieTest(){
        driver.get("https://otus.home.kartushin.su/training.html");
        //Добавить cookie#1 с параметрами Otus1 и значение Value1
        driver.manage().addCookie(new Cookie("Otus1","Value1"));
        //Добавить cookie#2 с параметрами Otus2 и значение Value2
        driver.manage().addCookie(new Cookie("Otus2","Value2"));
        //Добавить cookie#3 с параметрами Otus3 и значение Value3(добавлять через переменую , переменная должна быть сохранена)
        Cookie otus3 = new Cookie("Otus3","Value3");
        driver.manage().addCookie(otus3);
        //Добавить cookie#4 с параметрами Otus4 и значение Value4
        driver.manage().addCookie(new Cookie("Otus4","Value4"));
        //Вывести на экран все cookies
        System.out.println(driver.manage().getCookies());
        //Вывести на экран cookie1
        System.out.println(driver.manage().getCookieNamed("Otus1"));
        //удалить cookie2 по имени cookie
        driver.manage().deleteCookieNamed("Otus2");
        //удалить cooki3 по переменной cookie
        driver.manage().deleteCookie(otus3);
        //удалить все cookie убедиться что их нет
        driver.manage().deleteAllCookies();
        System.out.println(driver.manage().getCookies());
        Assertions.assertEquals(0,driver.manage().getCookies().size());
    }

    @Test
    public void windowTest() throws InterruptedException{
        //A:Запустить тест в полном окне (не киоск), получить его размер
        driver.manage().window().maximize();
        var windowSize = driver.manage();
        Thread.sleep(2000);
        System.out.println(windowSize);
        //B:Запустить тест в расширении 800 на 600, получить его позицию
        driver.manage().window().setSize(new Dimension(800,600));
        var windowPos = driver.manage().window().getPosition();
        Thread.sleep(2000);
        System.out.println(windowPos);
        //Тоже что В + передвинуть браузер по квадрату(четырем точкам)
        driver.manage().window().setPosition(new Point(windowPos.x+100,windowPos.y));
        Thread.sleep(2000);
        driver.manage().window().setPosition(new Point(windowPos.x+100,windowPos.y+100));
        Thread.sleep(2000);
        driver.manage().window().setPosition(new Point(windowPos.x,windowPos.y+100));
        Thread.sleep(2000);
        driver.manage().window().setPosition(new Point(windowPos.x,windowPos.y));
        Thread.sleep(2000);
    }
}

