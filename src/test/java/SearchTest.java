import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;


public class SearchTest extends BaseTest {

    @Test
    public void shouldSearchBook() {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        driver.get("https://czytam.pl/");
        WebElement searchInSection = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("szukaj_dzial")));
        new Select(searchInSection).selectByValue("ksiazki");
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("txtSearch"))).sendKeys("a żem jej powiedziała");

        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.name("gszukaj")))).submit();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("pricefor"))).sendKeys("20");
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("priceto"))).sendKeys("24");
        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement((By.name("form_sort"))))).click();

        Assert.assertEquals(driver.findElement(By.xpath("//h3[@class = 'product-title']/a/span")).getText(),"A ja żem jej powiedziała...");
    }
}
