package com.businessName.ticketApi;

import com.businessName.CustomerExceptions.LoginFailedException;
import com.businessName.CustomerExceptions.MalformedObjectException;
import com.businessName.CustomerExceptions.RecordNotFound;
import com.businessName.ticketDao.DataAccessImp;
import com.businessName.ticketDao.DataAccessInterface;
import com.businessName.ticketService.ClientInteractions;
import com.businessName.ticketService.EmployeeInteractions;
import com.businessName.ticketService.TechnicianInteractions;
import io.javalin.http.Handler;

public class HelpTicketController {

    public DataAccessInterface daoObject = new DataAccessImp();
    public EmployeeInteractions eiObject = new EmployeeInteractions(daoObject);
    public ClientInteractions ciObject = new ClientInteractions(daoObject);
    public TechnicianInteractions tiObject = new TechnicianInteractions(daoObject);

    public HelpTicketController() {
    }

    public Handler employeeLogin = ctx -> {
        String body = ctx.body();
        try {
            String response = "{\"token\":\"" + eiObject.doLogin(body) + "\"}";
            ctx.result(response);
            ctx.status(201);
        } catch (LoginFailedException e) {
            ctx.result("{\"message\":\"" + e.getMessage() + "\"}");
            ctx.status(400);
        }


    };

    public Handler clientCreateHelpRequest = ctx -> {
        String body = ctx.body();
        try {
            String response = ciObject.createHelpRequest(body);
            ctx.result(response);
            ctx.status(201);
        } catch (MalformedObjectException | RecordNotFound e) {
            ctx.result("{\"message\":\"" + e.getMessage() + "\"}");
            ctx.status(400);
        }
    };

    public Handler viewRequestStatus = ctx -> {
        String body = ctx.body();
        try {
 //            int id = Integer.parseInt(ctx.pathParam("employee_id"));
//            ClientInteractions ciObject =
            String response = "";
            ctx.result(response);
            ctx.status(201);
        } catch (RecordNotFound e) {
            ctx.result("{\"message\":\""+e.getMessage()+"\"}");
            ctx.status(400);
        }

    };

    public Handler clientUpdateHelpRequest = ctx -> {
        String body = ctx.body();
        try {
            String response = "{\"Updated\":\"" + ciObject.updateHelpRequest(body) + "\"}";
            ctx.result(response);
            ctx.status(201);
        } catch (RecordNotFound e) {
            ctx.result("{\"message\":\"" + e.getMessage() + "\"}");
            ctx.status(400);
        }


    };

    public Handler clientCancelHelpRequest = ctx -> {
        String body = ctx.body();
        try {
            String response = ciObject.cancelHelpRequest(body);
            ctx.result(response);
            ctx.status(201);
        } catch (MalformedObjectException | RecordNotFound e) {
            ctx.result("{\"message\":\"" + e.getMessage() + "\"}");
            ctx.status(400);
        }


    };
}
