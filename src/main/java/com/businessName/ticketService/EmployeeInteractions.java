package com.businessName.ticketService;

import com.businessName.CustomerExceptions.LoginFailedException;
import com.businessName.CustomerExceptions.MalformedObjectException;
import com.businessName.CustomerExceptions.RecordNotFound;
import com.businessName.dataEntity.DatabaseEntity;
import com.businessName.ticketDao.DataAccessImp;
import com.businessName.ticketDao.DataAccessInterface;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.json.JSONObject;

import java.awt.*;
import java.util.HashMap;
import java.util.Objects;

public class EmployeeInteractions {

    public DataAccessImp daoObject;

    public EmployeeInteractions(DataAccessInterface daoObject) {
        this.daoObject = (DataAccessImp) daoObject;
    }

    public String doLogin(String jsonFromApi) {
        HashMap<String, String> loginMap = new Gson().fromJson(
                String.valueOf(jsonFromApi),
                new TypeToken<HashMap<String, String>>() {
                }.getType());
        DatabaseEntity loginTech = new DatabaseEntity(loginMap);
        loginTech.sanitizeFromApi();
        DatabaseEntity[] employeeInfo = daoObject.selectObjectsDb(loginTech.selectDoLogin());
        if (employeeInfo.length < 1 || employeeInfo[0] == null) {
            throw new LoginFailedException("Incorrect Username!");
        } else if (Objects.equals(employeeInfo[0].newRowObject.get("pass"), loginMap.get("pass"))) {
            return employeeInfo[0].newRowObject.get("username") +
                    "_" + employeeInfo[0].newRowObject.get("type_id") +
                    "_" + employeeInfo[0].newRowObject.get("employees_id");
        } else {
            throw new LoginFailedException("Incorrect Password!");
        }
    }

    public String updatePersonalInfo(String jsonFromApi) {
        HashMap<String, String> updateMap = new Gson().fromJson(
                String.valueOf(jsonFromApi),
                new TypeToken<HashMap<String, String>>() {
                }.getType());
        DatabaseEntity updatePersonalInfo = new DatabaseEntity(updateMap);
        updatePersonalInfo.sanitizeFromApi();
        System.out.println(updateMap);
        if (updatePersonalInfo.newRowObject.get("first_name").length() <= 12) {
            if (updatePersonalInfo.newRowObject.get("last_name").length() <= 12) {
                HashMap<String, String> databaseResponse = daoObject.updateObjectDb(updatePersonalInfo.returnSqlForUpdateOne()).newRowObject;
                JSONObject updateRequestJson = new JSONObject(databaseResponse);
                return String.valueOf(updateRequestJson);
            } else {
                throw new MalformedObjectException("Please enter less than 12 characters in the description box");
            }
        } else {
            throw new MalformedObjectException("Please enter less than 12 characters in the description box");
        }
    }
}
