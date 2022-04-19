package com.businessName.ticketService;

import com.businessName.MalformedObjectException.MalformedObjectException;
import com.businessName.dataEntity.DatabaseEntity;
import com.businessName.ticketDao.DataAccessImp;
import com.businessName.ticketDao.DataAccessInterface;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.util.HashMap;
import java.util.Objects;

public class ClientInteractions extends EmployeeInteractions{


    public ClientInteractions(DataAccessInterface daoObject) {
        super(daoObject);
    }

    public String createHelpRequest(String jsonFromApi) { return null; }

    public String viewHelpRequest(String jsonFromApi) { return null; }

    public String updateHelpRequest(String jsonFromApi) { return null; }

    public String cancelHelpRequest(String jsonFromApi) {
        HashMap<String, String> loginMap = new Gson().fromJson(
                String.valueOf(jsonFromApi),
                new TypeToken<HashMap<String, String>>() {}.getType());
        DatabaseEntity cancelHelpRequest = new DatabaseEntity(loginMap);
        cancelHelpRequest.sanitizeFromApi();
        if(cancelHelpRequest.newRowObject.containsKey("ticket_request_id")) {
            int result = daoObject.deleteObjectDb(cancelHelpRequest.returnSqlForDeleteOne());
            if (result > 0){
                return "record deleted success";
            }else{
                return "recordNotFound";
            }
        }
        else{
            throw new MalformedObjectException("missing key");
        }
    }
}
