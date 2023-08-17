package StepDefinitions;

import CreateIssue.CreateIssue;
import IssuePage.IssuePage;
import NavBar.NavBar;
import ProjectPage.ProjectPage;
import LoginPage.LoginPage;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import DriverManager.DriverManager;
import org.junit.Assert;

import java.io.IOException;


public class StepDefinition {

    private DriverManager driverManager;
    private LoginPage loginPage;
    private NavBar navBar;
    private CreateIssue createIssue;
    private IssuePage issuePage;
    private ProjectPage projectPage;


    //Basic setup and initialization before tests
    @Before
    public void SetUp() throws IOException {
        driverManager = new DriverManager();
        loginPage = new LoginPage(driverManager.getDriver(), driverManager.getWait());
        navBar = new NavBar(driverManager.getDriver(), driverManager.getWait());
        createIssue = new CreateIssue(driverManager.getDriver(), driverManager.getWait());
        issuePage = new IssuePage(driverManager.getDriver(), driverManager.getWait());
        projectPage = new ProjectPage(driverManager.getDriver(), driverManager.getWait());
    }

    //Disconnect driver and close browser
    @After
    public void TearDown() {
        driverManager.tearDown();
    }


    //These steps are for the CreateIssue test
    @Given("I am logged in with valid credentials")
    public void i_am_logged_in_with_valid_credentials() {
        driverManager.goTo("https://jira-auto.codecool.metastage.net/secure/Dashboard.jspa");
        loginPage.successfulLogIn();
    }

    @When("I click the Create button in the navbar")
    public void i_click_the_create_button_in_the_navbar() {
        navBar.clickToCreateButton();
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

    @Then("I check the summary of the newly created issue, which equals {string}")
    public void i_am_redirected_to_the_created_issue(String summary) {
        String summ = issuePage.getSummary();

        Assert.assertEquals(summ, summary);
    }


    //These steps are for the BrowseProject test
    @When("I navigate to the given {string}")
    public void i_redirect_to_the_given(String string) {
        driverManager.goTo(string);
    }

    @Then("The key on the right side of the page matches {string}")
    public void the_key_on_the_right_side_of_the_page_matches(String string) {
        var projKey = projectPage.getProjectKey();

        Assert.assertEquals(projKey, string);
    }


}