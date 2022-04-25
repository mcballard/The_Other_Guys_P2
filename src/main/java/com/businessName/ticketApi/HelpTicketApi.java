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
            logger.info("Javalin starting...");
            config.enableCorsForAllOrigins();
            config.enableDevLogging();
            logger.info("Javalin started...");
        });

        HelpTicketController controller = new HelpTicketController();

        app.post("/login", controller.employeeLogin);


        app.post("/client/requests", controller.clientCreateHelpRequest);

        app.put("/client/requests", controller.viewRequestStatus);

        app.post("/technician/", controller.viewOpenRequestsTech);

        app.patch("/technician/", controller.fillCreateFormTech);

        app.post("/technician/requests", controller.createTicketTech);

        app.patch("/client/requests", controller.clientUpdateHelpRequest);

        app.delete("/client/requests", controller.clientCancelHelpRequest);

        app.patch("/technician/requests", controller.updateTicketTech);

        app.put("/technician/requests", controller.viewOpenTicketTech);

        app.delete("/technician/requests", controller.resolveTicketTech);

        app.post("/", controller.updatePersonalInfo);

        app.start();

    }
}
