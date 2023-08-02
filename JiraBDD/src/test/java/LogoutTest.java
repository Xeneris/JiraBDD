import DriverManager.DriverManager;
import LogoutPage.LogoutPage;
import LoginPage.LoginPage;
import NavBar.NavBar;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import static org.junit.Assert.assertEquals;

public class LogoutTest {

    private LoginPage loginPage;
    private LogoutPage logoutPage;
    private NavBar navBar;
    private DriverManager driverManager;

    @BeforeEach
    public void SetUp() throws IOException {
        driverManager = new DriverManager();

        loginPage = new LoginPage(driverManager.getDriver(), driverManager.getWait());
        navBar = new NavBar(driverManager.getDriver(), driverManager.getWait());
        logoutPage = new LogoutPage(driverManager.getDriver(),driverManager.getWait());
    }

    @AfterEach
    public void TearDown() {
        driverManager.tearDown();
    }

    @Test
    public void successfulLogOut(){
        loginPage.navigateToTheLoginPage();
        loginPage.successfulLogIn();
        navBar.clickToAvatarIcon();
        navBar.clickToLogOutOption();

        boolean isLogOutMessageDisplayed = logoutPage.isErrorMessageDisplayed();
        assertEquals(isLogOutMessageDisplayed, true);
    }
}
