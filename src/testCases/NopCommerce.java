package testCases;
import Utility.Tools;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import Utility.BaseDriver;
import org.testng.asserts.SoftAssert;
import java.util.List;
import static testCase.Parent.waiting;

public class NopCommerce extends BaseDriver {


    @Test(priority = 1, groups = {"Smoke", "Registeration"})
    public void US_501(){
        String firstname="Test1";
        String lastname="Test1";
        String company="None";
        String email="denememuharrem@muharrem.com";
        String password="Test123.";
        String confirmPassword="Test123.";
            nop.myClick(nop.register);
            nop.myClick(nop.female);
            nop.mySendKeys(nop.firstName,firstname);
            nop.mySendKeys(nop.lastName,lastname);
            nop.myClick(nop.birthDay);
            nop.myClick(nop.birthDay1);
            nop.myClick(nop.birthMonth);
            nop.myClick(nop.birthMonth1);
            nop.myClick(nop.birthYear);
            nop.myClick(nop.birthYear1);
            nop.mySendKeys(nop.Email,email);
            nop.myClick(nop.newsletter);
            nop.mySendKeys(nop.password,password);
            nop.mySendKeys(nop.confirmPassword,confirmPassword);
            nop.myClick(nop.registerbtn);
            Assert.assertTrue(nop.succesMessage.isDisplayed(),"Your registration completed");


        }

        @Test(priority = 2, groups = {"Smoke", "LoginTest"})
            public void US_502(){
                String email="denememuharrem@muharrem.com";
                String password="Test123.";
                nop.myClick(nop.logIn);
                nop.mySendKeys(nop.emailPlc,email);
                nop.mySendKeys(nop.passwordPlc,password);
                nop.myClick(nop.loginButton);

            }


    @Test(priority = 3, groups = {"Smoke", "LoginTest"}, dataProvider = "information")
    public void US_503(String email, String password) {

        nop.myClick(nop.logIn);
        nop.mySendKeys(nop.emailPlc, email);
        nop.mySendKeys(nop.passwordPlc, password);
        nop.myClick(nop.loginButton);
        SoftAssert _softassert = new SoftAssert();


        if (email.equals("denememuharrem@muharrem.com") && password.equals("Test123.")) {
            _softassert.assertTrue(nop.logout.isDisplayed(), "Login failed");
        } else {
            _softassert.assertTrue(nop.errorMessage.isDisplayed(), "Failed login warning couldn't displayed!");
        }
        _softassert.assertAll();
    }

    @DataProvider
    Object[][] information() {
        Object[][] emailPassword =
                {{"afadf@sfg.com", "admin1."},
                        {"afad2f@sfg.com", "admin.2"},
                        {"af3adf@sfg.com", "admin.3"},
                        {"afadf4@sfg.com", "admin.4"},
                        {"afadf@4sfg.com", "admin.5"},
                        {"admin6@tyu.nı", "admin6."},
                        {"denememuharrem@muharrem.com", "Test123."}
                };
        return emailPassword;
    }




    @Test(priority = 4, dataProvider = "tabMenuData", groups = {"UITesting", "TAB Menu"})
    public void US_504_2(String tabXpath, String subMenuXpath, String expectedUrl) throws InterruptedException {
        WebElement tab = driver.findElement(By.xpath(tabXpath));
        Actions action = new Actions(driver);
        action.moveToElement(tab).perform();
        Thread.sleep(1000); // Menünün açılması için biraz bekleyin

        if(subMenuXpath != null && !subMenuXpath.isEmpty()) {
            // Alt menü varsa, ona tıklayın
            WebElement subMenu = driver.findElement(By.xpath(subMenuXpath));
            subMenu.click();
        } else {
            // Alt menü yoksa, doğrudan ana menü öğesine tıklayın
            tab.click();
        }

        String currentUrl = driver.getCurrentUrl();
        Assert.assertEquals(currentUrl, expectedUrl, "URL does not match expected.");

    }


    @DataProvider(name = "tabMenuData")
    public Object[][] provideData() {
        return new Object[][]{
                {"//a[@href='/computers']", "//a[@href='/desktops']", "https://demo.nopcommerce.com/desktops"},
                {"//a[@href='/computers']", "//a[@href='/notebooks']", "https://demo.nopcommerce.com/notebooks"},
                {"//a[@href='/computers']", "//a[@href='/software']", "https://demo.nopcommerce.com/software"},
                {"//a[@href='/electronics']", "//a[@href='/camera-photo']", "https://demo.nopcommerce.com/camera-photo"},
                {"//a[@href='/electronics']", "//a[@href='/cell-phones']", "https://demo.nopcommerce.com/cell-phones"},
                {"//a[@href='/electronics']", "//a[@href='/cell-phones']", "https://demo.nopcommerce.com/cell-phones"},
                {"//a[@href='/electronics']", "//a[@href='/others']", "https://demo.nopcommerce.com/others"},
                {"//a[@href='/apparel']", "//a[@href='/shoes']", "https://demo.nopcommerce.com/shoes"},
                {"//a[@href='/apparel']", "//a[@href='/clothing']", "https://demo.nopcommerce.com/clothing"},
                {"//a[@href='/apparel']", "//a[@href='/accessories']", "https://demo.nopcommerce.com/accessories"},
                {"//a[@href='/digital-downloads']","", "https://demo.nopcommerce.com/digital-downloads"},
                {"//a[@href='/books']","", "https://demo.nopcommerce.com/books"},
                {"//a[@href='/jewelry']","", "https://demo.nopcommerce.com/jewelry"},
                {"//a[@href='/gift-cards']","", "https://demo.nopcommerce.com/gift-cards"},
        };
    }


