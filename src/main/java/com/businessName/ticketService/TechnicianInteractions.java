package com.businessName.ticketService;

import com.businessName.dataEntity.DatabaseEntity;
import com.businessName.ticketDao.DataAccessInterface;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.util.HashMap;
import java.util.Objects;

public class TechnicianInteractions implements EmployeeInteractions {
    public DataAccessInterface daoObject;

    public TechnicianInteractions(DataAccessInterface daoObject) {
        this.daoObject = daoObject;
    }

    @Override
    public String doLogin(String jsonFromApi) {
        HashMap<String, String> loginMap = new Gson().fromJson(
                String.valueOf(jsonFromApi), new TypeToken<HashMap<String, String>>() {}.getType()
        );
        DatabaseEntity loginTech = new DatabaseEntity(loginMap);
        loginTech.sanitizeFromApi();
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

    public String createHelpTicket(String jsonFromApi) { return null; }

    public String viewHelpTicket(String jsonFromApi) { return null; }

    public String updateHelpTicket(String jsonFromApi) { return null; }

    public String cancelHelpTicket(String jsonFromApi) { return null; }

}
