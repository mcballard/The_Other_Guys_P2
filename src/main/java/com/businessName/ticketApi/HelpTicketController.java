package com.businessName.ticketApi;

import com.businessName.CustomerExceptions.LoginFailedException;
import com.businessName.CustomerExceptions.MalformedObjectException;
import com.businessName.ticketDao.DataAccessImp;
import com.businessName.ticketService.TechnicianInteractions;
import com.google.gson.Gson;
import io.javalin.http.Handler;

public class HelpTicketController {


    public HelpTicketController() {
    }

    public Handler employeeLogin = ctx -> {
        DataAccessImp daoObject = new DataAccessImp();
        TechnicianInteractions tiObject = new TechnicianInteractions(daoObject);
        Gson gson = new Gson();
        String body = ctx.body();
        // use gson to convert the json into an object that we need
        // this code here simulates sending the new object into your service/da layer
        try {
            String response = "{\"token\":\""+tiObject.doLogin(body)+"\"}";
            ctx.result(response);
            ctx.status(201);
        } catch (LoginFailedException e) {
            ctx.result("{\"message\":\""+e.getMessage()+"\"}");
            ctx.status(400);
        }
    };


}
