package com.businessName.poms;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

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

    @FindBy(xpath = "/html/body/div[6]/div/div[3]/button[1]")
    public WebElement SweetalertClientCreateContinueButton;

    @FindBy(xpath = "/html/body/button[2]")
    public  WebElement updatePersonalInfoCollapsible;

    @FindBy(id="updateInfo")
    public  WebElement updatePersonalInfo;

    @FindBy(id="firstName")
    public WebElement firstNameInput;

    @FindBy(id="lastName")
    public WebElement lastNameInput;

    @FindBy(id="help_request_comment")
    public WebElement createHelpRequestCommentBox;

    @FindBy(id="createCollapseButton")
    public WebElement createRequestCollapsibleClient;

    @FindBy(id="create_request")
    public WebElement createRequestSubmitButton;

    @FindBy(id="cancelCollapseButton")
    public WebElement cancelCollapseButton;

    @FindBy(id="updateCollapseButton")
    public WebElement updateCollapseButton;

    @FindBy(id="viewCollapseButton")
    public WebElement viewCollapseButton;

    @FindBy(id="view_request")
    public WebElement viewRequestSubmitButton;

    @FindBy(id="help_request_comment")
    public WebElement helpRequestComment;

    @FindBy(id="update_ticket_comment")
    public WebElement updateTicketComment;

    @FindBy(id="employeeUpdate")
    public WebElement employeeUpdate;

    @FindBy(id="cancel_request")
    public WebElement cancelRequestSubmitButton;

    @FindBy(id="update_request_comment")
    public WebElement updateRequestCommentInput;

    @FindBy(id="update_request")
    public WebElement updateRequestSubmitButton;

    @FindBy(id="update_request_id")
    public WebElement updateRequestIdInput;

    @FindBy(id="ticket_requests_id")
    public  WebElement cancelRequestIdInput;

    @FindBy(id="view_request")
    public WebElement viewOpenRequestsTechnician;

    @FindBy(xpath = "/html/body/div[2]/table/tbody/tr[1]/td/button")
    public WebElement startCreateTicketTechnician;

    @FindBy(id = "create_request")
    public WebElement createsTicketToDatabase;

    @FindBy(id= "categoryList")
    public Select categoryDropDown;

    @FindBy(xpath = "//*[@id=\"update_request\"]")
    public WebElement updateTicket;

    @FindBy(id = "view_open_resolve")
    public WebElement viewOpenTicketResolve;

    @FindBy(xpath = "/html/body/div[5]/table/tbody/tr[3]/td/button")
    public WebElement resolveTicketTech;

    @FindBy(id="help_ticket_comment")
    public WebElement helpTicketComment;
    
}
