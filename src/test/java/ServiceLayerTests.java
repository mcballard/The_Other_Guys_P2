import com.businessName.CustomerExceptions.LoginFailedException;
import com.businessName.CustomerExceptions.MalformedObjectException;
import com.businessName.CustomerExceptions.RecordNotFound;
import com.businessName.dataEntity.DatabaseEntity;
import com.businessName.ticketDao.DataAccessImp;
import com.businessName.ticketService.ClientInteractions;
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

    @BeforeClass
    public void setup() {
        // define class to mock
        daoTestObject = Mockito.mock(DataAccessImp.class);
        DataAccessImp daoSuccessObject = new DataAccessImp();
        daoSuccessObject.deleteObjectDb("TRUNCATE TABLE p2_sandbox.ticket_requests RESTART IDENTITY CASCADE;");
        clientMockObject = new ClientInteractions(daoTestObject);
        clientTestObject = new ClientInteractions(daoSuccessObject);
    }


    @Test
    public void testDoLoginSuccess() {
        HashMap<String, String> testLogin = new HashMap<>();
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
        testHelpRequest.put("tableName", "ticket_requests");
        testHelpRequest.put("employee_id", "2");
        JSONObject json = new JSONObject(testHelpRequest);
        String result = clientTestObject.viewHelpRequest(String.valueOf(json));
        Assert.assertTrue(result.matches("(.*)ticket_requests_id(.*)"));
    }

    @Test(priority = 4, expectedExceptions = RecordNotFound.class, expectedExceptionsMessageRegExp = "You have no open help requests.")
    public void testViewHelpRequestNoRecord() {
        HashMap<String, String> testHelpRequest = new HashMap<>();
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
        testHelpRequest.put("tableName", "ticket_requests");
        testHelpRequest.put("employee_id", "2");
        testHelpRequest.put("ticket_requests_id", "1");
        JSONObject json = new JSONObject(testHelpRequest);
        String result = clientTestObject.cancelHelpRequest(String.valueOf(json));
        Assert.assertEquals(result, "record deleted success");
    }

    @Test(expectedExceptions = LoginFailedException.class, expectedExceptionsMessageRegExp = "Incorrect Username!")
    public void testDoLoginBadUsername() {
        HashMap<String, String> testLogin = new HashMap<>();
        testLogin.put("tableName", "employees");
        testLogin.put("username", "mb");
        testLogin.put("pass", "pass");
        DatabaseEntity loginTech = new DatabaseEntity(testLogin);
        JSONObject json = new JSONObject(testLogin);
        DatabaseEntity[] response = new DatabaseEntity[1];
        Mockito.doReturn(response).when(daoTestObject).selectObjectsDb(loginTech.selectDoLogin());
        String authToken = clientMockObject.doLogin(String.valueOf(json));
    }

    @Test(expectedExceptions = LoginFailedException.class, expectedExceptionsMessageRegExp = "Incorrect Password!")
    public void testDoLoginBadPassword() {
        HashMap<String, String> testLogin = new HashMap<>();
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
    @Test(expectedExceptions = MalformedObjectException.class, expectedExceptionsMessageRegExp = "Please enter less than 250 characters in the description box")
    public void testUpdateHelpRequestDescriptionLong() {
        HashMap<String, String> testHelpRequest = new HashMap<>();
        testHelpRequest.put("tableName", "ticket_requests");
        testHelpRequest.put("employee_id", "2");
        testHelpRequest.put("ticket_requests_id", "1");
        testHelpRequest.put("description", "qqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqq");
        JSONObject json = new JSONObject(testHelpRequest);
        DatabaseEntity[] response = new DatabaseEntity[1];
//        Mockito.doReturn(response).when(daoTestObject).updateObjectDb();
        String result = clientMockObject.updateHelpRequest(String.valueOf(json));
    }

    @Test(expectedExceptions = MalformedObjectException.class, expectedExceptionsMessageRegExp = "Please enter less than 250 characters in the description box")
    public void testCreateHelpRequestDescriptionTooLong() {
        HashMap<String, String> testHelpRequest = new HashMap<>();
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

    @Test(expectedExceptions = RecordNotFound.class, expectedExceptionsMessageRegExp = "record not found")
    public void testCancelRecordNotFound() {
        HashMap<String, String> cancelRequest = new HashMap<>();
        cancelRequest.put("tableName", "ticket_requests");
        cancelRequest.put("employee_id", "2");
        cancelRequest.put("ticket_requests_id", "9001");
        DatabaseEntity ticketRequest = new DatabaseEntity(cancelRequest);
        JSONObject json = new JSONObject(ticketRequest.newRowObject);
        DatabaseEntity[] response = new DatabaseEntity[1];
        Mockito.doReturn(response).when(daoTestObject).selectObjectsDb(ticketRequest.returnSqlForDeleteOne());
        String cancelResponse = clientMockObject.cancelHelpRequest(String.valueOf(json));
    }
}





