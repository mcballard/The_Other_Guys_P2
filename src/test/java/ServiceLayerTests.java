import com.businessName.CustomerExceptions.LoginFailedException;
import com.businessName.CustomerExceptions.MalformedObjectException;
import com.businessName.CustomerExceptions.RecordNotFound;
import com.businessName.dataEntity.DatabaseEntity;
import com.businessName.ticketDao.DataAccessImp;
import com.businessName.ticketService.ClientInteractions;
import com.businessName.ticketService.TechnicianInteractions;
import org.json.JSONObject;
import org.mockito.Mockito;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.HashMap;

public class ServiceLayerTests {

    public static DataAccessImp daoTestObject;
    public static ClientInteractions clientMockObject;
    public static ClientInteractions clientTestObject;
    public static TechnicianInteractions techTestObject;

    public static TechnicianInteractions techMockObject;

    @BeforeClass
    public void setup() {
        // define class to mock
        daoTestObject = Mockito.mock(DataAccessImp.class);
        DataAccessImp daoSuccessObject = new DataAccessImp();
        daoSuccessObject.deleteObjectDb("TRUNCATE TABLE p2_sandbox.ticket_requests RESTART IDENTITY CASCADE;");
        daoSuccessObject.deleteObjectDb("TRUNCATE TABLE p2_sandbox.tickets RESTART IDENTITY CASCADE;");

        clientMockObject = new ClientInteractions(daoTestObject);
        clientTestObject = new ClientInteractions(daoSuccessObject);
        techTestObject = new TechnicianInteractions(daoSuccessObject);
        techMockObject = new TechnicianInteractions(daoTestObject);

    }


    @Test
    public void testDoLoginSuccess() {
        HashMap<String, String> testLogin = new HashMap<>();
        testLogin.put("token", "sometoken");
        testLogin.put("tableName", "employees");
        testLogin.put("username", "mb1");
        testLogin.put("pass", "pass");
        DatabaseEntity loginTech = new DatabaseEntity(testLogin);
        JSONObject json = new JSONObject(testLogin);
        HashMap<String, String> testResponse = new HashMap<>();
        testResponse.put("employees_id", "2");
        testResponse.put("username", "mb1");
        testResponse.put("type_id", "1");
        testResponse.put("pass", "pass");
        DatabaseEntity[] response = new DatabaseEntity[1];
        DatabaseEntity testEntity = new DatabaseEntity(testResponse);
        response[0] = testEntity;
        // define mocked response for when a particular method is called
        Mockito.doReturn(response).when(daoTestObject).selectObjectsDb(loginTech.selectDoLogin());
        String authToken = clientMockObject.doLogin(String.valueOf(json));
        Assert.assertEquals(authToken, "mb1_1_2");
    }


    @Test(priority = 0)
    public void testCreateHelpRequestSuccess() {
        HashMap<String, String> testHelpRequest = new HashMap<>();
        testHelpRequest.put("token", "sometoken");
        testHelpRequest.put("tableName", "ticket_requests");
        testHelpRequest.put("employee_id", "2");
        testHelpRequest.put("status_id", "1");
        testHelpRequest.put("description", "I have a flat tire");
        JSONObject json = new JSONObject(testHelpRequest);
        String result = clientTestObject.createHelpRequest(String.valueOf(json));
        Assert.assertTrue(result.matches("(.*)I have a flat tire(.*)"));
    }


    @Test(priority = 2)
    public void testUpdateHelpRequestSuccess(){
        HashMap<String, String> testHelpRequest = new HashMap<>();
        testHelpRequest.put("token", "sometoken");
        testHelpRequest.put("tableName", "ticket_requests");
        testHelpRequest.put("employee_id", "2");
        testHelpRequest.put("ticket_requests_id", "1");
        testHelpRequest.put("description", "I have two flat tires");
        JSONObject json = new JSONObject(testHelpRequest);
        String result = clientTestObject.updateHelpRequest(String.valueOf(json));
        Assert.assertTrue(result.matches("(.*)I have two flat tires(.*)"));
    }

