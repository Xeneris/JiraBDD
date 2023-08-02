package ProjectPage;

import RootPage.RootPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ProjectPage  extends RootPage {

    @FindBy(xpath = "//dd[normalize-space() = 'MTP' or normalize-space() = 'COALA' or normalize-space() = 'TOUCAN' or normalize-space() = 'JETI']")
    private WebElement projectKey;

    @FindBy(xpath = "//h1[@class='projects-error-page-heading']")
    private WebElement errorMessage;

    public ProjectPage(WebDriver driver, Wait<WebDriver> wait) {
        super(driver, wait);
    }

    public String getProjectKey() {
        wait.until(ExpectedConditions.visibilityOf(projectKey));
        return projectKey.getText();
    }

}
