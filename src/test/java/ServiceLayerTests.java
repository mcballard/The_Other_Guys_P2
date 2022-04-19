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
    public static TechnicianInteractions tiTestObject;
    public static ClientInteractions clientTestObject;

    @BeforeClass
    public void setup(){
        //set up out divider object using mockito mock method
        daoTestObject = Mockito.mock(DataAccessImp.class);
        tiTestObject = new TechnicianInteractions(daoTestObject);
    }

//    @Test
//    public void testDoLoginSuccess() {
//        HashMap<String, String> testLogin = new HashMap<>();
//        testLogin.put("tableName","employees");
//        testLogin.put("username","mb1");
//        testLogin.put("pass","pass");
//        JSONObject json = new JSONObject(testLogin);
//        HashMap<String, String> testResponse = new HashMap<>();
//        testLogin.put("employees_id","2");
//        testLogin.put("username","mb1");
//        testLogin.put("type_id", "1");
//        testLogin.put("pass","pass");
//        DatabaseEntity[] response = new DatabaseEntity[1];
//        response[0] = new DatabaseEntity(testResponse);
//        Mockito.doReturn(response).when(daoTestObject).selectObjectsDb(tiTestObject.doLogin(String.valueOf(json)));
//        String authToken = tiTestObject.doLogin(String.valueOf(json));
//        Assert.assertEquals(authToken,"mb1_1_2");
//    }

    @Test
    public void testCreateHelpRequestSuccess() {
        HashMap<String, String> testHelpRequest = new HashMap<>();
        testHelpRequest.put("tableName","ticket_requests");
        testHelpRequest.put("employee_id","2");
        testHelpRequest.put("description","I have a flat tire");
        JSONObject json = new JSONObject(testHelpRequest);
        System.out.println(json);
//        DatabaseEntity helpRequestObject = new DatabaseEntity(testHelpRequest);
        String result = clientTestObject.createHelpRequest(String.valueOf(json));
        System.out.println(result);
//        Assert.assertNotEquals(result.newRowObject.get("ticket_requests_id"), "0");
    }

    @Test
    public void testUpdateHelpRequestSuccess(){
        HashMap<String, String> testHelpRequest = new HashMap<>();
        testHelpRequest.put("tableName","ticket_requests");
        testHelpRequest.put("ticket_requests_id", "1");
        testHelpRequest.put("description","I have two flat tires");
        JSONObject json = new JSONObject(testHelpRequest);
        String result = clientTestObject.updateHelpRequest(String.valueOf(json));
        Assert.assertTrue(result.matches("\\*I have two flat tires*"));
    }

}
