import com.businessName.dataEntity.DatabaseEntity;
import com.businessName.ticketDao.DataAccessImp;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.util.HashMap;

public class DataAccessTests {
    @Test(priority = 0)
    public void insetObjectDbSuccess() {
        DataAccessImp daoObj = new DataAccessImp();
        HashMap<String,String> test = new HashMap<>();
        test.put("tableName","ticket_requests");
        test.put("description", "this is something");
        test.put("employee_id", "2");
        test.put("status_id","1");
        DatabaseEntity testDbEntity = new DatabaseEntity(test);
        DatabaseEntity newTicketRequest = daoObj.insertObjectDb(testDbEntity.returnSqlForInsertOne());
        Assert.assertNotEquals(newTicketRequest.newRowObject.get("ticket_request_id"),"0");

    }


    @Test(priority =1)
    public void selectObjectDBSuccess() {
        DataAccessImp daoOBJ = new DataAccessImp();
        HashMap<String, String> test = new HashMap<>();
        test.put("tableName","employees");
        test.put("employees_id", "2");
        DatabaseEntity testDbEntity = new DatabaseEntity(test);
        DatabaseEntity[] selectOneTest = daoOBJ.selectObjectsDb(testDbEntity.returnSqlForSelectOne());
        Assert.assertEquals(selectOneTest.length, 1);
    }

    @Test(priority = 1)
    public void selectObjectsDBSuccess() {
        DataAccessImp daoOBJ = new DataAccessImp();
        HashMap<String, String> test = new HashMap<>();
        test.put("tableName","employees");
        DatabaseEntity testDbEntity = new DatabaseEntity(test);
        DatabaseEntity[] selectOneTest = daoOBJ.selectObjectsDb(testDbEntity.returnSqlForSelectAll());
        Assert.assertEquals(selectOneTest.length, 4);
    }


    @Test(priority = 1)
    public void updateObjectDBSuccessFirstName() {
        DataAccessImp daoOBJ = new DataAccessImp();
        HashMap<String, String> test = new HashMap<>();
        test.put("tableName","employees");
        test.put("employees_id", "2");
        test.put("first_name", "Bobby");
        test.put("last_name", "Hill");
        DatabaseEntity testDbEntity = new DatabaseEntity(test);
        DatabaseEntity updateTicketRequest = daoOBJ.updateObjectDb(testDbEntity.returnSqlForUpdateOne());
        Assert.assertEquals(updateTicketRequest.newRowObject.get("first_name"), "Bobby");
    }

    @Test(priority = 1)
    public void updateObjectDBSuccessLastName() {
        DataAccessImp daoOBJ = new DataAccessImp();
        HashMap<String, String> test = new HashMap<>();
        test.put("tableName","employees");
        test.put("employees_id", "2");
        test.put("first_name", "Chief");
        test.put("last_name", "Keef");
        DatabaseEntity testDbEntity = new DatabaseEntity(test);
        DatabaseEntity updateTicketRequest = daoOBJ.updateObjectDb(testDbEntity.returnSqlForUpdateOne());
        Assert.assertEquals(updateTicketRequest.newRowObject.get("last_name"), "Keef");
    }

    @Test(priority = 2)
    public void deleteObjectDbSuccess() {
        DataAccessImp daoObj = new DataAccessImp();
        HashMap<String,String> test = new HashMap<>();
        test.put("tableName","employees");
        test.put("employees_id", "-3");
        DatabaseEntity testDbEntity = new DatabaseEntity(test);
        int result = daoObj.deleteObjectDb(testDbEntity.returnSqlForDeleteOne());
        Assert.assertNotEquals(result,"0");

    }
}
