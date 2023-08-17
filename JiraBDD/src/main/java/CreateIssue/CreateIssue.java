package CreateIssue;

import RootPage.RootPage;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;

public class CreateIssue extends RootPage {

    @FindBy(xpath = "//input[@id='project-field']")
    private WebElement actualProjectNameOnCreateScreen;
    @FindBy(xpath = "//input[@id='project-field']")
    private WebElement projectInputField;
    @FindBy(xpath = "//input[@id='issuetype-field']")
    private WebElement issueTypeInputField;
    @FindBy(xpath = "//div[@role='alert']")
    private WebElement summaryErrorMessageOnCreateScreen;
    @FindBy(xpath = "//input[@id='issuetype-field']")
    private WebElement actualIssueType;
    @FindBy(xpath = "//input[@id='summary']")
    private WebElement summaryInputFieldOnCreateScreen;
    @FindBy(xpath = "//input[@id='create-issue-submit']")
    private WebElement createButtonOnCreateScreen;
    @FindBy(xpath = "//a[@class='issue-created-key issue-link']")
    private WebElement createIssuePopUp;
    @FindBy(xpath = "//input[@id='edit-issue-submit']")
    private WebElement updateIssueButton;

    public CreateIssue(WebDriver driver, Wait<WebDriver> wait) {
        super(driver, wait);
    }

    public String getActualProjectNameOnCreateScreen() {
        wait.until(ExpectedConditions.visibilityOf(actualProjectNameOnCreateScreen));
        return actualProjectNameOnCreateScreen.getAttribute("value");
    }

    public String getActualIssueTypeOnCreateScreen() {
        wait.until(ExpectedConditions.visibilityOf(actualIssueType));
        return actualIssueType.getAttribute("value");
    }

    public void clickToProjectInputField() {
        wait.until(ExpectedConditions.elementToBeClickable(projectInputField));
        projectInputField.click();
    }

    public void setProjectOnCreateScreen(String project) {
        wait.until(ExpectedConditions.elementToBeClickable(projectInputField));
        projectInputField.sendKeys(project);
    }

    public void sendBackSpaceToProjectInput() {
        wait.until(ExpectedConditions.elementToBeClickable(projectInputField));
        projectInputField.sendKeys(Keys.BACK_SPACE);
    }

    public void sendEnterToProjectInput() {
        wait.until(ExpectedConditions.elementToBeClickable(projectInputField));
        projectInputField.sendKeys(Keys.ENTER);
    }

    public void clickToIssueTypeInputField() {
        wait.until(ExpectedConditions.elementToBeClickable(issueTypeInputField));
        issueTypeInputField.click();
    }

    public void setIssueType(String type) {
        wait.until(ExpectedConditions.elementToBeClickable(issueTypeInputField));
        issueTypeInputField.sendKeys(type);
    }

    public void sendBackSpaceToIssueInput() {
        wait.until(ExpectedConditions.elementToBeClickable(issueTypeInputField));
        issueTypeInputField.sendKeys(Keys.BACK_SPACE);
    }

    public void sendEnterToIssueInput() {
        wait.until(ExpectedConditions.elementToBeClickable(issueTypeInputField));
        issueTypeInputField.sendKeys(Keys.ENTER);
    }

    public void setSummaryOnCreateScreen(String summary) {
        wait.until(ExpectedConditions.elementToBeClickable(summaryInputFieldOnCreateScreen));
        summaryInputFieldOnCreateScreen.sendKeys(summary);
    }

    public void clickToSubmitTheCreateIssue(){
        wait.until(ExpectedConditions.elementToBeClickable(createButtonOnCreateScreen));
        createButtonOnCreateScreen.click();
    }

    public void clickToCreatedIssuePopUp(){
        wait.until(ExpectedConditions.invisibilityOf(summaryInputFieldOnCreateScreen));
        wait.until(ExpectedConditions.elementToBeClickable(createIssuePopUp));
        createIssuePopUp.click();
    }

    public void validateTheProject(String project){
        wait.until(ExpectedConditions.visibilityOf(actualProjectNameOnCreateScreen));
        if(!getActualProjectNameOnCreateScreen().equals(project)){
            clickToProjectInputField();
            sendBackSpaceToProjectInput();
            setProjectOnCreateScreen(project);
            sendEnterToProjectInput();
        }
    }

    public void validateTheIssueType(String type){
        wait.until(ExpectedConditions.elementToBeClickable(actualIssueType));
        if(!getActualIssueTypeOnCreateScreen().equals(type)){
            clickToIssueTypeInputField();
            sendBackSpaceToIssueInput();
            setIssueType(type);
            sendEnterToIssueInput();
        }
    }
}
