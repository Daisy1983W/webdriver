import com.github.javafaker.Faker;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Locale;

public class RegistrationTest extends BaseTest {

    @Test
    public void shouldRegisterUser() {
        Faker faker = new Faker(new Locale("pl"));
        JavascriptExecutor js = (JavascriptExecutor) driver;
        WebDriverWait wait = new WebDriverWait(driver, 10);
        driver.get("https://czytam.pl/");
        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.linkText("Rejestracja")))).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("imie1"))).sendKeys(faker.name().firstName());
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("nazwisko1"))).sendKeys(faker.name().lastName());
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("telefon1"))).sendKeys(faker.phoneNumber().phoneNumber());
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("ulica1"))).sendKeys(faker.address().streetName());
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("nr_domu1"))).sendKeys(faker.address().buildingNumber());
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("nr_mieszkania1"))).sendKeys(faker.address().streetAddressNumber());
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("miejscowosc1"))).sendKeys(faker.address().city());
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("kod_pocztowy1"))).sendKeys(faker.address().zipCode());
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("przenies"))).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("em1"))).sendKeys(faker.internet().emailAddress());
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("haslo11"))).sendKeys(faker.internet().password());
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("haslo21"))).sendKeys(faker.internet().password());
        wait.until(ExpectedConditions.presenceOfElementLocated((By.id("zgoda")))).click();

        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.className("submit-next")))).submit();
        WebElement nextButton = wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.className("submit-next"))));
        js.executeScript("arguments[0].scrollIntoView();", nextButton);
        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.className("submit-next")))).click();

        Assert.assertEquals(driver.findElement(By.xpath("//*[@id=\"main\"]/div/div")).getText(), "Dziękujemy. Potwierdzenie rejestracji zostało wysłane na podany adres e-mail.\n×");
    }
}
