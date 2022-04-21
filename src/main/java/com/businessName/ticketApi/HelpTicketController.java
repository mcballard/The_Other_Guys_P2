package com.businessName.ticketApi;

import com.businessName.CustomerExceptions.LoginFailedException;
import com.businessName.CustomerExceptions.MalformedObjectException;
import com.businessName.CustomerExceptions.RecordNotFound;
import com.businessName.ticketDao.DataAccessImp;
import com.businessName.ticketService.ClientInteractions;
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
            String response = "{\"token\":\"" + tiObject.doLogin(body) + "\"}";
            ctx.result(response);
            ctx.status(201);
        } catch (LoginFailedException e) {
            ctx.result("{\"message\":\"" + e.getMessage() + "\"}");
            ctx.status(400);
        }


    };

    public Handler clientCreateHelpRequest = ctx -> {
        DataAccessImp daoObject = new DataAccessImp();
        ClientInteractions ciObject = new ClientInteractions(daoObject);
        Gson gson = new Gson();
        String body = ctx.body();
        // use gson to convert the json into an object that we need
        // this code here simulates sending the new object into your service/da layer
        try {
            String response = "";
            ctx.result(response);
            ctx.status(201);
        } catch (MalformedObjectException | RecordNotFound e) {
            ctx.result("{\"message\":\"" + e.getMessage() + "\"}");
            ctx.status(400);
        }
    };

    public Handler viewRequestStatus = ctx -> {

        DataAccessImp daoObject = new DataAccessImp();
        ClientInteractions ciObject = new ClientInteractions(daoObject);
        Gson gson = new Gson();
        String body = ctx.body();
//         use gson to convert the json into an object that we need
//         this code here simulates sending the new object into your service/da layer
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
        DataAccessImp daoObject = new DataAccessImp();
        ClientInteractions ciObject = new ClientInteractions(daoObject);
        Gson gson = new Gson();
        String body = ctx.body();
        // use gson to convert the json into an object that we need
        // this code here simulates sending the new object into your service/da layer
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
        DataAccessImp daoObject = new DataAccessImp();
        ClientInteractions ciObject = new ClientInteractions(daoObject);
        Gson gson = new Gson();
        String body = ctx.body();
        // use gson to convert the json into an object that we need
        // this code here simulates sending the new object into your service/da layer
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