package com.businessName.steps;

import com.businessName.runner.TestRunner;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;

public class loginGoodBadSteps {

    @Given("I am on the login page")
    public void i_am_on_the_login_page() {
        TestRunner.driver.get("login.html");
        //Assert.assertEquals();
    }
    @When("I enter wrong mb")
    public void i_enter_wrong_mb() {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }
    @When("I enter my pass")
    public void i_enter_my_pass() {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }
    @When("I click submit button")
    public void i_click_submit_button() {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }
    @When("I am notified of bad input")
    public void i_am_notified_of_bad_input() {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }
    @Then("I am redirected to login page")
    public void i_am_redirected_to_login_page() {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }

}
