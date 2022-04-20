package com.businessName.ticketApi;

import io.javalin.Javalin;

public class HelpTicketApi {
    /*
        javalin will be used to handle our http requests
     */

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
