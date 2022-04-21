package com.businessName.ticketApi;

import io.javalin.Javalin;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class HelpTicketApi {
    /*
        javalin will be used to handle our http requests
     */

    public static Logger logger = LogManager.getLogger(HelpTicketApi.class);

    public static void main(String[] args) {
        Javalin app = Javalin.create(config -> {
            config.enableCorsForAllOrigins();
            config.enableDevLogging();
        });

        HelpTicketController controller = new HelpTicketController();

        app.post("/login", controller.employeeLogin);

        app.start();

    }
}
