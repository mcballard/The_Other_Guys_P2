package com.businessName.ticketApi;

import io.javalin.Javalin;

public class JavalinExample {
    /*
        javalin will be used to handle our http requests
     */

    public static void main(String[] args) {
        Javalin app = Javalin.create(config -> {
            config.enableCorsForAllOrigins();
            config.enableDevLogging();
        });

        JavalinExampleController controller = new JavalinExampleController();

        app.post("/login", controller.helloWorld);

        app.start();

    }
}
