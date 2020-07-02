import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.openqa.selenium.*;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;


public class LoginTest extends BaseTest {

    @AfterMethod
    public void logoutUser() {
        driver.get("https://czytam.pl/wyloguj.html");
    }

    @Test(dataProvider = "provideLoginData")
    public void shouldLoginUser(String login, String password, String expectedText) {
        driver.get("https://czytam.pl/");
        driver.findElement(By.linkText("Logowanie")).click();
        driver.findElement(By.name("email")).sendKeys(login);
        driver.findElement(By.name("haslo")).sendKeys(password);

        driver.findElement(By.xpath("//input[@value = 'Zaloguj']")).submit();

        String headerAccountText = driver.findElement(By.xpath("//*[@id=\"header-account\"]/a[3]")).getText();
        Assert.assertEquals(headerAccountText, expectedText);
    }

    @DataProvider(name = "provideLoginData")
    private Object[][] readExcel() {
        String fileName = "login_data.xls";
        String sheetName = "Arkusz1";

        File file = new File(getClass().getResource(fileName).getFile());
        FileInputStream inputStream = null;
        try {
            inputStream = new FileInputStream(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        Workbook workbook = null;
        try {
            workbook = new HSSFWorkbook(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }

        Sheet sheet = workbook.getSheet(sheetName);

        int rowCount = sheet.getLastRowNum() - sheet.getFirstRowNum() + 1;
        Object[][] loginData = new Object[rowCount][];
        for (int i = 0; i < rowCount; i++) {
            Row row = sheet.getRow(i);
            Object[] loginDataRow = new Object[row.getLastCellNum()];
            for (int j = 0; j < row.getLastCellNum(); j++) {
                loginDataRow[j] = row.getCell(j).getStringCellValue();
            }
            loginData[i] = loginDataRow;
        }

        return loginData;
    }
}
