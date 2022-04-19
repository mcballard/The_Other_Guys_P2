package com.businessName.ticketService;

import com.businessName.dataEntity.DatabaseEntity;
import com.businessName.ticketDao.DataAccessInterface;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.util.HashMap;
import java.util.Objects;

public class TechnicianInteractions extends EmployeeInteractions {

    public TechnicianInteractions(DataAccessInterface daoObject) {
        super(daoObject);
    }

    public String createHelpTicket(String jsonFromApi) { return null; }

    public String viewHelpTicket(String jsonFromApi) { return null; }

    public String updateHelpTicket(String jsonFromApi) { return null; }

    public String cancelHelpTicket(String jsonFromApi) { return null; }

}
