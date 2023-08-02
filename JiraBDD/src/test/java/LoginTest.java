import LoginPage.LoginPage;
import NavBarPage.NavBarPage;
import DriverManager.DriverManager;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LoginTest {

    private NavBarPage navBarPage;
    private LoginPage loginPage;
    private Sheet sheet1;
    private String errorMessage = "Sorry, your username and password are incorrect - please try again.";
    private DriverManager driverManager;

    @BeforeEach
    public void SetUp() throws IOException {
        driverManager = new DriverManager();

        loginPage = new LoginPage(driverManager.getDriver(), driverManager.getWait());
        navBarPage = new NavBarPage(driverManager.getDriver(), driverManager.getWait());

        FileInputStream fis = new FileInputStream(new File("\\F:\\TW3\\Nevtelen_tablazat.xlsx\\"));
        Workbook workbook = new XSSFWorkbook(fis);
        sheet1 = workbook.getSheet("Users");
    }

    @AfterEach
    public void TearDown() {
        driverManager.tearDown();
    }

    @Test
    public void successfulLogin() {
        driverManager.goTo("https://jira-auto.codecool.metastage.net/secure/Dashboard.jspa");

        loginPage.successfulLogIn();

        navBarPage.clickToAvatarIcon();

        navBarPage.clickToProfileOption();

        assertEquals(loginPage.getUserName(), "automation48");
    }

    @Test
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
    public void leaveTheLoginFieldEmpty() {
        loginPage.navigateToTheLoginPage();
        loginPage.setUserName("");
        loginPage.setPassword("");
        loginPage.clickToLogInButton();

        String expectedErrorMessage = loginPage.getErrorMessage();

        assertEquals(expectedErrorMessage, errorMessage);
    }

    @Test
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
    public void isCaptchaWorksCorrectly(){
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
