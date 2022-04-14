import com.businessName.dataEntity.DatabaseEntity;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.List;
import org.testng.Assert;
import org.testng.annotations.Test;


public class EntityTests {

    @Test
    public void returnInsertQuerySuccess() {
        HashMap<String,String> test = new HashMap<>();
        test.put("tableName","ticket_requests");
        test.put("description", "this is something");
        test.put("employee_id", "1");
        test.put("status_id","0");
        DatabaseEntity testDbEntity = new DatabaseEntity(test);
        Assert.assertEquals(testDbEntity.returnSqlForInsertOne(),"insert into p2_sandbox.ticket_requests " +
                "('status_id','employee_id','description','ticket_requests_id') " +
                "values ('0','1','this is something',default) returning *;");
    }
/*
    @Test
    public void selectEmployeeByIdSuccess() {
        Employee employee = employeeDAO.selectById(1);
        Assert.assertEquals(employee.getFirstName(),"james");
    }

    @Test
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
                "where employees_id = 2;");

//        Employee employee = new Employee(3, "someone", "else");
//        Employee updated = employeeDAO.updateEmployeeById(employee);
//        Assert.assertEquals(updated.getFirstName(),"someone");
    }
/*
    @Test
    public void deleteByIdSuccess() {
        Assert.assertTrue(employeeDAO.deleteEmployeeById(3));
    }

 */
}

