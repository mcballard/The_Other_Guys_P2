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

    public Handler viewOpenRequestsTech = ctx -> {
        String body = ctx.body();
        try {
            String response = tiObject.viewOpenRequests(body);
            ctx.result(response);
            ctx.status(201);
        } catch (MalformedObjectException | RecordNotFound e) {
            ctx.result("{\"message\":\""+e.getMessage()+"\"}");
            ctx.status(400);
        }
    };

    public Handler createTicketTech = ctx -> {
        String body = ctx.body();
        try {
            String response = tiObject.createTicket(body);
            ctx.result(response);
            ctx.status(201);
        } catch (MalformedObjectException | RecordNotFound e) {
            ctx.result("{\"message\":\""+e.getMessage()+"\"}");
            ctx.status(400);
        }
    };

    public Handler fillCreateFormTech = ctx -> {
        String body = ctx.body();
        try {
            String response = tiObject.fillCategory(body);
            ctx.result(response);
            ctx.status(201);
        } catch (MalformedObjectException | RecordNotFound e) {
            ctx.result("{\"message\":\""+e.getMessage()+"\"}");
            ctx.status(400);
        }
    };

    public Handler viewRequestStatus = ctx -> {
        String body = ctx.body();
        try {
            String response = ciObject.viewHelpRequest(body);
            ctx.result(response);
            ctx.status(201);
        } catch (MalformedObjectException | RecordNotFound e) {
            ctx.result("{\"message\":\""+e.getMessage()+"\"}");
            ctx.status(400);
        }

    };

    public Handler clientUpdateHelpRequest = ctx -> {
        String body = ctx.body();
        try {
            String response = ciObject.updateHelpRequest(body);
            ctx.result(response);
            ctx.status(201);
        } catch (MalformedObjectException | RecordNotFound e) {
            ctx.result("{\"message\":\"" + e.getMessage() + "\"}");
            ctx.status(400);
        }


    };

    public Handler clientCancelHelpRequest = ctx -> {
        String body = ctx.body();
        try {
            String response = ciObject.cancelHelpRequest(body);
            ctx.result("{\"message\":\"" + response + "\"}");
            ctx.status(201);
        } catch (MalformedObjectException | RecordNotFound e) {
            ctx.result("{\"message\":\"" + e.getMessage() + "\"}");
            ctx.status(400);
        }

    };

        public Handler updateTicketTech = ctx -> {
            String body = ctx.body();
            try {
                String response = tiObject.updateTicket(body);
                ctx.result(response);
                ctx.status(201);
            } catch (RecordNotFound e) {
                ctx.result("{\"message\":\"" + e.getMessage() + "\"}");
                ctx.status(400);
            }


        };

    public Handler viewOpenTicketTech = ctx -> {
        String body = ctx.body();
        try {
            String response = tiObject.viewOpenTicket(body);
            ctx.result(response);
            ctx.status(201);
        } catch (RecordNotFound e) {
            ctx.result("{\"message\":\""+e.getMessage()+"\"}");
            ctx.status(400);
        }

    };




    public Handler resolveTicketTech = ctx -> {
        String body = ctx.body();
        try {
            String response = tiObject.resolveTicket(body);
            ctx.result(response);
            ctx.status(201);
        } catch (MalformedObjectException | RecordNotFound e) {
            ctx.result("{\"message\":\"" + e.getMessage() + "\"}");
            ctx.status(400);
        }


    };

    public Handler updatePersonalInfo = ctx -> {
        String body = ctx.body();
        try {
            String response = eiObject.updatePersonalInfo(body);
            ctx.result(response);
            ctx.status(201);
        } catch (MalformedObjectException e) {
            ctx.result("{\"message\":\"" + e.getMessage() + "\"}");
            ctx.status(400);
        }


    };
}
