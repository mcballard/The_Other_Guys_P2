import com.businessName.CustomerExceptions.LoginFailedException;
import com.businessName.dataEntity.DatabaseEntity;
import com.businessName.ticketDao.DataAccessImp;
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

    @BeforeClass
    public void setup(){
        // define class to mock
        daoTestObject = Mockito.mock(DataAccessImp.class);
        tiTestObject = new TechnicianInteractions(daoTestObject);
    }

    @Test
    public void testDoLoginSuccess() {
        HashMap<String, String> testLogin = new HashMap<>();
        testLogin.put("tableName","employees");
        testLogin.put("username","mb1");
        testLogin.put("pass","pass");
        DatabaseEntity loginTech = new DatabaseEntity(testLogin);
        JSONObject json = new JSONObject(testLogin);
        HashMap<String, String> testResponse = new HashMap<>();
        testResponse.put("employees_id","2");
        testResponse.put("username","mb1");
        testResponse.put("type_id", "1");
        testResponse.put("pass","pass");
        DatabaseEntity[] response = new DatabaseEntity[1];
        DatabaseEntity testEntity = new DatabaseEntity(testResponse);
        response[0] = testEntity;
        // define mocked response for when a particular method is called
        Mockito.doReturn(response).when(daoTestObject).selectObjectsDb(loginTech.selectDoLogin());
        String authToken = tiTestObject.doLogin(String.valueOf(json));
        Assert.assertEquals(authToken,"mb1_1_2");
    }

    @Test(expectedExceptions = LoginFailedException.class, expectedExceptionsMessageRegExp = "Incorrect Username!")
    public void testDoLoginBadUsername() {
        HashMap<String, String> testLogin = new HashMap<>();
        testLogin.put("tableName","employees");
        testLogin.put("username","mb");
        testLogin.put("pass","pass");
        DatabaseEntity loginTech = new DatabaseEntity(testLogin);
        JSONObject json = new JSONObject(testLogin);
        DatabaseEntity[] response = new DatabaseEntity[1];
        Mockito.doReturn(response).when(daoTestObject).selectObjectsDb(loginTech.selectDoLogin());
        String authToken = tiTestObject.doLogin(String.valueOf(json));
    }

    @Test(expectedExceptions = LoginFailedException.class, expectedExceptionsMessageRegExp = "Incorrect Password!")
    public void testDoLoginBadPassword() {
        HashMap<String, String> testLogin = new HashMap<>();
        testLogin.put("tableName","employees");
        testLogin.put("username","mb1");
        testLogin.put("pass","pas");
        DatabaseEntity loginTech = new DatabaseEntity(testLogin);
        JSONObject json = new JSONObject(testLogin);
        HashMap<String, String> testResponse = new HashMap<>();
        testResponse.put("employees_id","2");
        testResponse.put("username","mb1");
        testResponse.put("type_id", "1");
        testResponse.put("pass","pass");
        DatabaseEntity[] response = new DatabaseEntity[1];
        DatabaseEntity testEntity = new DatabaseEntity(testResponse);
        response[0] = testEntity;
        Mockito.doReturn(response).when(daoTestObject).selectObjectsDb(loginTech.selectDoLogin());
        String authToken = tiTestObject.doLogin(String.valueOf(json));
    }

}