    @Test(priority = 3)
    public void viewHelpRequestSuccess(){
        HashMap<String, String> testHelpRequest = new HashMap<>();
        testHelpRequest.put("token", "sometoken");
        testHelpRequest.put("tableName", "ticket_requests");
        testHelpRequest.put("employee_id", "2");
        JSONObject json = new JSONObject(testHelpRequest);
        String result = clientTestObject.viewHelpRequest(String.valueOf(json));
        Assert.assertTrue(result.matches("(.*)ticket_requests_id(.*)"));
    }

    @Test(priority = 4, expectedExceptions = RecordNotFound.class, expectedExceptionsMessageRegExp = "You have no open help requests.")
    public void testViewHelpRequestNoRecord() {
        HashMap<String, String> testHelpRequest = new HashMap<>();
        testHelpRequest.put("token", "sometoken");
        testHelpRequest.put("tableName", "ticket_requests");
        testHelpRequest.put("employee_id", "2");
        DatabaseEntity noRecord = new DatabaseEntity(testHelpRequest);
        JSONObject json = new JSONObject(testHelpRequest);
        DatabaseEntity[] response = new DatabaseEntity[0];
        Mockito.doReturn(response).when(daoTestObject).selectObjectsDb(noRecord.returnSqlForSelectByEmployeeId());
        String result = clientTestObject.viewHelpRequest(String.valueOf(json));
    }

    @Test(priority = 4)
    public void testCancelHelpRequestSuccess(){
        HashMap<String, String> testHelpRequest = new HashMap<>();
        testHelpRequest.put("token", "sometoken");
        testHelpRequest.put("tableName", "ticket_requests");
        testHelpRequest.put("employee_id", "2");
        testHelpRequest.put("ticket_requests_id", "1");
        JSONObject json = new JSONObject(testHelpRequest);
        String result = clientTestObject.cancelHelpRequest(String.valueOf(json));
        Assert.assertEquals(result, "record deleted success");
    }

    @Test(priority = 1, expectedExceptions = LoginFailedException.class, expectedExceptionsMessageRegExp = "Incorrect Username!")
    public void testDoLoginBadUsername() {
        HashMap<String, String> testLogin = new HashMap<>();
        testLogin.put("token", "sometoken");
        testLogin.put("tableName", "employees");
        testLogin.put("username", "mb");
        testLogin.put("pass", "pass");
        DatabaseEntity loginTech = new DatabaseEntity(testLogin);
        JSONObject json = new JSONObject(testLogin);
        DatabaseEntity[] response = new DatabaseEntity[1];
        Mockito.doReturn(response).when(daoTestObject).selectObjectsDb(loginTech.selectDoLogin());
        String authToken = clientMockObject.doLogin(String.valueOf(json));
    }

