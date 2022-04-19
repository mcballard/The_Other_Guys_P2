package com.businessName.ticketService;

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

    public String cancelHelpRequest(String jsonFromApi) { return null; }
}