    @Test(priority = 5, groups = {"Search", "UITesting", "Regression", "TAB Menu"})
    @Parameters("searchText")
    public void US_505(String txt) {
        String eMail = "denememuharrem@muharrem.com";
        String password = "Test123.";

        nop.myClick(nop.logIn);
        nop.mySendKeys(nop.emailPlc, eMail);
        nop.mySendKeys(nop.passwordPlc, password);
        nop.myClick(nop.loginButton);
        nop.myClick(nop.computersBtn);
        nop.myClick(nop.computersList.get(1));
        nop.mySendKeys(nop.searchBox, txt);
        nop.myClick(nop.searchBtn);
        nop.notebookList.get(Tools.randomGenerator(6)).getText();
        Assert.assertTrue(Tools.ListContainsString(nop.notebookList,
                        nop.notebookList.get(Tools.randomGenerator(6)).getText()),
                "Product not found in the list");

    }




    @Test(priority = 6, dataProvider = "inputs", groups = {"Order", "UITesting", "TAB Menu"})
    public void US_506(String recipientName, String senderName, String message) {
        String email = "denememuharrem@muharrem.com";
        String password = "Test123.";

        nop.myClick(nop.logIn);
        nop.mySendKeys(nop.emailPlc, email);
        nop.mySendKeys(nop.passwordPlc, password);
        nop.myClick(nop.loginButton);

        wait.until(ExpectedConditions.elementToBeClickable(nop.giftCardsButton));
        nop.myClick(nop.giftCardsButton);

        wait.until(ExpectedConditions.visibilityOfAllElements(nop.addToCartButtons));
        if (nop.addToCartButtons.size() >= 3) {
            List<WebElement> lastTwoElement = nop.addToCartButtons.subList(1, nop.addToCartButtons.size());
            int range = Tools.randomGenerator(2);
            WebElement randomSelect = lastTwoElement.get(range);
            wait.until(ExpectedConditions.visibilityOf(randomSelect));
            nop.scrollToElement(randomSelect);
            wait.until(ExpectedConditions.elementToBeClickable(randomSelect));
            nop.myClick(randomSelect);

        }
        nop.mySendKeys(nop.recipientNamePlc, recipientName);
        nop.mySendKeys(nop.senderNamePlc, senderName);
        nop.mySendKeys(nop.messageBox, message);

        wait.until(ExpectedConditions.visibilityOf(nop.addToCartButton));
        nop.scrollToElement(nop.addToCartButton);
        Tools.JSClick(nop.addToCartButton);

        wait.until(ExpectedConditions.elementToBeClickable(nop.warningCloseBtn));
        nop.myClick(nop.warningCloseBtn);

        wait.until(ExpectedConditions.visibilityOf(nop.warningMsg));
        Assert.assertTrue(nop.warningMsg.isDisplayed(), "No warning message is present!");
        logger.info("warning message: " + nop.warningMsg.getText());

    }
    @DataProvider
    public Object[][] inputs() {
        Object[][] inputList = {

                {" ", " ", "Enjoy your day!"},
                {"User123", " ", "Wishing you all the best with this gift!"}
        };
        return inputList;
    }




    @Test(priority = 7,groups = {"Order", "UITesting", "TAB Menu"})
    public void US_507(){
        String eMail = "denememuharrem@muharrem.com";
        String password = "Test123.";
        nop.myClick(nop.logIn);
        nop.mySendKeys(nop.emailPlc, eMail);
        nop.mySendKeys(nop.passwordPlc, password);
        nop.myClick(nop.loginButton);
        nop.myClick(nop.computersBtn);
        nop.myClick(nop.desktopsBtn);
        nop.myClick(nop.buildComputer);
        Select ramMenu=new Select(nop.ramSelect);
        ramMenu.selectByIndex((int) ((Math.random() * 3)+1));
        Tools.wait(3);
        nop.myClick(nop.hddSelection.get(Tools.randomGenerator(2)));
        Tools.wait(3);
        nop.myClick(nop.buildComputerAddToCart);
        nop.verifyContainsText(nop.addedYourSc, "has been added");

    }




    @Test(priority = 8,groups = {"Smoke", "UITesting", "Search", "Regression"})
    @Parameters("searchText")
    public void US_508(String txt) {
        String email = "denememuharrem@muharrem.com";
        String password = "Test123.";
        nop.myClick(nop.logIn);
        nop.mySendKeys(nop.emailPlc, email);
        nop.mySendKeys(nop.passwordPlc, password);
        nop.myClick(nop.loginButton);
        nop.mySendKeys(nop.searchBox, txt);
        nop.myClick(nop.searchBtn);

        Assert.assertEquals(txt, nop.adobeCS4.getText());

    }




}




