    @Test(priority = 1, expectedExceptions = LoginFailedException.class, expectedExceptionsMessageRegExp = "Incorrect Password!")
    public void testDoLoginBadPassword() {
        HashMap<String, String> testLogin = new HashMap<>();
        testLogin.put("token", "sometoken");
        testLogin.put("tableName", "employees");
        testLogin.put("username", "mb1");
        testLogin.put("pass", "pas");
        DatabaseEntity loginTech = new DatabaseEntity(testLogin);
        JSONObject json = new JSONObject(testLogin);
        HashMap<String, String> testResponse = new HashMap<>();
        testResponse.put("employees_id", "2");
        testResponse.put("username", "mb1");
        testResponse.put("type_id", "1");
        testResponse.put("pass", "pass");
        DatabaseEntity[] response = new DatabaseEntity[1];
        DatabaseEntity testEntity = new DatabaseEntity(testResponse);
        response[0] = testEntity;
        Mockito.doReturn(response).when(daoTestObject).selectObjectsDb(loginTech.selectDoLogin());
        String authToken = clientMockObject.doLogin(String.valueOf(json));
    }
    @Test(priority = 4, expectedExceptions = RecordNotFound.class, expectedExceptionsMessageRegExp = "No request with id 1 was found.")
    public void testUpdateHelpRequestNoRecord() {
        HashMap<String, String> testHelpRequest = new HashMap<>();
        testHelpRequest.put("token", "sometoken");
        testHelpRequest.put("tableName", "ticket_requests");
        testHelpRequest.put("employee_id", "2");
        testHelpRequest.put("ticket_requests_id", "1");
        testHelpRequest.put("description", "I have two flat tires");
        DatabaseEntity noRecord = new DatabaseEntity(testHelpRequest);
        JSONObject json = new JSONObject(testHelpRequest);
        DatabaseEntity[] response = new DatabaseEntity[0];
        Mockito.doReturn(response).when(daoTestObject).selectObjectsDb(noRecord.returnSqlForSelectByEmployeeId());
        String result = clientMockObject.updateHelpRequest(String.valueOf(json));

    }
    @Test(priority = 1, expectedExceptions = MalformedObjectException.class, expectedExceptionsMessageRegExp = "Please enter less than 250 characters in the description box")
    public void testUpdateHelpRequestDescriptionLong() {
        HashMap<String, String> testHelpRequest = new HashMap<>();
        testHelpRequest.put("token", "sometoken");
        testHelpRequest.put("tableName", "ticket_requests");
        testHelpRequest.put("employee_id", "2");
        testHelpRequest.put("ticket_requests_id", "1");
        testHelpRequest.put("description", "qqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqq");
        JSONObject json = new JSONObject(testHelpRequest);
        DatabaseEntity[] response = new DatabaseEntity[1];
        String result = clientMockObject.updateHelpRequest(String.valueOf(json));
    }

    @Test(priority = 1, expectedExceptions = MalformedObjectException.class, expectedExceptionsMessageRegExp = "Please enter less than 250 characters in the description box")
    public void testCreateHelpRequestDescriptionTooLong() {
        HashMap<String, String> testHelpRequest = new HashMap<>();
        testHelpRequest.put("token", "sometoken");
        testHelpRequest.put("tableName","ticket_requests");
        testHelpRequest.put("employee_id","2");
        testHelpRequest.put("status_id","1");
        testHelpRequest.put("description","Oh NO! I have a flat tire.Oh NO! I have a flat tire.Oh NO! I have a flat " +
                "tire.Oh NO! I have a flat tire.Oh NO! I have a flat tire.Oh NO! I have a flat tire.Oh NO! I have a flat" +
                " tire.Oh NO! I have a flat tire.Oh NO! I have a flat tire.Oh NO! I have a flat tire.Oh NO! I have a" +
                " flat tire.Oh NO! I have a flat tire.Oh NO! I have a flat tire.Oh NO! I have a flat tire.Oh NO! I " +
                "have a flat tire.Oh NO! I have a flat tire.Oh NO! I have a flat tire.Oh NO! I have a flat tire." +
                "Oh NO! I have a flat tire.Oh NO! I have a flat tire.Oh NO! I have a flat tire.Oh NO! I have a flat" +
                " tire.Oh NO! I have a flat tire.Oh NO! I have a flat tire.Oh NO! I have a flat tire.");
        JSONObject json = new JSONObject(testHelpRequest);
        String result = clientMockObject.createHelpRequest(String.valueOf(json));
    }

