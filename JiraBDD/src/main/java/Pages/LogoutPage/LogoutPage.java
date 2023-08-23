package Pages.LogoutPage;

import Pages.RootPage.RootPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Wait;

public class LogoutPage extends RootPage {

    @FindBy(xpath = "//strong[contains(text(),'You are now logged out. Any automatic login has al')]")
    private WebElement logOutMessage;

    public LogoutPage(WebDriver driver, Wait<WebDriver> wait) {
        super(driver, wait);
    }

    public boolean isErrorMessageDisplayed(){ return logOutMessage.isDisplayed();}
}
