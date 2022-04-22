package com.businessName.ticketService;

import com.businessName.CustomerExceptions.MalformedObjectException;
import com.businessName.CustomerExceptions.RecordNotFound;
import com.businessName.dataEntity.DatabaseEntity;
import com.businessName.ticketDao.DataAccessInterface;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Objects;

public class TechnicianInteractions extends EmployeeInteractions {

    public TechnicianInteractions(DataAccessInterface daoObject) {
        super(daoObject);
    }

    public String createTicket(String jsonFromApi) {
        HashMap<String, String> helpRequestMap = new Gson().fromJson(
                String.valueOf(jsonFromApi),
                new TypeToken<HashMap<String, String>>() {
                }.getType());
        DatabaseEntity helpTicketTech = new DatabaseEntity(helpRequestMap);
        helpTicketTech.sanitizeFromApi();
        System.out.println(daoObject.selectObjectsDb(helpTicketTech.returnSqlForSelectByEmployeeId()).length);
        if (helpTicketTech.newRowObject.containsKey("ticket_comments")) {
            if (helpTicketTech.newRowObject.get("ticket_comments").length() > 250) {
                throw new MalformedObjectException("Please enter less than 250 characters in the comments box");
            }
            if (!Objects.equals(helpTicketTech.newRowObject.get("category"), "1") && !Objects.equals(helpTicketTech.newRowObject.get("category"), "2")) {
                throw new MalformedObjectException("The category you have entered does not exist");
            }
            if (daoObject.selectObjectsDb(helpTicketTech.returnSqlForSelectByEmployeeId()).length < 1) {
                HashMap<String, String> databaseResponse =
                        daoObject.insertObjectDb(helpTicketTech.returnSqlForInsertOne()).newRowObject;
                JSONObject newRequestJson = new JSONObject(databaseResponse);
                return String.valueOf(newRequestJson);
            } else {
                throw new RecordNotFound("Can not have more than one ticket open at a time");
            }
        } else {
            throw new RecordNotFound("Something went wrong");
        }
    }


    public String viewOpenRequests(String jsonFromApi) {
        //for viewing all open client help requests
        HashMap<String, String> viewMap = new Gson().fromJson(
                String.valueOf(jsonFromApi),
                new TypeToken<HashMap<String, String>>() {
                }.getType());
        DatabaseEntity viewRequest = new DatabaseEntity(viewMap);
        viewRequest.sanitizeFromApi();
        DatabaseEntity[] viewResponse = daoObject.selectObjectsDb(viewRequest.returnSqlForSelectAll());
        if (viewResponse.length < 1) {
            throw new RecordNotFound("You have no open help requests.");
        }
        JSONObject viewResponseJson = new JSONObject(viewResponse[0].newRowObject);
        return String.valueOf(viewResponseJson);
    }

    public String viewOpenTicket(String jsonFromApi) {
        //for viewing only open ticket for specified employee_id

        HashMap<String, String> viewMap = new Gson().fromJson(
                String.valueOf(jsonFromApi),
                new TypeToken<HashMap<String, String>>() {
                }.getType());
        DatabaseEntity viewTicket = new DatabaseEntity(viewMap);
        viewTicket.sanitizeFromApi();
        System.out.println(viewTicket.returnSqlForSelectByEmployeeId());
        DatabaseEntity[] viewResponse = daoObject.selectObjectsDb(viewTicket.returnSqlForSelectByEmployeeId());
        if (viewResponse.length < 1) {
            throw new RecordNotFound("You have no open tickets.");
        }
        JSONObject viewResponseJson = new JSONObject(viewResponse[0].newRowObject);
        return String.valueOf(viewResponseJson);
    }


    public String updateTicket(String jsonFromApi) {
        HashMap<String, String> updateMap = new Gson().fromJson(
                String.valueOf(jsonFromApi),
                new TypeToken<HashMap<String, String>>() {
                }.getType());
        DatabaseEntity updateOpenTicket = new DatabaseEntity(updateMap);
        updateOpenTicket.sanitizeFromApi();
        if (updateOpenTicket.newRowObject.containsKey("tickets_id")) {
            if (updateOpenTicket.newRowObject.get("ticket_comments").length() <= 250) {
                if (updateOpenTicket.newRowObject.containsKey("category")) {
                    if (daoObject.selectObjectsDb(updateOpenTicket.returnSqlForSelectOne()).length < 1) {
                        throw new RecordNotFound("No request with id "
                                + updateOpenTicket.newRowObject.get("tickets_id") + " was found.");
                    }
                    HashMap<String, String> databaseResponse = daoObject.updateObjectDb(updateOpenTicket.returnSqlForUpdateOne()).newRowObject;
                    if (databaseResponse.isEmpty()) {
                        throw new RecordNotFound("Unable to locate record with id " + updateOpenTicket.newRowObject.get("tickets_id"));
                    }
                    JSONObject updateRequestJson = new JSONObject(databaseResponse);
                    return String.valueOf(updateRequestJson);
                } else {
                    throw new MalformedObjectException("Please choose new category");
                }
            } else {
                throw new MalformedObjectException("Please enter less than 250 characters in description");
            }
        } else {
            throw new MalformedObjectException("Key not found for ticket requests");
        }
    }


    public String resolveTicket(String jsonFromApi) {
        HashMap<String, String> resolveMap = new Gson().fromJson(
                String.valueOf(jsonFromApi),
                new TypeToken<HashMap<String, String>>() {
                }.getType());
        DatabaseEntity resolveTicket = new DatabaseEntity(resolveMap);
        resolveTicket.sanitizeFromApi();
        if (resolveTicket.newRowObject.containsKey("tickets_id")) {
            if (resolveTicket.newRowObject.get("resolution").length() > 250) {
                throw new MalformedObjectException("Please enter less than 250 characters in the resolution");
            } else { System.out.println(resolveTicket.returnSqlForResolution());
                HashMap<String, String> databaseResponse1 = daoObject.updateObjectDb(resolveTicket.returnSqlForResolution()).newRowObject;
                HashMap<String, String> databaseResponse2 = daoObject.updateObjectDb(resolveTicket.returnSqlForResolveTicket()).newRowObject;
                System.out.println((databaseResponse2));
                HashMap<String, String> databaseResponse3 = daoObject.updateObjectDb(resolveTicket.returnSqlForResolveHelpRequest(databaseResponse2.get("ticket_requests_id"))).newRowObject;
                System.out.println(databaseResponse1);
                System.out.println(databaseResponse2);
                System.out.println(databaseResponse3);
                return "{\"message\":\"request closed successfully\"}";
            }
        } else {
                throw new RecordNotFound("recordNotFound");
            }
        }
    }
//return String.valueOf(resolveRequestJson1 + "" + resolveRequestJson2 + "" + resolveRequestJson3);
//return String.valueOf(resolveRequestJson1 + " CHAR(13) " + resolveRequestJson2 + " CHAR(13) " + resolveRequestJson3);

