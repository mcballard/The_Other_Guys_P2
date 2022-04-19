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
        //set up out divider object using mockito mock method
        daoTestObject = Mockito.mock(DataAccessImp.class);
        tiTestObject = new TechnicianInteractions(daoTestObject);
    }

    @Test
    public void testDoLoginSuccess() {
        HashMap<String, String> testLogin = new HashMap<>();
        testLogin.put("tableName","employees");
        testLogin.put("username","mb1");
        testLogin.put("pass","pass");
        JSONObject json = new JSONObject(testLogin);
        HashMap<String, String> testResponse = new HashMap<>();
        testLogin.put("employees_id","2");
        testLogin.put("username","mb1");
        testLogin.put("type_id", "1");
        testLogin.put("pass","pass");
        DatabaseEntity[] response = new DatabaseEntity[1];
        response[0] = new DatabaseEntity(testResponse);
        Mockito.doReturn(response).when(daoTestObject).selectObjectsDb(tiTestObject.doLogin(String.valueOf(json)));
        String authToken = tiTestObject.doLogin(String.valueOf(json));
        Assert.assertEquals(authToken,"mb1_1_2");
    }

}
