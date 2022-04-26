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
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class HelpTicketController {

    public DataAccessInterface daoObject = new DataAccessImp();
    public EmployeeInteractions eiObject = new EmployeeInteractions(daoObject);
    public ClientInteractions ciObject = new ClientInteractions(daoObject);
    public TechnicianInteractions tiObject = new TechnicianInteractions(daoObject);

    public static Logger logger = LogManager.getLogger(HelpTicketController.class);

    public HelpTicketController() {
    }

    public Handler employeeLogin = ctx -> {
        String body = ctx.body();
        logger.info("login attempt with: "+body);
        try {
            String response = "{\"token\":\"" + eiObject.doLogin(body) + "\"}";
            ctx.result(response);
            ctx.status(201);
            logger.info("login success!");
        } catch (LoginFailedException e) {
            ctx.result("{\"message\":\"" + e.getMessage() + "\"}");
            ctx.status(400);
            logger.info("login fail!");
        }


    };

    public Handler clientCreateHelpRequest = ctx -> {
        String body = ctx.body();
        logger.info("client create request attempt with: "+body);
        try {
            String response = ciObject.createHelpRequest(body);
            ctx.result(response);
            ctx.status(201);
            logger.info("client create request success!");
        } catch (MalformedObjectException | RecordNotFound e) {
            ctx.result("{\"message\":\"" + e.getMessage() + "\"}");
            ctx.status(400);
            logger.info("client create request fail: "+e.getMessage());
        }
    };

    public Handler viewOpenRequestsTech = ctx -> {
        String body = ctx.body();
        logger.info("tech view request attempt with: "+body);
        try {
            String response = tiObject.viewOpenRequests(body);
            ctx.result(response);
            ctx.status(201);
            logger.info("tech view request success!");
        } catch (MalformedObjectException | RecordNotFound e) {
            ctx.result("{\"message\":\""+e.getMessage()+"\"}");
            ctx.status(400);
            logger.info("tech view request fail: "+e.getMessage());
        }
    };

    public Handler viewOpenResolveTech = ctx -> {
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
        logger.info("tech create ticket attempt with: "+body);
        try {
            String response = tiObject.createTicket(body);
            ctx.result(response);
            ctx.status(201);
            logger.info("tech create ticket success!");
        } catch (MalformedObjectException | RecordNotFound e) {
            ctx.result("{\"message\":\""+e.getMessage()+"\"}");
            ctx.status(400);
            logger.info("tech create ticket fail: "+e.getMessage());
        }
    };

    public Handler fillCreateFormTech = ctx -> {
        String body = ctx.body();
        logger.info("filling create ticket form: "+body);
        try {
            String response = tiObject.fillCategory(body);
            ctx.result(response);
            ctx.status(201);
            logger.info("fill form success!");
        } catch (MalformedObjectException | RecordNotFound e) {
            ctx.result("{\"message\":\""+e.getMessage()+"\"}");
            ctx.status(400);
            logger.info("fill form fail: "+e.getMessage());
        }
    };

    public Handler viewRequestStatus = ctx -> {
        String body = ctx.body();
        logger.info("client create request attempt with: "+body);
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
        logger.info("client update request attempt with: "+body);
        try {
            String response = ciObject.updateHelpRequest(body);
            ctx.result(response);
            ctx.status(201);
            logger.info("client update request success!");
        } catch (MalformedObjectException | RecordNotFound e) {
            ctx.result("{\"message\":\"" + e.getMessage() + "\"}");
            ctx.status(400);
            logger.info("client update request fail: "+e.getMessage());
        }


    };

    public Handler clientCancelHelpRequest = ctx -> {
        String body = ctx.body();
        logger.info("client cancel request attempt with: "+body);
        try {
            String response = ciObject.cancelHelpRequest(body);
            ctx.result("{\"message\":\"" + response + "\"}");
            ctx.status(201);
            logger.info("client cancel request success!");
        } catch (MalformedObjectException | RecordNotFound e) {
            ctx.result("{\"message\":\"" + e.getMessage() + "\"}");
            ctx.status(400);
            logger.info("client cancel request fail: "+e.getMessage());
        }

    };

        public Handler updateTicketTech = ctx -> {
            String body = ctx.body();
            logger.info("tech update ticket attempt with: "+body);
            try {
                String response = tiObject.updateTicket(body);
                ctx.result(response);
                ctx.status(201);
                logger.info("tech update ticket success!");
            } catch (RecordNotFound e) {
                ctx.result("{\"message\":\"" + e.getMessage() + "\"}");
                ctx.status(400);
                logger.info("tech update ticket fail: "+e.getMessage());
            }


        };

    public Handler viewOpenTicketTech = ctx -> {
        String body = ctx.body();
        logger.info("tech view ticket attempt with: "+body);
        try {
            String response = tiObject.viewOpenTicket(body);
            ctx.result(response);
            ctx.status(201);
            logger.info("tech view ticket success!");
        } catch (RecordNotFound e) {
            ctx.result("{\"message\":\""+e.getMessage()+"\"}");
            ctx.status(400);
            logger.info("tech view ticket fail: "+e.getMessage());
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
