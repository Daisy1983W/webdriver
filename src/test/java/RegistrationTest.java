import com.github.javafaker.Faker;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import java.io.File;
import java.io.IOException;

import java.util.Locale;


public class RegistrationTest {
        WebDriver driver;

        @BeforeClass
        public void beforeClass() {
            System.setProperty("webdriver.chrome.driver", "src/test/java/data/chromedriver.exe");
            driver = new ChromeDriver();
            driver.manage().window().maximize();


        }

        @AfterClass(alwaysRun = true)

        public void afterClass()
        {
            WebDriverWait wait = new WebDriverWait(driver, 30);
            File src= ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
            try {
                FileUtils.copyFile(src, new File("C:/Users/Monika/IdeaProjects/webdriver/src/test/resources/ScrenShot/" + src.getName()));
            } catch (IOException e) {
                e.printStackTrace();
            }

            driver.quit();

        }


        @Test
        public void mainTest() {

            Faker faker = new Faker(new Locale("pl"));
            JavascriptExecutor js = (JavascriptExecutor) driver;
            driver.get("https://czytam.pl/");
            driver.findElement(By.linkText("Rejestracja")).click();
            driver.findElement(By.id("imie1")).sendKeys(faker.name().firstName());
            driver.findElement(By.id("nazwisko1")).sendKeys(faker.name().lastName());
            driver.findElement(By.id("telefon1")).sendKeys(faker.phoneNumber().phoneNumber());
            driver.findElement(By.id("ulica1")).sendKeys(faker.address().streetName());
            driver.findElement(By.id("nr_domu1")).sendKeys(faker.address().buildingNumber());
            driver.findElement(By.id("nr_mieszkania1")).sendKeys(faker.address().streetAddressNumber());
            driver.findElement(By.id("miejscowosc1")).sendKeys(faker.address().city());
            driver.findElement(By.id("kod_pocztowy1")).sendKeys(faker.address().zipCode());
            driver.findElement(By.id("przenies")).click();
            driver.findElement(By.id("em1")).sendKeys(faker.internet().emailAddress());
            driver.findElement(By.id("haslo11")).sendKeys(faker.internet().password());
            driver.findElement(By.id("haslo21")).sendKeys(faker.internet().password());
            driver.findElement(By.id("zgoda")).click();
            driver.findElement(By.className("submit-next")).submit();
            WebElement Element = driver.findElement(By.className("submit-next"));
            js.executeScript("arguments[0].scrollIntoView();", Element);
            driver.findElement(By.className("submit-next")).click();
            Assert.assertEquals(driver.findElement(By.xpath("//*[@id=\"main\"]/div/div")).getText(), "Dziękujemy. Potwierdzenie rejestracji zostało wysłane na podany adres e-mail.\n×");




        }

    }

