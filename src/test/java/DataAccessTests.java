import com.businessName.dataEntity.DatabaseEntity;
import com.businessName.ticketDao.DataAccessImp;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.HashMap;

public class DataAccessTests {
    @Test
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


    @Test
    public void updateObjectDBSuccessFirstName() {
        DataAccessImp daoOBJ = new DataAccessImp();
        HashMap<String, String> test = new HashMap<>();
        test.put("tableName","employees");
        test.put("employees_id", "2");
        test.put("first_name", "Bobby");
        test.put("last_name", "Hill");
        DatabaseEntity testDbEntity = new DatabaseEntity(test);
        DatabaseEntity updateTicketRequest = daoOBJ.updateObjectDb(testDbEntity.returnSqlForUpdateOne());
        Assert.assertNotEquals(updateTicketRequest.newRowObject.get("first_name"), "Bobby");
    }

    @Test
    public void updateObjectDBSuccessLastName() {
        DataAccessImp daoOBJ = new DataAccessImp();
        HashMap<String, String> test = new HashMap<>();
        test.put("tableName","employees");
        test.put("employees_id", "2");
        test.put("first_name", "Bobby");
        test.put("last_name", "Shmurda");
        DatabaseEntity testDbEntity = new DatabaseEntity(test);
        DatabaseEntity updateTicketRequest = daoOBJ.updateObjectDb(testDbEntity.returnSqlForUpdateOne());
        Assert.assertNotEquals(updateTicketRequest.newRowObject.get("last_name"), "shmurda");
    }
}
