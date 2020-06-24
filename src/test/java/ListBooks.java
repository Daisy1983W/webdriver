import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import java.io.File;
import java.io.IOException;




public class ListBooks {
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

        driver.get("https://czytam.pl/");
        driver.findElement(By.xpath("//li[@class='submenu']")).click();
        driver.findElement(By.linkText("Dla dzieci 0 - 2 lata")).click();
        Select select1 = new Select(driver.findElement(By.name("n_sortuj")));
        select1.selectByValue("cdesc");
        String title = driver.findElement(By.xpath("//*[@id=\"main\"]/div[5]/div/ul/li[1]/div/div/div[2]/div[1]/div[1]/h3/a/span")).getText();
        driver.findElement(By.xpath("//*[@id=\"main\"]/div[5]/div/ul/li[1]/div/div/div[2]/a")).click();
        driver.findElement(By.xpath("//*[@id=\"modal_kosz\"]/div[1]/div[5]/form/input")).submit();
        Assert.assertEquals(driver.findElement(By.xpath("//*[@id=\"main\"]/div[7]/form/table/tbody/tr[1]/td[4]/div/a")).getText(), title);
    }

}


