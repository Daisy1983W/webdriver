import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;



    public class ChromeTest {
        WebDriver driver;

        @BeforeClass
        public void beforeClass() {
            System.setProperty("webdriver.chrome.driver", "src/test/java/data/chromedriver.exe");
            driver = new ChromeDriver();

        }

        @AfterClass(alwaysRun = true)
        public void afterClass() {
            driver.quit();

        }

        @Parameters({ "priority", "enabled" })
        @Test
   public void mainTest(int priority, boolean enabled) {

            driver.get("http://google.com");

   }

    }

