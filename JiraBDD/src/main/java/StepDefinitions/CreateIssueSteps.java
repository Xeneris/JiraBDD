package StepDefinitions;
import CreateIssue.CreateIssue;
import IssuePage.IssuePage;
import NavBarPage.NavBarPage;
import RootPage.RootPage;
import LoginPage.LoginPage;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import DriverManager.DriverManager;
import org.junit.Assert;

import java.io.IOException;

import static org.junit.Assert.assertEquals;

public class CreateIssueSteps {

    private DriverManager driverManager;
    private RootPage rootPage;
    private LoginPage loginPage;
    private NavBarPage navBarPage;
    private CreateIssue createIssue;
    private IssuePage issuePage;

    @Before
    public void SetUp() throws IOException {
        driverManager = new DriverManager();
        loginPage = new LoginPage(driverManager.getDriver(), driverManager.getWait());
        navBarPage = new NavBarPage(driverManager.getDriver(), driverManager.getWait());
        createIssue = new CreateIssue(driverManager.getDriver(),driverManager.getWait());
        issuePage = new IssuePage(driverManager.getDriver(), driverManager.getWait());
    }

    @After
    public void TearDown(){
        driverManager.tearDown();
    }

    @Given("I am logged in with valid credentials")
    public void i_am_logged_in_with_valid_credentials() {
        driverManager.goTo("https://jira-auto.codecool.metastage.net/secure/Dashboard.jspa");
        loginPage.successfulLogIn();
    }
    @When("I click the Create button in the navbar")
    public void i_click_the_create_button_in_the_navbar() {
        navBarPage.clickToCreateButton();
    }
    @When("Set project to {string}")
    public void set_project_to_coala_project(String project) throws InterruptedException {
        Thread.sleep(2000);
        createIssue.validateTheProject(project);
    }
    @When("Set issue to {string}")
    public void set_issue_to_bug(String issue) {
        createIssue.validateTheIssueType(issue);
    }
    @When("Set summary to {string}")
    public void set_summary_to_test_issue(String summary) {
        createIssue.setSummaryOnCreateScreen(summary);
    }
    @When("click the Create button")
    public void click_the_create_button() {
        createIssue.clickToSubmitTheCreateIssue();
    }
    @When("click the popup window on the top right")
    public void click_the_popup_window_on_the_top_right() {
        createIssue.clickToCreatedIssuePopUp();
    }
    @Then("I check the summary, which equals {string}")
    public void i_am_redirected_to_the_created_issue(String summary) {
        String summ = issuePage.getSummary();

        Assert.assertEquals(summ, summary);
    }


}