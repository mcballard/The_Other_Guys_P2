package com.businessName.ticketApi;

import io.javalin.http.Handler;

public class JavalinExampleController {


    public JavalinExampleController() {
    }

    public Handler helloWorld = ctx -> {
        ctx.result("hello world");
        ctx.status(200);
    };


}
