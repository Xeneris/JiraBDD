package LoginPage;

import RootPage.RootPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;

public class LoginPage extends RootPage {

    @FindBy(xpath = "//input[@id='login-form-username']")
    private WebElement userNameField;

    @FindBy(xpath = "//input[@id='login-form-password']")
    private WebElement passwordField;

    @FindBy(xpath = "//input[@id='login']")
    public static WebElement loginButton;

    @FindBy(xpath = "//dd[@id='up-d-username']")
    private WebElement userNameOnProfile;

    @FindBy(xpath = "//p[contains(text(),'Sorry, your username and password are incorrect - ')]")
    private WebElement errorMessageOnTheLoginPage;

    @FindBy(xpath = "//div[@id='captcha']")
    private WebElement captchaDivOnLoginPage;

    public LoginPage(WebDriver driver, Wait<WebDriver> wait) {
        super(driver, wait);
    }

    public void setUserName(String userName) {
        wait.until(ExpectedConditions.visibilityOf(userNameField));
        userNameField.sendKeys(userName);
    }

    public void setPassword(String password) {
        wait.until(ExpectedConditions.visibilityOf(passwordField));
        passwordField.sendKeys(password);
    }

    public void clickToLogInButton() {
        wait.until(ExpectedConditions.visibilityOf(loginButton));
        loginButton.click();
    }

    public void navigateToTheLoginPage(){
        driver.get("https://jira-auto.codecool.metastage.net/secure/Dashboard.jspa");
    }

    public String getUserName() {
        wait.until(ExpectedConditions.visibilityOf(userNameOnProfile));
        return userNameOnProfile.getText();
    }

    public boolean isCaptchaDisplayed() {
        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        wait.until(ExpectedConditions.visibilityOf(captchaDivOnLoginPage));
        return captchaDivOnLoginPage.isDisplayed();
    }

    public String getErrorMessage() {
        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        wait.until(ExpectedConditions.visibilityOf(errorMessageOnTheLoginPage));
        return errorMessageOnTheLoginPage.getText();
    }

    public void successfulLogIn() {
        setUserName("automation48");
        setPassword("CCAutoTest19.");
        clickToLogInButton();
        wait.until(ExpectedConditions.invisibilityOf(loginButton));
    }
}
