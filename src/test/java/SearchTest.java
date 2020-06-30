import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Test;


public class SearchTest extends BaseTest {

    @Test
    public void shouldSearchBook() {
        driver.get("https://czytam.pl/");
        WebElement searchInSection = driver.findElement(By.id("szukaj_dzial"));
        new Select(searchInSection).selectByValue("ksiazki");
        driver.findElement(By.id("txtSearch")).sendKeys("a żem jej powiedziała");

        driver.findElement(By.name("gszukaj")).submit();
        driver.findElement(By.id("pricefor")).sendKeys("20");
        driver.findElement(By.id("priceto")).sendKeys("24");
        driver.findElement((By.name("form_sort"))).click();

        Assert.assertEquals(driver.findElement(By.xpath("//h3[@class = 'product-title']/a/span")).getText(),"A ja żem jej powiedziała...");
    }
}
