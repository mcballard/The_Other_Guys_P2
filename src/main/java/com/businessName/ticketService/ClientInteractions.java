package com.businessName.ticketService;

import com.businessName.MalformedObjectException.MalformedObjectException;
import com.businessName.dataEntity.DatabaseEntity;
import com.businessName.ticketDao.DataAccessImp;
import com.businessName.ticketDao.DataAccessInterface;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Objects;

public class ClientInteractions extends EmployeeInteractions{


    public ClientInteractions(DataAccessInterface daoObject) {
        super(daoObject);
    }

    public String createHelpRequest(String jsonFromApi) { return null; }

    public String viewHelpRequest(String jsonFromApi) { return null; }


























    public String updateHelpRequest(String jsonFromApi) {
        HashMap<String, String> updateMap = new Gson().fromJson(
                String.valueOf(jsonFromApi),
                new TypeToken<HashMap<String, String>>() {}.getType());
        DatabaseEntity updateRequest = new DatabaseEntity(updateMap);
        updateRequest.sanitizeFromApi();
        if(updateRequest.newRowObject.containsKey("employee_id")) {
            HashMap<String, String> databaseResponse = daoObject.updateObjectDb(updateRequest.returnSqlForUpdateOne()).newRowObject;
            JSONObject updateRequestJson = new JSONObject(databaseResponse);
            return String.valueOf(updateRequestJson);
        }
        else {
            throw new RecordNotFound("no active request");
        }
    }

    public String cancelHelpRequest(String jsonFromApi) { return null; }
}
