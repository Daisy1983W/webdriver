import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import java.lang.reflect.Method;

public class CartTest extends BaseTest {

    @AfterMethod
    public void removeFromCart(Method m) {
        if (m.getName().equals("shouldAddBookToCart")) {
            driver.get("https://czytam.pl/koszyk.html");
            WebElement selectProduct = driver.findElement(By.xpath("//*[@id=\"main\"]/div[7]/form/table/tbody/tr/td[1]/div/span"));
            selectProduct.click();
            WebElement removeSelected = driver.findElement(By.xpath("//*[@id=\"main\"]/div[7]/form/table/tfoot/tr[1]/td[2]/input[1]"));
            removeSelected.click();
        }
    }

    @Test
    public void shouldAddBookToCart() {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        driver.get("https://czytam.pl/");
        WebElement submenu = driver.findElement(By.xpath("//li[@class='submenu']"));
        submenu.click();
        WebElement selectCategory = driver.findElement(By.linkText("Dla dzieci 0 - 2 lata"));
        selectCategory.click();
        Select sort = new Select(driver.findElement(By.name("n_sortuj")));
        sort.selectByValue("cdesc");
        String expectedTitle = driver.findElement(By.xpath("//*[@id=\"main\"]/div[5]/div/ul/li[1]/div/div/div[2]/div[1]/div[1]/h3/a/span")).getText().replace("\n", " ");

        WebElement addBookToCart = driver.findElement(By.xpath("//*[@id=\"main\"]/div[5]/div/ul/li[1]/div/div/div[2]/a"));
        addBookToCart.click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("koszykp")));
        WebElement goToCart = driver.findElement(By.xpath("//input[@value='Przejdź do koszyka']"));
        goToCart.click();

        Assert.assertEquals(driver.findElement(By.xpath("//*[@id=\"main\"]/div[7]/form/table/tbody/tr[1]/td[4]/div/a")).getText(), expectedTitle);
    }

    @Test
    public void shouldRemoveBookFromCart() {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        driver.get("https://czytam.pl/");
        WebElement addToCart = driver.findElement(By.xpath("//*[@id=\"main\"]/div[2]/div/ul/li[1]/div/div/div[2]/a"));
        addToCart.click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("koszykp")));
        WebElement goToCart = driver.findElement(By.xpath("//input[@value='Przejdź do koszyka']"));
        goToCart.submit();

        WebElement selectProduct = driver.findElement(By.xpath("//*[@id=\"main\"]/div[7]/form/table/tbody/tr/td[1]/div/span"));
        selectProduct.click();
        WebElement removeSelected = driver.findElement(By.xpath("//*[@id=\"main\"]/div[7]/form/table/tfoot/tr[1]/td[2]/input[1]"));
        removeSelected.click();

        driver.get("https://czytam.pl/koszyk.html");
        WebElement cart = driver.findElement(By.xpath("/html/body/div[1]/div/section/div/div/div"));
        Assert.assertEquals(cart.getText(), "Koszyk jest pusty");
    }
}
