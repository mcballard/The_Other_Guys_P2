import com.businessName.MalformedObjectException.MalformedObjectException;
import com.businessName.dataEntity.DatabaseEntity;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.util.HashMap;

public class EntityTests {

    @Test
    public void sanitizeFromApiTestNoTableName() {
        HashMap<String,String> test = new HashMap<>();
        test.put("description", "this is something");
        test.put("employee_id", "1");
        test.put("status_id","0");
        try {
            DatabaseEntity testDbEntity = new DatabaseEntity(test);
            testDbEntity.sanitizeFromApi();
            Assert.fail();
        } catch(MalformedObjectException e) {
            Assert.assertEquals(e.getMessage(),"Object does not contain tableName key!");
        }
    }

    @Test
    public void sanitizeFromApiTestIdValuesNotConvertible() {
        HashMap<String,String> test = new HashMap<>();
        test.put("tableName","ticket_requests");
        test.put("description", "this is something");
        test.put("employee_id", "1a");
        try {
            DatabaseEntity testDbEntity = new DatabaseEntity(test);
            testDbEntity.sanitizeFromApi();
            Assert.fail();
        } catch(MalformedObjectException e) {
            Assert.assertEquals(e.getMessage(),"Id cannot be converted to integer!");
        }
    }

    @Test
    public void sanitizeFromApiTestIdValuesNotConvertibleIsEmpty() {
        HashMap<String,String> test = new HashMap<>();
        test.put("tableName","ticket_requests");
        test.put("description", "this is something");
        test.put("employee_id", "");
        try {
            DatabaseEntity testDbEntity = new DatabaseEntity(test);
            testDbEntity.sanitizeFromApi();
            Assert.fail();
        } catch(MalformedObjectException e) {
            Assert.assertEquals(e.getMessage(),"Id cannot be converted to integer!");
        }
    }

    @Test
    public void sanitizeFromApiTestIdValuesNotConvertibleNoUnderscore() {
        HashMap<String,String> test = new HashMap<>();
        test.put("tableName","ticket_requests");
        test.put("description", "this is something");
        test.put("employeeId", "1a");
        try {
            DatabaseEntity testDbEntity = new DatabaseEntity(test);
            testDbEntity.sanitizeFromApi();
            Assert.fail();
        } catch(MalformedObjectException e) {
            Assert.assertEquals(e.getMessage(),"Id cannot be converted to integer!");
        }
    }

    @Test
    public void sanitizeFromApiTestDescriptionEmpty() {
        HashMap<String,String> test = new HashMap<>();
        test.put("tableName","ticket_requests");
        test.put("description", "");
        test.put("employee_id", "1");
        try {
            DatabaseEntity testDbEntity = new DatabaseEntity(test);
            testDbEntity.sanitizeFromApi();
            Assert.fail();
        } catch(MalformedObjectException e) {
            Assert.assertEquals(e.getMessage(),"Description must have at least 1 character!");
        }
    }

    @Test
    public void sanitizeFromApiTestDescriptionNull() {
        HashMap<String,String> test = new HashMap<>();
        test.put("tableName","ticket_requests");
        test.put("description", null);
        test.put("employee_id", "1");
        try {
            DatabaseEntity testDbEntity = new DatabaseEntity(test);
            testDbEntity.sanitizeFromApi();
            Assert.fail();
        } catch(MalformedObjectException e) {
            Assert.assertEquals(e.getMessage(),"Description must have at least 1 character!");
        }
    }

    @Test
    public void returnInsertQuerySuccess() {
        HashMap<String,String> test = new HashMap<>();
        test.put("tableName","ticket_requests");
        test.put("description", "this is something");
        test.put("employee_id", "1");
        test.put("status_id","0");
        DatabaseEntity testDbEntity = new DatabaseEntity(test);
        Assert.assertEquals(testDbEntity.returnSqlForInsertOne(),"insert into p2_sandbox.ticket_requests " +
                "(status_id,employee_id,description,ticket_requests_id) " +
                "values ('0','1','this is something',default) returning *;");
    }

    @Test
    public void returnSqlForSelectOne() {
        HashMap<String,String> test = new HashMap<>();
        test.put("tableName","ticket_requests");
        test.put("ticket_requests_id", "1");
        DatabaseEntity testDbEntity = new DatabaseEntity(test);
        Assert.assertEquals(testDbEntity.returnSqlForSelectOne(),"select * from p2_sandbox.ticket_requests where ticket_requests_id=1;");

    }

   /* @Test
    public void selectAllEmployeesSuccess() {
        List<Employee> employees = employeeDAO.selectAllEmployees();
        Assert.assertTrue(employees.size() >= 1);
    }
*/
    @Test
    public void updateEmployeeSuccess() {
        HashMap<String,String> test = new HashMap<>();
        test.put("tableName","employees");
        test.put("employees_id", "2");
        test.put("first_name", "Bobby");
        test.put("last_name", "Hill");
        DatabaseEntity testDbEntity = new DatabaseEntity(test);
        Assert.assertEquals(testDbEntity.returnSqlForUpdateOne(),"update p2_sandbox.employees " +
                "set last_name = 'Hill',first_name = 'Bobby' " +
                "where employees_id = 2;returning */");

    }

    @Test
    public void returnDeleteRequest() {
        HashMap<String , String> test = new HashMap<>();
        test.put("tableName", "ticket_requests");
        test.put("requestID", "1");
        DatabaseEntity testDbEntity = new DatabaseEntity(test);
        Assert.assertEquals(testDbEntity.returnSqlForDeleteOne(),"delete from p2_sandbox.ticket_requests where request_id=1;");
    }
}