    @Test(priority = 1, expectedExceptions = RecordNotFound.class, expectedExceptionsMessageRegExp = "Can not have more than one request open at a time")
    public void testCreateHelpRequestOneAlreadyExists() {
        HashMap<String, String> testHelpRequest = new HashMap<>();
        testHelpRequest.put("token", "sometoken");
        testHelpRequest.put("tableName","ticket_requests");
        testHelpRequest.put("employee_id","2");
        testHelpRequest.put("status_id","1");
        testHelpRequest.put("description","I have a flat tire.");
        DatabaseEntity createClient = new DatabaseEntity(testHelpRequest);
        JSONObject json = new JSONObject(testHelpRequest);
        HashMap<String, String> testHelpRequestMock = new HashMap<>();
        testHelpRequestMock.put("tableName","ticket_requests");
        testHelpRequestMock.put("employee_id","2");
        testHelpRequestMock.put("status_id","1");
        testHelpRequestMock.put("description","I have a flat tire.");
        DatabaseEntity createClient2 = new DatabaseEntity(testHelpRequestMock);
        DatabaseEntity[] response = new DatabaseEntity[1];
        response[0] = createClient2;
        Mockito.doReturn(response).when(daoTestObject).selectObjectsDb(createClient.returnSqlForSelectByEmployeeId());
        String result = clientMockObject.createHelpRequest(String.valueOf(json));
    }
    @Test(priority = 1)
    public void testUpdatePersonalInfoFirstNameSuccess(){
        HashMap<String, String> testHelpRequest = new HashMap<>();
        testHelpRequest.put("token", "sometoken");
        testHelpRequest.put("tableName","employees");
        testHelpRequest.put("employees_id", "2");
        testHelpRequest.put("first_name", "Chicken");
        testHelpRequest.put("last_name", "Little");
        JSONObject json = new JSONObject(testHelpRequest);
        String result = clientTestObject.updatePersonalInfo(String.valueOf(json));
        Assert.assertTrue(result.matches("(.*)Chicken(.*)"));
    }
    @Test(priority = 1)
    public void testUpdatePersonalInfoLastNameSuccess(){
        HashMap<String, String> testHelpRequest = new HashMap<>();
        testHelpRequest.put("token", "sometoken");
        testHelpRequest.put("tableName","employees");
        testHelpRequest.put("employees_id", "2");
        testHelpRequest.put("first_name", "Chicken");
        testHelpRequest.put("last_name", "Little");
        JSONObject json = new JSONObject(testHelpRequest);
        String result = clientTestObject.updatePersonalInfo(String.valueOf(json));
        Assert.assertTrue(result.matches("(.*)Little(.*)"));
    }
    @Test(priority = 1, expectedExceptions = MalformedObjectException.class, expectedExceptionsMessageRegExp = "Please enter less than 12 characters in the description box")
    public void testUpdatePersonalInfoTooManyCharacters(){
        HashMap<String, String> testHelpRequest = new HashMap<>();
        testHelpRequest.put("token", "sometoken");
        testHelpRequest.put("tableName","employees");
        testHelpRequest.put("employees_id", "2");
        testHelpRequest.put("first_name", "ChickenChickenChicken");
        testHelpRequest.put("last_name", "Little");
        JSONObject json = new JSONObject(testHelpRequest);
        DatabaseEntity[] response = new DatabaseEntity[1];
        String result = clientMockObject.updatePersonalInfo(String.valueOf(json));
    }


    @Test(priority = 0)
    public void testCreateHelpTicketSuccess() {
        HashMap<String, String> testHelpTicket = new HashMap<>();
        testHelpTicket.put("token", "sometoken");
        testHelpTicket.put("tableName", "tickets");
        testHelpTicket.put("employee_id", "2");
        testHelpTicket.put("category", "1");
        testHelpTicket.put("ticket_comments", "Buddy has a flat tire");
        JSONObject json = new JSONObject(testHelpTicket);
        String result = techTestObject.createTicket(String.valueOf(json));
        Assert.assertTrue(result.matches("(.*)Buddy has a flat tire(.*)"));
    }

