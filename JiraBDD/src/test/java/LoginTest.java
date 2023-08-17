import LoginPage.LoginPage;
import NavBar.NavBar;
import DriverManager.DriverManager;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.jupiter.api.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class LoginTest {

    private NavBar navBar;
    private LoginPage loginPage;
    private Sheet sheet1;
    private String errorMessage = "Sorry, your username and password are incorrect - please try again.";
    private DriverManager driverManager;

    @BeforeEach
    public void SetUp() throws IOException {
        driverManager = new DriverManager();

        loginPage = new LoginPage(driverManager.getDriver(), driverManager.getWait());
        navBar = new NavBar(driverManager.getDriver(), driverManager.getWait());

        FileInputStream fis = new FileInputStream(new File("\\F:\\TW3\\Nevtelen_tablazat.xlsx\\"));
        Workbook workbook = new XSSFWorkbook(fis);
        sheet1 = workbook.getSheet("Users");
    }

    @AfterEach
    public void TearDown() {
        driverManager.tearDown();
    }

    @Test
    @Order(1)
    public void successfulLogin() {
        driverManager.goTo("https://jira-auto.codecool.metastage.net/secure/Dashboard.jspa");

        loginPage.successfulLogIn();

        navBar.clickToAvatarIcon();

        navBar.clickToProfileOption();

        assertEquals(loginPage.getUserName(), "automation48");
    }

    @Test
    @Order(2)
    public void loginWithValidUsernameAndInvalidPassword() {
        String userName = sheet1.getRow(2).getCell(0).toString();
        String password = sheet1.getRow(2).getCell(1).toString();

        driverManager.goTo("https://jira-auto.codecool.metastage.net/secure/Dashboard.jspa");
        loginPage.setUserName(userName);
        loginPage.setPassword(password);
        loginPage.clickToLogInButton();

        String expectedErrorMessage = loginPage.getErrorMessage();

        loginPage.successfulLogIn();

        assertEquals(expectedErrorMessage, errorMessage);
    }

    @Test
    @Order(3)
    public void loginWithValidPasswordAndInvalidUsername() {
        String userName = sheet1.getRow(3).getCell(0).toString();
        String password = sheet1.getRow(3).getCell(1).toString();

        loginPage.navigateToTheLoginPage();
        loginPage.setUserName(userName);
        loginPage.setPassword(password);
        loginPage.clickToLogInButton();

        String expectedErrorMessage = loginPage.getErrorMessage();

        assertEquals(expectedErrorMessage, errorMessage);
    }

    @Test
    @Order(4)
    public void leaveTheLoginFieldEmpty() {
        loginPage.navigateToTheLoginPage();
        loginPage.setUserName("");
        loginPage.setPassword("");
        loginPage.clickToLogInButton();

        String expectedErrorMessage = loginPage.getErrorMessage();

        assertEquals(expectedErrorMessage, errorMessage);
    }

    @Test
    @Order(5)
    public void loginWithValidUsernameAndEmptyPassword() {
        String userName = sheet1.getRow(4).getCell(0).toString();

        loginPage.navigateToTheLoginPage();
        loginPage.setUserName(userName);
        loginPage.setPassword("");
        loginPage.clickToLogInButton();

        String expectedErrorMessage = loginPage.getErrorMessage();

        loginPage.successfulLogIn();

        assertEquals(expectedErrorMessage, errorMessage);
    }

    @Test
    @Order(6)
    public void captchaPopsUpSuccessfully(){
        String userName = sheet1.getRow(2).getCell(0).toString();
        String password = sheet1.getRow(2).getCell(1).toString();

        //First try
        loginPage.navigateToTheLoginPage();
        loginPage.setUserName(userName);
        loginPage.setPassword(password);
        loginPage.clickToLogInButton();

        //Second try
        loginPage.navigateToTheLoginPage();
        loginPage.setUserName(userName);
        loginPage.setPassword(password);
        loginPage.clickToLogInButton();

        //Third try
        loginPage.navigateToTheLoginPage();
        loginPage.setUserName(userName);
        loginPage.setPassword(password);
        loginPage.clickToLogInButton();

        boolean isCaptchaDisplayed = loginPage.isCaptchaDisplayed();

        assertEquals(isCaptchaDisplayed,true);
    }
}
