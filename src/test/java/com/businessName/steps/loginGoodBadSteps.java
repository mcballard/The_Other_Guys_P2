package com.businessName.steps;

import com.businessName.runner.TestRunner;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;

public class loginGoodBadSteps {

    @Given("I am on the login page")
    public void i_am_on_the_login_page() {
        TestRunner.driver.get("file://C:\\"+System.getenv("HOMEPATH")+"\\Desktop\\The_Other_Guys_P2\\login.html");
    }
    @When("I enter my username {string}")
    public void i_enter_wrong_mb(String string) {
        TestRunner.loginPom.usernameField.sendKeys(string);
    }
    @When("I enter my password {string}")
    public void i_enter_my_pass(String string) {
        TestRunner.loginPom.passwordField.sendKeys(string);
    }
    @When("I click login button")
    public void i_click_login_button() {
        TestRunner.loginPom.loginSubmitButton.click();
    }
    @When("I click the continue button after being notified of a successful login")
    public void iClickTheContinueButtonAfterBeingNotifiedOfASuccessfulLogin() { TestRunner.loginPom.sweetalertContinueButton.click(); }
    @When("I am notified of bad input")
    public void i_am_notified_of_bad_input() {
        TestRunner.loginPom.sweetalertContinueButton.click();
    }
    @Then("I am redirected to login page")
    public void i_am_redirected_to_login_page() {
        Assert.assertEquals(TestRunner.driver.getTitle(),"Employee Login");
    }
    @Then("I am redirected to employee home page")
    public void i_am_redirected_to_employee_home_page() {
        Assert.assertEquals(TestRunner.driver.getTitle(),"Client Home Page");
    }
    @Given("I am logged in and on the client home page")
    public void i_am_logged_in_and_on_the_client_home_page() {
        TestRunner.driver.get("file://C:\\"+System.getenv("HOMEPATH")+"\\Desktop\\The_Other_Guys_P2\\login.html");
        TestRunner.loginPom.usernameField.sendKeys("mb1");
        TestRunner.loginPom.passwordField.sendKeys("pass");
        TestRunner.loginPom.loginSubmitButton.click();
        TestRunner.loginPom.sweetalertContinueButton.click();
        Assert.assertEquals(TestRunner.driver.getTitle(),"Client Home Page");
    }

    @Then("I remain on the client home page")
    public void iRemainOnTheClientHomePage() {
        Assert.assertEquals(TestRunner.driver.getTitle(),"Client Home Page");
    }

    @When("I click the continue button after being notified of successful creation")
    public void iClickTheContinueButtonAfterBeingNotifiedOfSuccessfulCreation() {
        TestRunner.loginPom.SweetalertClientCreateContinueButton.click();
    }

    @When("I click the create request button")
    public void iClickTheCreateRequestButton() {
        TestRunner.loginPom.createRequestSubmitButton.click();
    }

    @When("I enter my request description {string}")
    public void iEnterMyRequest(String arg0) {
        TestRunner.loginPom.createHelpRequestCommentBox.sendKeys(arg0);
    }

    @When("I click create help request collapsible button")
    public void iClickCreateHelpRequestCollapsibleButton() {
        TestRunner.loginPom.createRequestCollapsibleClient.click();
    }

    @When("I am notified that the request comment is too long and i click the continue button")
    public void iAmNotifiedThatTheRequestCommentIsTooLongAndIClickTheContinueButton() {
        TestRunner.loginPom.SweetalertClientCreateContinueButton.click();
    }

    @When("I click the view status collapsible button")
    public void iClickTheViewStatusCollapsibleButton() {
        TestRunner.loginPom.viewCollapseButton.click();
    }

    @When("I am notified with a popup of my request status and I click continue")
    public void iAmNotifiedWithAPopupOfMyRequestStatusAndIClickContinue() {
        TestRunner.loginPom.SweetalertClientCreateContinueButton.click();
    }

    @When("I click the view request button")
    public void iClickTheViewRequestButton() {
        TestRunner.loginPom.viewRequestSubmitButton.click();
    }

    @When("I click the update information button")
    public void iClickTheUpdateInformationButton() {
        TestRunner.loginPom.updatePersonalInfoCollapsible.click();
    }

    @When("I enter my first name {string}")
    public void iEnterMyFirstName(String arg0) {
        TestRunner.loginPom.firstNameInput.sendKeys(arg0);
    }

    @When("I enter my last name {string}")
    public void iEnterMyLastName(String arg0) {
        TestRunner.loginPom.lastNameInput.sendKeys(arg0);
    }

    @When("I click the update button")
    public void iClickTheUpdateButton() {
        TestRunner.loginPom.updatePersonalInfo.click();
    }

