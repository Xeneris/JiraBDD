package Pages.RootPage;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Wait;

public abstract class RootPage {

    protected Wait<WebDriver> wait;
    protected WebDriver driver;
    public RootPage(WebDriver driver, Wait<WebDriver> wait) {
        this.wait = wait;
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
}