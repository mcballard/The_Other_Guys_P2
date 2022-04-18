package com.businessName.ticketApi;

import com.businessName.MalformedObjectException.MalformedObjectException;
import com.businessName.ticketService.TechnicianInteractions;
import com.google.gson.Gson;
import io.javalin.http.Handler;

public class JavalinExampleController {


    public JavalinExampleController() {
    }

    public Handler helloWorld = ctx -> {
        TechnicianInteractions tiObject = new TechnicianInteractions();
        Gson gson = new Gson();
        String body = ctx.body();
        // use gson to convert the json into an object that we need
        // this code here simulates sending the new object into your service/da layer
        try {
            String response = "{\"token\":\""+tiObject.doLogin(body)+"\"}";
            ctx.result(response);
            ctx.status(201);
        } catch (MalformedObjectException e) {
            ctx.result("{'message':'"+e+"'}");
            ctx.status(400);
        }
    };


}
