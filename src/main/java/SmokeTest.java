import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;

/*Napisati dva smoke testa za https://www.telerik.com/support/demos:
Proveriti da klikom na Desktop odlazimo na tu sekciju
Proveriti da klikom na Mobile odlazimo na tu sekciju
Koristiti TestNG, asserte.
Domaći na git.*/
public class SmokeTest {
    private WebDriver driver;

    @BeforeClass
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\mila-\\Desktop\\EXE file\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();

    }

    @BeforeMethod
    public void beforeMethod() {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(10));
        driver.get("https://www.telerik.com/support/demos");
        driver.manage().window().maximize();
        //nadam se da rade, jer me windows defender zza
    }

    @Test
    public void testDesktopTitle() {
        WebElement desktop = driver.findElement(By.xpath("//*[@id=\"ContentPlaceholder1_T53129E6C012_Col00\"]/nav/div/div[2]/a[2]"));
        desktop.click();
        WebElement desktopSection = driver.findElement(By.id("desktop"));
        String expectedTitle = "Desktop";
        String actualTitle = desktopSection.getText();
        Assert.assertEquals(actualTitle, expectedTitle);
    }

    @Test
    public void testMobileTitle() {
        WebElement mobile = driver.findElement(By.xpath("//*[@id=\"ContentPlaceholder1_T53129E6C012_Col00\"]/nav/div/div[2]/a[3]"));
        mobile.click();
        WebElement mobileSection = driver.findElement(By.id("mobile"));
        String expectedTitle = "Mobile";
        String actualTitle = mobileSection.getText();
        Assert.assertEquals(actualTitle, expectedTitle);
    }

    @AfterClass
    public void afterClass() {
        driver.close();
    }
}
