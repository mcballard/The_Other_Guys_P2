package com.businessName.ticketService;

import com.businessName.dataEntity.DatabaseEntity;
import com.businessName.ticketDao.DataAccessImp;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.util.HashMap;
import java.util.Objects;

public class TechnicianInteractions implements EmployeeInteractions {
    DataAccessImp daoObject = new DataAccessImp();

    @Override
    public String doLogin(String jsonFromApi) {
        HashMap<String, String> loginMap = new Gson().fromJson(
                String.valueOf(jsonFromApi), new TypeToken<HashMap<String, String>>() {}.getType()
        );
        DatabaseEntity loginTech = new DatabaseEntity(loginMap);
        DatabaseEntity[] employeeInfo = daoObject.selectObjectsDb(loginTech.selectDoLogin());
        if(employeeInfo.length<1) {
            return "Incorrect Username!";
        } else if(Objects.equals(employeeInfo[0].newRowObject.get("pass"), loginMap.get("pass"))) {
            return employeeInfo[0].newRowObject.get("username")+
                    "_"+employeeInfo[0].newRowObject.get("type_id")+
                    "_"+employeeInfo[0].newRowObject.get("employees_id");
        } else {
            System.out.println(employeeInfo[0].newRowObject.get("pass"));
            System.out.println(loginMap.get("pass"));
            return "Incorrect Password!";
        }

    }

    @Override
    public String updatePersonalInfo(String jsonFromApi) {
        return null;
    }
/*
    public static void main(String[] args) {
        HashMap<String, String> testLogin = new HashMap<>();
        testLogin.put("tableName","employees");
        testLogin.put("username","mb1");
        testLogin.put("pass","pass");
        JSONObject json = new JSONObject(testLogin);
        TechnicianInteractions tiTest = new TechnicianInteractions();
        String results = tiTest.doLogin(String.valueOf(json));

        System.out.println(results);

        HashMap<String, String> map = new HashMap<>();
// Convert a map having list of values.
        String[] value1 = new String[] { "value11", "value12", "value13" };
        String[] value2 = new String[] { "value21", "value22", "value23" };
        map.put("key1", "value1");
        map.put("key2", "value2");

        JSONObject json = new JSONObject(map);
        HashMap<String, String> retMap = new Gson().fromJson(
                String.valueOf(json), new TypeToken<HashMap<String, String>>() {}.getType()
        );
        System.out.println(retMap.get("key1"));
    }

 */


}