    @Test(priority = 1, expectedExceptions = MalformedObjectException.class, expectedExceptionsMessageRegExp = "Please enter less than 250 characters in the comments box")
    public void testCreateHelpTicketCommentTooLong(){
        HashMap<String, String> testHelpTicket = new HashMap<>();
        testHelpTicket.put("token", "sometoken");
        testHelpTicket.put("tableName", "tickets");
        testHelpTicket.put("employee_id", "2");
        testHelpTicket.put("category", "1");
        testHelpTicket.put("ticket_comments", "Buddy has a flat tire. Buddy has a flat tire. Buddy has a flat tire. " +
                "Buddy has a flat tire. Buddy has a flat tire. Buddy has a flat tire. Buddy has a flat tire. Buddy " +
                "has a flat tire. Buddy has a flat tire. Buddy has a flat tire. Buddy has a flat tire. Buddy has a " +
                "flat tire. Buddy has a flat tire. Buddy has a flat tire. Buddy has a flat tire. Buddy has a flat " +
                "tire. Buddy has a flat tire. Buddy has a flat tire. Buddy has a flat tire. Buddy has a flat tire. " +
                "Buddy has a flat tire. Buddy has a flat tire. Buddy has a flat tire. Buddy has a flat tire. Buddy " +
                "has a flat tire. Buddy has a flat tire. Buddy has a flat tire. ");
        JSONObject json = new JSONObject(testHelpTicket);
        String result = techTestObject.createTicket(String.valueOf(json));
    }

    @Test(priority = 1, expectedExceptions = MalformedObjectException.class, expectedExceptionsMessageRegExp = "The category you have entered does not exist")
    public void testCreateHelpTicketIncorrectCategory() {
        HashMap<String, String> testHelpTicket = new HashMap<>();
        testHelpTicket.put("token", "sometoken");
        testHelpTicket.put("tableName", "tickets");
        testHelpTicket.put("employee_id", "2");
        testHelpTicket.put("category", "5");
        testHelpTicket.put("ticket_comments", "Buddy has a flat tire.");
        JSONObject json = new JSONObject(testHelpTicket);
        String result = techTestObject.createTicket(String.valueOf(json));
    }

    @Test(priority = 1, expectedExceptions = RecordNotFound.class, expectedExceptionsMessageRegExp = "Can not have more than one ticket open at a time")
    public void testCreateHelpTicketOneAlreadyExists() {
        HashMap<String, String> testHelpTicket = new HashMap<>();
        testHelpTicket.put("token", "sometoken");
        testHelpTicket.put("tableName", "tickets");
        testHelpTicket.put("employee_id", "2");
        testHelpTicket.put("category", "1");
        testHelpTicket.put("ticket_comments", "Buddy has a flat tire.");
        JSONObject json = new JSONObject(testHelpTicket);
        String result = techTestObject.createTicket(String.valueOf(json));
    }

    @Test(priority = 1)
    public void viewHelpTicketSuccess() {
        HashMap<String, String> testHelpRequest = new HashMap<>();
        testHelpRequest.put("token", "sometoken");
        testHelpRequest.put("tableName", "tickets");
        testHelpRequest.put("employee_id", "2");
        JSONObject json = new JSONObject(testHelpRequest);
        String result = clientTestObject.viewHelpRequest(String.valueOf(json));
        Assert.assertTrue(result.matches("(.*)tickets_id(.*)"));
    }

    @Test(priority = 1, expectedExceptions = RecordNotFound.class, expectedExceptionsMessageRegExp = "You have no open tickets.")
    public void viewHelpTicketNoOpenTicket() {
        HashMap<String, String> testHelpRequest = new HashMap<>();
        testHelpRequest.put("token", "sometoken");
        testHelpRequest.put("tableName", "tickets");
        testHelpRequest.put("employee_id", "5");
        DatabaseEntity noRecord = new DatabaseEntity(testHelpRequest);
        JSONObject json = new JSONObject(testHelpRequest);
        DatabaseEntity[] response = new DatabaseEntity[0];
        Mockito.doReturn(response).when(daoTestObject).selectObjectsDb(noRecord.returnSqlForSelectByEmployeeId());
        String result = techTestObject.viewOpenTicket(String.valueOf(json));
    }


