package com.businessName.steps;

import com.businessName.runner.TestRunner;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;

public class loginGoodBadSteps {

    @Given("I am on the login page")
    public void i_am_on_the_login_page() {
        TestRunner.driver.get("file://C:\\Users\\meow\\Desktop\\The_Other_Guys_P2\\login.html");
    }
    @When("I enter wrong {string}")
    public void i_enter_wrong_mb(String string) {
        TestRunner.loginPom.usernameField.sendKeys(string);
    }
    @When("I enter my {string}")
    public void i_enter_my_pass(String string) {
        TestRunner.loginPom.passwordField.sendKeys(string);
    }
    @When("I click submit button")
    public void i_click_login_button() {
        TestRunner.loginPom.loginSubmitButton.click();
    }
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
        Assert.assertEquals(TestRunner.driver.getTitle(),"Employee Home Page");
    }

    @Given("I am logged in and on the client home page")
    public void i_am_logged_in_and_on_the_client_home_page() {
        TestRunner.driver.get("File://C:\\Users\\Almas\\Desktop\\The_Other_Guys_P2\\login.html");
        TestRunner.loginPom.usernameField.sendKeys("mb1");
        TestRunner.loginPom.passwordField.sendKeys("pass");
        TestRunner.loginPom.loginSubmitButton.click();
        TestRunner.loginPom.sweetalertContinueButton.click();
        Assert.assertEquals(TestRunner.driver.getTitle(),"Client Home Page");
    }
    @When("I click create request button")
    public void i_click_create_request_button() {
       TestRunner.loginPom.createRequestCollapsibleClient.click();
    }
    @When("I enter request description")
    public void i_enter_request_description() {
        TestRunner.loginPom.helpRequestComment.click();
    }
    @When("I click the submit button")
    public void i_click_the_submit_button() {
       TestRunner.loginPom.sweetalertContinueButton.click();
    }
    @Then("I am notified that the request has been too long")
    public void i_am_notified_that_helpRequest_has_been_too_long() {
       TestRunner.loginPom.helpRequestComment.isDisplayed();
    }

    @When("I click click help request button")
    public void i_click_click_help_request_button() {
       TestRunner.loginPom.createRequestCollapsibleClient.click();
    }
    @When("I enter my request description")
    public void i_enter_my_request_description() {
        TestRunner.loginPom.createRequestCollapsibleClient.click();
    }
    /*
    @When("I click the submit button")
    public void i_click_the_submit_button() {
        TestRunner.loginPom.createRequestCollapsibleClient.click();
    }
    */

    @Then("I am notified that the request has been submitted")
    public void i_am_notified_that_the_request_has_been_submitted() {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }

    @When("I click the update request button")
    public void i_click_the_update_request_button() {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }
    @When("I edit my help request description")
    public void i_edit_my_help_request_description() {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }
    /*
    @When("I click the submit button")
    public void i_click_the_submit_button() {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }

     */
    @Then("I am notified of a successful update")
    public void i_am_notified_of_a_successful_update() {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }

    @When("I click the update information button")
    public void i_click_the_update_information_button() {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }
    @When("I am redirected to the client profile page")
    public void i_am_redirected_to_the_client_profile_page() {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }
    @When("I enter my first name")
    public void i_enter_my_first_name() {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }
    @When("I enter my last name")
    public void i_enter_my_last_name() {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }
    @When("I click the update button")
    public void i_click_the_update_button() {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }
    /*
    @When("I am notified of a successful update")
    public void i_am_notified_of_a_successful_update() {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }

     */
    @When("I click the continue button")
    public void i_click_the_continue_button() {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }
    @Then("I am redirected to the client home page")
    public void i_am_redirected_to_the_client_home_page() {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }

    @When("I click the cancel request button")
    public void i_click_the_cancel_request_button() {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }
    @When("I am notified with a popup that I have no open help request")
    public void i_am_notified_with_a_popup_that_i_have_no_open_help_request() {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }
    /*
    @When("I click the continue button")
    public void i_click_the_continue_button() {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }

     */
    @Then("I remain on the client home page")
    public void i_remain_on_the_client_home_page() {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }
/*
    @When("I click the cancel request button")
    public void i_click_the_cancel_request_button() {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }

 */
    @When("I am prompted with a confirm button")
    public void i_am_prompted_with_a_confirm_button() {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }
    @When("I click confirm")
    public void i_click_confirm() {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }
    @When("I am notified of a successful request deletion")
    public void i_am_notified_of_a_successful_request_deletion() {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }
    /*
    @When("I click the continue button")
    public void i_click_the_continue_button() {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }
    @Then("I remain on the client home page")
    public void i_remain_on_the_client_home_page() {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }

    @When("I click the view status button")
    public void i_click_the_view_status_button() {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }
    @When("I am notified with a popup that I have no open help request")
    public void i_am_notified_with_a_popup_that_i_have_no_open_help_request() {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }
    @When("I click the continue button")
    public void i_click_the_continue_button() {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }
    @Then("I remain on the client home page")
    public void i_remain_on_the_client_home_page() {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }

    @When("I click the view status button")
    public void i_click_the_view_status_button() {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }
    @When("I am notified with a popup of my request status")
    public void i_am_notified_with_a_popup_of_my_request_status() {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }
    @When("I click the continue button")
    public void i_click_the_continue_button() {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }
    @Then("I remain on the client home page")
    public void i_remain_on_the_client_home_page() {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }
*/
}
