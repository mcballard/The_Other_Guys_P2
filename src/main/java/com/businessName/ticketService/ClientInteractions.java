package com.businessName.ticketService;

import com.businessName.MalformedObjectException.MalformedObjectException;
import com.businessName.MalformedObjectException.RecordNotFound;
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

    public String createHelpRequest(String jsonFromApi) {
        HashMap<String, String> helpRequestMap = new Gson().fromJson(
                String.valueOf(jsonFromApi),
                new TypeToken<HashMap<String, String>>() {}.getType());
        DatabaseEntity helpRequestClient = new DatabaseEntity(helpRequestMap);
        helpRequestClient.sanitizeFromApi();
        if(helpRequestClient.newRowObject.containsKey("description")){
            if (helpRequestClient.newRowObject.get("description").length() > 250){
                throw new MalformedObjectException("Please enter less than 250 characters in the description box");
            } else if (daoObject.selectObjectsDb(helpRequestClient.returnSqlForSelectByEmployeeId())[0] == null){
                 HashMap<String, String> databaseResponse = daoObject.insertObjectDb(helpRequestClient.returnSqlForInsertOne()).newRowObject;
                JSONObject newRequestJson = new JSONObject(databaseResponse);
                return String.valueOf(newRequestJson);
            } else {
                throw new RecordNotFound("Can not have more than one request open at a time");
            }
        } else {
            throw new RecordNotFound("Must include description");
        }
    }

    public String viewHelpRequest(String jsonFromApi) { return null; }

    public String updateHelpRequest(String jsonFromApi) { return null; }

    public String cancelHelpRequest(String jsonFromApi) { return null; }
}
