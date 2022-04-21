package com.businessName.steps;

import com.businessName.poms.runner.TestRunner;
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
    public void i_click_submit_button() {
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

}
