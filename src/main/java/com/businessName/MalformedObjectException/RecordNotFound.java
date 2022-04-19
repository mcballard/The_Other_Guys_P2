package com.businessName.MalformedObjectException;

public class RecordNotFound extends RuntimeException{

    public RecordNotFound(String message){
        super(message);
    }
}
