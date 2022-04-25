package com.businessName.poms;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPom {

    private WebDriver driver;

    public LoginPom(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }

    @FindBy(id="username")
    public WebElement usernameField;

    @FindBy(id="password")
    public WebElement passwordField;

    @FindBy(id="submitInfo")
    public WebElement loginSubmitButton;

    @FindBy(xpath="/html/body/div/div/div[3]/button[1]")
    public WebElement sweetalertContinueButton;

    @FindBy(id="createCollapseButton")
    public WebElement createRequestCollapsibleClient;

}
