package Utility;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.annotations.*;
import testCases.Nopcommerce_Elements;


import java.time.Duration;

public class BaseDriver {
    public static Logger logger = LogManager.getLogger();
    public static WebDriver driver;
    public static WebDriverWait wait;
    public static Nopcommerce_Elements nop;

    @BeforeClass (groups = {"Regression Test","UI Test","Smoke Test","Login Test"})
    public void InitialProcedure() {

        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(15));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        nop = new Nopcommerce_Elements();
        DashBoardPage();
    }

    @AfterClass(groups = {"Regression Test","UI Test","Smoke Test","Login Test"})
    public void CloseProcedure() {
        Tools.wait(2);
        driver.quit();
        logger.info("Driver Closed");
    }

    public void DashBoardPage() {
        logger.info("Dashboard Page Procedure Started");
        driver.get("https://demo.nopcommerce.com/");
        logger.info("Dashboard Page Procedure Finished");
    }



    @BeforeMethod
    public void beforeMethod() {
        logger.info("Method started");
    }

    @AfterMethod
    public void afterMethod(ITestResult result) {
        logger.info(result.getName() + " Method finished " + (result.getStatus() == 1 ? "Passed" : "failed"));

    }
//    @AfterMethod
//    public void logoutAfterTest() {
//        driver.get("https://demo.nopcommerce.com/");
//        nop.myClick(nop.logout);
//    }
}
