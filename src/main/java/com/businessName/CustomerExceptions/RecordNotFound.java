package com.businessName.CustomerExceptions;

public class RecordNotFound extends RuntimeException{

    public RecordNotFound(String message){
        super(message);
    }
}