    @Test(priority = 1, expectedExceptions = RecordNotFound.class, expectedExceptionsMessageRegExp = "record not found")
    public void testCancelRecordNotFound() {
        HashMap<String, String> cancelRequest = new HashMap<>();
        cancelRequest.put("token", "sometoken");
        cancelRequest.put("tableName", "ticket_requests");
        cancelRequest.put("employee_id", "2");
        cancelRequest.put("ticket_requests_id", "9001");
        DatabaseEntity ticketRequest = new DatabaseEntity(cancelRequest);
        JSONObject json = new JSONObject(ticketRequest.newRowObject);
        DatabaseEntity[] response = new DatabaseEntity[1];
        Mockito.doReturn(response).when(daoTestObject).selectObjectsDb(ticketRequest.returnSqlForDeleteOne());
        String cancelResponse = clientMockObject.cancelHelpRequest(String.valueOf(json));
    }

    @Test
    public void testUpdateTicketSuccess(){
        HashMap<String, String> updateTicket = new HashMap<>();
        updateTicket.put("token", "sometoken");
        updateTicket.put("tableName", "tickets");
        updateTicket.put("tickets_id", "1");
        updateTicket.put("employee_id", "2");
        updateTicket.put("ticket_comments", "I have two flat tires");
        updateTicket.put("category", "1");
        JSONObject json = new JSONObject(updateTicket);
        String result = techTestObject.updateTicket(String.valueOf(json));
        Assert.assertTrue(result.matches("(.*)I have two flat tires(.*)"));
    }
    @Test(expectedExceptions = MalformedObjectException.class, expectedExceptionsMessageRegExp = "Please enter less than 250 characters in description")
    public void testUpdateTicketNegativeComment(){
        HashMap<String, String> updateTicket = new HashMap<>();
        updateTicket.put("token", "sometoken");
        updateTicket.put("tableName", "tickets");
        updateTicket.put("tickets_id", "1");
        updateTicket.put("employee_id", "2");
        updateTicket.put("ticket_comments", "qqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqq");
        updateTicket.put("category", "1");
        JSONObject json = new JSONObject(updateTicket);
        DatabaseEntity[] response = new DatabaseEntity[1];
        String result = techTestObject.updateTicket(String.valueOf(json));
    }
    @Test(expectedExceptions = RecordNotFound.class, expectedExceptionsMessageRegExp = "No request with id 1 was found.")
    public void testUpdateTicketNoTicket(){
        HashMap<String, String> updateTicket = new HashMap<>();
        updateTicket.put("token", "sometoken");
        updateTicket.put("tableName", "tickets");
        updateTicket.put("tickets_id", "1");
        updateTicket.put("employee_id", "2");
        updateTicket.put("ticket_comments", "I have two flat tires");
        updateTicket.put("category", "1");
        DatabaseEntity noRecord = new DatabaseEntity(updateTicket);
        JSONObject json = new JSONObject(updateTicket);
        DatabaseEntity[] response = new DatabaseEntity[0];
        Mockito.doReturn(response).when(daoTestObject).selectObjectsDb(noRecord.returnSqlForSelectByEmployeeId());
        String result = techMockObject.updateTicket(String.valueOf(json));
    }
    @Test(expectedExceptions = MalformedObjectException.class, expectedExceptionsMessageRegExp = "Please choose new category")
    public void testUpdateTicketNoCategory() {
        HashMap<String, String> updateTicket = new HashMap<>();
        updateTicket.put("token", "sometoken");
        updateTicket.put("tableName", "tickets");
        updateTicket.put("tickets_id", "1");
        updateTicket.put("employee_id", "2");
        updateTicket.put("ticket_comments", "I have two flat tires");
        JSONObject json = new JSONObject(updateTicket);
        DatabaseEntity[] response = new DatabaseEntity[1];
        String result = techTestObject.updateTicket(String.valueOf(json));
    }
}





