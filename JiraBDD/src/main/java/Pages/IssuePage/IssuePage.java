package Pages.IssuePage;
import Pages.RootPage.RootPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;

public class IssuePage extends RootPage {
    @FindBy(css = "#summary-val")
    public WebElement summaryOnCreatedIssue;

    public IssuePage(WebDriver driver, Wait<WebDriver> wait) {
        super(driver, wait);
    }

    public String getSummary(){
        wait.until(ExpectedConditions.visibilityOf(summaryOnCreatedIssue));
        return summaryOnCreatedIssue.getText();
    }
}

