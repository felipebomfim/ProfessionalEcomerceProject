package com.ecommerce.project.exceptions;

public class APIException  extends RuntimeException{
    private static final long serialVersionUID = 1l;

    public APIException(){
        super();
    }

    public APIException(String message){
        super(message);
    }
}