    @When("I am notified of a successful update and I click the continue button")
    public void iAmNotifiedOfASuccessfulUpdateAndIClickTheContinueButton() {
        TestRunner.loginPom.SweetalertClientCreateContinueButton.click();
    }

    @When("I click the update request collapsible button")
    public void iClickTheUpdateRequestCollapsibleButton() {
        TestRunner.loginPom.updateCollapseButton.click();
    }

    @When("I update my request description {string}")
    public void iUpdateMyRequestDescription(String arg0) {
        TestRunner.loginPom.updateRequestCommentInput.sendKeys(arg0);
    }

    @When("I click the update request submit button")
    public void iClickTheUpdateRequestSubmitButton() {
        TestRunner.loginPom.updateRequestSubmitButton.click();
    }

    @When("I click the cancel request collapsible button")
    public void iClickTheCancelRequestCollapsibleButton() {
        TestRunner.loginPom.cancelCollapseButton.click();
    }

    @When("I enter the {string}")
    public void iEnterThe(String arg0) {
        TestRunner.loginPom.cancelRequestIdInput.sendKeys(arg0);
    }

    @When("I click the cancel request submit button")
    public void iClickTheCancelRequestSubmitButton() {
        TestRunner.loginPom.cancelRequestSubmitButton.click();
    }

    @When("I am notified of a successful request deletion and click the continue button")
    public void iAmNotifiedOfASuccessfulRequestDeletionAndClickTheContinueButton() {
        TestRunner.loginPom.SweetalertClientCreateContinueButton.click();
    }

    @When("I am notified with a popup that I have no open help request and I click the continue button")
    public void iAmNotifiedWithAPopupThatIHaveNoOpenHelpRequestAndIClickTheContinueButton() {
        TestRunner.loginPom.SweetalertClientCreateContinueButton.click();
    }

    @Given("I am logged in and on the technician home page")
    public void iAmLoggedInAndOnTheTechnicianHomePage() {
        TestRunner.driver.get("file://C:\\"+System.getenv("HOMEPATH")+"\\Desktop\\The_Other_Guys_P2\\login.html");
        TestRunner.loginPom.usernameField.sendKeys("test");
        TestRunner.loginPom.passwordField.sendKeys("test");
        TestRunner.loginPom.loginSubmitButton.click();
        TestRunner.loginPom.sweetalertContinueButton.click();
        Assert.assertEquals(TestRunner.driver.getTitle(),"Technician Home Page");
    }

    @When("I click the create ticket collapsible button")
    public void iClickTheCreateTicketCollapsibleButton() {
        TestRunner.loginPom.createRequestCollapsibleClient.click();
    }

    @When("I click the view open help requests button")
    public void iClickTheViewOpenHelpRequestsButton() {
        TestRunner.loginPom.viewOpenRequestsTechnician.click();
    }

    @When("I am notified that there are no help requests and I click the continue button")
    public void iAmNotifiedThatThereAreNoHelpRequestsAndIClickTheContinueButton() {
        TestRunner.loginPom.sweetalertContinueButton.click();
    }

    @Then("I remain on the technician home page")
    public void iRemainOnTheTechnicianHomePage() {
        Assert.assertEquals(TestRunner.driver.getTitle(),"Technician Home Page");
    }

    @When("I click the view my open ticket collapsible button")
    public void iClickTheViewMyOpenTicketCollapsibleButton() {
        TestRunner.loginPom.viewCollapseButton.click();
    }

    @When("I am notified of a failed ticket viewing and click the continue button")
    public void iAmNotifiedOfAFailedTicketViewingAndClickTheContinueButton() {
        TestRunner.loginPom.sweetalertContinueButton.click();
    }

    @When("I click the create ticket button")
    public void iClickTheCreateTicketButton() {
        TestRunner.loginPom.startCreateTicketTechnician.click();
    }

    @When("I enter my ticket description {string}")
    public void iEnterMyTicketDescription(String arg0) {
        TestRunner.loginPom.helpTicketComment.sendKeys(arg0);
    }


    @When("I select a category in the drop down bar {string}")
    public void iSelectACategoryInTheDropDownBar(String arg0) {
        TestRunner.loginPom.categoryDropDown.selectByValue(arg0);
    }

    @When("I click the {int}nd create ticket button")
    public void iClickTheNdCreateTicketButton(int arg0) {
        TestRunner.loginPom.createsTicketToDatabase.click();
    }

    @When("I am notified of a failed creation and click the continue button")
    public void iAmNotifiedOfAFailedCreationAndClickTheContinueButton() {
        TestRunner.loginPom.sweetalertContinueButton.click();
    }
}

