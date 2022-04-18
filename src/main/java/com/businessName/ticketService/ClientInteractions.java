package com.businessName.ticketService;

import com.businessName.ticketDao.DataAccessImp;

public class ClientInteractions implements EmployeeInteractions{
    DataAccessImp daoObject = new DataAccessImp();

    @Override
    public String doLogin(String jsonFromApi) {
        return null;
    }

    @Override
    public String updatePersonalInfo(String jsonFromApi) {
        return null;
    }
}
